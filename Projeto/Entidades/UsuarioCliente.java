package Entidades;

import Excecoes.DadosInvalidosException;

public class UsuarioCliente extends Usuario {
    private String enderecoCliente;
    private String telefoneCliente;
    private String veiculoDoCliente;


    // Construtor
    public UsuarioCliente( String nome, String cpf, String enderecoCliente, String telefoneCliente, String veiculoDoCliente) throws DadosInvalidosException {
        super(nome, cpf );
        validarCampos(nome, cpf, enderecoCliente, telefoneCliente, veiculoDoCliente);
        this.enderecoCliente = enderecoCliente;
        this.telefoneCliente = telefoneCliente;
        this.veiculoDoCliente = veiculoDoCliente;
        System.out.println("Cliente cadastrado com sucesso!");
    }

    // Método para validar campos
    private void validarCampos(String nome, String cpf, String enderecoCliente, String telefoneCliente, String veiculoDoCliente) throws DadosInvalidosException {
        if (nome == null || nome.isEmpty()) {
            throw new DadosInvalidosException("Nome não pode estar vazio");
        }

        if (cpf == null || cpf.isEmpty()) {
            throw new DadosInvalidosException("CPF não pode estar vazio");
        }

        if (enderecoCliente == null || enderecoCliente.isEmpty()) {
            throw new DadosInvalidosException("Endereço não pode estar vazio");
        }
        
        if (telefoneCliente == null || telefoneCliente.isEmpty()) {
            throw new DadosInvalidosException("Telefone não pode estar vazio");
        }

        if (veiculoDoCliente == null || veiculoDoCliente.isEmpty()) {
            throw new DadosInvalidosException("Veiculo não pode estar vazio");
        }
        
    }

    public UsuarioCliente(){

    }

    // Getters e setters para enderecoCliente, telefoneCliente e veiculoDoCliente
        public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public String getVeiculoDoCliente() {
        return veiculoDoCliente;
    }

    public void setVeiculoDoCliente(String veiculoDoCliente) {
        this.veiculoDoCliente = veiculoDoCliente;
    }

    @Override
    public String toString() {
        return ", Nome: " + getNome() + ", CPF: " + getcpf() + ", Endereço: " + enderecoCliente + ", Telefone: "
                + telefoneCliente + ", Veículo: " + veiculoDoCliente;
    }


}

