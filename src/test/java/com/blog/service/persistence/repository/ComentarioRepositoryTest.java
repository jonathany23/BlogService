package com.blog.service.persistence.repository;

import com.blog.service.BlogServiceApplication;
import com.blog.service.persistence.entity.Comentario;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BlogServiceApplication.class, ComentarioRepositoryTestConfiguration.class})
@ActiveProfiles("test")
class ComentarioRepositoryTest {

    @MockBean
    private ComentarioRepository comentarioRepository;

    @Test
    void save() {
        Comentario comentarioToSave = new Comentario();
        comentarioToSave.setIdPost(1);
        comentarioToSave.setComentario("Prueba 1");

        Comentario comentarioResp = new Comentario();
        comentarioResp.setId(1);
        comentarioResp.setIdPost(1);
        comentarioResp.setComentario("Prueba 1");

        Mockito.doReturn(Optional.of(comentarioResp)).when(comentarioRepository).save(comentarioToSave);

        Optional<Comentario> comentarioOpt = comentarioRepository.save(comentarioToSave);

        assertTrue(comentarioOpt.isPresent());
        assertEquals(1, comentarioOpt.get().getId());
    }

    @Test
    void getCommentsByPost() {
        List<Comentario> comentarios = new ArrayList<>();
        Comentario cm = new Comentario();
        cm.setId(1);
        cm.setIdPost(1);
        cm.setComentario("Prueba 1");
        comentarios.add(cm);

        Mockito.doReturn(Optional.of(comentarios)).when(comentarioRepository).getCommentsByPost(1);


        Optional<List<Comentario>> commentsByPost = comentarioRepository.getCommentsByPost(1);
        assertTrue(commentsByPost.isPresent());
        assertEquals(1, commentsByPost.get().size());
    }
}