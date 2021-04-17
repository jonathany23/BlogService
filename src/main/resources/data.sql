DROP TABLE IF EXISTS comentarios;

CREATE TABLE comentarios(
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_post INT NOT NULL,
    comentario VARCHAR(255) NOT NULL
);