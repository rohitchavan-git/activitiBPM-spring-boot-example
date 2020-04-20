package com.rohit.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohit.activiti.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

}