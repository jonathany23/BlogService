package com.blog.service.entity;

import javax.persistence.*;

@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_post")
    private Integer idPost;
    
    private Integer comentario;
}
