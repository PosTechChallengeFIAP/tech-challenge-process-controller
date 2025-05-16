package com.tech.challenge.QueueService.core.domain.repositories;

import com.tech.challenge.QueueService.core.domain.entities.Queue;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IQueueRepository {

    Optional<Queue> findById(Integer id);
    List<Queue> findAllNotFinishedItems();
    Queue save(Queue queue);
}
