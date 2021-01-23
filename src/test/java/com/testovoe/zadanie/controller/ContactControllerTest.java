package com.testovoe.zadanie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testovoe.zadanie.dto.ContactDto;
import com.testovoe.zadanie.service.ContactService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class ContactControllerTest {

    @Autowired
    MockMvc mock;
    @MockBean
    ContactService service;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void create() throws Exception {
        ContactDto dto = new ContactDto();
        dto.setId(1L);
        dto.setFullName("Ivan Ivanov");
        dto.setPhone("12345");
        dto.setCreateAt(Timestamp.from(Instant.now()));

        MvcResult result = mock.perform(MockMvcRequestBuilders.post("/api/add")
                .content(mapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void getAll() throws Exception {
        MvcResult result = mock.perform(MockMvcRequestBuilders.get("/api/getAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void update() throws Exception {
        ContactDto dto = new ContactDto();
        dto.setId(1L);
        dto.setFullName("Ivan Ivanov");
        dto.setPhone("12345");
        dto.setCreateAt(Timestamp.from(Instant.now()));

        MvcResult result = mock.perform(MockMvcRequestBuilders.put("/api/update")
                .content(mapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void delete() throws Exception {
        ContactDto dto = new ContactDto();
        dto.setId(1L);
        dto.setFullName("Ivan Ivanov");
        dto.setPhone("12345");
        dto.setCreateAt(Timestamp.from(Instant.now()));
        service.add(dto);

        MvcResult result = mock.perform(MockMvcRequestBuilders.delete("/api/delete/1")
                .content(mapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void filter() throws Exception {
        ContactDto dto = new ContactDto();
        dto.setId(1L);
        dto.setFullName("Ivan Ivanov");
        dto.setPhone("12345");
        dto.setCreateAt(Timestamp.from(Instant.now()));
        service.add(dto);

        MvcResult result = mock.perform(MockMvcRequestBuilders.get("/api/filter")
                .queryParam("key", "123").content(mapper.writeValueAsString(dto)))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }
}