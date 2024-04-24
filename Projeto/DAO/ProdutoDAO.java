package DAO;

import Entidades.*;
import java.sql.*;
import java.util.Scanner;

public class ProdutoDAO {
    String sql;
    Scanner entrada = new Scanner(System.in);


    public void cadastrarProduto(Produto produto) {
        Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
        sql = "INSERT INTO produtos (nome_produto,descricao_prod, preco_prod, quantidade) VALUES (?,?,?,?)";

        try {
            PreparedStatement instrucao = connection.prepareStatement(sql);
            instrucao.setString(1, produto.getNomeProduto());
            instrucao.setString(2, produto.getDescricaoProduto());
            instrucao.setDouble(3, produto.getPrecoProduto());
            instrucao.setInt(4, produto.getQuantidadeProduto());
            instrucao.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarProdutos() {
        Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
        sql = "SELECT * FROM produtos";
        try {
            PreparedStatement instrucao = connection.prepareStatement(sql);
            ResultSet consulta = instrucao.executeQuery();
            boolean produtosEncontrados = false; // Flag para verificar se há produtos para listar
            while (consulta.next()) {
                produtosEncontrados = true; // Há pelo menos um produto para listar
                int id_produto = consulta.getInt("id_produto");
                String nome_produto = consulta.getString("nome_produto");
                String desc_prod = consulta.getString("descricao_prod");
                double preco_prod = consulta.getDouble("preco_prod");
                int quantidade = consulta.getInt("quantidade");
                System.out.println("ID do produto: " + id_produto + "\nNome do produto: " + nome_produto + "\nDescrição do produto: " + desc_prod + "\nPreço do produto: " + preco_prod + "\nQuantidade em estoque: " + quantidade);
                System.out.println("\n\n");
            }
            if (!produtosEncontrados) {
                System.out.println("\nNenhum produto encontrado.");
            }else{
                System.out.println("\nTodos os produtos previamente cadastrados foram listados com sucesso.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarProduto(Produto produto, String coluna, int idProduto) {
        Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
        sql = "UPDATE produtos SET "+coluna+" = ? WHERE id_produto = ?";
        try {
            // Verificar se o ID do produto existe
            PreparedStatement verificaId = connection.prepareStatement("SELECT COUNT(*) AS total FROM produtos WHERE id_produto = ?");
            verificaId.setInt(1, idProduto);
            ResultSet resultado = verificaId.executeQuery();
            resultado.next();
            int total = resultado.getInt("total");
            if (total == 0) {
                System.out.println("\nNão foi possivel editar o produto, pois não existe o ID inserido\n");
                return;
            }
            PreparedStatement instrucao = connection.prepareStatement(sql);
            
            switch(coluna) {
                case "nome_produto":
                    instrucao.setString(1, produto.getNomeProduto());
                    System.out.println("\nCampo nome editado com sucesso\n");
                    break;
                case "descricao_prod":
                    instrucao.setString(1, produto.getDescricaoProduto());
                    System.out.println("\nCampo descrição editado com sucesso\n");
                    break;
                case "preco_prod":
                    instrucao.setDouble(1, produto.getPrecoProduto());
                    System.out.println("\nCampo preço editado com sucesso\n");
                    break;
                case "quantidade":
                    instrucao.setInt(1, produto.getQuantidadeProduto());
                    System.out.println("\nCampo quantidade editado com sucesso\n");
                    break;
                default:
                    System.out.println("\nColuna inexistente no banco de dados\n");
                    return; 
            }
            
            instrucao.setInt(2, idProduto);
            instrucao.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarProduto(int idProduto) throws SQLException {
        Connection connection = null;
        PreparedStatement instrucao = null;
        try {
            connection = ConexaoPostgreSQL.getInstancia().getConexao();
            String sql = "DELETE FROM produtos WHERE id_produto = ?";
            instrucao = connection.prepareStatement(sql);
            instrucao.setInt(1, idProduto);
            int linhasAfetadas = instrucao.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new SQLException("Nenhum produto encontrado com este ID.");
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
