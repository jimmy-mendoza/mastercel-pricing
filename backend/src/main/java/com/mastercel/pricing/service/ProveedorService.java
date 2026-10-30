package com.mastercel.pricing.service;

import com.mastercel.pricing.dto.ProveedorRequest;
import com.mastercel.pricing.dto.ProveedorResponse;
import com.mastercel.pricing.entity.Proveedores;
import com.mastercel.pricing.exception.ConflictException;
import com.mastercel.pricing.exception.NotFoundException;
import com.mastercel.pricing.mapper.ProveedorMapper;
import com.mastercel.pricing.repository.ConsultasRepository;
import com.mastercel.pricing.repository.ProveedoresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProveedorService {

    private final ProveedoresRepository proveedoresRepository;
    private final ConsultasRepository consultasRepository;
    private final ProveedorMapper proveedorMapper;

    @Transactional
    public ProveedorResponse crear (ProveedorRequest request) {
        String nombre = request.nombre().trim();

        if(proveedoresRepository.contarPorNombre(nombre) > 0) {
            throw new ConflictException("Ya existe un proveedor con este nombre: " + nombre );
        }

        Proveedores guardado = proveedoresRepository.save(proveedorMapper.toEntity(request));
        return proveedorMapper.toResponse(guardado);
    }

    @Transactional(readOnly = true)
    public ProveedorResponse obtenerPorId(Long id) {
        return  proveedorMapper.toResponse(buscarPorId(id));
    }

    @Transactional
    public ProveedorResponse actualizar (ProveedorRequest request, Long id) {
        Proveedores proveedor = buscarPorId(id);
        String nombre = request.nombre().trim();

        if(proveedoresRepository.contarPorNombreExcluyendoId(nombre,id) > 0) {
            throw new ConflictException("Ya existe un proveedor con este nombre: " + nombre );
        }

        proveedor.setNombre(request.nombre());
        proveedor.setUbicacion(request.ubicacion());

        return   proveedorMapper.toResponse(proveedoresRepository.save(proveedor));
    }

    @Transactional
    public List<ProveedorResponse> lisar() {
        return proveedoresRepository.findAll().stream()
                .map(proveedorMapper::toResponse)
                .toList();
    }

    @Transactional
    public void eliminar (Long id) {

        Proveedores proveedor = buscarPorId(id);

        if(consultasRepository.existsByProveedorId(id)){
            throw new NotFoundException("No se puede eliminar el proveedor porque tiene consultas registradas");
        }

        proveedoresRepository.delete(proveedor);
    }

    private Proveedores buscarPorId(Long id) {
        return proveedoresRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proveedor no encontrado por id " + id));
    }
}
