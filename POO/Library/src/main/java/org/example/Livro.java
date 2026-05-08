package org.example;

import java.time.LocalDate;

public class Livro {
    private int idLivro;
    private String autor;
    private String titulo;
    private String editora;
    private LocalDate dataPublicacao; // Tipo atualizado

    public Livro(String autor, String titulo, String editora, LocalDate dataPublicacao) {
        this.autor = autor;
        this.titulo = titulo;
        this.editora = editora;
        this.dataPublicacao = dataPublicacao;
    }

    public Livro(int idLivro, String autor, String titulo, String editora, LocalDate dataPublicacao) {
        this.idLivro = idLivro;
        this.autor = autor;
        this.titulo = titulo;
        this.editora = editora;
        this.dataPublicacao = dataPublicacao;
    }


    public int getIdLivro() { return idLivro; }
    public void setIdLivro(int idLivro) { this.idLivro = idLivro; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getEditora() { return editora; }
    public void setEditora(String editora) { this.editora = editora; }
    public LocalDate getDataPublicacao() { return dataPublicacao; }
    public void setDataPublicacao(LocalDate dataPublicacao) { this.dataPublicacao = dataPublicacao; }
}