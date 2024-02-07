package Projeto.DAO;

import Projeto.Entidades.*;
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
        while(consulta.next()){
            int id_produto = consulta.getInt("id_produto");
            String nome_produto = consulta.getString("nome_produto");
            String desc_prod = consulta.getString("descricao_prod");
            double preco_prod = consulta.getDouble("preco_prod");
            int quantidade = consulta.getInt("quantidade");
            System.out.println("ID do produto: "+id_produto+"\nNome do produto: "+nome_produto+"\nDescrição do produto: "+desc_prod+"\nPreco do produto: "+preco_prod+"\nQuantidade em estoque: "+quantidade);
            System.out.println("\n\n");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public void editarProduto(Produto produto, String coluna, int idProduto) {
        Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
        sql = "UPDATE produtos SET "+coluna+" = ? WHERE id_produto = ?";
        try {
            PreparedStatement instrucao = connection.prepareStatement(sql);
            if(coluna.equals("nome_produto")){
                instrucao.setString(1, produto.getNomeProduto());
                instrucao.setInt(2, idProduto);
                instrucao.executeUpdate();
        } else if(coluna.equals("descricao_prod")){
            instrucao.setString(1, produto.getDescricaoProduto());
            instrucao.setInt(2, idProduto);
            instrucao.executeUpdate();
        } else if (coluna.equals("preco_prod")){
            instrucao.setDouble(1, produto.getPrecoProduto());
            instrucao.setInt(2, idProduto);
            instrucao.executeUpdate();
        } else if(coluna.equals("quantidade")){
            instrucao.setInt(1, produto.getQuantidadeProduto());
            instrucao.setInt(2, idProduto);
            instrucao.executeUpdate();
        } else {
            System.out.println("Coluna inexistente no banco de dados");
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        

    }

    public void deletarProduto(int idProduto) {
       Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
       sql = "DELETE FROM produtos WHERE id_produto = ?";
       try {
        PreparedStatement instrucao = connection.prepareStatement(sql);
        instrucao.setInt(1, idProduto);
        instrucao.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

}
