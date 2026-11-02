package com.mastercel.pricing.service;

import com.mastercel.pricing.dto.*;
import com.mastercel.pricing.entity.Consultas;
import com.mastercel.pricing.entity.Proveedores;
import com.mastercel.pricing.enums.TipoConsulta;
import com.mastercel.pricing.exception.BadRequestException;
import com.mastercel.pricing.exception.NotFoundException;
import com.mastercel.pricing.mapper.ConsultaMapper;
import com.mastercel.pricing.repository.ConsultasRepository;
import com.mastercel.pricing.repository.ProveedoresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.NotCompliantMBeanException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final CalculadoraPreciosService calculadoraPreciosService;
    private final ProveedoresRepository proveedoresRepository;
    private final ConsultasRepository consultasRepository;
    private final ConsultaMapper consultaMapper;

    public ConsultaCalculoResponse calcular(ConsultaRequest request) {

        PreciosCalculados precios = calculadoraPreciosService.calcular(request.tipo(), request.costo());

        String nombreRepuesto = request.nombreRepuesto() == null ? null : request.nombreRepuesto().trim();

        return new ConsultaCalculoResponse(
                request.tipo(),
                request.marca().trim(),
                request.modelo().trim(),
                nombreRepuesto,
                request.calidad(),
                request.costo(),
                precios
        );
    }

    // Guardar la consulta
    @Transactional
    public ConsultaResponse guardar(ConsultaRequest request) {

        validarNombreRepuesto(request); // validamos si es nombre del repuesto
        // Validamos si existe el proveedor con el Id
        Proveedores proveedor = proveedoresRepository.findById(request.proveedorId())
                .orElseThrow(() -> new NotFoundException(
                        "No se encontro el proveedor con el id: " + request.proveedorId()));
        // Calculammos los precios de la Request
        PreciosCalculados precios = calculadoraPreciosService.calcular(request.tipo(), request.costo());
        // guardamos la consulta con el con el proveedor y los precios ya calculados
        Consultas guardada = consultasRepository.save(
                consultaMapper.toEntity(request, proveedor, precios));

        return new ConsultaMapper().toResponse(guardada);
    }

    private void validarNombreRepuesto(ConsultaRequest request) {

        boolean esRepuesto = request.tipo() == TipoConsulta.REPUESTO;
        boolean sinNombre = request.nombreRepuesto() == null || request.nombreRepuesto().isBlank();

        // manejo de Nombre De Repuesto
        if(esRepuesto && sinNombre) {
            throw new BadRequestException("El nombre de repuesto es obligatorio");
        }

    }

    @Transactional(readOnly = true)
    public List<ConsultaResumenResponse> listar(TipoConsulta tipo, String modelo) {
        List<Consultas> consultas = (modelo == null || modelo.isBlank())
                ? consultasRepository.findByTipoOrderByFechaDesc(tipo)
                : consultasRepository.findByTipoAndModeloContainingIgnoreCaseOrderByFechaDesc(tipo, modelo);

        return consultas.stream()
                .map(consultaMapper::toResumen)
                .toList();
    }

    @Transactional(readOnly = true)
    public ConsultaResponse ObtenerPorId(Long id) {
        Consultas consulta = consultasRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Consulta no encontrada con id: "+id));
        return new ConsultaMapper().toResponse(consulta);
    }
}
