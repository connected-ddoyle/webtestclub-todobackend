package io.connected.webtestclub.controller;

import io.connected.webtestclub.exception.service.DuplicateUserException;
import io.connected.webtestclub.exception.service.InvalidUserNameException;
import io.connected.webtestclub.model.UserModel;
import io.connected.webtestclub.model.generic.ResponseModel;
import io.connected.webtestclub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/", produces = "application/json")
	public @ResponseBody
	ResponseEntity<ResponseModel<UserModel>> getCurrentUser(Principal principal){
		return new ResponseEntity<>(new ResponseModel<>("Ok!" ,userService.getUser(principal.getName())), HttpStatus.OK);
	}

	@PostMapping(value = "/", produces = "application/json")
	public @ResponseBody
	ResponseEntity<ResponseModel.Simple> create(@RequestBody UserModel.DetailedUserModel userModel) throws InvalidUserNameException, DuplicateUserException {
		userService.register(userModel);
		return new ResponseEntity<>(new ResponseModel.Simple("Created"), HttpStatus.CREATED);
	}

}
