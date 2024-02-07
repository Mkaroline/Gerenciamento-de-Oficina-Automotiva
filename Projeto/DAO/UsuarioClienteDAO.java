package Projeto.DAO;

import Projeto.Entidades.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioClienteDAO {
    String sql;

    public void cadastrarCliente(UsuarioCliente cliente) {
        Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
        sql = "INSERT INTO clientes (endereco, telefone, veiculo, nome_do_cliente) VALUES (?,?,?,?)";
        try {
            PreparedStatement instrucao = connection.prepareStatement(sql);
            instrucao.setString(1, cliente.getEnderecoCliente());
            instrucao.setString(2, cliente.getTelefoneCliente());
            instrucao.setString(3, cliente.getVeiculoDoCliente());
            instrucao.setString(4, cliente.getNome());  
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
        while(consulta.next()){
            String endereco = consulta.getString("endereco");
            String telefone = consulta.getString("telefone");
            String veiculo = consulta.getString("veiculo");
            String nome_do_cliente = consulta.getString("nome_do_cliente");
            System.out.println("DADOS DO CLIENTE");
            System.out.println("Nome do cliente: "+nome_do_cliente);
            System.out.println("Endereço: "+endereco);
            System.out.println("Telefone: "+telefone);
            System.out.println("Veiculo: "+veiculo);
            System.out.println("\n");

        }
    } catch (SQLException e) {
        e.printStackTrace();
        }
    }

    public void editarCliente(UsuarioCliente cliente, String coluna, int idCliente) {
        Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
        String sql = "UPDATE clientes SET "+coluna+" = ? WHERE id_cliente = ?";
        try {
            PreparedStatement instrucao = connection.prepareStatement(sql);
            if(coluna.equals("endereco")){
                instrucao.setString(1, cliente.getEnderecoCliente());
                instrucao.setInt(2, idCliente);
                instrucao.executeUpdate();
            } else if (coluna.equals("telefone")){
                instrucao.setString(1, cliente.getTelefoneCliente());
                instrucao.setInt(2, idCliente);
                instrucao.executeUpdate();
            } else if (coluna.equals("veiculo")){
                instrucao.setString(1, cliente.getVeiculoDoCliente());
                instrucao.setInt(2, idCliente);
                instrucao.executeUpdate();
            } else if (coluna.equals("nome_do_cliente")){
                instrucao.setString(1, cliente.getNome());
                instrucao.setInt(2, idCliente);
                instrucao.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarCliente(int idCliente) {
        Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        try {
            PreparedStatement instrucao = connection.prepareStatement(sql);
            instrucao.setInt(1, idCliente);
            instrucao.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
