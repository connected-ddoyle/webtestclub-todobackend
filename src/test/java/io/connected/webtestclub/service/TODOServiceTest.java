package io.connected.webtestclub.service;

import io.connected.webtestclub.exception.service.DoesNotExistException;
import io.connected.webtestclub.exception.service.DuplicateEntryException;
import io.connected.webtestclub.exception.service.InvalidTodoNameException;
import io.connected.webtestclub.model.TODOModel;
import io.connected.webtestclub.respository.TODORepository;
import io.connected.webtestclub.respository.entity.TODOEntity;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.mockito.Mockito.*;

public class TODOServiceTest {

	private TODOService service;
	private TODORepository mockRepository;

	@Before
	public void setup() {
		mockRepository = mock(TODORepository.class);
		service = new TODOService(mockRepository);
	}

	@Test(expected = InvalidTodoNameException.class)
	public void saveAnInvalidItem() throws InvalidTodoNameException, DuplicateEntryException {
		TODOModel entity = new TODOModel();
		service.save(entity);
	}

	@Test(expected = DoesNotExistException.class)
	public void deleteAnInvalidItem() throws DoesNotExistException {
		doThrow(new EmptyResultDataAccessException(0)).when(mockRepository).deleteById(anyLong());
		long invalidId = 37;
		service.delete(invalidId);
	}

	@Test(expected = DuplicateEntryException.class)
	public void saveATodoItem_shouldFail_whenDuplicateItemExists() throws InvalidTodoNameException, DuplicateEntryException {
		doReturn(new TODOEntity()).when(mockRepository).findTODOEntityByTodo(anyString());
		TODOModel data = new TODOModel();
		data.setTodo("x");
		service.save(data);
	}

}