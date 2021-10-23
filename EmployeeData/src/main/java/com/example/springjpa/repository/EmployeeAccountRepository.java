package com.example.springjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAccountRepository extends JpaRepository<EmployeeAccountRepository, Long> {

}
