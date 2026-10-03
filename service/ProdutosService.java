package com.homework.ebac.dev.service;

import com.homework.ebac.dev.exceptions.ProdutosException;
import com.homework.ebac.dev.model.Produtos;
import com.homework.ebac.dev.repository.ProdutosRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProdutosService {

    private ProdutosRepository produtosRepository;

    public ProdutosService(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }


    public Produtos salvarProduto(Produtos produtoNovo){
        List<Produtos> produtos = produtosRepository.findAll();
        for (Produtos p : produtos){
            if (p.getId().equals(produtoNovo.getId())){
                throw new ProdutosException("Produto com id já existente");
            }else if (p.getNome().equals(produtoNovo.getNome())){
                throw new ProdutosException("Produto com nome já existente");
            }
        }
        return produtosRepository.save(produtoNovo);
    }

    public List<Produtos> listarProdutos(){
        return produtosRepository.findAll();
    }

    public Produtos buscarProdutoPorId(Integer id){
        return produtosRepository.findById(id);
    }

    public Produtos atualizarProduto(Integer id,
                                     Produtos produtoNovo){
        List<Produtos> produtos = produtosRepository.findAll();
        for (Produtos p : produtos){
            if (p.getId().equals(id)){
                Integer idSalvo = p.getId();
                produtosRepository.deleteById(id);
                Produtos p1 = new Produtos(idSalvo,produtoNovo.getNome(),produtoNovo.getPreco());
                return produtosRepository.save(p1);
            }
        }
        throw new ProdutosException("Produto não encontrado!");
    }

    public void deletarProdutoPorId(Integer id){
        List<Produtos> produtos = produtosRepository.findAll();
        for (Produtos p : produtos){
            if (p.getId().equals(id)){
                produtosRepository.deleteById(id);
                return;
            }
        }
        throw new ProdutosException("Produto não encontrado!");
    }
}
