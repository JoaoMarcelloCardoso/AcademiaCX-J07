package com.commerce.acdemycx.repository;

import com.commerce.acdemycx.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Long> {

}
