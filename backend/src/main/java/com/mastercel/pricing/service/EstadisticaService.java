package com.mastercel.pricing.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mastercel.pricing.dto.EstadisticaResponse;
import com.mastercel.pricing.enums.TipoConsulta;
import com.mastercel.pricing.exception.BadRequestException;
import com.mastercel.pricing.repository.ConsultasRepository;


import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class EstadisticaService {
    private static final int ANIO_MINIMO = 2000;
    private static final int ANIO_MAXIMO = 2100;
    private static final int LIMITE_MAXIMO = 50;

    private final ConsultasRepository consultasRepository;

    @Transactional(readOnly = true)
    public List<EstadisticaResponse> masConsultados(TipoConsulta tipo, int anio,int mes, int limite) {
        if(mes < 1 || mes > 12) {
            throw new BadRequestException("El mes debe estar entre 1 y 12");
        } 
        if(anio < ANIO_MINIMO || anio > ANIO_MAXIMO) {
            throw new BadRequestException("El año debe estar entre " + ANIO_MINIMO + " y " + ANIO_MAXIMO);
        }
        if(limite < 1 || limite > LIMITE_MAXIMO) {  
            throw new BadRequestException("El límite debe estar entre 1 y " + LIMITE_MAXIMO);
        }
        return consultasRepository.masConsultados(tipo, anio, mes, PageRequest.of(0,limite));
    }
    
}
