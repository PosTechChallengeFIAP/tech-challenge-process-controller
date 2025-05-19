package com.tech.challenge.QueueService.core.domain.entities;
import com.tech.challenge.QueueService.core.application.exceptions.UnableToParseQueueStatusException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EQueueStatusTest {

    @Test
    void fromValue_ShouldReturnEnum_WhenValidValue() {
        assertEquals(EQueueStatus.RECEIVED, EQueueStatus.RECEIVED.fromValue("received"));
        assertEquals(EQueueStatus.PREPARING, EQueueStatus.PREPARING.fromValue("preparing"));
        assertEquals(EQueueStatus.DONE, EQueueStatus.DONE.fromValue("done"));
        assertEquals(EQueueStatus.FINISHED, EQueueStatus.FINISHED.fromValue("finished"));
    }

    @Test
    void fromValue_ShouldThrowException_WhenInvalidValue() {
        String invalidValue = "invalid_status";

        UnableToParseQueueStatusException exception = assertThrows(
                UnableToParseQueueStatusException.class,
                () -> EQueueStatus.RECEIVED.fromValue(invalidValue)
        );

        assertEquals("Unable to parse queue status. value = " + invalidValue, exception.getMessage());
    }
}