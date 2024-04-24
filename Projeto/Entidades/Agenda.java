package Entidades;

import java.sql.Date;

import Excecoes.DadosInvalidosException;
import java.util.Calendar;

public class Agenda {
    private Date data;
    private String servico;
    private String cliente;
    
    public Agenda(Date data, String  servico, String cliente) throws DadosInvalidosException{
        super();
        validarCampos(data, servico, cliente);
        this.data = data;
        this.servico = servico;
        this.cliente = cliente;
        System.out.println("Agendado com sucesso!");
    }

    private void validarCampos(Date data, String servico, String cliente) throws DadosInvalidosException {
        if (data == null) {
            throw new DadosInvalidosException("Data não pode estar vazia");
        }
    
        if (servico == null || servico.isEmpty()) {
            throw new DadosInvalidosException("Nome do serviço não pode estar vazio");
        }
    
        if (cliente == null || cliente.isEmpty()) {
            throw new DadosInvalidosException("Nome do cliente não pode estar vazio");
        }
    }


    public static void validarData(Date data) throws DadosInvalidosException {
        Calendar hoje = Calendar.getInstance();
        hoje.set(Calendar.HOUR_OF_DAY, 0);
        hoje.set(Calendar.MINUTE, 0);
        hoje.set(Calendar.SECOND, 0);
        hoje.set(Calendar.MILLISECOND, 0);

        Calendar dataAgendada = Calendar.getInstance();
        dataAgendada.setTime(data);

        if (dataAgendada.before(hoje)) {
            throw new DadosInvalidosException("Não é possível agendar para datas retroativas.");
        }
    }


    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String  getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    

    public Agenda() {
    }

    @Override
    public String toString() {
        return "Agenda {" +  "data =" + data +  ", servico =" + servico +  ", cliente =" + cliente +  '}';
    }
}

