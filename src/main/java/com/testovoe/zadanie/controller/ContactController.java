package com.testovoe.zadanie.controller;

import com.testovoe.zadanie.dto.ContactDto;
import com.testovoe.zadanie.service.ContactService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactController {

    private final ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody ContactDto dto) {
        return service.add(dto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return service.getAll();
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ContactDto dto) {
        return service.update(dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return service.delete(id);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestParam String key) {
        return service.filterByKey(key);
    }
}
