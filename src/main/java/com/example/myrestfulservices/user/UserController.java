package com.example.myrestfulservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

	private UserDaoService service;

	private UserController(UserDaoService service) {
		this.service = service;
	}

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {

		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public User retrieveOneUsers(@PathVariable int id) {

		User user = service.fineOne(id);
		if (user == null) {
			throw new UserNotFoundException(String.format("ID[%s] not found", id));

		}
		return user;
	}

	@PostMapping("/users")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User savedUser = service.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/users/{id}")
	public void updateUser(@PathVariable int id , @RequestBody User user) {
		
		User updateUser = service.updateUser(user,id);

	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);

		if (user == null) {
			throw new UserNotFoundException(String.format("ID[%s] not found", id));

		}

	}

}
