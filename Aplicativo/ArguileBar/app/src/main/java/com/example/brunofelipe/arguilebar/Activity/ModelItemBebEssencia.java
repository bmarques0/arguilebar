package com.example.brunofelipe.arguilebar.Activity;

import java.io.Serializable;

public class ModelItemBebEssencia implements Serializable {

    String marca;
    String preco;

    public ModelItemBebEssencia(String marca, String preco){
        this.marca = marca;
        this.preco = preco;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}