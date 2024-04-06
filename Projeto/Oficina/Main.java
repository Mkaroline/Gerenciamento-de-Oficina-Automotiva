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
        int tentativas = 0;
        UsuarioAdministrador admin = new UsuarioAdministrador("Iris", "Gerente", "Iris_Matias", "123456789");
        Scanner entrada = new Scanner(System.in);

        while (tentativas < 3) {
            System.out.println("Insira o usuário: ");
            login = entrada.nextLine();
            System.out.println("Insira a senha: ");
            senha = entrada.nextLine();
            log = admin.validarLogin(login, senha);
            if (log) {
                Menu.iniciaMenu();
                break;
            } else {
                tentativas++;
                if (tentativas < 3) {
                    System.out.println("Tente novamente.");
                } else {
                    System.out.println("Você excedeu o número máximo de tentativas. Acesso bloqueado.");
                }
            }
        }
    }
}
