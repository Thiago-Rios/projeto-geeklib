package com.infnet.author_service;

import com.infnet.author_service.model.Author;
import com.infnet.author_service.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
@Slf4j
public class AuthorServiceApplication implements CommandLineRunner {
	private final AuthorService authorService;

	public static void main(String[] args) {
		SpringApplication.run(AuthorServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Author author1 = new Author();
		author1.setName("Christopher Paolini");

		Author author2 = new Author();
		author2.setName("H. P. Lovecraft");

		Author author3 = new Author();
		author3.setName("Lee Child");

		Author author4 = new Author();
		author4.setName("J. K. Rowling");

		authorService.save(author1);
		authorService.save(author2);
		authorService.save(author3);
		authorService.save(author4);

		log.info("Autores mockados inseridos no banco de dados.");
	}

}
