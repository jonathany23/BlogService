package com.blog.service.service;

import com.blog.service.model.Post;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PostConsumerServiceImpTest {

    @MockBean
    private PostConsumerService postConsumerService;

    @Test
    void processPostDataFromObjectArray() {
        List<Post> posts = new ArrayList<>();
        Post post = new Post();
        post.setId(1);
        post.setUserId(1);
        post.setTitle("Test Title 1");
        post.setBody("Prueba post 1");

        posts.add(post);

        Mockito.doReturn(Optional.of(posts)).when(postConsumerService).processPostDataFromObjectArray();

        Optional<List<Post>> postsOpt = postConsumerService.processPostDataFromObjectArray();

        assertTrue(postsOpt.isPresent());
        assertEquals(1, postsOpt.get().size());
        assertEquals(1, postsOpt.get().get(0).getId());
    }

    @Test
    void getPostById() {
        Post post = new Post();
        post.setId(1);
        post.setUserId(1);
        post.setTitle("Test Title 1");
        post.setBody("Prueba post 1");

        Mockito.doReturn(Optional.of(post)).when(postConsumerService).getPostById(1);

        Optional<Post> postByIdOpt = postConsumerService.getPostById(1);

        assertTrue(postByIdOpt.isPresent());
        assertEquals(1, postByIdOpt.get().getId());

    }
}