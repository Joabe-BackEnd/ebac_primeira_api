package com.homework.ebac.dev.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produtos {

    private Integer id;
    private String nome;
    private double preco;
}