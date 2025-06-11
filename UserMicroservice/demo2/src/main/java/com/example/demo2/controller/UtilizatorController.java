package com.example.demo2.controller;

import com.example.demo2.domain.dto.ContactDTO;
import com.example.demo2.domain.dto.LogInDTO;
import com.example.demo2.domain.dto.UtilizatorDTO;
import com.example.demo2.service.ContactService;
import com.example.demo2.service.UtilizatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilizatori")
@RequiredArgsConstructor
public class UtilizatorController {

    private final UtilizatorService utilizatorService;
    private final ContactService contactService;

    @PostMapping("/login")
    public Optional<UtilizatorDTO> login(@RequestBody LogInDTO loginRequest) {
        return utilizatorService.login(loginRequest);
    }

    @GetMapping
    public List<UtilizatorDTO> getAllUtilizatori() {
        return utilizatorService.getAllUtilizatori();
    }

    @PostMapping
    public UtilizatorDTO saveUtilizator(@RequestBody UtilizatorDTO utilizatorDTO) {
        return utilizatorService.save(utilizatorDTO);
    }

    @PutMapping("/{id}")
    public UtilizatorDTO updateUtilizator(@PathVariable Integer id, @RequestBody UtilizatorDTO utilizatorDTO) {
        return utilizatorService.updateUtilizator(id, utilizatorDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUtilizator(@PathVariable Integer id) {
        List<ContactDTO> contacts = contactService.getContactsForUser(id);
        for (ContactDTO contactDTO : contacts) {
            contactService.deleteContact(contactDTO.getIdContact());
        }
        utilizatorService.delete(id);
    }
}
