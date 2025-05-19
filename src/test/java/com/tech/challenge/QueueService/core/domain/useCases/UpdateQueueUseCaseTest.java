package com.tech.challenge.QueueService.core.domain.useCases;

import com.tech.challenge.QueueService.adapters.infra.impl.QueueRepository;
import com.tech.challenge.QueueService.core.application.exceptions.ResourceNotFoundException;
import com.tech.challenge.QueueService.core.domain.entities.EQueueStatus;
import com.tech.challenge.QueueService.core.domain.entities.Queue;
import com.tech.challenge.QueueService.core.domain.entities.QueueBuilder;
import com.tech.challenge.QueueService.core.domain.repositories.IQueueRepository;
import com.tech.challenge.QueueService.core.domain.useCases.UpdateQueueUseCase.DTOs.UpdateQueueUseCaseRequest;
import com.tech.challenge.QueueService.core.domain.useCases.UpdateQueueUseCase.UpdateQueueUseCase;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UpdateQueueUseCaseTest {

    @Autowired
    private UpdateQueueUseCase updateQueueUseCase;

    @MockBean
    private QueueRepository queueRepository;

    @Test
    public void executeTest(){

        QueueBuilder queueBuilder = new QueueBuilder();

        Queue queue = queueBuilder.build();

        when(queueRepository.findById(queue.getId())).thenReturn(Optional.of(queue));

        when(queueRepository.save(queue)).thenReturn(queue);

        UpdateQueueUseCaseRequest updateQueueUseCaseRequest = new UpdateQueueUseCaseRequest(1, EQueueStatus.FINISHED);

        Queue updatedQueue = updateQueueUseCase.execute(updateQueueUseCaseRequest);

        assert(updatedQueue.getStatus().equals(EQueueStatus.FINISHED));


    }

    @Test
    public void executeExceptionTest(){

        QueueBuilder queueBuilder = new QueueBuilder();

        Queue queue = queueBuilder.build();

        UpdateQueueUseCaseRequest updateQueueUseCaseRequest = new UpdateQueueUseCaseRequest(1, EQueueStatus.FINISHED);
        when(queueRepository.findById(queue.getId())).thenReturn(Optional.empty());

        assertThrows(ValidationException.class, ()->{
            updateQueueUseCase.execute(updateQueueUseCaseRequest);
        });

    }

}
