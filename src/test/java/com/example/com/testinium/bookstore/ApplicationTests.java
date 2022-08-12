package com.example.com.testinium.bookstore;

import com.example.com.testinium.bookstore.entity.Book;
import com.example.com.testinium.bookstore.entity.Category;
import com.example.com.testinium.bookstore.repository.BookRepository;
import com.example.com.testinium.bookstore.repository.CategoryRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest(
		classes = Application.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class ApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private Integer port;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private BookRepository bookRepository;


	@AfterEach
	void tearDown() {
		categoryRepository.deleteAll();
		bookRepository.deleteAll();
	}

	@Test
	void get_all_categories() throws Exception{
		Category category= new Category();
		category.setCategoryId(12L);
		category.setCategoryName("Toy");

		categoryRepository.save(category);

		final String baseUrl = "http://localhost:"+port+"/inventory/category";
		URI uri = new URI(baseUrl);

		ResponseEntity<List<Category>> response = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference() {
				}
		);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).hasSize(1);
		AssertionsForClassTypes.assertThat(Objects.requireNonNull(response.getBody()).get(0).getCategoryName()).isEqualTo("Toy");


	}

	@Test
	void get_all_product() throws Exception{
		Book book= new Book();

		book.setBookId(5L);
		book.setBookName("Insan ne ile yasar");
		book.getCategory().setCategoryId(5L);
		book.setPrice(100);




		bookRepository.save(book);

		final String baseUrl = "http://localhost:"+port+"/inventory/product";
		URI uri = new URI(baseUrl);

		ResponseEntity<List<Book>> response = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference() {
				}
		);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).hasSize(1);
		AssertionsForClassTypes.assertThat(Objects.requireNonNull(response.getBody()).get(0).getBookName()).isEqualTo("Malcom X");


	}
}
