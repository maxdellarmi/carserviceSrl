package com.nesea.exercise.carservicesrl.repository;

import com.nesea.exercise.carservicesrl.model.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface AutomobileRepository extends JpaRepository<Automobile, Integer> {
    List<Automobile> findAll();

    Optional<Automobile> findByOwner_Id(Integer Id);

    List<Automobile> findAllByDataVenditaAfter(LocalDate dateAfter);

    List<Automobile> findAllByDataVenditaBetween(LocalDate firstDate, LocalDate lastDate);
}