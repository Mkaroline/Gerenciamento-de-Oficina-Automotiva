package Projeto.DAO;

import Projeto.Entidades.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioMecanicoDAO {
    private String sql;

    public void cadastrarMecanico(UsuarioMecanico mecanico) {
        Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
        sql = "INSERT INTO mecanicos (especialidade, nome_do_mecanico) VALUES (?,?)";
        try {
            PreparedStatement instrucao = connection.prepareStatement(sql);
            instrucao.setString(1, mecanico.getEspecialidadeMecanico());
            instrucao.setString(2, mecanico.getNome());
            instrucao.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void listarMecanicos() {
        Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
        sql = "SELECT * FROM mecanicos";
        PreparedStatement instrucao;
        try {
            instrucao = connection.prepareStatement(sql);
            ResultSet consulta = instrucao.executeQuery();
            while(consulta.next()){
                int id_mecanico = consulta.getInt("id_mecanico");
                String especialidade = consulta.getString("especialidade");
                String nome_do_mecanico = consulta.getString("nome_do_mecanico");
                System.out.println("DADOS DO MECÂNICO: ");
                System.out.println("ID do mecânico: "+id_mecanico);
                System.out.println("Nome: "+nome_do_mecanico);
                System.out.println("Especialdiade: "+especialidade);
                System.out.println("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }

    public void editarMecanico(UsuarioMecanico mecanico, String coluna, int idMecanico) {
        Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
        sql = "UPDATE mecanicos SET "+coluna+" = ? WHERE id_mecanico = ?";
        try {
            PreparedStatement instrucao = connection.prepareStatement(sql);
            if(coluna.equals("nome_do_mecanico")){
                instrucao.setString(1, mecanico.getNome());
                instrucao.setInt(2, idMecanico);
                instrucao.executeUpdate();
        } else if(coluna.equals("especialidade")){
            instrucao.setString(1, mecanico.getEspecialidadeMecanico());
                instrucao.setInt(2, idMecanico);
                instrucao.executeUpdate();
        } else {
            System.out.println("Coluna inexistente no banco de dados");
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarMecanico(int idMecanico) {
         Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
        sql = "DELETE FROM mecanicos WHERE id_mecanico = ?";
        try {
            PreparedStatement instrucao = connection.prepareStatement(sql);
            instrucao.setInt(1, idMecanico);
            instrucao.executeUpdate();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        
    }

}
