package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
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

	public List<Book> addBook(String name, String auther) {
		
		return  (List<Book>) bookRepository.save(new Book(1,name,auther));
	}

	public String deleteBookByName(String name) {
		
		return bookRepository.deleteBookByName(name);
	}


	
	
}
