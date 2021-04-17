package com.blog.service.persistence.service;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.blog.service.model.Post;
import com.blog.service.persistence.entity.Comentario;
import com.blog.service.persistence.repository.ComentarioRepository;
import com.blog.service.service.PostConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PostConsumerService postConsumerService;

    public Optional<Comentario> save(Comentario comentario){
        Optional<Post> postOpt = postConsumerService.getPostById(comentario.getIdPost());
        if (postOpt.isPresent()) {
            Post post = postOpt.get();
            if (post.getId() != null) {
                return comentarioRepository.save(comentario);
            }
        }
        return Optional.empty();
    }

    public Optional<List<Comentario>> getCommentsByPost(int idPost){
        return comentarioRepository.getCommentsByPost(idPost);
    }


}
