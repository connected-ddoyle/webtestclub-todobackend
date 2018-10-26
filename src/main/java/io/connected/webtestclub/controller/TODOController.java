package io.connected.webtestclub.controller;

import io.connected.webtestclub.model.ResponseModel;
import io.connected.webtestclub.respository.entity.TODOEntity;
import io.connected.webtestclub.service.TODOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/todo")
public class TODOController {

	private final TODOService todoService;

	@Autowired
	public TODOController(TODOService todoService) {
		this.todoService = todoService;
	}

	@GetMapping(name = "/", produces = "application/json")
	public @ResponseBody
	ResponseEntity<List<TODOEntity>> get(){
		return new ResponseEntity<>(todoService.getAll(), HttpStatus.OK);
	}

	@PostMapping(name = "/", produces = "application/json")
	public @ResponseBody
	ResponseEntity<ResponseModel.Simple> post(@RequestBody TODOEntity body){
		todoService.save(body);
		return new ResponseEntity<>(new ResponseModel.Simple("Created!"), HttpStatus.CREATED);
	}
}
