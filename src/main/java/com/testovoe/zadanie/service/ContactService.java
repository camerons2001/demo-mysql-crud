package com.testovoe.zadanie.service;

import com.testovoe.zadanie.dto.ContactDto;
import com.testovoe.zadanie.model.Contact;
import com.testovoe.zadanie.repo.ContactRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ContactService {

    private final ContactRepo repo;

    public ContactService(ContactRepo repo) {
        this.repo = repo;
    }

    public ResponseEntity<?> getAll() {
        List<Contact> entities = repo.findAll();
        if (entities.isEmpty()) {
            return new ResponseEntity<>("no content", HttpStatus.OK);
        }
        List<ContactDto> dtoList = entities.stream()
                .filter(Objects::nonNull)
                .map(contact -> {
                    ContactDto dto = new ContactDto();
                    dto.setId(contact.getId());
                    dto.setFullName(contact.getFullName());
                    dto.setPhone(contact.getPhone());
                    dto.setCreateAt(contact.getCreateAt());
                    return dto;
                }).collect(Collectors.toList());
        log.info("retrieving entities");
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<?> add(ContactDto dto) {
        if (Objects.isNull(dto)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Contact entity = new Contact();
        Optional.ofNullable(dto.getFullName()).ifPresent(entity::setFullName);
        Optional.ofNullable(dto.getPhone()).ifPresent(entity::setPhone);
        log.info("added new entity with id " + entity.getId());
        return ResponseEntity.ok(repo.save(entity));
    }

    public ResponseEntity<?> update(ContactDto dto) {
        Optional<Contact> optional = repo.findById(dto.getId());
        if (optional.isEmpty()) {
            return new ResponseEntity<>("no such contact", HttpStatus.BAD_REQUEST);
        }
        Contact contact = optional.get();
        Optional.ofNullable(dto.getFullName()).ifPresent(contact::setFullName);
        Optional.ofNullable(dto.getPhone()).ifPresent(contact::setPhone);
        log.info("entity with id " + dto.getId() + " updated");
        return ResponseEntity.ok(repo.save(contact));
    }

    public ResponseEntity<?> delete(long id) {
        repo.deleteById(id);
        log.info("deleted entity with id " + id);
        return ResponseEntity.ok("SUCCESS");
    }

    public ResponseEntity<?> filterByKey(String key) {
        List<Contact> entities = repo.findByFullNameAndPhone(key);
        if (entities.isEmpty()) {
            return new ResponseEntity<>("no content", HttpStatus.OK);
        }
        log.info("filtered by phone and name for key " + key);
        return ResponseEntity.ok(entities);
    }
}
