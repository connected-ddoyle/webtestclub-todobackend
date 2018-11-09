package io.connected.webtestclub.service;

import io.connected.webtestclub.exception.service.DoesNotExistException;
import io.connected.webtestclub.exception.service.InvalidTodoNameException;
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
	public void setup(){
		mockRepository = mock(TODORepository.class);
		service = new TODOService(mockRepository);
	}

	@Test(expected = InvalidTodoNameException.class)
	public void saveAnInvalidItem() throws InvalidTodoNameException {
		TODOEntity entity = new TODOEntity();
		service.save(entity);
	}

	@Test(expected = DoesNotExistException.class)
	public void deleteAnInvalidItem() throws DoesNotExistException {
		doThrow(new EmptyResultDataAccessException(0)).when(mockRepository).delete(anyLong());
		long invalidId = 37;
		service.delete(invalidId);
	}

}