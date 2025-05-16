package com.tech.challenge.QueueService.core.domain.useCases.UpdateQueueUseCase.DTOs;


import com.tech.challenge.QueueService.core.domain.entities.EQueueStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class UpdateQueueUseCaseRequest {

    private Integer queueId;
    private EQueueStatus status;
}
