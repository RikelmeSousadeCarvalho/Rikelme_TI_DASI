package org.example;

import java.util.Date;

public class Exemplar {
    private int idExemplar;
    private int qntdDisponivel;
    private Livro livro;

    public Exemplar(int idExemplar, int qntdDisponivel) {
        this.idExemplar = idExemplar;
        this.qntdDisponivel = qntdDisponivel;
    }

    public Exemplar(int qntdDisponivel) {
        this.qntdDisponivel = qntdDisponivel;
    }

    public int getIdExemplar() {
        return idExemplar;
    }

    public void setIdExemplar(int idExemplar) {
        this.idExemplar = idExemplar;
    }

    public int getQntdDisponivel() {
        return qntdDisponivel;
    }

    public void setQntdDisponivel(int qntdDisponivel) {
        this.qntdDisponivel = qntdDisponivel;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
