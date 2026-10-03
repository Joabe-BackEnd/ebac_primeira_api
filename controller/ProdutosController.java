package com.homework.ebac.dev.controller;

import com.homework.ebac.dev.model.Produtos;
import com.homework.ebac.dev.service.ProdutosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    private ProdutosService produtosService;

    public ProdutosController(ProdutosService produtosService) {
        this.produtosService = produtosService;
    }

    @GetMapping
    public ResponseEntity<List<Produtos>> listarProdutos(){
        return ResponseEntity.ok(produtosService.listarProdutos());
    }

    @PostMapping("/salvar")
    public ResponseEntity<Produtos> salvarProduto(@RequestBody Produtos produtoNovo){
        return ResponseEntity.status(201).body(produtosService.salvarProduto(produtoNovo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produtos> buscarProdutoPorId(@PathVariable Integer id){
        return ResponseEntity.ok(produtosService.buscarProdutoPorId(id));
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<Produtos> atualizarProduto(@PathVariable Integer id,
                                                     @RequestBody Produtos produtoNovo){
        return ResponseEntity.accepted().body(produtosService.atualizarProduto(id, produtoNovo));
    }

    @DeleteMapping("/{id}")
    public void deletarProdutoPorId(@PathVariable Integer id){
        produtosService.deletarProdutoPorId(id);
        ResponseEntity.ok();
    }
}
