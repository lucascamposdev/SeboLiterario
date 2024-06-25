package com.presto.sebo.repositories;

import com.presto.sebo.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByTitulo(String titulo);
    List<Livro> findByQuantidadeGreaterThan(int quantidade);

    List<Livro> findByTituloContainingAndQuantidadeGreaterThan(String nome, int quantidade);
}
