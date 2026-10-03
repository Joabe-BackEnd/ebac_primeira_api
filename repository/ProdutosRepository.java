package com.homework.ebac.dev.repository;

import com.homework.ebac.dev.exceptions.ProdutosException;
import com.homework.ebac.dev.exceptions.ProdutosExceptionHandler;
import com.homework.ebac.dev.model.Produtos;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProdutosRepository {

    private final String caminhoArquivo = System.getenv("CAMINHO");

    public Produtos save(Produtos produto){
        try {

            BufferedWriter writer = new BufferedWriter( new FileWriter(caminhoArquivo, true));

            writer.write( produto.getId() + "|" + produto.getNome() + "|" + produto.getPreco());

            writer.newLine();
            writer.close();

            return produto;

        }catch (Exception e){
            throw new ProdutosException("Erro ao salvar cliente");
        }
    }

    // BUSCAR TODOS OS CLIENTES
    public List<Produtos> findAll(){
        List<Produtos> produtos = new ArrayList<>();

        File arquivo = new File(caminhoArquivo);

        if (!arquivo.exists()) {
            return produtos;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));

            String linha;

            while((linha = reader.readLine()) != null){

                String[] dados = linha.split("\\|");
                Produtos produtoExistente = new Produtos();
                produtoExistente.setId(Integer.parseInt(dados[0]));
                produtoExistente.setNome(dados[1]);
                produtoExistente.setPreco(Double.parseDouble(dados[2]));

                produtos.add(produtoExistente);
            }
        }catch (IOException e){
            throw new RuntimeException("Erro ao ler clientes", e);
        }

        return produtos;
    }

    // BUSCAR UM CLIENTE PELO ID
    public Produtos findById(Integer id) {
        List<Produtos> produtos = findAll();
        for (Produtos produto : produtos) {
            if (produto.getId().equals(id)) {
                return produto; }
        }
        throw new ProdutosException("Produto não encontrado!");
    }

    //DELETAR UM CLIENTE PELO ID
    public void deleteById(Integer id){
        List<Produtos> produtos = findAll();

        boolean removido = produtos.removeIf(produto -> produto.getId().equals(id));

        if (!removido){
            System.out.println("Não existe cliente com esse id.");
            return;
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo));

            for (Produtos produto: produtos){
                writer.write(produto.getId() + "|" + produto.getNome() + "|" + produto.getPreco());
                writer.newLine();
            }
            writer.close();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar produto", e);
        }
    }

}
