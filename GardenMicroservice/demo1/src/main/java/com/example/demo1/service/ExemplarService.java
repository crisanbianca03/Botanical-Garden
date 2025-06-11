package com.example.demo1.service;

import com.example.demo1.domain.Exemplar;
import com.example.demo1.domain.Planta;
import com.example.demo1.domain.adapter.ExemplarAdapter;
import com.example.demo1.domain.dto.ExemplarDTO;
import com.example.demo1.repository.ExemplarRepository;
import com.example.demo1.repository.PlantaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExemplarService {

    private final ExemplarRepository exemplarRepository;
    private final PlantaRepository plantaRepository;
    private final ExemplarAdapter exemplarAdapter;

    public ExemplarService(ExemplarRepository exemplarRepository,
                           PlantaRepository plantaRepository,
                           ExemplarAdapter exemplarAdapter) {
        this.exemplarRepository = exemplarRepository;
        this.plantaRepository = plantaRepository;
        this.exemplarAdapter = exemplarAdapter;
    }

    public ExemplarDTO addExemplar(ExemplarDTO dto) {
        Planta planta = plantaRepository.findById(dto.getIdPlanta())
                .orElseThrow(() -> new RuntimeException("Planta nu a fost găsită."));

        Exemplar exemplar = exemplarAdapter.toEntity(dto);
        exemplar.setPlanta(planta);

        return exemplarAdapter.toDto(exemplarRepository.save(exemplar));
    }

    public void deleteExemplar(Integer idExemplar) {
        if (!exemplarRepository.existsById(idExemplar)) {
            throw new RuntimeException("Exemplarul cu id " + idExemplar + " nu a fost găsit.");
        }
        exemplarRepository.deleteById(idExemplar);
    }

    public ExemplarDTO getExemplarById(Integer id) {
        Exemplar exemplar = exemplarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exemplarul nu există."));
        return exemplarAdapter.toDto(exemplar);
    }

    public List<ExemplarDTO> getExemplareSortateDupaDenumire() {
        return exemplarRepository.findAll(Sort.by("denumire").ascending())
                .stream()
                .map(exemplarAdapter::toDto)
                .toList();
    }

    public List<ExemplarDTO> filtreazaDupaZona(String zonaGradina) {
        return (zonaGradina == null || zonaGradina.isEmpty()
                ? exemplarRepository.findAll()
                : exemplarRepository.findByZonaGradinaIgnoreCase(zonaGradina))
                .stream()
                .map(exemplarAdapter::toDto)
                .toList();
    }

    public List<ExemplarDTO> cautaDupaDenumire(String text) {
        return exemplarRepository.findByDenumireContainingIgnoreCase(text)
                .stream()
                .map(exemplarAdapter::toDto)
                .toList();
    }

    public List<Map<String, Object>> statisticaExemplarePeSpecie() {
        return exemplarRepository.numarExemplarePeSpecie().stream().map(obj -> {
            Map<String, Object> map = new HashMap<>();
            map.put("specie", obj[0]);
            map.put("numar", obj[1]);
            return map;
        }).toList();
    }

    public List<Map<String, Object>> statisticaExemplarePeZona() {
        return exemplarRepository.numarExemplarePeZona().stream().map(obj -> {
            Map<String, Object> map = new HashMap<>();
            map.put("zona", obj[0]);
            map.put("numar", obj[1]);
            return map;
        }).toList();
    }

    public List<ExemplarDTO> cautaDupaSpecie(String specie) {
        return exemplarRepository.findByPlanta_SpecieIgnoreCase(specie).stream()
                .map(exemplarAdapter::toDto)
                .toList();
    }
    public List<ExemplarDTO> filtrareCombinata(String specie, String zona) {
        return exemplarRepository.findAll().stream()
                .filter(e -> specie == null || specie.isBlank() || e.getPlanta().getSpecie().equalsIgnoreCase(specie))
                .filter(e -> zona == null || zona.isBlank() || e.getZonaGradina().equalsIgnoreCase(zona))
                .map(exemplarAdapter::toDto)
                .toList();
    }

    public ExemplarDTO updateExemplar(Integer id, ExemplarDTO dto) {
        Exemplar exemplar = exemplarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exemplarul cu id " + id + " nu a fost găsit."));

        Planta planta = plantaRepository.findById(dto.getIdPlanta())
                .orElseThrow(() -> new RuntimeException("Planta cu id " + dto.getIdPlanta() + " nu a fost găsită."));

        exemplar.setZonaGradina(dto.getZonaGradina());
        exemplar.setDenumire(dto.getDenumire());
        exemplar.setDataPlantarii(dto.getDataPlantarii());
        exemplar.setCaracteristiciSpecifice(dto.getCaracteristiciSpecifice());
        exemplar.setPlanta(planta);

        return exemplarAdapter.toDto(exemplarRepository.save(exemplar));
    }

}
