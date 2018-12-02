package com.example.brunofelipe.arguilebar.Activity;

import java.io.Serializable;

public class ModelItem implements Serializable {

    int idEssencia;
    String sabor;
    String marca;
    String preco;
    String categoria;
    String quantidade;
    String imageUrl;
    String descricao;

    public ModelItem(String marca, String preco){
        this.marca = marca;
        this.preco = preco;
    }

    public ModelItem(int idEssencia, String sabor, String marca, String preco, String categoria, String quantidade, String imageUrl, String descricao) {
        this.idEssencia = idEssencia;
        this.sabor = sabor;
        this.marca = marca;
        this.preco = preco;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.imageUrl = imageUrl;
        this.descricao = descricao;
    }

    public ModelItem(int idEssencia, String sabor, String marca, String preco) {
        this.idEssencia = idEssencia;
        this.sabor = sabor;
        this.marca = marca;
        this.preco = preco;
    }

    public ModelItem() {}

    public int getIdEssencia() {
        return idEssencia;
    }

    public void setIdEssencia(int idEssencia) {
        this.idEssencia = idEssencia;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}