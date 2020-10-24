package com.pacheco.springbootmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.pacheco.springbootmongodb.domain.Post;
import com.pacheco.springbootmongodb.domain.User;
import com.pacheco.springbootmongodb.repository.PostRepository;
import com.pacheco.springbootmongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		sdf1.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, sdf1.parse("18/06/1999"), "Traveling...", "Going to São Paulo", alex);
		Post post2 = new Post(null, sdf1.parse("26/03/1999"), "Good Morning", "Woke up happy today!", maria);

		postRepository.saveAll(Arrays.asList(post1, post2));

	}

}
