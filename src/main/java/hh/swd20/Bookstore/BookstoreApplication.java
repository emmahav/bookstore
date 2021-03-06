package hh.swd20.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;
import hh.swd20.Bookstore.domain.Genre;
import hh.swd20.Bookstore.domain.GenreRepository;
import hh.swd20.Bookstore.domain.User;
import hh.swd20.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner studentDemo(BookRepository brepository, GenreRepository grepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of books");
			
			grepository.save(new Genre("Nonfiction"));
			grepository.save(new Genre("Fantasy"));
			grepository.save(new Genre("Crime"));
			
			brepository.save(new Book("Norwegian Wood", "Haruki Murakami", 1987, "9780307744661", 7.60, grepository.findByName("Nonfiction").get(0)));
			brepository.save(new Book("Homo Deus", "Yuval Noah Harari", 2017, "9781784703936", 10.50, grepository.findByName("Fantasy").get(0)));	
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
			
			// Create users: admin/admin user/user
			User user1 = new User("user"
			,
			"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin"
			,
			"$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

		};
	}
}

