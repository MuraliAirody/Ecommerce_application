package com.ecommerce.dao;

import com.ecommerce.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDao extends JpaRepository<Address, Long> {

}
