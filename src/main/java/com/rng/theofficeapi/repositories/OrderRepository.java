package com.rng.theofficeapi.repositories;

import com.rng.theofficeapi.dto.ClientDTO;
import com.rng.theofficeapi.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findByClient(ClientDTO clientDTO, Pageable pageable);
}