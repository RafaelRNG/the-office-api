package com.rng.theofficeapi.repositories;

import com.rng.theofficeapi.entities.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SalesmanRepository extends JpaRepository<Salesman, Long> {
}