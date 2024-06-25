package com.presto.sebo.repositories;

import com.presto.sebo.entities.Livro;
import com.presto.sebo.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}
