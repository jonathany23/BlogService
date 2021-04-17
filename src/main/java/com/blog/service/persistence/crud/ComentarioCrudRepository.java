package com.blog.service.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import com.blog.service.persistence.entity.Comentario;

import java.util.List;
import java.util.Optional;

public interface ComentarioCrudRepository extends CrudRepository<Comentario, Integer> {

    Optional<List<Comentario>> findByIdPost(int idPost);
}
