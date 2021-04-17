package com.blog.service.service;

import com.blog.service.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostConsumerService {

    Optional<List<Post>> processPostDataFromObjectArray();
}
