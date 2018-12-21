package io.connected.webtestclub.controller;

import io.connected.webtestclub.exception.HTTPException;
import io.connected.webtestclub.exception.controller.BadRequestException;
import io.connected.webtestclub.exception.controller.ConflictException;
import io.connected.webtestclub.exception.controller.NotFoundException;
import io.connected.webtestclub.exception.service.DoesNotExistException;
import io.connected.webtestclub.exception.service.DuplicateEntryException;
import io.connected.webtestclub.exception.service.InvalidTodoNameException;
import io.connected.webtestclub.model.TODOModel;
import io.connected.webtestclub.model.generic.ResponseModel;
import io.connected.webtestclub.respository.entity.TODOEntity;
import io.connected.webtestclub.service.TODOService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class TODOControllerTest {

    private TODOController controller;
    private TODOService service;

    @Before
    public void setup(){
         service = mock(TODOService.class);
        controller = new TODOController(service);
    }

    @Test(expected = BadRequestException.class)
    public void shouldReturnBadRequestIfInvalidName() throws InvalidTodoNameException, HTTPException, DuplicateEntryException {
        when(service.save(any())).thenThrow(new InvalidTodoNameException());
        TODOModel.SimpleTODOModel entity = new TODOModel.SimpleTODOModel();
        entity.setTodo("");

        controller.post(entity);
    }

    @Test(expected = NotFoundException.class)
    public void shouldReturnNotFoundIfNonExistingId() throws DoesNotExistException, HTTPException {
        doThrow(new DoesNotExistException()).when(service).delete(anyLong());

        controller.delete(42L);
    }

    @Test
    public void shouldReturnSuccessAfterDeletingItem() throws DoesNotExistException, HTTPException {
        doNothing().when(service).delete(anyLong());

        ResponseEntity<ResponseModel.Simple> response = controller.delete(42L);

        assert response.getStatusCode().value() == 200;
    }

    @Test(expected = ConflictException.class)
    public void shouldThrowExceptionWhenSavingDuplicateItem() throws DuplicateEntryException, InvalidTodoNameException, HTTPException {
        doThrow(new DuplicateEntryException()).when(service).save(any());

        controller.post(new TODOModel.SimpleTODOModel());
    }

    @Test
    public void shouldReturnTodoWhenGetById() {
        TODOEntity todo = new TODOEntity();
        TODOModel.SimpleTODOModel simpleTODOModel = new TODOModel.SimpleTODOModel(todo);
        todo.setTodo("test");
        doReturn(simpleTODOModel).when(service).getById(anyLong());

        ResponseEntity<ResponseModel<TODOModel.SimpleTODOModel>> result = controller.getById(1);

        assert result.getStatusCode().value() == 200;
    }
}