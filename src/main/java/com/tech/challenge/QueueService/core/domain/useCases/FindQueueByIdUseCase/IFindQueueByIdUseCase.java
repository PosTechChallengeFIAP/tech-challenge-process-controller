package com.tech.challenge.QueueService.core.domain.useCases.FindQueueByIdUseCase;

import com.tech.challenge.QueueService.core.domain.entities.Queue;

import java.util.UUID;

public interface IFindQueueByIdUseCase {

    Queue execute(Integer id);
}
