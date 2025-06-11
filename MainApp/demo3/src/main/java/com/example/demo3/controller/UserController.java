package com.example.demo3.controller;

import com.example.demo3.controller.service.UserService;
import com.example.demo3.model.ContactDTO;
import com.example.demo3.model.LogInDTO;
import com.example.demo3.model.UtilizatorDTO;
import org.springframework.web.bind.annotation.*;
import com.example.demo3.model.ContacteMultipleDTO;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public UtilizatorDTO login(@RequestBody LogInDTO loginDTO) {
        return userService.login(loginDTO);
    }
    @GetMapping("/cu-contacte")
    public List<ContacteMultipleDTO> getAllUtilizatoriCuContacte() {
        return userService.getUtilizatoriCuContacte();
    }
    @PutMapping("/{id}")
    public void updateUtilizator(@PathVariable Integer id, @RequestBody UtilizatorDTO utilizatorDTO) {
        utilizatorDTO.setIdUtilizator(id);
        userService.updateUtilizator(utilizatorDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUtilizator(@PathVariable Integer id) {
        userService.deleteUtilizator(id);
    }
    @PostMapping
    public UtilizatorDTO createUtilizator(@RequestBody UtilizatorDTO utilizatorDTO) {
        return userService.createUtilizator(utilizatorDTO);
    }

    @PostMapping("/contacts")
    public ContactDTO addContact(@RequestBody ContactDTO contactDTO) {
        return userService.createContact(contactDTO);
    }

    @DeleteMapping("/contacts/{id}")
    public void deleteContact(@PathVariable Integer id) {
        userService.deleteContact(id);
    }

}
