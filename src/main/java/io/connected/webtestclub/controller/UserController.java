package io.connected.webtestclub.controller;

import io.connected.webtestclub.model.ResponseModel;
import io.connected.webtestclub.model.UserModel;
import io.connected.webtestclub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	ResponseEntity<ResponseModel<UserModel>> getCurrentUser(@RequestAttribute("user") UserModel userModel){
		return new ResponseEntity<>(new ResponseModel<>("Ok!" ,userModel), HttpStatus.OK);
	}

	@PostMapping(value = "/register", produces = "application/json")
	public @ResponseBody
	ResponseEntity<ResponseModel.Simple> getCurrentUser(@RequestBody UserModel.DetailedUserModel userModel){
		userService.register(userModel);
		return new ResponseEntity<>(new ResponseModel.Simple("Created"), HttpStatus.CREATED);
	}

}
