package com.example.demo.rest.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/books/{id}")
	public Optional<Book> getBookById(@PathVariable int id) {
		return bookService.getBookById(id);
		
	}
	
	
	@GetMapping("/books/addBook/{name}/{auther}")
	public List<Book> getBookById(@PathVariable String name,@PathVariable  String auther) {
		return bookService.addBook(name,auther);
		
	}
	
	@GetMapping("/books/deleteBook/{name}")
	public String getBookById(@PathVariable String name) {
		return bookService.deleteBookByName(name);
		
	}
	
	
	
	

}
