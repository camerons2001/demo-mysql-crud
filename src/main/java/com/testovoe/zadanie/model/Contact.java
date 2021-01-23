package com.testovoe.zadanie.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Getter @Setter
@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "create_at")
    private Timestamp createAt;

    public Contact() {
        this.createAt = Timestamp.from(Instant.now());
    }

    public Contact(String fullName, String phone) {
        this.fullName = fullName;
        this.phone = phone;
        this.createAt = Timestamp.from(Instant.now());
    }
}
