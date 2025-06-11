package com.example.demo1.domain.adapter;

import com.example.demo1.domain.Planta;
import com.example.demo1.domain.dto.PlantaDTO;
import org.springframework.stereotype.Component;

@Component
public class PlantaAdapter implements EntityAdaper<Planta, PlantaDTO> {

    @Override
    public Planta toEntity(PlantaDTO dto) {
        Planta planta = new Planta();
        planta.setDenumire(dto.getDenumire());
        planta.setTip(dto.getTip());
        planta.setSpecie(dto.getSpecie());
        planta.setPlanteCarnivore(dto.getPlanteCarnivore());
        planta.setDescriere(dto.getDescriere());
        return planta;
    }

    @Override
    public PlantaDTO toDto(Planta planta) {
        PlantaDTO dto = new PlantaDTO();
        dto.setId(planta.getIdPlanta());
        dto.setDenumire(planta.getDenumire());
        dto.setTip(planta.getTip());
        dto.setSpecie(planta.getSpecie());
        dto.setPlanteCarnivore(planta.getPlanteCarnivore());
        dto.setDescriere(planta.getDescriere());
        return dto;
    }
}
