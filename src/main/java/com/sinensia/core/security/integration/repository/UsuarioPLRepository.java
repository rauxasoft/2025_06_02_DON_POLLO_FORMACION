package com.sinensia.core.security.integration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinensia.core.security.integration.model.UsuarioPL;

public interface UsuarioPLRepository extends JpaRepository<UsuarioPL, Long>{

	Optional<UsuarioPL> findByUsername(String username);
}
