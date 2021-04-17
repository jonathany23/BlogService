package com.blog.service.persistence.service;

import com.blog.service.persistence.entity.Comentario;
import com.blog.service.persistence.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public Comentario save(Comentario comentario){
        return comentarioRepository.save(comentario);
    }
}
