package Projeto.Entidades;

public class UsuarioCliente extends Usuario {
    private String enderecoCliente;
    private String telefoneCliente;
    private String veiculoDoCliente;


    // Construtor
    public UsuarioCliente( String nome, String enderecoCliente, String telefoneCliente, String veiculoDoCliente) {
        super(nome);
        this.enderecoCliente = enderecoCliente;
        this.telefoneCliente = telefoneCliente;
        this.veiculoDoCliente = veiculoDoCliente;
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
        return  ", Nome: " + getNome() + ", Endereço: " + enderecoCliente + ", Telefone: " + telefoneCliente + ", Veículo: " + veiculoDoCliente;
    }

}
