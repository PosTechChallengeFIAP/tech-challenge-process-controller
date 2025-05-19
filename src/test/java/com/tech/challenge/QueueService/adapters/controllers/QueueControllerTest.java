package com.tech.challenge.QueueService.adapters.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.challenge.QueueService.adapters.infra.impl.QueueRepository;
import com.tech.challenge.QueueService.core.domain.entities.EQueueStatus;
import com.tech.challenge.QueueService.core.domain.entities.Queue;
import com.tech.challenge.QueueService.core.domain.entities.QueueBuilder;
import com.tech.challenge.QueueService.core.domain.useCases.CreateQueueUseCase.ICreateQueueUseCase;
import com.tech.challenge.QueueService.core.domain.useCases.FindNotFinishedItemsUseCase.IFindNotFinishedItemsUseCase;
import com.tech.challenge.QueueService.core.domain.useCases.FindQueueByIdUseCase.IFindQueueByIdUseCase;
import com.tech.challenge.QueueService.core.domain.useCases.UpdateQueueUseCase.DTOs.UpdateQueueUseCaseRequest;
import com.tech.challenge.QueueService.core.domain.useCases.UpdateQueueUseCase.IUpdateQueueUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QueueControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ICreateQueueUseCase createQueueUseCase;

    @MockBean
    private IFindNotFinishedItemsUseCase findNotFinishedItemsUseCase;

    @MockBean
    private IFindQueueByIdUseCase findQueueByIdUseCase;

    @MockBean
    private IUpdateQueueUseCase updateQueueUseCase;

    @MockBean
    private QueueRepository queueRepository;

    private String BASE_URL;
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        this.BASE_URL = String.format("http://localhost:%d", port);

    }

    private String getFullUrl(String endpoint) {
        return BASE_URL + endpoint;
    }

    @Test
    public void findQueueById_Success() {
        Queue queue = new QueueBuilder().build();
        when(findQueueByIdUseCase.execute(queue.getId())).thenReturn(queue);

        ResponseEntity<Queue> response = restTemplate.getForEntity(getFullUrl("/queue/" + queue.getId()), Queue.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        //assertEquals(queue, response.getBody());
    }

    @Test
    public void findQueueById_NotFound() {
        Integer id = 999;
        when(findQueueByIdUseCase.execute(id)).thenThrow(new RuntimeException("Not Found"));

        ResponseEntity<String> response = restTemplate.getForEntity(getFullUrl("/queue/" + id), String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).contains("Not Found"));
    }

    @Test
    public void findAllPendingQueue_Success() {
        Queue queue = new QueueBuilder().build();
        when(findNotFinishedItemsUseCase.execute()).thenReturn(List.of(queue));

        ResponseEntity<List> response = restTemplate.getForEntity(getFullUrl("/queue"), List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    public void createQueue_Success() {
        Queue queue = new QueueBuilder().build();
        when(createQueueUseCase.execute(queue)).thenReturn(queue);

        ResponseEntity<Queue> response = restTemplate.postForEntity(getFullUrl("/queue"), queue, Queue.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        //assertEquals(queue, response.getBody());
    }
/*
    @Test
    public void updateQueue_Success() {
        Queue queue = new QueueBuilder().build();
        //queue.setStatus(EQueueStatus.DONE); // garantir status válido

        UpdateQueueUseCaseRequest request = new UpdateQueueUseCaseRequest(queue.getId(), queue.getStatus());
        when(updateQueueUseCase.execute(request)).thenReturn(queue);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Queue> entity = new HttpEntity<>(queue, headers);

        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory()); // habilita PATCH

        ResponseEntity<Queue> response = restTemplate.exchange(
                getFullUrl("/queue/" + queue.getId()),
                HttpMethod.PATCH,
                entity,
                Queue.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(queue, response.getBody());
    }*/
}

