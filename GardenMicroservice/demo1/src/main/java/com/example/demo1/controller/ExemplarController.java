package com.example.demo1.controller;

import com.example.demo1.domain.dto.ExemplarDTO;
import com.example.demo1.service.ExemplarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/exemplare")
public class ExemplarController {

    private final ExemplarService exemplarService;

    public ExemplarController(ExemplarService exemplarService) {
        this.exemplarService = exemplarService;
    }

    @GetMapping
    public List<ExemplarDTO> getAllExemplare() {
        return exemplarService.getExemplareSortateDupaDenumire();
    }

    @GetMapping("/{id}")
    public ExemplarDTO getExemplarById(@PathVariable Integer id) {
        return exemplarService.getExemplarById(id);
    }

    @PostMapping
    public ExemplarDTO addExemplar(@RequestBody ExemplarDTO dto) {
        return exemplarService.addExemplar(dto);
    }

    @PutMapping("/{id}")
    public ExemplarDTO updateExemplar(@PathVariable Integer id, @RequestBody ExemplarDTO dto) {
        return exemplarService.updateExemplar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteExemplar(@PathVariable Integer id) {
        exemplarService.deleteExemplar(id);
    }

    @GetMapping("/filtrare")
    public List<ExemplarDTO> filtreazaExemplare(@RequestParam(required = false) String zona) {
        return exemplarService.filtreazaDupaZona(zona);
    }

    @GetMapping("/cautare")
    public List<ExemplarDTO> cautaExemplare(@RequestParam String text) {
        return exemplarService.cautaDupaDenumire(text);
    }

    @GetMapping("/statistici/exemplare-pe-specie")
    public List<Map<String, Object>> statisticaExemplarePeSpecie() {
        return exemplarService.statisticaExemplarePeSpecie();
    }

    @GetMapping("/statistici/exemplare-pe-zona")
    public List<Map<String, Object>> statisticaExemplarePeZona() {
        return exemplarService.statisticaExemplarePeZona();
    }
    @GetMapping("/cautare-specie")
    public List<ExemplarDTO> cautaDupaSpecie(@RequestParam String specie) {
        return exemplarService.cautaDupaSpecie(specie);
    }
    @GetMapping("/filtrare-completa")
    public List<ExemplarDTO> filtrareCompletă(
            @RequestParam(required = false) String specie,
            @RequestParam(required = false) String zona) {
        return exemplarService.filtrareCombinata(specie, zona);
    }

}
