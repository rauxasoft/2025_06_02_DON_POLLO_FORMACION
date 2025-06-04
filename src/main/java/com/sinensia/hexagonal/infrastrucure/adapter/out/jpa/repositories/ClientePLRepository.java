package com.sinensia.hexagonal.infrastrucure.adapter.out.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinensia.hexagonal.infrastrucure.adapter.out.jpa.entities.ClientePL;

public interface ClientePLRepository extends JpaRepository<ClientePL, Long>{

	boolean existsByNif(String nif);
}

