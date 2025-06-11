package com.example.demo2.service;

import com.example.demo2.domain.Contact;
import com.example.demo2.domain.Utilizator;
import com.example.demo2.domain.dto.ContactDTO;
import com.example.demo2.repository.ContactRepository;
import com.example.demo2.repository.UtilizatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final UtilizatorRepository utilizatorRepository;

    public List<ContactDTO> getContactsForUser(Integer idUtilizator) {
        return contactRepository.findByUtilizatorIdUtilizator(idUtilizator)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void deleteContact(Integer idContact) {
        contactRepository.deleteById(idContact);
    }

    public ContactDTO addContact(ContactDTO contactDTO) {
        Contact contact = mapToEntity(contactDTO);
        return mapToDTO(contactRepository.save(contact));
    }

    public ContactDTO updateContact(Integer idContact, ContactDTO contactDTO) {
        Contact contactExistent = contactRepository.findById(idContact)
                .orElseThrow(() -> new RuntimeException("Contactul nu a fost găsit"));

        contactExistent.setTip(contactDTO.getTip());
        contactExistent.setInformatie(contactDTO.getInformatie());

        return mapToDTO(contactRepository.save(contactExistent));
    }

    private Contact mapToEntity(ContactDTO contactDTO) {
        Utilizator utilizator = utilizatorRepository.getReferenceById(contactDTO.getIdUtilizator());
        return Contact.builder()
                .idContact(contactDTO.getIdContact())
                .tip(contactDTO.getTip())
                .informatie(contactDTO.getInformatie())
                .utilizator(utilizator)
                .build();
    }

    private ContactDTO mapToDTO(Contact contact) {
        return ContactDTO.builder()
                .idContact(contact.getIdContact())
                .tip(contact.getTip())
                .informatie(contact.getInformatie())
                .idUtilizator(contact.getUtilizator().getIdUtilizator())
                .build();
    }

}
