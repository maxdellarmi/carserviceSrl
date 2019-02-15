package com.nesea.exercise.carservicesrl.repository;

import com.nesea.exercise.carservicesrl.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Integer> {
    List<Proprietario> findAll();

    Optional<Proprietario> findByAutomobile_Id(Integer Id);

    List<Proprietario> findAllByAutomobile_Owner_IdNull();

 }
