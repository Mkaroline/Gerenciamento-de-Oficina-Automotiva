package DAO;

import java.sql.Connection;
import Entidades.*;
import java.util.Scanner;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AgendaDAO {
    String sql;
    Scanner entrada = new Scanner(System.in);

    public void cadastrarAgenda(Agenda agenda) {
        Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
        sql = "INSERT INTO agenda (data, servico, id_cliente) VALUES (?,?,(SELECT id_cliente FROM clientes WHERE nome_do_cliente = ? LIMIT 1))";

        try {
            PreparedStatement instrucao = connection.prepareStatement(sql);
            instrucao.setDate(1, agenda.getData());
            instrucao.setString(2, agenda.getServico());
            instrucao.setString(3, agenda.getCliente());
            instrucao.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void pesquisarNaAgenda(LocalDate data) {
        String sql = "SELECT a.data, a.servico, c.id_cliente, c.nome_do_cliente, c.endereco, c.telefone, c.veiculo FROM agenda a INNER JOIN clientes c ON a.id_cliente = c.id_cliente WHERE a.data = ?";
        Connection connection = null;
        PreparedStatement instrucao = null;
        ResultSet consulta = null;
        try {
            connection = ConexaoPostgreSQL.getInstancia().getConexao();
            instrucao = connection.prepareStatement(sql);
            instrucao.setDate(1, java.sql.Date.valueOf(data));
            consulta = instrucao.executeQuery();
            
            boolean encontrouCompromissos = false; 
    
            while (consulta.next()) {
                encontrouCompromissos = true; 
                LocalDate dataEvento = consulta.getDate("data").toLocalDate();
                String servico = consulta.getString("servico");
                int id_cliente = consulta.getInt("id_cliente");
                String endereco = consulta.getString("endereco");
                String telefone = consulta.getString("telefone");
                String veiculo = consulta.getString("veiculo");
                String nome_do_cliente = consulta.getString("nome_do_cliente");
                System.out.println("\nAgendamento: ");
                System.out.println("Data: " + dataEvento);
                System.out.println("Serviço: " + servico);
                System.out.println("\nDados do cliente que agendou o serviço: ");
                System.out.println("Id do Cliente: " + id_cliente);
                    if(nome_do_cliente != null){
                         System.out.println("Nome do cliente: "+nome_do_cliente);
                    } else {
                    System.out.println("Este serviço não possui um cliente cadastrado");
                    }
                System.out.println("Endereço: "+endereco);
                System.out.println("Telefone: "+telefone);
                System.out.println("Veiculo: "+veiculo);
                System.out.println("\n");
                System.out.println();
            }
    
            if (!encontrouCompromissos) {
                System.out.println("Não há compromissos agendados para a data informada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (consulta != null) {
                try {
                    consulta.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (instrucao != null) {
                try {
                    instrucao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            
        }
    }

    public void listarAgenda(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio.isAfter(dataFim)) {
            System.out.println("A data de início deve ser anterior à data de fim.");
            return;
        }
        
        String sql = "SELECT a.data, a.servico, c.id_cliente, c.nome_do_cliente, c.endereco, c.telefone, c.veiculo FROM agenda a INNER JOIN clientes c ON a.id_cliente = c.id_cliente WHERE a.data BETWEEN ? AND ?";
        Connection connection = null;
        PreparedStatement instrucao = null;
        ResultSet consulta = null;
    
        try {
            connection = ConexaoPostgreSQL.getInstancia().getConexao();
            instrucao = connection.prepareStatement(sql);
            instrucao.setDate(1, java.sql.Date.valueOf(dataInicio));
            instrucao.setDate(2, java.sql.Date.valueOf(dataFim));
            consulta = instrucao.executeQuery();
    
            boolean encontrouCompromissos = false;
    
            while (consulta.next()) {
                encontrouCompromissos = true;
                LocalDate dataEvento = consulta.getDate("data").toLocalDate();
                String servico = consulta.getString("servico");
                int id_cliente = consulta.getInt("id_cliente");
                String endereco = consulta.getString("endereco");
                String telefone = consulta.getString("telefone");
                String veiculo = consulta.getString("veiculo");
                String nome_do_cliente = consulta.getString("nome_do_cliente");
                System.out.println("\nAgendamento: ");
                System.out.println("Data: " + dataEvento);
                System.out.println("Serviço: " + servico);
                System.out.println("\nDados do cliente que agendou o serviço: ");
                System.out.println("Id do Cliente: " + id_cliente);
                if (nome_do_cliente != null) {
                    System.out.println("Nome do cliente: " + nome_do_cliente);
                } else {
                    System.out.println("Este serviço não possui um cliente cadastrado");
                }
                System.out.println("Endereço: " + endereco);
                System.out.println("Telefone: " + telefone);
                System.out.println("Veiculo: " + veiculo);
                System.out.println("\n");
            }
    
            if (!encontrouCompromissos) {
                System.out.println("Não há compromissos agendados para o período informado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (consulta != null) {
                try {
                    consulta.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (instrucao != null) {
                try {
                    instrucao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
