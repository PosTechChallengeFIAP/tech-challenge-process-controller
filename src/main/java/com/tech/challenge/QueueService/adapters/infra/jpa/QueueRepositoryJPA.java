package com.tech.challenge.QueueService.adapters.infra.jpa;

import com.tech.challenge.QueueService.core.domain.entities.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QueueRepositoryJPA extends JpaRepository<Queue,Integer> {

    @Query(value = "SELECT * FROM queue WHERE status IN (0,1,2) ORDER BY CASE WHEN status = 2 THEN 1 WHEN status = 1 THEN 2 ELSE 3 END, created_at ASC;", nativeQuery = true)
    List<Queue> findAllNotFinishedItems();
}
