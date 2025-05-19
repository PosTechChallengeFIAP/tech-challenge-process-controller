package com.tech.challenge.QueueService.core.application.exceptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundExceptionTest {

    static class DummyResource {}

    @Test
    void testConstructorWithClassOnly() {
        ResourceNotFoundException ex = new ResourceNotFoundException(DummyResource.class);

        String expectedMessage = "DummyResource not found.";
        assertEquals(expectedMessage, ex.getMessage());
    }

    @Test
    void testConstructorWithClassAndInfo() {
        String extraInfo = "ID 123";
        ResourceNotFoundException ex = new ResourceNotFoundException(DummyResource.class, extraInfo);

        String expectedMessage = "DummyResource not found. ID 123";
        assertEquals(expectedMessage, ex.getMessage());
    }
}