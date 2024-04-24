package DAO;

import Entidades.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioMecanicoDAO {
    private String sql;

    public void cadastrarMecanico(UsuarioMecanico mecanico) {
        Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
        sql = "INSERT INTO mecanicos (especialidade, nome_do_mecanico, cpf_do_mecanico) VALUES (?,?, ?)";
        try {
            PreparedStatement instrucao = connection.prepareStatement(sql);
            instrucao.setString(1, mecanico.getEspecialidadeMecanico());
            instrucao.setString(2, mecanico.getNome());
            instrucao.setString(3, mecanico.getcpf());
            instrucao.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void listarMecanicos() {
        Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
        sql = "SELECT * FROM mecanicos";

        try {
            boolean mecanicosEncontrados = false;
            PreparedStatement instrucao = connection.prepareStatement(sql);
            ResultSet consulta = instrucao.executeQuery();
            while(consulta.next()){
                mecanicosEncontrados = true;
                int id_mecanico = consulta.getInt("id_mecanico");
                String nome_do_mecanico = consulta.getString("nome_do_mecanico");
                String cpf_do_mecanico = consulta.getString("cpf_do_mecanico");
                String especialidade = consulta.getString("especialidade");
                System.out.println("DADOS DO MECÂNICO: ");
                System.out.println("ID do mecânico: "+id_mecanico);
                System.out.println("Nome: "+nome_do_mecanico);
                System.out.println("CPF: "+cpf_do_mecanico);
                System.out.println("Especialdiade: "+especialidade);

                System.out.println("\n");
            }
            if(!mecanicosEncontrados){
                System.out.println("\n Nenhum mecanico encontrado. ");
            }else{
                System.out.println("\n Todos os mecanicos previamente cadastrados foram listados com sucesso. ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarMecanico(UsuarioMecanico mecanico, String coluna, String cpfMecanico) {
        Connection connection = ConexaoPostgreSQL.getInstancia().getConexao();
        sql = "UPDATE mecanicos SET "+coluna+" = ? WHERE cpf_do_mecanico = ?";
        try {
            // Verificar se o CPF do mecânico existe
            PreparedStatement verificaCpf = connection.prepareStatement("SELECT COUNT(*) AS total FROM mecanicos WHERE cpf_do_mecanico = ?");
            verificaCpf.setString(1, cpfMecanico);
            ResultSet resultado = verificaCpf.executeQuery();
            resultado.next();
            int total = resultado.getInt("total");
            if (total == 0) {
                System.out.println("\nNão foi possivel editar o mecanico, pois não existe o CPF inserido\n");
                return;
            }
            
            // Atualizar o mecânico
            PreparedStatement instrucao = connection.prepareStatement(sql);
            
            switch(coluna) {
                case "nome_do_mecanico":
                    instrucao.setString(1, mecanico.getNome());
                    System.out.println("\nCampo nome editado com sucesso\n");
                    break;
                case "especialidade":
                    instrucao.setString(1, mecanico.getEspecialidadeMecanico());
                    System.out.println("\nCampo especialidade editado com sucesso\n");
                    break;
                default:
                    System.out.println("Coluna inexistente no banco de dados\n");
                    return; 
            }
            
            instrucao.setString(2, cpfMecanico);
            instrucao.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarMecanico(String cpfMecanico) throws SQLException {
        Connection connection = null;
        PreparedStatement instrucao = null;
       try {
            connection = ConexaoPostgreSQL.getInstancia().getConexao();
            String  sql = "DELETE FROM mecanicos WHERE cpf_do_mecanico = ?";
            instrucao =  connection.prepareStatement(sql);
           instrucao.setString(1, cpfMecanico);
           int linhasAfetadas = instrucao.executeUpdate();
           if(linhasAfetadas == 0){
            throw new SQLException( "Nenhum mecanico encontrado com este CPF.");
           }
         } finally {
            try {
                instrucao.close();
        } catch (SQLException e) {
           e.printStackTrace();
        }
        }
    }
}