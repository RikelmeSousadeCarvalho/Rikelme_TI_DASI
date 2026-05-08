package org.example;

public class Usuario {
    private int idUsuario;
    private String nome;
    private String email;
    private String telefone;
    private String clb;
    private int idade;

    public Usuario(String nome, String clb, String email, String telefone, int idade) {
        this.nome = nome;
        this.clb = clb;
        this.email = email;
        this.telefone = telefone;
        this.idade = idade;
    }

    public Usuario(int idUsuario, String nome, String clb, String email, String telefone, int idade) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.clb = clb;
        this.email = email;
        this.telefone = telefone;
        this.idade = idade;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getClb() {
        return clb;
    }

    public void setClb(String clb) {
        this.clb = clb;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
