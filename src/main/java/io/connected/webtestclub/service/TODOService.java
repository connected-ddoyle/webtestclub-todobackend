package io.connected.webtestclub.service;

import io.connected.webtestclub.respository.TODORepository;
import io.connected.webtestclub.respository.entity.TODOEntity;
import org.springframework.beans.factory.annotation.Autowired;
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

	public void save(TODOEntity body) {
		todoRepository.save(body);
	}
}
