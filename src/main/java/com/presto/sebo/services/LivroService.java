package com.presto.sebo.services;

import com.presto.sebo.dto.LivroMinimalResponseDto;
import com.presto.sebo.dto.TradeDto;
import com.presto.sebo.dto.TradeResponseDto;
import com.presto.sebo.entities.Livro;
import com.presto.sebo.entities.Transacao;
import com.presto.sebo.enums.TipoTransacao;
import com.presto.sebo.repositories.LivroRepository;
import com.presto.sebo.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    TransacaoRepository transacaoRepository;

    public List<LivroMinimalResponseDto> findAll() {
        return livroRepository.findByQuantidadeGreaterThan(0)
                .stream()
                .map(livro -> new LivroMinimalResponseDto(livro.getId(), livro.getTitulo(), livro.getPreco(), livro.getQuantidade()))
                .collect(Collectors.toList());
    }

    public Livro findOne(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public List<LivroMinimalResponseDto> searchByTitle(String titulo) {
        return livroRepository.findByTituloContainingAndQuantidadeGreaterThan(titulo, 0)
                .stream()
                .map(livro -> new LivroMinimalResponseDto(livro.getId(), livro.getTitulo(), livro.getPreco(), livro.getQuantidade()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Livro add(Livro livro, boolean isTrade){
        var livroEncontrado = livroRepository.findByTitulo(livro.getTitulo());

        Livro livroAlterado;

        if(livroEncontrado.isPresent()){
            livroAlterado = livroEncontrado.get();
            livroAlterado.setQuantidade(livroAlterado.getQuantidade() + livro.getQuantidade());
            livroAlterado = livroRepository.save(livroAlterado);
        }else{
            livroAlterado = livroRepository.save(livro);
        }

        if(!isTrade){
            BigDecimal valor = livroAlterado.getPreco().multiply(BigDecimal.valueOf(livro.getQuantidade()));
            registrarTransacao(livroAlterado, TipoTransacao.COMPRA, valor);
        }

        return livroAlterado;
    }

    @Transactional
    public Livro sell(Long id, boolean isTrade){
        var livroEncontrado = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não existe"));

        if(livroEncontrado.getQuantidade() > 0){
            livroEncontrado.setQuantidade(livroEncontrado.getQuantidade() - 1);
            livroEncontrado = livroRepository.save(livroEncontrado);
        }else{
            throw new RuntimeException("Quantidade insuficiente");
        }

        if(!isTrade){
            registrarTransacao(livroEncontrado, TipoTransacao.VENDA, livroEncontrado.getPreco());
        }

        return livroEncontrado;
    }

    @Transactional
    public TradeResponseDto trade(TradeDto dto){
        var livroAdicionado = this.add(dto.getLivroRecebido(), true);
        var livroAtualizado = this.sell(dto.getLivroTrocadoId(), true);

        Transacao transacaoRecebido = new Transacao(null, TipoTransacao.TROCA_RECEBIDO, BigDecimal.ZERO, LocalDateTime.now(), livroAdicionado);
        Transacao transacaoOfertado = new Transacao(null, TipoTransacao.TROCA_TROCADO, BigDecimal.ZERO, LocalDateTime.now(), livroAtualizado);

        transacaoRepository.save(transacaoRecebido);
        transacaoRepository.save(transacaoOfertado);

        var livroAdicionadoResponse = new LivroMinimalResponseDto(livroAdicionado.getId(), livroAdicionado.getTitulo(), livroAdicionado.getPreco(), livroAdicionado.getQuantidade());
        var livroAtualizadoResponse = new LivroMinimalResponseDto(livroAtualizado.getId(), livroAtualizado.getTitulo(), livroAtualizado.getPreco(), livroAtualizado.getQuantidade());

        return new TradeResponseDto(livroAdicionadoResponse, livroAtualizadoResponse);
    }

    private void registrarTransacao(Livro livro, TipoTransacao tipoTransacao, BigDecimal valor){
        Transacao transacao = new Transacao(null, tipoTransacao, valor, LocalDateTime.now(), livro);
        transacaoRepository.save(transacao);
    }
}
