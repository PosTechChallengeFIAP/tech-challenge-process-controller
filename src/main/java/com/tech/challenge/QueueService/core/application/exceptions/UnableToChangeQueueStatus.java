package com.tech.challenge.QueueService.core.application.exceptions;

public class UnableToChangeQueueStatus extends  RuntimeException {

    public UnableToChangeQueueStatus(String oldValue, String newValue) {
        super(String.format("Unable to change queue status from %s to %s", oldValue, newValue));
    }
}
