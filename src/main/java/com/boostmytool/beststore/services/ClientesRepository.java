package com.boostmytool.beststore.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boostmytool.beststore.models.Cliente;

public interface ClientesRepository extends JpaRepository<Cliente,Integer>{

}
