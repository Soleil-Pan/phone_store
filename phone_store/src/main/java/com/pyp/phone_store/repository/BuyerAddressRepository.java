package com.pyp.phone_store.repository;

import com.pyp.phone_store.entity.BuyerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerAddressRepository extends JpaRepository<BuyerAddress,Integer> {
}
