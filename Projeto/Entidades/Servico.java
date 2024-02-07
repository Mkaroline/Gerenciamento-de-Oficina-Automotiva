package Projeto.Entidades;

import java.util.ArrayList;
import java.util.List;

public class Servico {
    private String nomeServico;
    private String descricaoServico;
    private List<Produto> pecasUtilizadasnoServico;
    private UsuarioMecanico mecanicoResponsavel;    //Mecanico responsavel
    private Double orcamentoServico;
    private String cliente;

    public Servico(String nomeServico, String descricaoServico, String pecasUtilizadas, UsuarioMecanico mecanicoResponsavel, Double orcamentoServico, String cliente){
        this.nomeServico = nomeServico;
        this.descricaoServico = descricaoServico;
        this.mecanicoResponsavel = mecanicoResponsavel;
        this.orcamentoServico = orcamentoServico;
        this.pecasUtilizadasnoServico = new ArrayList<>();  //Inicializa a lista de peças utilizadas.
        this.cliente = cliente;
    }

    public Servico() {

    }

    //Getters e setters para os atributos existentes...

    public Servico(String nomeDoServico, String descServico, UsuarioMecanico auxMecanico, double orcamento,
            String nomeDoCliente) {
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public String getCliente(){
        return cliente;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public List<Produto> getPecasUtilizadas() {
        return pecasUtilizadasnoServico;
    }

    public void setPecasUtilizadas(List<Produto> pecasUtilizadasnoServico) {
        this.pecasUtilizadasnoServico = pecasUtilizadasnoServico;
    }

    public  UsuarioMecanico getMecanicoResponsavel() {
        return mecanicoResponsavel;
    }

    public void setMecanicoResponsavel(UsuarioMecanico mecanicoResponsavel) {
        this.mecanicoResponsavel = mecanicoResponsavel;
    }

    public Double getOrcamentoServico() {
        return orcamentoServico;
    }

    public void setOrcamentoServico(Double orcamentoServico) {
        this.orcamentoServico = orcamentoServico;
    }

    //Metodo para adicionar uma peça a lista de peças utilizadas
    public void adicionarPecaUtilizada(Produto peca){
        pecasUtilizadasnoServico.add(peca);
    }

    //Metodo para remover uma peça da lista de peças utilizadas
    public void removerPecaUtilizada(Produto peca){
        pecasUtilizadasnoServico.remove(peca);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(nomeServico).append("\n");
        sb.append("Descrição: ").append(descricaoServico).append("\n");
        sb.append("Mecânico Responsável: ").append(mecanicoResponsavel.getNome()).append("\n");
        sb.append("Orçamento: ").append(orcamentoServico).append("\n");
        
        sb.append("Peças Utilizadas no Serviço:\n");
        for (Produto peca : pecasUtilizadasnoServico) {
            sb.append("- ").append(peca.getNomeProduto()).append("\n");
        }
        
        return sb.toString();
    }
}