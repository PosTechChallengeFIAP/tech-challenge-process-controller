package com.tech.challenge.QueueService.core.domain.useCases.CreateQueueUseCase;

import com.tech.challenge.QueueService.core.domain.entities.Queue;

public interface ICreateQueueUseCase {

    public Queue execute(Queue queue);
}
