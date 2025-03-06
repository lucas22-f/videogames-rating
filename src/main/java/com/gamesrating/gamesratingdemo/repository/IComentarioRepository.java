package com.gamesrating.gamesratingdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamesrating.gamesratingdemo.models.Comentario;
@Repository
public interface IComentarioRepository extends JpaRepository<Comentario,Long>{

}
