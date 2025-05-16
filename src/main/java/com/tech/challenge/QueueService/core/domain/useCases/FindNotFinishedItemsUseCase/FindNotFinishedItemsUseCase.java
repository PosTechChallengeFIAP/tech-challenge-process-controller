package com.tech.challenge.QueueService.core.domain.useCases.FindNotFinishedItemsUseCase;

import com.tech.challenge.QueueService.adapters.infra.impl.QueueRepository;
import com.tech.challenge.QueueService.core.domain.entities.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindNotFinishedItemsUseCase implements  IFindNotFinishedItemsUseCase{

    @Autowired
    private QueueRepository queueRepository;
    @Override
    public List<Queue> execute() {
        return queueRepository.findAllNotFinishedItems();
    }
}
