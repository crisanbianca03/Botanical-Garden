package com.example.demo1.controller;

import com.example.demo1.domain.dto.PlantaDTO;
import com.example.demo1.domain.export.*;
import com.example.demo1.service.PlantaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/plante")
public class PlantaController {

    private final PlantaService plantaService;

    public PlantaController(PlantaService plantaService) {
        this.plantaService = plantaService;
    }

    @GetMapping
    public List<PlantaDTO> getAllPlante() {
        return plantaService.getAllPlante();
    }

    @GetMapping("/{id}")
    public PlantaDTO getPlantaById(@PathVariable Integer id) {
        return plantaService.getPlantaById(id);
    }

    @PostMapping
    public PlantaDTO addPlanta(@RequestBody PlantaDTO dto) {
        return plantaService.addPlanta(dto);
    }

    @PutMapping("/{id}")
    public PlantaDTO updatePlanta(@PathVariable Integer id, @RequestBody PlantaDTO dto) {
        return plantaService.updatePlanta(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePlanta(@PathVariable Integer id) {
        plantaService.deletePlanta(id);
    }

    @GetMapping("/by-tip")
    public List<PlantaDTO> getPlanteByTip(@RequestParam String tip) {
        return plantaService.getPlanteByTip(tip);
    }

    @GetMapping("/by-specie")
    public List<PlantaDTO> getPlanteBySpecie(@RequestParam String specie) {
        return plantaService.getPlanteBySpecie(specie);
    }

    @GetMapping("/sortate-tip-specie")
    public List<PlantaDTO> getPlanteSortateDupaTipSiSpecie() {
        return plantaService.getPlanteSortateTipSpecie();
    }
    @GetMapping("/filtrare")
    public List<PlantaDTO> filtreazaPlante(
            @RequestParam(required = false) String tip,
            @RequestParam(required = false) String specie,
            @RequestParam(required = false) Boolean planteCarnivore) {
        return plantaService.filtreazaPlante(tip, specie, planteCarnivore);
    }
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportPlante(@RequestParam String format) {
        List<PlantaDTO> plante = plantaService.getAllPlante();

        ExportFormat exportFormat;
        switch (format.toLowerCase()) {
            case "csv" -> exportFormat = new CsvExport();
            case "json" -> exportFormat = new JsonExport();
            case "doc" -> exportFormat = new DocExport();
            default -> throw new IllegalArgumentException("Format invalid: " + format);
        }

        ExportService exportService = new ExportService(exportFormat);
        byte[] rezultat = exportService.exportData(plante);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=plante" + exportFormat.getFileExtension())
                .contentType(org.springframework.http.MediaType.parseMediaType(exportFormat.getContentType()))
                .body(rezultat);
    }

    @GetMapping("/statistici/plante-pe-tip")
    public List<Map<String, Object>> statisticaPlantePeTip() {
        return plantaService.statisticaPlantePeTip();
    }

    @GetMapping("/specii")
    public List<String> getSpeciiDistincte() {
        return plantaService.getSpeciiDistincte();
    }

}
