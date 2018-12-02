package com.example.brunofelipe.arguilebar.Activity;

import java.io.Serializable;

public class ModelItemBebida implements Serializable {

    int idBebida;
    String marcaBebida;
    String precoBebida;
    String quantidadeBebida;
    String imageUrlBebida;

    public ModelItemBebida(String marcaBebida, String precoBebida){
        this.marcaBebida=marcaBebida;
        this.precoBebida=precoBebida;
    }

    public ModelItemBebida(int idBebida, String marcaBebida, String precoBebida, String quantidadeBebida, String imageUrlBebida) {
        this.idBebida = idBebida;
        this.marcaBebida = marcaBebida;
        this.precoBebida = precoBebida;
        this.quantidadeBebida = quantidadeBebida;
        this.imageUrlBebida = imageUrlBebida;
    }

    public ModelItemBebida(int idBebida, String marcaBebida, String precoBebida) {
        this.idBebida = idBebida;
        this.marcaBebida = marcaBebida;
        this.precoBebida = precoBebida;

    }


    public ModelItemBebida() {}

    public int getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(int idBebida) {
        this.idBebida = idBebida;
    }

    public String getMarcaBebida() {
        return marcaBebida;
    }

    public void setMarcaBebida(String marcaBebida) {
        this.marcaBebida = marcaBebida;
    }

    public String getPrecoBebida() {
        return precoBebida;
    }

    public void setPrecoBebida(String precoBebida) {
        this.precoBebida = precoBebida;
    }

    public String getQuantidadeBebida() {
        return quantidadeBebida;
    }

    public void setQuantidadeBebida(String quantidadeBebida) {
        this.quantidadeBebida = quantidadeBebida;
    }

    public String getImageUrlBebida() {
        return imageUrlBebida;
    }

    public void setImageUrlBebida(String imageUrlBebida) {
        this.imageUrlBebida = imageUrlBebida;
    }
}