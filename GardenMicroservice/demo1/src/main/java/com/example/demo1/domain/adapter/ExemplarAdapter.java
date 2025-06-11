package com.example.demo1.domain.adapter;

import com.example.demo1.domain.Exemplar;
import com.example.demo1.domain.dto.ExemplarDTO;
import org.springframework.stereotype.Component;

@Component
public class ExemplarAdapter implements EntityAdaper<Exemplar, ExemplarDTO> {

    @Override
    public Exemplar toEntity(ExemplarDTO dto) {
        Exemplar exemplar = new Exemplar();
        exemplar.setZonaGradina(dto.getZonaGradina());
        exemplar.setDenumire(dto.getDenumire());
        exemplar.setDataPlantarii(dto.getDataPlantarii());
        exemplar.setCaracteristiciSpecifice(dto.getCaracteristiciSpecifice());
        return exemplar;
    }

    @Override
    public ExemplarDTO toDto(Exemplar exemplar) {
        ExemplarDTO dto = new ExemplarDTO();
        dto.setIdExemplar(exemplar.getIdExemplar());
        dto.setZonaGradina(exemplar.getZonaGradina());
        dto.setDenumire(exemplar.getDenumire());
        dto.setDataPlantarii(exemplar.getDataPlantarii());
        dto.setCaracteristiciSpecifice(exemplar.getCaracteristiciSpecifice());
        dto.setIdPlanta(exemplar.getPlanta() != null ? exemplar.getPlanta().getIdPlanta() : null);

        return dto;
    }
}
