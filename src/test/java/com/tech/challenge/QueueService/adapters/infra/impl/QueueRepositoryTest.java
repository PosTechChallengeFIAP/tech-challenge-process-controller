package com.tech.challenge.QueueService.adapters.infra.impl;
import com.tech.challenge.QueueService.adapters.infra.jpa.QueueRepositoryJPA;
import com.tech.challenge.QueueService.core.domain.entities.EQueueStatus;
import com.tech.challenge.QueueService.core.domain.entities.Queue;
import com.tech.challenge.QueueService.core.domain.entities.QueueBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class QueueRepositoryTest {

    @Autowired
    private QueueRepositoryJPA queueRepositoryJPA;

    private QueueRepository queueRepository;

    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
        queueRepository = new QueueRepository();
        queueRepository.queueRepositoryJPA = queueRepositoryJPA; // injetando manualmente
    }

    @Test
    public void testSaveAndFindById() {
        Queue queue = new QueueBuilder().build();

        // Salva no banco (h2 em memória durante o teste)
        Queue savedQueue = queueRepository.save(queue);

        assertNotNull(savedQueue.getId());

        // Busca pelo id
        Optional<Queue> found = queueRepository.findById(savedQueue.getId());

        assertTrue(found.isPresent());
        assertEquals(savedQueue.getId(), found.get().getId());
    }

    @Test
    public void testFindAllNotFinishedItems() {
        // Adiciona dois itens (supondo que um deles esteja não finalizado)
        Queue q1 = new QueueBuilder().build();
        QueueBuilder queueBuilder = new QueueBuilder();
        queueBuilder.setStatus(EQueueStatus.RECEIVED);
        Queue q2 = queueBuilder.build();

        queueRepository.save(q1);
        queueRepository.save(q2);

        List<Queue> notFinishedItems = queueRepository.findAllNotFinishedItems();

        assertNotNull(notFinishedItems);
        assertTrue(notFinishedItems.stream().anyMatch(q -> q.getId().equals(q1.getId())));
        //assertTrue(notFinishedItems.stream().noneMatch(q -> q.getId().equals(q2.getId())));
    }
}
