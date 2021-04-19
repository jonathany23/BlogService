package com.blog.service.persistence.service;

import com.blog.service.persistence.crud.ComentarioCrudRepository;
import com.blog.service.persistence.entity.Comentario;
import com.blog.service.persistence.repository.ComentarioRepository;
import com.blog.service.persistence.service.ComentarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ComentarioServiceTest {

    @MockBean
    private ComentarioRepository repository;

    @Autowired
    private ComentarioService comentarioService;

    @Test
    void save() {
        Comentario comentario = new Comentario();
        comentario.setIdPost(1);
        comentario.setComentario("test");

        Comentario comentarioResp = new Comentario();
        comentarioResp.setId(1);
        comentarioResp.setIdPost(1);
        comentarioResp.setComentario("test");

        Mockito.doReturn(Optional.of(comentarioResp)).when(repository).save(comentario);

        Optional<Comentario> returnedComment = comentarioService.save(comentario);

        assertTrue(returnedComment.isPresent());
        assertEquals(returnedComment.get().getComentario(), comentario.getComentario());

    }

    @Test
    void getCommentsByPost() {
        List<Comentario> comentarios = new ArrayList<>();

        Comentario comentarioResp = new Comentario();
        comentarioResp.setId(1);
        comentarioResp.setIdPost(1);
        comentarioResp.setComentario("test");

        comentarios.add(comentarioResp);

        Mockito.doReturn(Optional.of(comentarios)).when(repository).getCommentsByPost(1);

        Optional<List<Comentario>> commentsByPost = comentarioService.getCommentsByPost(1);

        assertTrue(commentsByPost.isPresent());
        assertEquals(commentsByPost.get().get(0).getIdPost(), 1);
    }
}