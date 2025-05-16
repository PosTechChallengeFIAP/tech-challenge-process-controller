package com.tech.challenge.QueueService.core.domain.entities;

import com.tech.challenge.QueueService.core.application.exceptions.UnableToChangeQueueStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "queue")
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "queue_seq")
    @SequenceGenerator(name = "queue_seq", sequenceName = "queue_seq", allocationSize = 1)
    private Integer id;

    @Enumerated(EnumType.ORDINAL)
    private EQueueStatus status;

    @Column(name= "name")
    private String name;

    @Column(name= "order_Id")
    private Integer orderId;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name= "pvd_Id")
    private Integer pvdId;

    public void setStatus(EQueueStatus newStatus) {
        if(!Objects.isNull(this.status)) {
            EQueueStatus oldStatus = this.status;

            boolean caseReceivedShouldBePreparingDoneOrFinished =
                    oldStatus == EQueueStatus.RECEIVED &&
                            (
                                    newStatus == EQueueStatus.PREPARING ||
                                            newStatus == EQueueStatus.DONE ||
                                            newStatus == EQueueStatus.FINISHED
                            );
            boolean casePreparingShouldBeDoneOrFinished =
                    oldStatus == EQueueStatus.PREPARING &&
                            (
                                    newStatus == EQueueStatus.DONE ||
                                            newStatus == EQueueStatus.FINISHED
                            );
            boolean caseDoneShouldBeFinished =
                    oldStatus == EQueueStatus.DONE &&
                            newStatus == EQueueStatus.FINISHED;

            boolean valid =
                    caseReceivedShouldBePreparingDoneOrFinished ||
                            casePreparingShouldBeDoneOrFinished ||
                            caseDoneShouldBeFinished;

            if (!valid) {
                throw new UnableToChangeQueueStatus(oldStatus.name(), newStatus.name());
            }
        }

        this.status = newStatus;
    }
}
