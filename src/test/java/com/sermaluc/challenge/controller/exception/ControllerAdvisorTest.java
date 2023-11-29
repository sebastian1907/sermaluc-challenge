package com.sermaluc.challenge.controller.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public class ControllerAdvisorTest {

    @Test
    public void mock() {
        Exception exception = Mockito.mock(Exception.class);
        WebRequest request = Mockito.mock(WebRequest.class );

        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        ResponseEntity<Object> response = controllerAdvisor.handleExceptionGeneric(exception, request);

        Assertions.assertNotNull(response);
    }

    @Test
    public void mockHandleConflictSaveUserByEmailException() {

        DataIntegrityViolationException exception = Mockito.mock(DataIntegrityViolationException.class );
        WebRequest request = Mockito.mock(WebRequest.class);

        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        ResponseEntity<Object> responseEntity = controllerAdvisor.handleConflictSaveUserByEmailException(exception, request);

        Assertions.assertNotNull(responseEntity);

    }

    @Test
    public void mockHandleRunTimeException() {

        RuntimeException exception = Mockito.mock(RuntimeException.class );
        WebRequest request = Mockito.mock(WebRequest.class);

        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        ResponseEntity<Object> responseEntity = controllerAdvisor.handleRunTimeException(exception, request);

        Assertions.assertNotNull(responseEntity);

    }

}
