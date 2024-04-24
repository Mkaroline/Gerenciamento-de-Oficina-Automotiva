package Entidades;

import Excecoes.DadosInvalidosException;

public class Produto {
    private String nomeProduto;
    private String descricaoProduto;
    private double precoProduto;
    private int quantidadeProduto;

    public Produto(String nomeProduto, String descricaoProduto, double precoProduto, int quantidadeProduto)throws DadosInvalidosException {
        validarCampos(nomeProduto, descricaoProduto, precoProduto, quantidadeProduto);
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.precoProduto = precoProduto;
        this.quantidadeProduto = quantidadeProduto;
        System.out.println("Produto cadastrado com sucesso!");
    }

    // Método para validar campos
    private void validarCampos(String nomeProduto, String descricaoProduto, 
    double precoProduto, int quantidadeProduto) throws DadosInvalidosException {
        if (nomeProduto == null || nomeProduto.isEmpty()) {
            throw new DadosInvalidosException("Nome do produto não pode estar vazio");
        }

        if (descricaoProduto == null || descricaoProduto.isEmpty()) {
            throw new DadosInvalidosException("Descrição do produto não pode estar vazio");
        }

        if (precoProduto < 0) {
            throw new DadosInvalidosException("Preço tem que ser maior que zero");
        }

        if (quantidadeProduto <= 0) {
            throw new DadosInvalidosException("Quantidade tem que ser maior ou igual que zero");
        }
    
    }


    public Produto(){}
    // Getters e setters

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    @Override
    public String toString() {
        return "Nome: " + nomeProduto + ", Descrição: " + descricaoProduto + ", Preço: " + precoProduto + ", Quantidade: " + quantidadeProduto;
    }
}
