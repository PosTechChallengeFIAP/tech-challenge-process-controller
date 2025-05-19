package com.tech.challenge.QueueService.core.domain.entities;

import jakarta.persistence.Column;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

import static org.assertj.core.util.DateUtil.now;

public class QueueBuilder {

    private Integer id;

    private EQueueStatus status;

    private String name;

    private Integer orderId;

    private Date createdAt;

    private Date updatedAt;

    private Integer pvdId;

    public QueueBuilder(){
        this.id = 1;
        this.status = EQueueStatus.DONE;
        this.name = "TestQueue";
        this.orderId = 1;
        this.createdAt = now();
        this.updatedAt = now() ;
        this.pvdId = 1;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EQueueStatus getStatus() {
        return status;
    }

    public void setStatus(EQueueStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getPvdId() {
        return pvdId;
    }

    public void setPvdId(Integer pvdId) {
        this.pvdId = pvdId;
    }

    public Queue build(){

        Queue queue = new Queue();

        queue.setId(this.id);
        queue.setName(this.name);
        queue.setStatus(this.status);
        queue.setOrderId(this.orderId);
        queue.setCreatedAt(this.createdAt);
        queue.setUpdatedAt(this.updatedAt);
        queue.setPvdId(this.pvdId);

        return queue;
    }
}
