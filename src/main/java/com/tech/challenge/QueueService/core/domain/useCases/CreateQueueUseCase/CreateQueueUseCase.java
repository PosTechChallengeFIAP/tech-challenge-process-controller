package com.tech.challenge.QueueService.core.domain.useCases.CreateQueueUseCase;

import com.tech.challenge.QueueService.core.domain.entities.Queue;
import com.tech.challenge.QueueService.core.domain.repositories.IQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateQueueUseCase implements ICreateQueueUseCase{

    @Autowired
    private IQueueRepository queueRepository;
    public Queue execute(Queue queue){

        return queueRepository.save(queue);
    }
}
