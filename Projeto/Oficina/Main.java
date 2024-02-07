package Projeto.Oficina;

import java.util.Scanner;

import Projeto.DAO.*;
import Projeto.Entidades.*;
import Projeto.Excecoes.DadosInvalidosException;
public class Main {
    public static void main(String[] args) throws DadosInvalidosException {
        String login;
        String senha;
        boolean log;
        UsuarioAdministrador admin = new UsuarioAdministrador("Iris", "Gerente", "Iris_Matias", "123456789");
        Scanner entrada = new Scanner(System.in);
        System.out.println("Insira o usuario: ");
        login = entrada.nextLine();
        System.out.println("Insira a senha: ");
        senha = entrada.nextLine();
        log = admin.validarLogin(login, senha);
        if(log == true){
         Menu.iniciaMenu();
        } else {

        }
        
    }

}