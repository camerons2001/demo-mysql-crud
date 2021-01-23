package com.testovoe.zadanie.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class ContactDto {
    private Long id;
    private String fullName;
    private String phone;
    private Timestamp createAt;
}
