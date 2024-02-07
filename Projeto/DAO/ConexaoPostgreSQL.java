package Projeto.DAO;

import java.sql.*;

public class ConexaoPostgreSQL {
        private static ConexaoPostgreSQL instancia;
        private Connection conexao;

        private final String url = "jdbc:postgresql://localhost:5432/OficinaBD";
        private final String usuario = "Iris";
        private final String senha = "123456";

    private ConexaoPostgreSQL(){

        try{
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão com o PostgreSQL estabelecida");
        }catch(SQLException e){
            System.err.println("Erro ao conectar: "+ e.getMessage());
        }
    }

    public static synchronized ConexaoPostgreSQL getInstancia(){
    if(instancia == null){
        instancia = new ConexaoPostgreSQL();
    }
    return instancia;
}

    public Connection getConexao(){
        return conexao;
    }

    public void fecharConexao(){
        try {
            conexao.close();
        } catch (SQLException e) {
            
        }
    }

}



