package com.example.demo.rest.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.services.BookService;

@RestController
public class BookController {
	
	@Autowired
	BookService bookService;
	

	@GetMapping("/books")
	public List<Book> getAllBooks(){
		return bookService.GetAllBooks();
		
	}
	
	@GetMapping("/books/getBook/{id}")
	public Optional<Book> getBookById(@PathVariable int id) {
		return bookService.getBookById(id);
		
	}
	
	

	@RequestMapping(
			  value = "/books/addBook/{name}/{auther}", 
			  produces = "application/json", 
			  method = {RequestMethod.PUT,RequestMethod.POST})
	public String getBookById(@PathVariable String name,@PathVariable  String auther) {
		
		return bookService.addBook(new Book(0, name,auther));
		
	}
	
	@DeleteMapping("/books/deleteBook/{name}")
	public String getBookById(@PathVariable String name) {
		return bookService.deleteBookByName(name);
		
	}
	
	
	
	

}
