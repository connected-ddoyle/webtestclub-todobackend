package io.connected.webtestclub.controller;

import io.connected.webtestclub.exception.controller.BadRequestException;
import io.connected.webtestclub.exception.controller.NotFoundException;
import io.connected.webtestclub.exception.service.DoesNotExistException;
import io.connected.webtestclub.exception.service.InvalidTodoNameException;
import io.connected.webtestclub.model.ResponseModel;
import io.connected.webtestclub.respository.entity.TODOEntity;
import io.connected.webtestclub.service.TODOService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TODOControllerTest {

    private TODOController controller;
    private TODOService service;

    @Before
    public void setup(){
         service = mock(TODOService.class);
        controller = new TODOController(service);
    }

    @Test()
    public void shouldReturnBadRequestIfInvalidName() throws InvalidTodoNameException, BadRequestException {
        when(service.save(any())).thenThrow(new InvalidTodoNameException());
        TODOEntity entity = new TODOEntity();
        entity.setTodo("");

        ResponseEntity<ResponseModel.Simple> response = controller.post(entity);

        assert response.getStatusCode().value() == 400;
    }

    @Test()
    public void shouldReturnNotFoundIfNonExistingId() throws DoesNotExistException, NotFoundException {
        doThrow(new DoesNotExistException()).when(service).delete(anyLong());

        ResponseEntity<ResponseModel.Simple> response = controller.delete(42L);

        assert response.getStatusCode().value() == 404;
    }
}