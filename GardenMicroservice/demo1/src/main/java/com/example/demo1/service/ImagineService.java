package com.example.demo1.service;

import com.example.demo1.domain.Exemplar;
import com.example.demo1.domain.Imagine;
import com.example.demo1.domain.dto.ImagineDTO;
import com.example.demo1.repository.ExemplarRepository;
import com.example.demo1.repository.ImagineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImagineService {
    @Autowired
    private ImagineRepository imagineRepository;

    @Autowired
    private ExemplarRepository exemplarRepository;

    public ImagineDTO adaugaImagine(ImagineDTO dto) {
        Exemplar exemplar = exemplarRepository.findById(dto.getExemplarId())
                .orElseThrow(() -> new RuntimeException("Exemplarul nu a fost găsit."));

        Imagine imagine = new Imagine();
        imagine.setDenumire(dto.getUrl());
        imagine.setExemplar(exemplar);

        Imagine salvata = imagineRepository.save(imagine);

        ImagineDTO rezultat = new ImagineDTO();
        rezultat.setId(salvata.getIdImagine());
        rezultat.setUrl(salvata.getDenumire());
        rezultat.setExemplarId(exemplar.getIdExemplar());

        return rezultat;
    }
    public List<ImagineDTO> getImaginiByExemplarId(int exemplarId) {
        List<Imagine> imagini = imagineRepository.findAll().stream()
                .filter(img -> img.getExemplar() != null && img.getExemplar().getIdExemplar() == exemplarId)
                .toList();

        return imagini.stream().map(imagine -> {
            ImagineDTO dto = new ImagineDTO();
            dto.setId(imagine.getIdImagine());
            dto.setUrl(imagine.getDenumire());
            dto.setExemplarId(imagine.getExemplar().getIdExemplar());
            return dto;
        }).toList();
    }
    public void stergeImagine(int id) {
        if (!imagineRepository.existsById(id)) {
            throw new RuntimeException("Imaginea cu id " + id + " nu a fost găsită.");
        }
        imagineRepository.deleteById(id);
    }

}
