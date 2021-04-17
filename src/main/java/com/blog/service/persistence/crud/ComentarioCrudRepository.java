package com.blog.service.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import com.blog.service.persistence.entity.Comentario;

public interface ComentarioCrudRepository extends CrudRepository<Comentario, Integer> {
}
