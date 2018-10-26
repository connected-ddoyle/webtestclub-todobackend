package io.connected.webtestclub.service;

import io.connected.webtestclub.respository.TODORepository;
import io.connected.webtestclub.respository.entity.TODOEntity;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TODOServiceTest {

	private TODOService service;
	private TODORepository mockRepository;

	@Before
	public void setup(){
		mockRepository = mock(TODORepository.class);
		service = new TODOService(mockRepository);
	}

	@Test
	public void saveAValidItem() {
		TODOEntity entity = new TODOEntity();
		entity.setTodo("Write tests!");

		when(mockRepository.save(any(TODOEntity.class))).thenReturn(null);

		service.save(entity);
	}

}