package Projeto.Entidades;

public class Produto {
    private String nomeProduto;
    private String descricaoProduto;
    private double precoProduto;
    private int quantidadeProduto;

    public Produto(String nomeProduto, String descricaoProduto, double precoProduto, int quantidadeProduto) {
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.precoProduto = precoProduto;
        this.quantidadeProduto = quantidadeProduto;
    }

    public Produto(){}
    //Getters e setters

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
