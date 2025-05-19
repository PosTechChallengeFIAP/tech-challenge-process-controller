package com.tech.challenge.QueueService.adapters.infra.impl;

import com.tech.challenge.QueueService.adapters.infra.jpa.QueueRepositoryJPA;
import com.tech.challenge.QueueService.core.domain.entities.Queue;
import com.tech.challenge.QueueService.core.domain.repositories.IQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class QueueRepository implements IQueueRepository {

    @Autowired
    public QueueRepositoryJPA queueRepositoryJPA;

    @Override
    public Optional<Queue> findById(Integer id) {
        Optional<Queue> queueItem = queueRepositoryJPA.findById(id);
        return queueItem;
    }

    @Override
    public Queue save(Queue queueToSave) {
        return queueRepositoryJPA.save(queueToSave);
    }

    @Override
    public List<Queue> findAllNotFinishedItems() {
        return queueRepositoryJPA.findAllNotFinishedItems();
    }
}
