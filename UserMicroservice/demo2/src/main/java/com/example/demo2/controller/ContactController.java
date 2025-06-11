package com.example.demo2.controller;

import com.example.demo2.domain.dto.ContactDTO;
import com.example.demo2.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/user/{idUtilizator}")
    public List<ContactDTO> getContactsForUser(@PathVariable Integer idUtilizator) {
        return contactService.getContactsForUser(idUtilizator);
    }

    @PostMapping
    public ContactDTO addContact(@RequestBody ContactDTO contactDTO) {
        return contactService.addContact(contactDTO);
    }

    @PutMapping("/{idContact}")
    public ContactDTO updateContact(@PathVariable Integer idContact, @RequestBody ContactDTO contactDTO) {
        return contactService.updateContact(idContact, contactDTO);
    }

    @DeleteMapping("/{idContact}")
    public void deleteContact(@PathVariable Integer idContact) {
        contactService.deleteContact(idContact);
    }
}
