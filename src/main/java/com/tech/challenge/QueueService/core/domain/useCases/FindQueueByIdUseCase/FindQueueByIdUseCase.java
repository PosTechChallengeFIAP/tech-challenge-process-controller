package com.tech.challenge.QueueService.core.domain.useCases.FindQueueByIdUseCase;

import com.tech.challenge.QueueService.core.application.exceptions.ResourceNotFoundException;
import com.tech.challenge.QueueService.core.domain.entities.Queue;
import com.tech.challenge.QueueService.core.domain.repositories.IQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindQueueByIdUseCase implements IFindQueueByIdUseCase {

    @Autowired
    private IQueueRepository queueRepository;

    public Queue execute (Integer id) {
        return queueRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(Queue.class,String.format("No Queue with ID %s",id))
        );
    }
}
