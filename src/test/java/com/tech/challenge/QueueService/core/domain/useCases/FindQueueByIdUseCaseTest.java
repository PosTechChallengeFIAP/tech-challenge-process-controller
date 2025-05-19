package com.tech.challenge.QueueService.core.domain.useCases;

import com.tech.challenge.QueueService.adapters.infra.impl.QueueRepository;
import com.tech.challenge.QueueService.core.application.exceptions.ResourceNotFoundException;
import com.tech.challenge.QueueService.core.domain.entities.Queue;
import com.tech.challenge.QueueService.core.domain.entities.QueueBuilder;
import com.tech.challenge.QueueService.core.domain.repositories.IQueueRepository;
import com.tech.challenge.QueueService.core.domain.useCases.FindQueueByIdUseCase.FindQueueByIdUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FindQueueByIdUseCaseTest {

    @Autowired
    private FindQueueByIdUseCase findQueueByIdUseCase;

    @MockBean
    private QueueRepository queueRepository;

    @Test
    public void executeTest() throws Exception{

        QueueBuilder queueBuilder = new QueueBuilder();

        Queue queueRecord = queueBuilder.build();

        when(queueRepository.findById(queueRecord.getId())).thenReturn(Optional.of(queueRecord));

        Queue retrivedQueue = findQueueByIdUseCase.execute(queueRecord.getId());

        assertEquals(queueRecord,retrivedQueue);

    }

    @Test
    public void executeNotFoundTest() throws Exception{

        QueueBuilder queueBuilder = new QueueBuilder();

        Queue queueRecord = queueBuilder.build();

        when(queueRepository.findById(queueRecord.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()->{
            findQueueByIdUseCase.execute(queueRecord.getId());
        });

    }

}
