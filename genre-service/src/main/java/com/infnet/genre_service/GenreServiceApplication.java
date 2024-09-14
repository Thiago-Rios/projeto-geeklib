package com.infnet.genre_service;

import com.infnet.genre_service.model.Genre;
import com.infnet.genre_service.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
@Slf4j
public class GenreServiceApplication implements CommandLineRunner {
	private final GenreService genreService;

	public static void main(String[] args) {
		SpringApplication.run(GenreServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Genre genre1 = new Genre();
		genre1.setName("Fantasia");

		Genre genre2 = new Genre();
		genre2.setName("Ação");

		Genre genre3 = new Genre();
		genre3.setName("Terror");

		genreService.save(genre1);
		genreService.save(genre2);
		genreService.save(genre3);

		log.info("Gêneros mockados inseridos no banco de dados.");
	}

}
