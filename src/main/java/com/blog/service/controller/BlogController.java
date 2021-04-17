package com.blog.service.controller;

import com.blog.service.model.Post;
import com.blog.service.persistence.entity.Comentario;
import com.blog.service.persistence.service.ComentarioService;
import com.blog.service.service.PostConsumerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("blog")
public class BlogController {

    @Autowired
    private PostConsumerService postConsumerService;

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping(value = "/posts", produces = "application/json")
    @ApiOperation("Consulta los posts utilizando un recurso externo disponible en https://jsonplaceholder.typicode.com/posts este recurso retorna los valores tal cual se reciben en el servicio.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 409, message = "CONFLICT")
    })
    public ResponseEntity<List<Post>> getPost(){
        return postConsumerService.processPostDataFromObjectArray()
                .map(posts -> new ResponseEntity<>(posts, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    @PostMapping("/comment/save")
    public ResponseEntity<Comentario> createComment(@RequestBody Comentario comentario){
        return new ResponseEntity<>(comentarioService.save(comentario), HttpStatus.CREATED);
    }


}
