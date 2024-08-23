package com.developer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developer.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
