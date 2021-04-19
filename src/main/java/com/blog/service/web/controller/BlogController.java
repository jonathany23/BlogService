package com.blog.service.web.controller;

import com.blog.service.model.Post;
import com.blog.service.persistence.entity.Comentario;
import com.blog.service.persistence.service.ComentarioService;
import com.blog.service.service.PostConsumerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
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
    public ResponseEntity<List<Post>> getPost() {
        return postConsumerService.processPostDataFromObjectArray()
                .map(posts -> new ResponseEntity<>(posts, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    @PostMapping("/comment/save")
    @ApiOperation("Guardar un comentario relacionado a un Post. Si el post no existe no permite guardar el comentario")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    public ResponseEntity<Comentario> createComment(@RequestBody Comentario comentario) {
        if (comentario != null && comentario.getIdPost() != null) {
            return comentarioService.save(comentario)
                    .map(comentario1 -> new ResponseEntity<>(comentario1, HttpStatus.CREATED))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/comment/{idPost}")
    @ApiOperation("Listar todos los comentarios relacionados a un post.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    public ResponseEntity<List<Comentario>> getCommentsByPost(
            @ApiParam(value = "Id del Post", required = true, example = "1")
            @PathVariable("idPost") int idPost) {

        return comentarioService.getCommentsByPost(idPost)
                .map(comentario -> new ResponseEntity<>(comentario, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
