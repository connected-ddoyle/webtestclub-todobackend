package io.connected.webtestclub.service;

import io.connected.webtestclub.exception.service.DoesNotExistException;
import io.connected.webtestclub.exception.service.DuplicateEntryException;
import io.connected.webtestclub.exception.service.InvalidTodoNameException;
import io.connected.webtestclub.model.DetailedTODOModel;
import io.connected.webtestclub.model.TODOModel;
import io.connected.webtestclub.respository.TODORepository;
import io.connected.webtestclub.respository.entity.TODOEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TODOService {

	private final TODORepository todoRepository;

	@Autowired
	public TODOService(TODORepository todoRepository){
		this.todoRepository = todoRepository;
	}

	public List<DetailedTODOModel> getAll() {
		return todoRepository.findAll().stream().map(DetailedTODOModel::new).collect(Collectors.toList());
	}

	public TODOEntity save(TODOModel body) throws InvalidTodoNameException, DuplicateEntryException {

		if(body.getTodo() == null || body.getTodo().trim().length() == 0)
			throw new InvalidTodoNameException();

		TODOEntity result = todoRepository.findTODOEntityByTodo(body.getTodo());

		if (result != null) {
			throw new DuplicateEntryException();
		}

		return todoRepository.save(body.toEntity());
	}

	public void delete(Long id) throws DoesNotExistException {
		try {
			todoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException erdae) {
			throw new DoesNotExistException();
		}
	}

	public TODOModel getById(long id) {
		Optional<TODOEntity> result = todoRepository.findById(id);
		return result.map(TODOModel::new).orElse(null);
	}
}
