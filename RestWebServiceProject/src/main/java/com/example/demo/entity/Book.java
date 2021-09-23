package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Book implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6135837651241011937L;


	public Book() {
		
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + "]";
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	private BookReader bookReader ;
	
	@Column
	private String name;
	
	@Column
	private String author;
	
	
	public Book(long id, String name, String author) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
	}
	
	
}
