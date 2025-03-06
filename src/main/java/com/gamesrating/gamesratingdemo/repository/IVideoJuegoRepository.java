package com.gamesrating.gamesratingdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamesrating.gamesratingdemo.models.Videojuego;
@Repository
public interface IVideoJuegoRepository extends JpaRepository<Videojuego,Long> {
    
}
