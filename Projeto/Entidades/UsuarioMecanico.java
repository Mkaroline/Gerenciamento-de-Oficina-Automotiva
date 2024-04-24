package Entidades;

import Excecoes.DadosInvalidosException;

public class UsuarioMecanico extends Usuario {
    private String especialidadeMecanico;

    // Construtor
    public UsuarioMecanico( String nome, String cpf, String especialidadeMecanico) throws DadosInvalidosException{
        super(nome, cpf);
        validarCampos(nome, cpf, especialidadeMecanico);
        this.especialidadeMecanico = especialidadeMecanico;
        System.out.println("Mecanico cadastrado com sucesso!");
    }

     // Método para validar campos
     private void validarCampos(String nome, String cpf, String especialidadeMecanico) throws DadosInvalidosException {
        if (nome == null || nome.isEmpty()) {
            throw new DadosInvalidosException("Nome não pode estar vazio");
        }

        if (cpf == null || cpf.isEmpty()) {
            throw new DadosInvalidosException("CPF não pode estar vazio");
        }

        if (especialidadeMecanico == null || especialidadeMecanico.isEmpty()) {
            throw new DadosInvalidosException("Especialidade não pode estar vazio");
        }
        
    }

    public UsuarioMecanico(){
        
    }

    // Getters e setters para especialidadeMecanico
    public String getEspecialidadeMecanico() {
        return especialidadeMecanico;
    }

    public void setEspecialidadeMecanico(String especialidadeMecanico) {
        this.especialidadeMecanico = especialidadeMecanico;
    }

    @Override
    public String toString() {
        return ", Nome: " + getNome() + ", CPF: " + getcpf() + ", Especialidade: " + especialidadeMecanico;
    }
}
