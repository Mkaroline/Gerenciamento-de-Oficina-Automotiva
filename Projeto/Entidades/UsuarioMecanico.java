package Projeto.Entidades;

public class UsuarioMecanico extends Usuario {
    private String especialidadeMecanico;

    // Construtor
    public UsuarioMecanico( String nome, String especialidadeMecanico) {
        super(nome);
        this.especialidadeMecanico = especialidadeMecanico;
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
        return ", Nome: " + getNome() + ", Especialidade: " + especialidadeMecanico;
    }
}