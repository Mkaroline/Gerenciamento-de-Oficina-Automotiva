package DAO;

import Entidades.*;

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
        sql = "INSERT INTO servicos (name_srvc,desc_srvc, mecanico_id, id_cliente, orcamento, status_Servico) VALUES (?,?,(SELECT id_mecanico FROM mecanicos WHERE nome_do_mecanico = ? LIMIT 1),(SELECT id_cliente FROM clientes WHERE nome_do_cliente = ? LIMIT 1),?,?)";
        
        try {
            PreparedStatement instrucao = connection.prepareStatement(sql);
            instrucao.setString(1, servico.getNomeServico());
            instrucao.setString(2, servico.getDescricaoServico());
            instrucao.setString(3, servico.getMecanicoResponsavel().getNome());
            instrucao.setString(4, servico.getCliente());
            instrucao.setDouble(5, servico.getOrcamentoServico());
            instrucao.setString(6, servico.getstatusServico());
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
            
            boolean servicosPreparados = false;
            
            while(consulta.next()) {
                servicosPreparados = true;
                int id_servico = consulta.getInt("id_servico");
                String name_srvc = consulta.getString("name_srvc");
                String desc_srvc = consulta.getString("desc_srvc");
                double orcamento = consulta.getDouble("orcamento");
                String status_Servico = consulta.getString("status_Servico");
                String especialidade = consulta.getString("especialidade");
                int id_mecanico = consulta.getInt("mecanico_id");
                String nome_do_mecanico = consulta.getString("nome_do_mecanico");
                int id_cliente = consulta.getInt("id_cliente");
                String endereco = consulta.getString("endereco");
                String telefone = consulta.getString("telefone");
                String veiculo = consulta.getString("veiculo");
                String nome_do_cliente = consulta.getString("nome_do_cliente");
                
                System.out.println("DADOS DO SERVIÇO: ");
                System.out.println("ID do serviço: " + id_servico);
                System.out.println("Nome do serviço: " + name_srvc);
                System.out.println("Descrição do serviço: " + desc_srvc);
                System.out.println("Orçamento do serviço: " + orcamento);
                System.out.println("Status do Serviço: " + status_Servico);
                System.out.println("\nDADOS DO MECÂNICO RELACIONADO AO SERVIÇO: ");
                System.out.println("ID do mecânico: " + id_mecanico);
                
                if(nome_do_mecanico != null){
                    System.out.println("Nome do mecânico: " + nome_do_mecanico);
                } else {
                    System.out.println("Este serviço não possui um mecânico cadastrado");
                }
                
                System.out.println("Especialidade do mecânico: " + especialidade);
                System.out.println("\nDADOS DO CLIENTE QUE SOLICITOU O SERVIÇO: ");
                System.out.println("ID do cliente: " + id_cliente);
                
                if(nome_do_cliente != null){
                    System.out.println("Nome do cliente: " + nome_do_cliente);
                } else {
                    System.out.println("Este serviço não possui um cliente cadastrado");
                }
                
                System.out.println("Endereço: " + endereco);
                System.out.println("Telefone: " + telefone);
                System.out.println("Veiculo: " + veiculo);
                System.out.println("\n");
            }
            
            if (!servicosPreparados) {
                System.out.println("Não há serviços cadastrados.");
            }else{
                System.out.println("\nTodos os serviços previamente cadastrados foram listados com sucesso.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void editarServico(Servico servico, String coluna, int idServico) {
        Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
        sql = "UPDATE servicos SET "+coluna+" = ? WHERE id_servico = ?";
        try {
            // Verificar se o ID do serviço existe
            PreparedStatement verificaId = connection.prepareStatement("SELECT COUNT(*) AS total FROM servicos WHERE id_servico = ?");
            verificaId.setInt(1, idServico);
            ResultSet resultado = verificaId.executeQuery();
            resultado.next();
            int total = resultado.getInt("total");
            if (total == 0) {
                System.out.println("\nNão foi possivel editar o serviço, pois não existe o ID inserido\n");
                return;
            }
            
            // Atualizar o serviço
            PreparedStatement instrucao = connection.prepareStatement(sql);
            
            switch(coluna) {
                case "name_srvc":
                    instrucao.setString(1, servico.getNomeServico());
                    System.out.println("\nCampo nome do serviço editado com sucesso\n");
                    break;
                case "desc_srvc":
                    instrucao.setString(1, servico.getDescricaoServico());
                    System.out.println("\nCampo descrição do serviço editado com sucesso\n");
                    break;
                case "orcamento":
                    instrucao.setDouble(1, servico.getOrcamentoServico());
                    System.out.println("\nCampo orçamento editado com sucesso\n");
                    break;
                case "status_Servico":
                    instrucao.setString(1, servico.getstatusServico());
                    System.out.println("\nCampo status do serviço editado com sucesso\n");
                    break;
                default:
                    System.out.println("\nColuna inexistente no banco de dados\n");
                    return; // Sai do método se a coluna não for reconhecida
            }
            
            instrucao.setInt(2, idServico);
            instrucao.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarServico(int idServico) throws SQLException {
        Connection connection = null;
        PreparedStatement instrucao = null;
        try {
            connection = ConexaoPostgreSQL.getInstancia().getConexao();
            String sql = "DELETE FROM servicos WHERE id_servico = ?";
            instrucao = connection.prepareStatement(sql);
            instrucao.setInt(1, idServico);
            int linhasAfetadas = instrucao.executeUpdate();
            if(linhasAfetadas == 0){
                throw new SQLException("Nenhum cliente encontrado com este CPF.");
            }
        } finally {
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

