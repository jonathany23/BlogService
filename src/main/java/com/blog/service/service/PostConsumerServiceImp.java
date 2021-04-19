package com.blog.service.service;

import com.blog.service.model.Post;
import com.blog.service.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostConsumerServiceImp implements PostConsumerService {

    private static final Logger log = LoggerFactory.getLogger(PostConsumerServiceImp.class);

    //private static final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private static ObjectMapper mapper;

    private final RestTemplate restTemplate;

    public PostConsumerServiceImp(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Optional<List<Post>> processPostDataFromObjectArray() {
        ResponseEntity<Object[]> responseEntiry = restTemplate.getForEntity(Constants.URL, Object[].class);
        Object[] objects = responseEntiry.getBody();
        List<Post> posts = Arrays.stream(objects)
                                .map(object -> mapper.convertValue(object, Post.class))
                                .collect(Collectors.toList());
        return Optional.of(posts);
    }

    @Override
    public Optional<Post> getPostById(int idPost) {
        String postUrl = Constants.URL + idPost;
        ResponseEntity<Post> post = restTemplate.getForEntity(postUrl, Post.class);
        return Optional.of(post.getBody());
    }
}
