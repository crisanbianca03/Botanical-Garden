package com.example.demo2.service;

import com.example.demo2.domain.Utilizator;
import com.example.demo2.domain.dto.ContactDTO;
import com.example.demo2.domain.dto.ContacteMultipleDTO;
import com.example.demo2.domain.dto.LogInDTO;
import com.example.demo2.domain.dto.UtilizatorDTO;
import com.example.demo2.domain.mail.MailObserver;
import com.example.demo2.domain.mail.UserNotify;
import com.example.demo2.repository.UtilizatorRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UtilizatorService {

    private final UtilizatorRepository utilizatorRepository;
    private final ContactService contactService;
    private final UserNotify notifier = UserNotify.getInstance();
    private final MailObserver emailObserver;

    @PostConstruct
    public void initObserver() {
        notifier.addObserver(emailObserver);
    }

    public List<UtilizatorDTO> getAllUtilizatori() {
        return utilizatorRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UtilizatorDTO> login(LogInDTO loginRequest) {
        return utilizatorRepository.findByUsername(loginRequest.getUsername())
                .filter(u -> u.getParola().equals(loginRequest.getParola()))
                .map(this::mapToDTO);
    }

    public UtilizatorDTO save(UtilizatorDTO utilizatorDTO) {
        Utilizator utilizator = mapToEntity(utilizatorDTO);
        return mapToDTO(utilizatorRepository.save(utilizator));
    }

    public UtilizatorDTO updateUtilizator(Integer id, UtilizatorDTO utilizatorDTO) {
        Utilizator utilizatorExistent = utilizatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilizatorul nu a fost găsit"));

        utilizatorExistent.setUsername(utilizatorDTO.getUsername());
        utilizatorExistent.setParola(utilizatorDTO.getParola());
        utilizatorExistent.setTipUtilizator(utilizatorDTO.getTipUtilizator());
        utilizatorExistent.setNume(utilizatorDTO.getNume());
        utilizatorExistent.setPrenume(utilizatorDTO.getPrenume());

        Utilizator utilizatorSalvat = utilizatorRepository.save(utilizatorExistent);
        UtilizatorDTO rezultatDTO = mapToDTO(utilizatorSalvat);

        ContacteMultipleDTO dtoCuContacte = new ContacteMultipleDTO();
        dtoCuContacte.setUtilizator(rezultatDTO);
        List<ContactDTO> contacte = contactService.getContactsForUser(id);
        dtoCuContacte.setContacte(contacte);

        notifier.userUpdated(dtoCuContacte);

        return rezultatDTO;
    }

    private Utilizator mapToEntity(UtilizatorDTO utilizatorDTO) {
        return Utilizator.builder()
                .idUtilizator(utilizatorDTO.getIdUtilizator())
                .username(utilizatorDTO.getUsername())
                .tipUtilizator(utilizatorDTO.getTipUtilizator())
                .nume(utilizatorDTO.getNume())
                .prenume(utilizatorDTO.getPrenume())
                .parola(utilizatorDTO.getParola())
                .build();
    }


    public void delete(Integer idUtilizator) {
        utilizatorRepository.deleteById(idUtilizator);
    }

    private UtilizatorDTO mapToDTO(Utilizator utilizator) {
        return UtilizatorDTO.builder()
                .idUtilizator(utilizator.getIdUtilizator())
                .username(utilizator.getUsername())
                .tipUtilizator(utilizator.getTipUtilizator())
                .nume(utilizator.getNume())
                .prenume(utilizator.getPrenume())
                .parola(utilizator.getParola())
                .build();
    }
}
