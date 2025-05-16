package com.tech.challenge.QueueService.core.domain.useCases.UpdateQueueUseCase;

import com.tech.challenge.QueueService.core.domain.entities.EQueueStatus;
import com.tech.challenge.QueueService.core.domain.entities.Queue;
import com.tech.challenge.QueueService.core.domain.repositories.IQueueRepository;
import com.tech.challenge.QueueService.core.domain.useCases.UpdateQueueUseCase.DTOs.UpdateQueueUseCaseRequest;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateQueueUseCase implements IUpdateQueueUseCase{

    @Autowired
    private IQueueRepository queueRepository;
    @Override
    public Queue execute(UpdateQueueUseCaseRequest request) {
        Integer queueId = request.getQueueId();
        EQueueStatus newStatus = request.getStatus();

        Optional<Queue> queueFromRP = queueRepository.findById(queueId);

        if (queueFromRP.isEmpty()) {
            throw new ValidationException("Item not found.");
        }

        Queue queueItem = queueFromRP.get();

        queueItem.setStatus(newStatus);

        Queue savedQueue = queueRepository.save(queueItem);

        return savedQueue;
    }
}
