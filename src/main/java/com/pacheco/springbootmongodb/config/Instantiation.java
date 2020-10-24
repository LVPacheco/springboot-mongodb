package com.pacheco.springbootmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.pacheco.springbootmongodb.domain.Post;
import com.pacheco.springbootmongodb.domain.User;
import com.pacheco.springbootmongodb.dto.AuthorDTO;
import com.pacheco.springbootmongodb.dto.CommentDTO;
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

		Post post1 = new Post(null, sdf1.parse("18/06/1999"), "Traveling...", "Going to São Paulo", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf1.parse("26/03/1999"), "Good Morning", "Woke up happy today!",new AuthorDTO(maria));

		CommentDTO c1 = new CommentDTO("Have a nice trip", sdf1.parse("02/03/1970"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Enjoy", sdf1.parse("01/05/1970"), new AuthorDTO(alex));
		CommentDTO c3 = new CommentDTO("Have a nice day", sdf1.parse("06/02/1970"), new AuthorDTO(bob));
		
		post1.getComments().addAll(Arrays.asList(c1,c3));
		post2.getComments().add(c2);
		
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);

	}

}
