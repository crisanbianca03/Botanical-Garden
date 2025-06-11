package com.example.demo1.controller;

import com.example.demo1.domain.dto.ImagineDTO;
import com.example.demo1.service.ImagineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/imagini")
public class ImagineController {

     private final ImagineService imagineService;

     public ImagineController(ImagineService imagineService) {
            this.imagineService = imagineService;
        }

    @PostMapping
    public ImagineDTO adaugaImagine(@RequestBody ImagineDTO dto) {
         return imagineService.adaugaImagine(dto);
     }

    @GetMapping("/by-exemplar/{id}")
    public List<ImagineDTO> getImaginiDupaExemplar(@PathVariable int id) {
        return imagineService.getImaginiByExemplarId(id);
    }

    @DeleteMapping("/{id}")
    public void stergeImagine(@PathVariable int id) {
        imagineService.stergeImagine(id);
    }

    @PostMapping("/upload/{idExemplar}")
    public ResponseEntity<?> uploadImagine(
            @PathVariable int idExemplar,
            @RequestParam("imagine") MultipartFile imagine) {

        try {

            String fileName = UUID.randomUUID() + "_" + imagine.getOriginalFilename();
            Path uploadPath = Paths.get("../demo3/uploads/images");
            Files.createDirectories(uploadPath);

            Files.write(uploadPath.resolve(fileName), imagine.getBytes());

            ImagineDTO dto = new ImagineDTO();
            dto.setExemplarId(idExemplar);
            dto.setUrl(fileName);

            return ResponseEntity.ok(imagineService.adaugaImagine(dto));
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Eroare la salvare imagine: " + e.getMessage());
        }
    }


}

