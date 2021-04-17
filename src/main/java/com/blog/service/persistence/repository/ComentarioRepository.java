package com.blog.service.persistence.repository;

import com.blog.service.persistence.crud.ComentarioCrudRepository;
import com.blog.service.persistence.entity.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ComentarioRepository {

    @Autowired
    private ComentarioCrudRepository comentarioCrudRepository;

    public Optional<Comentario> save(Comentario comentario){
        return Optional.ofNullable(comentarioCrudRepository.save(comentario));
    }

    public Optional<List<Comentario>> getCommentsByPost(int idPost){
        return comentarioCrudRepository.findByIdPost(idPost);
    }
}
