package com.blog.service.web.controller;

import com.blog.service.model.Post;
import com.blog.service.persistence.entity.Comentario;
import com.blog.service.persistence.service.ComentarioService;
import com.blog.service.service.PostConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private PostConsumerService postConsumerService;

    @MockBean
    @Autowired
    private ComentarioService comentarioService;

    @Test
    void getPostTest() throws Exception {
        List<Post> posts = new ArrayList<>();

        Post p =new Post();
        p.setId(1);
        p.setTitle("fg");
        p.setUserId(1);
        p.setBody("huh");

        posts.add(p);

        Mockito.when(postConsumerService.processPostDataFromObjectArray()).thenReturn(Optional.ofNullable(posts));
        String url = "/posts";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse = mapper.writeValueAsString(posts);

        assertEquals(actualJsonResponse, expectedJsonResponse);
    }

    /*@Test
    void createNewCommentTest() throws Exception {
        Comentario comentario = new Comentario();
        comentario.setIdPost(1);
        comentario.setComentario("test");

        Comentario comentarioResp = new Comentario();
        comentarioResp.setId(1);
        comentarioResp.setIdPost(1);
        comentarioResp.setComentario("test");

        Mockito.doReturn(Optional.of(comentarioResp)).when(comentarioService).save(comentario);

        //Mockito.when(comentarioService.save(comentario)).thenReturn(Optional.ofNullable(comentarioResp));

        String url = "/comment/save";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(comentario));

        mockMvc.perform(requestBuilder)
                                    .andExpect(status().isCreated())
                                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                    .andExpect(jsonPath("$.id", is(1)));

    }*/

    @Test
    void getCommentsByPostTest() throws Exception {
        List<Comentario> comentarios = new ArrayList<>();

        Comentario comentario = new Comentario();
        comentario.setId(1);
        comentario.setIdPost(1);
        comentario.setComentario("test");

        comentarios.add(comentario);

        Mockito.when(comentarioService.getCommentsByPost(1)).thenReturn(Optional.ofNullable(comentarios));
        String url = "/comment/" + comentario.getIdPost();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse = mapper.writeValueAsString(comentarios);

        assertEquals(actualJsonResponse, expectedJsonResponse);
    }
}