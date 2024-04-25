package Oficina;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import Entidades.UsuarioAdministrador;
import Excecoes.DadosInvalidosException;

public class Main {
    public static void main(String[] args) throws DadosInvalidosException {
        String login;
        String senha;
        boolean log;
        int tentativas = 0;
        // Obter a senha da variável de ambiente
        String senhaAmbiente = System.getenv("SENHA_ADMINISTRADOR");
        if (senhaAmbiente == null) {
            System.err.println("Erro: A senha do administrador não foi configurada.");
            return;
        }
        // Gerando um hash da senha para armazenar
        String senhaHash = hashSenha(senhaAmbiente);
        UsuarioAdministrador admin = new UsuarioAdministrador("Iris","70803928470", "Gerente", "Oficina4A", senhaHash);
        Scanner entrada = new Scanner(System.in);

    try {
        while (tentativas < 3) {
            System.out.println("Insira o usuário: ");
            login = entrada.nextLine();
            System.out.println("Insira a senha: ");
            senha = entrada.nextLine();
            // Gerando um hash da senha fornecida para comparar com o hash armazenado
            String senhaInputHash = hashSenha(senha);
            log = admin.validarLogin(login, senhaInputHash);
            if (log) {
                Menu.iniciaMenu();
                break;
            } else {
                tentativas++;
                if (tentativas < 3) {
                    System.out.println("\n");
                    System.out.println("Tente novamente.");
                } else {
                    System.out.println("Você excedeu o número máximo de tentativas. Acesso bloqueado.");
                }
            }
        }
    } finally {
        entrada.close();
        }
}

    // Método para gerar um hash da senha usando o algoritmo SHA-256
    private static String hashSenha(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(senha.getBytes());

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Erro: Algoritmo de hash não encontrado.");
            e.printStackTrace();
            return null;
        }
    }
}
