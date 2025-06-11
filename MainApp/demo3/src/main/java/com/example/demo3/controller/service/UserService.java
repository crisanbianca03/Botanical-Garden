package com.example.demo3.controller.service;

import com.example.demo3.model.ContactDTO;
import com.example.demo3.model.ContacteMultipleDTO;
import com.example.demo3.model.LogInDTO;
import com.example.demo3.model.UtilizatorDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final RestTemplate restTemplate;
    private final String utilizatorUrl = "http://localhost:8082/api/utilizatori";
    private final String contactUrl = "http://localhost:8082/api/contacts";
    private final String contactByUserUrl = "http://localhost:8082/api/contacts/user/";

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public UtilizatorDTO login(LogInDTO loginDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LogInDTO> request = new HttpEntity<>(loginDTO, headers);

        ResponseEntity<UtilizatorDTO> response = restTemplate.postForEntity(
                utilizatorUrl + "/login",
                request,
                UtilizatorDTO.class
        );

        return response.getBody();
    }
    public List<ContacteMultipleDTO> getUtilizatoriCuContacte() {
        String utilizatoriEndpoint = "http://localhost:8082/api/utilizatori";

        ResponseEntity<UtilizatorDTO[]> utilizatoriResponse =
                restTemplate.getForEntity(utilizatoriEndpoint, UtilizatorDTO[].class);

        UtilizatorDTO[] utilizatori = utilizatoriResponse.getBody();
        if (utilizatori == null) return List.of();

        return List.of(utilizatori).stream().map(utilizator -> {
            String contacteEndpoint = contactByUserUrl + utilizator.getIdUtilizator();

            ResponseEntity<ContactDTO[]> contacteResponse =
                    restTemplate.getForEntity(contacteEndpoint, ContactDTO[].class);

            List<ContactDTO> contacte = List.of(contacteResponse.getBody());

            ContacteMultipleDTO dto = new ContacteMultipleDTO();
            dto.setUtilizator(utilizator);
            dto.setContacte(contacte);
            return dto;
        }).collect(Collectors.toList());
    }

    public void updateUtilizator(UtilizatorDTO utilizatorDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UtilizatorDTO> request = new HttpEntity<>(utilizatorDTO, headers);
        restTemplate.put(utilizatorUrl + "/" + utilizatorDTO.getIdUtilizator(), request);
    }


    public void deleteUtilizator(Integer idUtilizator) {
        restTemplate.delete(utilizatorUrl + "/" + idUtilizator);
    }
    public UtilizatorDTO createUtilizator(UtilizatorDTO utilizatorDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UtilizatorDTO> request = new HttpEntity<>(utilizatorDTO, headers);

        ResponseEntity<UtilizatorDTO> response = restTemplate.postForEntity(
                utilizatorUrl, request, UtilizatorDTO.class
        );

        return response.getBody();
    }
    public ContactDTO createContact(ContactDTO contactDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ContactDTO> request = new HttpEntity<>(contactDTO, headers);

        ResponseEntity<ContactDTO> response = restTemplate.postForEntity(
                contactUrl,
                request,
                ContactDTO.class
        );

        return response.getBody();
    }

    public void deleteContact(Integer idContact) {
        restTemplate.delete(contactUrl + "/" + idContact);
    }

}
