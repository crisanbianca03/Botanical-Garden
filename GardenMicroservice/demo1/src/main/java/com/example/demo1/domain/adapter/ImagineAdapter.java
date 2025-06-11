package com.example.demo1.domain.adapter;

import com.example.demo1.domain.Imagine;
import com.example.demo1.domain.dto.ImagineDTO;
import org.springframework.stereotype.Component;

@Component
public class ImagineAdapter implements EntityAdaper<Imagine, ImagineDTO>{
    @Override
    public Imagine toEntity(ImagineDTO dto) {
        Imagine imagine = new Imagine();
        imagine.setDenumire(dto.getUrl());
        return imagine;
    }

    @Override
    public ImagineDTO toDto(Imagine imagine) {
        ImagineDTO dto = new ImagineDTO();
        dto.setId(imagine.getIdImagine());
        dto.setUrl(imagine.getDenumire());
        dto.setExemplarId(imagine.getExemplar() != null ? imagine.getExemplar().getIdExemplar() : null);
        return dto;
    }
}
