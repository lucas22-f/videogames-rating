package com.gamesrating.gamesratingdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamesrating.gamesratingdemo.models.Calificacion;

@Repository
public interface ICalificacionRepository extends JpaRepository<Calificacion,Long>{

}
