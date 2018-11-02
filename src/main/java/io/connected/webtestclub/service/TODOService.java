package io.connected.webtestclub.service;

import io.connected.webtestclub.exception.DoesNotExistException;
import io.connected.webtestclub.exception.InvalidTodoNameException;
import io.connected.webtestclub.respository.TODORepository;
import io.connected.webtestclub.respository.entity.TODOEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TODOService {

	private final TODORepository todoRepository;

	@Autowired
	public TODOService(TODORepository todoRepository){
		this.todoRepository = todoRepository;
	}

	public List<TODOEntity> getAll() {
		return todoRepository.findAll();
	}

	public TODOEntity save(TODOEntity body) throws InvalidTodoNameException {

		if(body.getTodo() == null || body.getTodo().trim().length() == 0)
			throw new InvalidTodoNameException();

		return todoRepository.save(body);
	}

	public void delete(Long id) throws DoesNotExistException {
		try {
			todoRepository.delete(id);
		} catch (EmptyResultDataAccessException erdae) {
			throw new DoesNotExistException();
		}
	}

	public TODOEntity getOne(long id) {
		return todoRepository.findOne(id);
	}
}
