package com.tech.challenge.QueueService.core.domain.useCases.FindNotFinishedItemsUseCase;

import com.tech.challenge.QueueService.core.domain.entities.Queue;

import java.util.List;

public interface IFindNotFinishedItemsUseCase {

    public List<Queue> execute();
}
