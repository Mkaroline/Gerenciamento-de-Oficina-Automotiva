package Entidades;

import Excecoes.DadosInvalidosException;

public class Servico {
    private String nomeServico;
    private String descricaoServico;
    private UsuarioMecanico mecanicoResponsavel; // Mecânico responsável
    private double orcamentoServico;
    private String cliente;
    private String statusServico;



    public Servico(String nomeServico, String descricaoServico, UsuarioMecanico mecanicoResponsavel, double orcamentoServico, String cliente, String statusServico) throws DadosInvalidosException {
        validarCampos(nomeServico, descricaoServico, descricaoServico, orcamentoServico, cliente, statusServico);
        this.nomeServico = nomeServico;
        this.descricaoServico = descricaoServico;
        this.mecanicoResponsavel = mecanicoResponsavel;
        this.orcamentoServico = orcamentoServico;
        this.cliente = cliente;
        this.statusServico= statusServico;
        System.out.println("Serviço cadastrado com sucesso!");
    }

    private void validarCampos(String nomeServico, String descricaoServiço, String mecanicorResponsavel, 
            Double orcamentoServico, String cliente, String statusServico) throws DadosInvalidosException {
        if (nomeServico == null || nomeServico.isEmpty()) {
            throw new DadosInvalidosException("Nome do serviço não pode estar vazio");
        }

        if (descricaoServiço == null || descricaoServiço.isEmpty()) {
            throw new DadosInvalidosException("Descrição não pode estar vazio");
        }

        if (mecanicorResponsavel == null || mecanicorResponsavel.isEmpty()) {
            throw new DadosInvalidosException("Especialidade não pode estar vazio");
        }
        
        if (orcamentoServico == null) {
            throw new DadosInvalidosException("Orçam não pode estar vazio");
        }

        if (cliente == null || cliente.isEmpty()) {
            throw new DadosInvalidosException("Cliente não pode estar vazio");
        }

        if (statusServico == null || statusServico.isEmpty()) {
            throw new DadosInvalidosException("Status do serviço não pode estar vazio");
        }
    }

    public Servico(){
        
    }

    // Getters e setters para os atributos existentes...

    public String getNomeServico() {
        return nomeServico;
    }

    public String getCliente(){
        return cliente;
    }

    public String getstatusServico(){
        return statusServico;
    }

    public void setstatusServico(String statusServico){
        this.statusServico = statusServico;
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


    public UsuarioMecanico getMecanicoResponsavel() {
        return mecanicoResponsavel;
    }

    public void setMecanicoResponsavel(UsuarioMecanico mecanicoResponsavel) {
        this.mecanicoResponsavel = mecanicoResponsavel;
    }

    public double getOrcamentoServico() {
        return orcamentoServico;
    }

    public void setOrcamentoServico(double orcamentoServico) {
        this.orcamentoServico = orcamentoServico;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(nomeServico).append("\n");
        sb.append("Descrição: ").append(descricaoServico).append("\n");
        sb.append("Mecânico Responsável: ").append(mecanicoResponsavel.getNome()).append("\n");
        sb.append("Orçamento: ").append(orcamentoServico).append("\n");
        
        
        
        return sb.toString();
    }
}