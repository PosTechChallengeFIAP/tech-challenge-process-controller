package com.tech.challenge.QueueService.core.domain.useCases;

import com.tech.challenge.QueueService.adapters.infra.impl.QueueRepository;
import com.tech.challenge.QueueService.core.domain.entities.Queue;
import com.tech.challenge.QueueService.core.domain.repositories.IQueueRepository;
import com.tech.challenge.QueueService.core.domain.useCases.CreateQueueUseCase.CreateQueueUseCase;
import com.tech.challenge.QueueService.core.domain.useCases.CreateQueueUseCase.ICreateQueueUseCase;
import com.tech.challenge.QueueService.core.domain.useCases.FindNotFinishedItemsUseCase.FindNotFinishedItemsUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CreateQueueUseCaseTest {

    @Autowired
    private CreateQueueUseCase createQueueUseCase;

    @MockBean
    private QueueRepository queueRepository;

    @Test
    public void executeTest() throws Exception{

        Queue queue = new Queue();

        when(queueRepository.save(queue)).thenReturn(queue);

        Queue insertedQueue = createQueueUseCase.execute(queue);

        assertEquals(queue, insertedQueue);

    }

}
