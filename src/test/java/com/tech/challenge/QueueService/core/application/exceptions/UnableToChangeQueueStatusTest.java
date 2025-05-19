package com.tech.challenge.QueueService.core.application.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class UnableToChangeQueueStatusTest {
    @Test
    void testExceptionMessage() {
        String oldValue = "PENDING";
        String newValue = "COMPLETED";

        UnableToChangeQueueStatus ex = new UnableToChangeQueueStatus(oldValue, newValue);

        String expectedMessage = "Unable to change queue status from PENDING to COMPLETED";
        assertEquals(expectedMessage, ex.getMessage());
    }
}
