package com.tech.challenge.QueueService.core.domain.entities;

import com.tech.challenge.QueueService.core.application.exceptions.UnableToParseQueueStatusException;

public enum EQueueStatus {

    RECEIVED("received"),
    PREPARING("preparing"),
    DONE("done"),
    FINISHED("finished");

    private String value;

    EQueueStatus(String value) {
        this.value = value;
    }

    public EQueueStatus fromValue(String value){
        for(EQueueStatus status : EQueueStatus.values()){
            if(status.value.equals(value)) return status;
        }

        throw new UnableToParseQueueStatusException(value);
    }
}
