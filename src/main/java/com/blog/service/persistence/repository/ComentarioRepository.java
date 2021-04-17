package com.blog.service.persistence.repository;

import com.blog.service.persistence.crud.ComentarioCrudRepository;
import com.blog.service.persistence.entity.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ComentarioRepository {

    @Autowired
    private ComentarioCrudRepository comentarioCrudRepository;

    public Comentario save(Comentario comentario){
        return comentarioCrudRepository.save(comentario);
    }
}
