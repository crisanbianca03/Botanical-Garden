package com.example.demo1.service;

import com.example.demo1.domain.Planta;
import com.example.demo1.domain.adapter.PlantaAdapter;
import com.example.demo1.domain.dto.PlantaDTO;
import com.example.demo1.repository.PlantaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlantaService {

    private final PlantaRepository repository;
    private final PlantaAdapter plantaAdapter;

    public PlantaService(PlantaRepository repository, PlantaAdapter plantaAdapter) {
        this.repository = repository;
        this.plantaAdapter = plantaAdapter;
    }

    public List<PlantaDTO> getAllPlante() {
        return repository.findAll().stream()
                .map(plantaAdapter::toDto)
                .toList();
    }

    public PlantaDTO getPlantaById(Integer id) {
        Planta planta = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Planta nu a fost găsită"));
        return plantaAdapter.toDto(planta);
    }

    public PlantaDTO addPlanta(PlantaDTO dto) {
        Planta planta = plantaAdapter.toEntity(dto);
        return plantaAdapter.toDto(repository.save(planta));
    }

    public PlantaDTO updatePlanta(Integer id, PlantaDTO dto) {
        Planta planta = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Planta cu id " + id + " nu a fost găsită"));

        planta.setDenumire(dto.getDenumire());
        planta.setTip(dto.getTip());
        planta.setSpecie(dto.getSpecie());
        planta.setPlanteCarnivore(dto.getPlanteCarnivore());
        planta.setDescriere(dto.getDescriere());

        return plantaAdapter.toDto(repository.save(planta));
    }

    public void deletePlanta(Integer id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Planta nu există");
        }
        repository.deleteById(id);
    }

    public List<PlantaDTO> getPlanteByTip(String tip) {
        return repository.findByTip(tip).stream()
                .map(plantaAdapter::toDto)
                .toList();
    }

    public List<PlantaDTO> getPlanteBySpecie(String specie) {
        return repository.findBySpecie(specie).stream()
                .map(plantaAdapter::toDto)
                .toList();
    }
    public List<Planta> getAllPlanteEntities() {
        return repository.findAll();
    }

    public List<PlantaDTO> getPlanteSortateTipSpecie() {
        return repository.findAll(Sort.by("tip").ascending().and(Sort.by("specie").ascending()))
                .stream()
                .map(plantaAdapter::toDto)
                .toList();
    }
    public List<PlantaDTO> filtreazaPlante(String tip, String specie, Boolean planteCarnivore) {
        return repository.filterPlante(tip, specie, planteCarnivore)
                .stream()
                .map(plantaAdapter::toDto)
                .toList();
    }
    public List<Map<String, Object>> statisticaPlantePeTip() {
        return repository.numarPlantePeTip().stream()
                .map(obj -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("tip", obj[0]);
                    map.put("numar", obj[1]);
                    return map;
                })
                .toList();
    }
    public List<String> getSpeciiDistincte() {
        return repository.findAll()
                .stream()
                .map(Planta::getSpecie)
                .distinct()
                .toList();
    }

}
