package com.pacheco.springbootmongodb.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pacheco.springbootmongodb.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		User ana = new User("1", "Ana Clara", "klarinhanunes11@gmail.com");
		User lucas = new User("2", "Lucas Pacheco", "lucas.vinicius719@gmail.com");
		List<User> users = new ArrayList<>();
		users.addAll(Arrays.asList(ana, lucas));
		return ResponseEntity.ok().body(users);
	}

}
