package com.presto.sebo.controllers;

import com.presto.sebo.dto.LivroMinimalResponseDto;
import com.presto.sebo.dto.TradeDto;
import com.presto.sebo.entities.Livro;
import com.presto.sebo.services.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/livros")
public class LivroController {

    @Autowired
    LivroService livroService;

    @GetMapping
    public ResponseEntity<List<LivroMinimalResponseDto>> buscarTodos(){
        var lista = livroService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarUm(@PathVariable Long id){
        var livro = livroService.findOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(livro);
    }

    @GetMapping("/search")
    public ResponseEntity<List<LivroMinimalResponseDto>> procurar(@RequestParam String titulo) {
        var lista = livroService.searchByTitle(titulo);
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @PostMapping
    public ResponseEntity<LivroMinimalResponseDto> add(@RequestBody @Valid Livro livroRequest){
        var livro = livroService.add(livroRequest, false);
        var livroResponse = new LivroMinimalResponseDto(livro.getId(), livro.getTitulo(), livro.getPreco(), livro.getQuantidade());
        return ResponseEntity.status(HttpStatus.CREATED).body(livroResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroMinimalResponseDto> sell(@PathVariable Long id){
        var livro = livroService.sell(id, false);
        var livroResponse = new LivroMinimalResponseDto(livro.getId(), livro.getTitulo(), livro.getPreco(), livro.getQuantidade());
        return ResponseEntity.status(HttpStatus.OK).body(livroResponse);
    }

    @PostMapping("/trade")
    public ResponseEntity<?> trade(@RequestBody @Valid TradeDto dto){
        try {
            var dadosDaTroca = livroService.trade(dto);
            return ResponseEntity.status(HttpStatus.OK).body(dadosDaTroca);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
