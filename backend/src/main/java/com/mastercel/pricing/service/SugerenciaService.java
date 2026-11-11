package com.mastercel.pricing.service;

import com.mastercel.pricing.dto.SugerenciaModeloResponse;
import com.mastercel.pricing.repository.CatalogoDispositivosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SugerenciaService {

    private static final int MINIMO_LETRAS = 2;
    private static final int MAXIMO_RESULTADOS = 20;
    private final CatalogoDispositivosRepository catalogoDispositivosRepository;

    @Transactional(readOnly = true)
    public List<String> sugerirMarcas(String texto) {
        if(textoMuyCorto(texto)){
            return List.of();
        }
        return catalogoDispositivosRepository.sugerirMarcas(texto.trim(), PageRequest.of(0, MAXIMO_RESULTADOS));
    }

    @Transactional(readOnly = true)
    public List<SugerenciaModeloResponse> sugerirModelos(String texto, String marca){
        if(textoMuyCorto(texto)){
            return List.of();
        }
        String marcaFiltro = marca == null ? "" : marca.trim();

        return catalogoDispositivosRepository
                .sugerirModelos(texto.trim(), marcaFiltro, PageRequest.of(0, MAXIMO_RESULTADOS))
                .stream()
                .map(d -> new SugerenciaModeloResponse(d.getMarca(), d.getNombreComercial(), d.getCodigoModelo()))
                .toList();
    }

    private boolean textoMuyCorto(String texto) {
    return texto == null || texto.trim().length() < MINIMO_LETRAS;
    }
}
