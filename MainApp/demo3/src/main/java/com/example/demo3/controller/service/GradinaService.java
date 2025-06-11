package com.example.demo3.controller.service;

import com.example.demo3.model.ExemplarDTO;
import com.example.demo3.model.ImagineDTO;
import com.example.demo3.model.PlantaDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;


@Service
public class GradinaService {
    private final RestTemplate restTemplate;
    private final String plantServiceUrl = "http://localhost:8081/api/plante";
    private final String EXEMPLARE_URL = "http://localhost:8081/api/exemplare";
    private final String IMAGINI_URL = "http://localhost:8081/api/imagini/by-exemplar/";
    private final String IMAGINI_POST_URL = "http://localhost:8081/api/imagini";
    private final Path uploadPath = Paths.get(System.getProperty("user.dir"), "frontend", "public", "images");



    public GradinaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<PlantaDTO> getAllPlante() {
        PlantaDTO[] plante = restTemplate.getForObject(plantServiceUrl, PlantaDTO[].class);
        return Arrays.asList(plante);
    }

    public List<PlantaDTO> searchPlante(String denumire, String specie, String tip, Boolean carnivora, String zona) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(plantServiceUrl + "/search");

        if (denumire != null) builder.queryParam("denumire", denumire);
        if (specie != null) builder.queryParam("specie", specie);
        if (tip != null) builder.queryParam("tip", tip);
        if (carnivora != null) builder.queryParam("carnivora", carnivora);
        if (zona != null) builder.queryParam("zona", zona);

        PlantaDTO[] plante = restTemplate.getForObject(builder.toUriString(), PlantaDTO[].class);
        return Arrays.asList(plante);
    }
    public List<ExemplarDTO> getAllExemplare() {
        ExemplarDTO[] exemplare = restTemplate.getForObject(EXEMPLARE_URL, ExemplarDTO[].class);

        for (ExemplarDTO ex : exemplare) {
            ImagineDTO[] imagini = restTemplate.getForObject(IMAGINI_URL + ex.getIdExemplar(), ImagineDTO[].class);
            ex.setImagini(Arrays.asList(imagini));
        }

        return Arrays.asList(exemplare);
    }
    public List<ExemplarDTO> filtreazaExemplare(String specie, String zona) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8081/api/exemplare/filtrare-completa");

        if (specie != null && !specie.isEmpty()) {
            builder.queryParam("specie", specie);
        }
        if (zona != null && !zona.isEmpty()) {
            builder.queryParam("zona", zona);
        }

        ExemplarDTO[] exemplare = restTemplate.getForObject(builder.toUriString(), ExemplarDTO[].class);

        for (ExemplarDTO ex : exemplare) {
            ImagineDTO[] imagini = restTemplate.getForObject(IMAGINI_URL + ex.getIdExemplar(), ImagineDTO[].class);
            ex.setImagini(Arrays.asList(imagini));
        }

        return Arrays.asList(exemplare);
    }


    public List<String> getSpecii() {
        String url = plantServiceUrl + "/specii";
        String[] specii = restTemplate.getForObject(url, String[].class);
        return Arrays.asList(specii);
    }
    public List<PlantaDTO> getPlanteSortateDupaTipSiSpecie() {
        String url = "http://localhost:8081/api/plante/sortate-tip-specie";
        PlantaDTO[] plante = restTemplate.getForObject(url, PlantaDTO[].class);
        return Arrays.asList(plante);
    }
    public PlantaDTO addPlanta(PlantaDTO plantaDTO) {
        return restTemplate.postForObject(plantServiceUrl, plantaDTO, PlantaDTO.class);
    }

    public PlantaDTO updatePlanta(int id, PlantaDTO plantaDTO) {
        String url = plantServiceUrl + "/" + id;
        restTemplate.put(url, plantaDTO);
        return restTemplate.getForObject(url, PlantaDTO.class);
    }


    public void deletePlanta(int id) {
        String url = plantServiceUrl + "/" + id;
        restTemplate.delete(url);
    }
    public byte[] exportPlante(String format) {
        String url = plantServiceUrl+"/export?format=" + format;
        return restTemplate.getForObject(url, byte[].class);
    }
    public ExemplarDTO adaugaExemplar(ExemplarDTO exemplarDTO) {
        return restTemplate.postForObject(EXEMPLARE_URL, exemplarDTO, ExemplarDTO.class);
    }
    public ExemplarDTO updateExemplar(int id, ExemplarDTO exemplarDTO) {
        String url = EXEMPLARE_URL + "/" + id;
        restTemplate.put(url, exemplarDTO);
        return restTemplate.getForObject(url, ExemplarDTO.class);
    }
    public void deleteExemplar(int id) {
        restTemplate.delete(EXEMPLARE_URL + "/" + id);
    }
    public void uploadImagine(int idExemplar, MultipartFile imagine) throws IOException {
        File uploadDir = uploadPath.toFile();
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String fileName = imagine.getOriginalFilename();
        File dest = new File(uploadDir, fileName);
        imagine.transferTo(dest);
        System.out.println("Salvez imaginea în: " + dest.getAbsolutePath());

        ImagineDTO imagineDTO = new ImagineDTO();
        imagineDTO.setExemplarId(idExemplar);
        imagineDTO.setUrl(fileName);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ImagineDTO> request = new HttpEntity<>(imagineDTO, headers);

        restTemplate.postForObject("http://localhost:8081/api/imagini", request, ImagineDTO.class);
    }

    public void deleteImagine(int idImagine) {
        String url = "http://localhost:8081/api/imagini/" + idImagine;
        restTemplate.delete(url);
    }
    public List<Map<String, Object>> getStatisticaExemplarePeSpecie() {
        String url = "http://localhost:8081/api/exemplare/statistici/exemplare-pe-specie";
        return restTemplate.getForObject(url, List.class);
    }

    public List<Map<String, Object>> getStatisticaExemplarePeZona() {
        String url = "http://localhost:8081/api/exemplare/statistici/exemplare-pe-zona";
        return restTemplate.getForObject(url, List.class);
    }

    public List<Map<String, Object>> getStatisticaPlantePeTip() {
        String url = "http://localhost:8081/api/plante/statistici/plante-pe-tip";
        return restTemplate.getForObject(url, List.class);
    }
    public byte[] exportStatisticiInWord() {
        String url = "http://localhost:8081/api/plante/export?format=doc";
        return restTemplate.getForObject(url, byte[].class);
    }



}
