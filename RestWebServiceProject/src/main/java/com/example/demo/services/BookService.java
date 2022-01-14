package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BookService  {

	@Autowired
	BookRepository bookRepository;
	
	public List<Book> GetAllBooks() {
		
		return bookRepository.findAll();
	}

	public Optional<Book> getBookById(long id) {
		
		return bookRepository.findById( id);
	}

	public String addBook(Book book) {
		
		return bookRepository.save(book).toString();
		
	}
	@Transactional
	public String deleteBookByName(String name) {
		
		return bookRepository.deleteBookByName(name);
	}


	
	
}
