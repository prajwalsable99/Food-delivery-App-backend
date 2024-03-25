package com.prajwal.FoodApp.repository;

import com.prajwal.FoodApp.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address ,Long> {
}
