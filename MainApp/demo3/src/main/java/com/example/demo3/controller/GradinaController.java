package com.example.demo3.controller;

import com.example.demo3.controller.service.GradinaService;
import com.example.demo3.model.ExemplarDTO;
import com.example.demo3.model.ImagineDTO;
import com.example.demo3.model.PlantaDTO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GradinaController {

    @Autowired
    private GradinaService gradinaService;

    @GetMapping("/plante")
    public List<PlantaDTO> getAllPlante() {
        return gradinaService.getAllPlante();
    }

    @GetMapping("/search")
    public List<PlantaDTO> searchPlante(
            @RequestParam(required = false) String denumire,
            @RequestParam(required = false) String specie,
            @RequestParam(required = false) String tip,
            @RequestParam(required = false) Boolean carnivora,
            @RequestParam(required = false) String zona
    ) {
        return gradinaService.searchPlante(denumire, specie, tip, carnivora, zona);
    }
    @GetMapping("/exemplare")
    public List<ExemplarDTO> getExemplare() {
        return gradinaService.getAllExemplare();
    }
    @GetMapping("/exemplare/filtrare")
    public List<ExemplarDTO> filtreazaExemplare(
            @RequestParam(required = false) String specie,
            @RequestParam(required = false) String zona
    ) {
        return gradinaService.filtreazaExemplare(specie, zona);
    }

    @GetMapping("/specii")
    public List<String> getSpecii() {
        return gradinaService.getSpecii();
    }
    @GetMapping("/plante/sortate-tip-specie")
    public List<PlantaDTO> getPlanteSortateTipSpecie() {
        return gradinaService.getPlanteSortateDupaTipSiSpecie();
    }
    @PostMapping("/plante")
    public PlantaDTO addPlanta(@RequestBody PlantaDTO plantaDTO) {
        return gradinaService.addPlanta(plantaDTO);
    }

    @PutMapping("/plante/{id}")
    public PlantaDTO updatePlanta(@PathVariable int id, @RequestBody PlantaDTO plantaDTO) {
        return gradinaService.updatePlanta(id, plantaDTO);
    }

    @DeleteMapping("/plante/{id}")
    public void deletePlanta(@PathVariable int id) {
        gradinaService.deletePlanta(id);
    }
    @GetMapping("/plante/export")
    public ResponseEntity<byte[]> exportPlante(@RequestParam String format) {
        byte[] rezultat = gradinaService.exportPlante(format);

        String contentType = switch (format.toLowerCase()) {
            case "csv" -> "text/csv";
            case "json" -> "application/json";
            case "doc" -> "application/msword";
            default -> "application/octet-stream";
        };

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=plante." + format.toLowerCase())
                .contentType(org.springframework.http.MediaType.parseMediaType(contentType))
                .body(rezultat);
    }

    @PostMapping("/exemplare")
    public ExemplarDTO adaugaExemplar(@RequestBody ExemplarDTO exemplarDTO) {
        return gradinaService.adaugaExemplar(exemplarDTO);
    }
    @PutMapping("/exemplare/{id}")
    public ExemplarDTO actualizeazaExemplar(@PathVariable int id, @RequestBody ExemplarDTO exemplarDTO) {
        return gradinaService.updateExemplar(id, exemplarDTO);
    }
    @DeleteMapping("/exemplare/{id}")
    public void stergeExemplar(@PathVariable int id) {
        gradinaService.deleteExemplar(id);
    }
    @PostMapping("/imagini/upload/{idExemplar}")
    public ResponseEntity<String> uploadImagine(@PathVariable int idExemplar, @RequestParam("imagine") MultipartFile imagine) {
        try {
            gradinaService.uploadImagine(idExemplar, imagine);
            return ResponseEntity.ok("Imagine încărcată");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Eroare la încărcare imagine: " + e.getMessage());
        }
    }


    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws IOException {
        Path imagePath = Paths.get("frontend/public/images").resolve(filename).normalize();

        Resource resource = new UrlResource(imagePath.toUri());

        if (resource.exists() && resource.isReadable()) {
            return ResponseEntity.ok()
                    .header("Content-Disposition", "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/imagini/{idImagine}")
    public void stergeImagine(@PathVariable int idImagine) {
        gradinaService.deleteImagine(idImagine);
    }

    @GetMapping("/statistici/exemplare-pe-specie")
    public List<Map<String, Object>> getStatisticaExemplarePeSpecie() {
        return gradinaService.getStatisticaExemplarePeSpecie();
    }

    @GetMapping("/statistici/exemplare-pe-zona")
    public List<Map<String, Object>> getStatisticaExemplarePeZona() {
        return gradinaService.getStatisticaExemplarePeZona();
    }

    @GetMapping("/statistici/plante-pe-tip")
    public List<Map<String, Object>> getStatisticaPlantePeTip() {
        return gradinaService.getStatisticaPlantePeTip();
    }
    @GetMapping("/statistici/export-word")
    public ResponseEntity<byte[]> exportStatisticiInWord() {
        byte[] rezultat = gradinaService.exportStatisticiInWord();

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=statistici.doc")
                .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
                .body(rezultat);
    }
    @PostMapping("/statistici/export-word")
    public ResponseEntity<byte[]> exportStatisticiToWord(
            @RequestParam("img1") MultipartFile img1,
            @RequestParam("img2") MultipartFile img2,
            @RequestParam("img3") MultipartFile img3
    ) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XWPFDocument doc = new XWPFDocument();

        addImageToDoc(doc, img1.getBytes(), "Imagine 1");
        addImageToDoc(doc, img2.getBytes(), "Imagine 2");
        addImageToDoc(doc, img3.getBytes(), "Imagine 3");

        doc.write(outputStream);
        doc.close();

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=statistici.docx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(outputStream.toByteArray());
    }

    private void addImageToDoc(XWPFDocument doc, byte[] imageData, String title) throws IOException {
        XWPFParagraph titlePara = doc.createParagraph();
        XWPFRun run = titlePara.createRun();
        run.setText(title);
        run.setBold(true);
        run.addBreak();

        XWPFParagraph imagePara = doc.createParagraph();
        XWPFRun imageRun = imagePara.createRun();
        try (InputStream is = new ByteArrayInputStream(imageData)) {
            imageRun.addPicture(is,
                    Document.PICTURE_TYPE_PNG,
                    "chart.png",
                    Units.toEMU(400),
                    Units.toEMU(250));
            imageRun.addBreak();
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

}
