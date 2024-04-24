package DAO;

import Entidades.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioClienteDAO {
    String sql;

    public void cadastrarCliente(UsuarioCliente cliente) {
        Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
        sql = "INSERT INTO clientes (endereco, telefone, veiculo, nome_do_cliente, cpf_do_cliente) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement instrucao = connection.prepareStatement(sql);
            instrucao.setString(1, cliente.getEnderecoCliente());
            instrucao.setString(2, cliente.getTelefoneCliente());
            instrucao.setString(3, cliente.getVeiculoDoCliente());
            instrucao.setString(4, cliente.getNome()); 
            instrucao.setString(5, cliente.getcpf());
            instrucao.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    public void listarClientes() {
        Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
       sql = "SELECT * FROM clientes";
       try {
        PreparedStatement instrucao = connection.prepareStatement(sql);
        ResultSet consulta = instrucao.executeQuery();
       boolean clientesEncontrados = false;
        while(consulta.next()){
            clientesEncontrados = true;
            String endereco = consulta.getString("endereco");
            String telefone = consulta.getString("telefone");
            String veiculo = consulta.getString("veiculo");
            String nome_do_cliente = consulta.getString("nome_do_cliente");
            String cpf_do_cliente = consulta.getString("cpf_do_cliente");
            System.out.println("DADOS DO CLIENTE");
            System.out.println("Nome do cliente: "+nome_do_cliente);
            System.out.println("CPF do cliente: "+cpf_do_cliente);
            System.out.println("Endereço: "+endereco);
            System.out.println("Telefone: "+telefone);
            System.out.println("Veiculo: "+veiculo);
            System.out.println("\n");

        }if(!clientesEncontrados){
            System.out.println("Nenhum cliente encontrado");
        }else{
            System.out.println("Todos os cliente previamente cadastrados foram listados com sucesso.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        }
    }

    public void editarCliente(UsuarioCliente cliente, String coluna, String cpfCliente) {
        Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
        sql = "UPDATE clientes SET "+coluna+" = ? WHERE cpf_do_cliente = ?";
        try {
            // Verificar se o CPF do cliente existe
            PreparedStatement verificaCpf = connection.prepareStatement("SELECT COUNT(*) AS total FROM clientes WHERE cpf_do_cliente = ?");
            verificaCpf.setString(1, cpfCliente);
            ResultSet resultado = verificaCpf.executeQuery();
            resultado.next();
            int total = resultado.getInt("total");
            if (total == 0) {
                System.out.println("\nNão foi possivel editar o cliente, pois não existe o CPF inserido\n");
                return;
            }
            
            // Atualizar o cliente
            PreparedStatement instrucao = connection.prepareStatement(sql);
            
            switch(coluna) {
                case "endereco":
                    instrucao.setString(1, cliente.getEnderecoCliente());
                    System.out.println("\nCampo endereço editado com sucesso\n");
                    break;
                case "telefone":
                    instrucao.setString(1, cliente.getTelefoneCliente());
                    System.out.println("\nCampo telefone editado com sucesso\n");
                    break;
                case "veiculo":
                    instrucao.setString(1, cliente.getVeiculoDoCliente());
                    System.out.println("\nCampo veículo editado com sucesso\n");
                    break;
                case "nome_do_cliente":
                    instrucao.setString(1, cliente.getNome());
                    System.out.println("\nCampo nome do cliente editado com sucesso\n");
                    break;
                default:
                    System.out.println("\nColuna inexistente no banco de dados\n");
                    return; 
            }
            
            instrucao.setString(2, cpfCliente);
            instrucao.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarCliente(String cpfCliente) throws SQLException { 
        Connection connection = null;
        PreparedStatement instrucao = null;
        try {
            connection = ConexaoPostgreSQL.getInstancia().getConexao();
            String sql = "DELETE FROM clientes WHERE cpf_do_cliente = ?";
            instrucao = connection.prepareStatement(sql);
            instrucao.setString(1, cpfCliente);
            int linhasAfetadas = instrucao.executeUpdate();
            if (linhasAfetadas == 0) {
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

