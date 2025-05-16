package com.tech.challenge.QueueService.core.domain.useCases.UpdateQueueUseCase;

import com.tech.challenge.QueueService.core.domain.entities.Queue;
import com.tech.challenge.QueueService.core.domain.useCases.UpdateQueueUseCase.DTOs.UpdateQueueUseCaseRequest;

public interface IUpdateQueueUseCase {

    public Queue execute(UpdateQueueUseCaseRequest request);
}
