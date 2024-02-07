package Projeto.Entidades;

abstract class Usuario {
    private String nome;

    //Construtor
    public Usuario(String nome) {
        this.nome = nome;
    }

    public Usuario(){

    }

    //Getters e setters para nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        this.nome=nome;
    }

    @Override
    public String toString(){
        return ", Nome: " + nome;
    }
}
