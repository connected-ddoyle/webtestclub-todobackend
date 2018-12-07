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
import io.connected.webtestclub.service.TODOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/todo")
public class TODOController {

	private final TODOService todoService;

	@Autowired
	public TODOController(TODOService todoService) {
		this.todoService = todoService;
	}

	@GetMapping(value = "/", produces = "application/json")
	public @ResponseBody
	ResponseEntity<ResponseModel<List<TODOModel.DetailedTODOModel>>> getAll(){
		return new ResponseEntity<>(new ResponseModel<>("Ok!" ,todoService.getAll()), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public @ResponseBody
	ResponseEntity<ResponseModel<TODOModel.SimpleTODOModel>> getById(@PathVariable long id){
		return new ResponseEntity<>(new ResponseModel<>("Ok!" ,todoService.getById(id)), HttpStatus.OK);
	}

	@PostMapping(value = "/", produces = "application/json")
	public @ResponseBody
	ResponseEntity<ResponseModel.Simple> post(@RequestBody TODOModel body) throws HTTPException {
		try {
			todoService.save(body);
		} catch (InvalidTodoNameException e) {
			throw new BadRequestException(e);
		} catch (DuplicateEntryException e) {
			throw new ConflictException(e);
		}
		return new ResponseEntity<>(new ResponseModel.Simple("Created!"), HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id}", produces = "application/json")
	public @ResponseBody
	ResponseEntity<ResponseModel.Simple> delete(@PathVariable Long	 id) throws HTTPException {
		try {
			todoService.delete(id);
		} catch (DoesNotExistException dnee) {
			throw new NotFoundException(dnee);
		}

		return new ResponseEntity<>(new ResponseModel.Simple("Ok!"), HttpStatus.OK);
	}

}
