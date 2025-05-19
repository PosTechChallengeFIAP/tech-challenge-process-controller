package com.tech.challenge.QueueService.core.application.exceptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class UnableToParseQueueStatusExceptionTest {

    @Test
    void testExceptionMessage() {
        String value = "INVALID_STATUS";

        UnableToParseQueueStatusException ex = new UnableToParseQueueStatusException(value);

        String expectedMessage = "Unable to parse queue status. value = INVALID_STATUS";
        assertEquals(expectedMessage, ex.getMessage());
    }
}
