package com.tech.challenge.QueueService.core.domain.useCases;

import com.tech.challenge.QueueService.adapters.infra.impl.QueueRepository;
import com.tech.challenge.QueueService.core.domain.entities.Queue;
import com.tech.challenge.QueueService.core.domain.repositories.IQueueRepository;
import com.tech.challenge.QueueService.core.domain.useCases.CreateQueueUseCase.ICreateQueueUseCase;
import com.tech.challenge.QueueService.core.domain.useCases.FindNotFinishedItemsUseCase.FindNotFinishedItemsUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FindNotFinishedItemsUseCaseTest {

    @Autowired
    private FindNotFinishedItemsUseCase findNotFinishedItemsUseCase;

    @MockBean
    private ICreateQueueUseCase createQueueUseCase;

    @MockBean
    private QueueRepository queueRepository;

    @Test
    public void executeTest() throws Exception{

        Queue firstQueue = new Queue();
        Queue secondQueue = new Queue();
        List<Queue> queues = List.of(firstQueue,secondQueue);

        when(queueRepository.findAllNotFinishedItems()).thenReturn(queues);

        List<Queue> returnedQueues = findNotFinishedItemsUseCase.execute();

        assertEquals(queues, returnedQueues);



    }


}
