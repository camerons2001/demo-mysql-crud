package com.testovoe.zadanie.repo;

import com.testovoe.zadanie.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepo extends JpaRepository<Contact, Long> {

    @Query(value = "select * from contact c where concat(c.fullname, ' ', c.phone) like concat('%', ?1, '%') order by id desc", nativeQuery = true)
    List<Contact> findByFullNameAndPhone(String key);
}
