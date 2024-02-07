package Projeto.DAO;

import Projeto.Entidades.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ServicoDAO {
    String sql;
    Scanner entrada = new Scanner(System.in);

    public void cadastrarServico(Servico servico) {
        Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
        sql = "INSERT INTO servicos (name_srvc,desc_srvc, mecanico_id, id_cliente, orcamento) VALUES (?,?,(SELECT id_mecanico FROM mecanicos WHERE nome_do_mecanico = ? LIMIT 1),(SELECT id_cliente FROM clientes WHERE nome_do_cliente = ? LIMIT 1),?)";
        
        try {
            PreparedStatement instrucao = connection.prepareStatement(sql);
            instrucao.setString(1, servico.getNomeServico());
            instrucao.setString(2, servico.getDescricaoServico());
            instrucao.setString(3, servico.getMecanicoResponsavel().getNome());
            instrucao.setString(4, servico.getCliente());
            instrucao.setDouble(5, servico.getOrcamentoServico());
            instrucao.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarServicos() {
       Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
       sql = "SELECT servicos.*, mecanicos.nome_do_mecanico AS nome_do_mecanico, mecanicos.especialidade, clientes.nome_do_cliente AS nome_do_cliente, clientes.endereco,clientes.telefone,clientes.veiculo, clientes.nome_do_cliente FROM servicos INNER JOIN mecanicos ON servicos.mecanico_id = mecanicos.id_mecanico INNER JOIN clientes ON servicos.id_cliente = clientes.id_cliente";
        
        try {
            PreparedStatement instrucao;
            instrucao = connection.prepareStatement(sql);
            ResultSet consulta = instrucao.executeQuery();
            while(consulta.next()){
            int id_servico = consulta.getInt("id_servico");
            String name_srvc = consulta.getString("name_srvc");
            String desc_srvc = consulta.getString("desc_srvc");
            String especialidade = consulta.getString("especialidade");
            int id_mecanico = consulta.getInt("mecanico_id");
            String nome_do_mecanico = consulta.getString("nome_do_mecanico");
            int id_cliente = consulta.getInt("id_cliente");
            String endereco = consulta.getString("endereco");
            String telefone = consulta.getString("telefone");
            String veiculo = consulta.getString("veiculo");
            String nome_do_cliente = consulta.getString("nome_do_cliente");
            
            System.out.println("DADOS DO SERVIÇO: ");
            System.out.println("ID do serviço: "+id_servico);
            System.out.println("Nome do serviço: "+name_srvc);
            System.out.println("Descrição do serviço: "+desc_srvc);
            System.out.println("\nDADOS DO MECÂNICO RELACIONADO AO SERVIÇO: ");
            System.out.println("ID do mecânico: "+id_mecanico);
            if(nome_do_mecanico != null){
                System.out.println("Nome do mecânico: "+nome_do_mecanico);
            } else {
                System.out.println("Este serviço não possui um mecânico cadastrado");
            }
            
            System.out.println("Especialidade do mecânico: "+especialidade);
            System.out.println("\nDADOS DO CLIENTE QUE SOLICITOU O SERVIÇO: ");
            System.out.println("ID do cliente: "+id_cliente);
            
            if(nome_do_cliente != null){
                System.out.println("Nome do cliente: "+nome_do_cliente);
            } else {
                System.out.println("Este serviço não possui um mecânico cadastrado");
            }
            System.out.println("Endereço: "+endereco);
            System.out.println("Telefone: "+telefone);
            System.out.println("Veiculo: "+veiculo);
            System.out.println("\n");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    public void editarServico(Servico servico,String coluna, int idServico) {
        Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
        sql = "UPDATE servicos SET "+coluna+" = ? WHERE id_servico = ?";
        try {
            PreparedStatement instrucao = connection.prepareStatement(sql);
            if(coluna.equals("name_srvc")){
                instrucao.setString(1, servico.getNomeServico());
                instrucao.setInt(2, idServico);
                instrucao.executeUpdate();
            } else if(coluna.equals("desc_srvc")){
                instrucao.setString(1, servico.getDescricaoServico());
                instrucao.setInt(2, idServico);
                instrucao.executeUpdate();
            } else if(coluna.equals("orcamento")){
                instrucao.setDouble(1, servico.getOrcamentoServico());
                instrucao.setInt(2, idServico);
                instrucao.executeUpdate();
            } else {
                System.out.println("Coluna inexistente no banco de dados");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarServico(int idServico) {
        Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
        sql = "DELETE FROM servicos WHERE id_servico = ?";
        try {
            PreparedStatement instrucao = connection.prepareStatement(sql);
            instrucao.setInt(1, idServico);
            instrucao.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
