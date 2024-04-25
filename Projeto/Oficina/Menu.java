package Oficina;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import DAO.*;
import Entidades.*;
import Excecoes.DadosInvalidosException;

public class Menu {
    static void iniciaMenu() throws DadosInvalidosException{
    UsuarioMecanico auxMecanico = new UsuarioMecanico();
    UsuarioMecanicoDAO daoMecanico = new UsuarioMecanicoDAO();
    UsuarioCliente auxCliente = new UsuarioCliente();
    UsuarioClienteDAO daoCliente = new UsuarioClienteDAO();
    Servico auxServico = new Servico();
    ServicoDAO daoServico = new ServicoDAO();
    Produto auxProduto = new Produto();
    ProdutoDAO daoProduto = new ProdutoDAO();
    Agenda auxAgenda = new Agenda();
    AgendaDAO daoAgenda = new AgendaDAO();

    Scanner opcao = new Scanner(System.in);
    Scanner entradas= new Scanner(System.in);
      int op = 1;
      
        do {
            limparTela();
            System.out.println("\nSISTEMA DE GERENCIAMENTO DE OFICINA AUTOMOTIVA\n");
            System.out.println("Selecione uma opção");
            System.out.println("1 - Gerenciamento de Produtos");
            System.out.println("2 - Gerenciamento de Usuarios");
            System.out.println("3 - Gerenciamento de Serviços");
            System.out.println("4 - Gerenciamento de Agenda");
            System.out.println("0 - Sair\n");
                int op2 = 1;
                int op3 = 1;
                op = opcao.nextInt();
                switch(op){
                case 1:
                while(op2 != 0){
                    System.out.println("\nMENU PRODUTOS\n");
                    System.out.println("Selecione uma opção:");
                    System.out.println("1 - Cadastrar produto");
                    System.out.println("2 - Listar produtos");
                    System.out.println("3 - Editar produto");
                    System.out.println("4 - Deletar produto");
                    System.out.println("0 - Voltar\n");
                    op2 = opcao.nextInt();
                    Menu.clearBuffer(opcao);
            
                    switch(op2){
                    case 1:
                        System.out.println("Insira o nome do produto: ");
                        String nomeDoProduto = entradas.nextLine();
                        Menu.clearBuffer(entradas);
                        System.out.println("Insira a descrição do produto: ");
                        String descProduto = entradas.nextLine();
                        Menu.clearBuffer(entradas);
                        System.out.println("Insira o preço do produto: ");
                        double precoProduto = entradas.nextDouble();
                        Menu.clearBuffer(entradas);
                        System.out.println("Insira a quantidade do produto em estoque: ");
                        int quantidade = entradas.nextInt();
                        Menu.clearBuffer(entradas);
                        auxProduto = new Produto(nomeDoProduto, descProduto, precoProduto, quantidade);
                        daoProduto.cadastrarProduto(auxProduto);
                        break;
                    case 2:
                        daoProduto.listarProdutos();
                    break;
                    case 3:
                    System.out.println("Escolha um campo para editar: ");
                    System.out.println("1 - Nome");
                    System.out.println("2 - Descrição");
                    System.out.println("3 - Preço");
                    System.out.println("4 - Quantidade");
                    int campo = entradas.nextInt();
                    Menu.clearBuffer(entradas);
                        //Caso seja o nome do produto
                        if(campo == 1){
                            System.out.println("Insira o id do produto: ");
                            int idProduto = entradas.nextInt();
                            Menu.clearBuffer(entradas);
                            System.out.println("Insira o novo nome: ");
                            nomeDoProduto = entradas.nextLine();
                            Menu.clearBuffer(entradas);
                            auxProduto.setNomeProduto(nomeDoProduto);
                            daoProduto.editarProduto(auxProduto, "nome_produto", idProduto);
                        } 
                        //Caso seja a descrição
                        else if(campo == 2){
                            System.out.println("Insira o id do produto: ");
                            int idProduto = entradas.nextInt();
                            Menu.clearBuffer(entradas);
                            System.out.println("Insira a nova descrição: ");
                            descProduto = entradas.nextLine();
                            Menu.clearBuffer(entradas);
                            auxProduto.setDescricaoProduto(descProduto);
                            daoProduto.editarProduto(auxProduto, "descricao_prod", idProduto);
                        } 
                        //Caso seja o preço
                        else if(campo == 3){
                            System.out.println("Insira o id do produto: ");
                            int idProduto = entradas.nextInt();
                            Menu.clearBuffer(entradas);
                            System.out.println("Insira o novo preço: ");
                            precoProduto = entradas.nextDouble();
                            Menu.clearBuffer(entradas);
                            auxProduto.setPrecoProduto(precoProduto);
                            daoProduto.editarProduto(auxProduto, "preco_prod", idProduto);
                        } 
                        //Caso seja a quantidade
                        else if(campo == 4 ){
                            System.out.println("Insira o id do produto: ");
                            int idProduto = entradas.nextInt();
                            Menu.clearBuffer(entradas);
                            System.out.println("Insira a nova quantidade: ");
                            quantidade = entradas.nextInt();
                            Menu.clearBuffer(entradas);
                            auxProduto.setQuantidadeProduto(quantidade);
                            daoProduto.editarProduto(auxProduto, "quantidade", idProduto);
                    
                        } else {
                            System.out.println("Opçao invalida");
                        }
            
                        break;
                        case 4:
                            System.out.println("Insira o id do produto: ");
                            int idProduto = entradas.nextInt();
                            Menu.clearBuffer(entradas);
                        try {
                            daoProduto.deletarProduto(idProduto);
                            System.out.println("Produto deletado com sucesso.");
                        } catch (SQLException e) {
                            
                        }
                        break;
                        case 0:
                            System.out.println("\nRetornando para o menu principal");
                        break;
                        default:
                            System.out.println("\nOpção inválida");
                        break;
                        }
                    }
                    break;
                    case 2:
                    while(op2 != 0){
                    System.out.println("\nMENU USUARIOS\n");
                    System.out.println("Selecione uma opção:");
                    System.out.println("1 - Mecanicos");
                    System.out.println("2 - Clientes");
                    System.out.println("0 - Voltar\n");
                    op2 = opcao.nextInt();
                    Menu.clearBuffer(opcao);
                    switch(op2){
                        case 1:
                            while(op3!= 0){
                        
                            System.out.println("\nMENU MECANICOS\n");
                            System.out.println("Selecione uma opção:");
                            System.out.println("1 - Cadastrar mecanico");
                            System.out.println("2 - Listar mecanicos");
                            System.out.println("3 - Editar mecanico");
                            System.out.println("4 - Deletar mecanico");
                            System.out.println("0 - Voltar");
                            op3 = opcao.nextInt();
                            switch(op3){
                            case 1:
                            System.out.println("Insira o nome do mecanico: ");
                            String nomeMecanico = entradas.nextLine();
                            Menu.clearBuffer(entradas);
                            System.out.println("Insira o CPF do mecanico: ");
                            String cpfMecanico = entradas.nextLine();
                            Menu.clearBuffer(entradas);
                            System.out.println("Insira a especialidade do mecanico: ");
                            String especialidadeDoMecanico = entradas.nextLine();
                            Menu.clearBuffer(entradas);
                            auxMecanico = new UsuarioMecanico(nomeMecanico, cpfMecanico, especialidadeDoMecanico);
                            daoMecanico.cadastrarMecanico(auxMecanico);
                            break;
                            case 2:
                            daoMecanico.listarMecanicos();
                            break;
                            case 3:
                            System.out.println("Escolha um campo para editar: ");
                            System.out.println("1 - Nome");
                            System.out.println("2 - Especialidade");
                            int campo = entradas.nextInt();
                            Menu.clearBuffer(entradas);
                            
                            if(campo == 1){
                                System.out.println("Insira o CPF do mecanico: ");
                                String cpf_do_Mecanico = entradas.nextLine();
                                Menu.clearBuffer(entradas);
                                System.out.println("Insira o novo nome: ");
                                nomeMecanico = entradas.nextLine();
                                Menu.clearBuffer(entradas);
                                auxMecanico.setNome(nomeMecanico);
                                daoMecanico.editarMecanico(auxMecanico, "nome_do_mecanico", cpf_do_Mecanico);
                            } else if(campo == 2){
                                System.out.println("Insira o CPF do mecanico: ");
                                String cpf_do_Mecanico = entradas.nextLine();
                                Menu.clearBuffer(entradas);
                                System.out.println("Insira a nova especialidade: ");
                                especialidadeDoMecanico = entradas.nextLine();
                                Menu.clearBuffer(entradas);
                                auxMecanico.setEspecialidadeMecanico(especialidadeDoMecanico);
                                daoMecanico.editarMecanico(auxMecanico, "especialidade", cpf_do_Mecanico);
                            } else {
                                System.out.println("Opçao invalida");
                            }
                            break;
                            case 4:
                                System.out.println("Insira o CPF do mecanico: ");
                                String cpf_do_Mecanico = entradas.nextLine();
                                Menu.clearBuffer(entradas);
                            try{
                                daoMecanico.deletarMecanico(cpf_do_Mecanico);
                                System.out.println("Mecanico deletado com sucesso");
                            }catch(SQLException e){
                                System.out.println("Nenhum mecanico encontrado com este CPF");
                            }
                            break;
                            case 0:
                                System.out.println("Voltando ao menu anterior");
                        }
                    }      
                    break;
                        case 2: 
                            while(op3 != 0){
                            System.out.println("\nMENU CLIENTES\n");
                            System.out.println("Selecione uma opção:");
                            System.out.println("1 - Cadastrar cliente");
                            System.out.println("2 - Listar clientes");
                            System.out.println("3 - Editar clientes");
                            System.out.println("4 - Deletar cliente");
                            System.out.println("0 - Voltar");
                            op3 = opcao.nextInt();
                            switch(op3){
                                case 1:
                                System.out.println("Insira o nome do cliente: ");
                                String nomeCliente = entradas.nextLine();
                                Menu.clearBuffer(entradas);
                                System.out.println("Insira o endereço do cliente: ");
                                String endereco = entradas.nextLine();
                                Menu.clearBuffer(entradas);
                                System.out.println("Insira o CPF do cliente: ");
                                String cpfCliente = entradas.nextLine();
                                Menu.clearBuffer(entradas);
                                System.out.println("Insira o telefone do cliente: ");
                                String telefone = entradas.nextLine();
                                Menu.clearBuffer(entradas);
                                System.out.println("Veiculo do cliente: ");
                                String veiculo = entradas.nextLine();
                                Menu.clearBuffer(entradas);
                                auxCliente = new UsuarioCliente(nomeCliente, cpfCliente, endereco, telefone, veiculo);
                                daoCliente.cadastrarCliente(auxCliente);
                                break;
                            case 2:
                                daoCliente.listarClientes();
                                break;
                                case 3:
                                System.out.println("Escolha um campo para editar: ");
                                System.out.println("1 - Nome");
                                System.out.println("2 - Endereço");
                                System.out.println("3 - telefone");
                                System.out.println("4 - Veiculo");
                                int campo = entradas.nextInt();
                                Menu.clearBuffer(entradas);
                            
                            if(campo == 1){
                                System.out.println("Insira o CPF do cliente: ");
                                String cpf_do_cliente  = entradas.nextLine();
                                Menu.clearBuffer(entradas);
                                System.out.println("Insira o novo nome: ");
                                nomeCliente = entradas.nextLine();
                                Menu.clearBuffer(entradas);
                                auxCliente.setNome(nomeCliente);
                                daoCliente.editarCliente(auxCliente,"nome_do_cliente", cpf_do_cliente );
                            } 
                            
                            else if(campo == 2){
                                System.out.println("Insira o CPF do cliente: ");
                                String cpf_do_cliente  = entradas.nextLine();
                                Menu.clearBuffer(entradas);
                                System.out.println("Insira o novo endereço: ");
                                endereco = entradas.nextLine();
                                Menu.clearBuffer(entradas);
                                auxCliente.setEnderecoCliente(endereco);
                                daoCliente.editarCliente(auxCliente, "endereco", cpf_do_cliente );
                            } 
                            
                            else if(campo == 3){
                                System.out.println("Insira o CPF do cliente: ");
                                String cpf_do_cliente  = entradas.nextLine();
                                Menu.clearBuffer(entradas);
                                System.out.println("Insira o novo telefone: ");
                                telefone = entradas.nextLine();
                                Menu.clearBuffer(entradas);
                                auxCliente.setTelefoneCliente(telefone);
                                daoCliente.editarCliente(auxCliente, "telefone", cpf_do_cliente );
                            } 
                            //Caso seja a quantidade
                            else if(campo == 4 ){
                                System.out.println("Insira o CPF do cliente: ");
                                String cpf_do_cliente  = entradas.nextLine();
                                Menu.clearBuffer(entradas);
                                System.out.println("Insira o nova veiculo: ");
                                veiculo = entradas.nextLine();
                                Menu.clearBuffer(entradas);
                                auxCliente.setVeiculoDoCliente(veiculo);
                                daoCliente.editarCliente(auxCliente, "veiculo", cpf_do_cliente );
                                } else {
                                System.out.println("Opçao invalida");
                            }
                                break;
                                
                            case 4:
                                System.out.println("Insira o CPF do cliente: ");
                                String cpf_do_cliente  = entradas.nextLine();
                                Menu.clearBuffer(entradas);
                                try {
                                    daoCliente.deletarCliente(cpf_do_cliente);
                                    System.out.println("Cliente deletado com sucesso.");
                                } catch (SQLException e) {
                                    System.out.println("Nenhum cliente encontrado com este CPF.");
                                }
                            break;
                            }
                    
                        }
                
                    break;
                        case 0:
                            System.out.println("\nRetornando para o menu principal");
                        break;
                        default:
                            System.out.println("\nOpção inválida");
                        break;       
                    }//Fecha switch opcaoMenu
                }  
                break;
                case 3:
                while(op2!=0){
                System.out.println("\nSERVIÇOS\n");
                System.out.println("Selecione uma opção:");
                System.out.println("1 - Cadastrar serviço");
                System.out.println("2 - Listar serviços");
                System.out.println("3 - Editar serviços");
                System.out.println("4 - Deletar serviço");
                System.out.println("0 - Voltar\n");
                op2 = opcao.nextInt();
                Menu.clearBuffer(opcao);
                switch(op2){
                case 1:
                    System.out.println("Insira o nome do serviço: ");
                    String nomeDoServico = entradas.nextLine();
                    Menu.clearBuffer(entradas);
                    System.out.println("Insira a descrição do serviço");
                    String descServico = entradas.nextLine();
                    Menu.clearBuffer(entradas);
                    System.out.println("Insira o nome do mecânico responsavel pelo serviço: ");
                    String nomeDoMecanico = entradas.nextLine();
                    Menu.clearBuffer(entradas);
                    System.out.println("Insira o nome do cliente que solicitou o serviço: ");
                    String nomeDoCliente = entradas.nextLine();
                    Menu.clearBuffer(entradas);
                    System.out.println("Insira o orçamento do serviço: ");
                    double orcamento = entradas.nextDouble();
                    Menu.clearBuffer(entradas);
                    System.out.println("Informe o Status do serviço: ");
                    String statusServico = entradas.nextLine();
                    Menu.clearBuffer(entradas);
                    auxMecanico.setNome(nomeDoMecanico);
                    auxServico = new Servico(nomeDoServico,descServico, auxMecanico, orcamento, nomeDoCliente, statusServico);
                    daoServico.cadastrarServico(auxServico);
                break;
                case 2:
                    daoServico.listarServicos();
                break;
                case 3:
                    System.out.println("Escolha um campo para editar: ");
                    System.out.println("1 - Nome do serviço");
                    System.out.println("2 - Descrição do serviço");
                    System.out.println("3 - Orçamento");
                    System.out.println("4 - Status do Serviço");
                    int campo = entradas.nextInt();
                    Menu.clearBuffer(entradas);
                    
                    if(campo == 1){
                        System.out.println("Insira o id do serviço: ");
                        int idServico = entradas.nextInt();
                        Menu.clearBuffer(entradas);
                        System.out.println("Insira o novo nome do serviço: ");
                        nomeDoServico = entradas.nextLine();
                        Menu.clearBuffer(entradas);
                        auxServico.setNomeServico(nomeDoServico);
                        daoServico.editarServico(auxServico, "name_srvc", idServico);
                    } 
                    
                    else if(campo == 2){
                        System.out.println("Insira o id do serviço: ");
                        int idServico = entradas.nextInt();
                        Menu.clearBuffer(entradas);
                        System.out.println("Insira a nova descrição: ");
                        descServico = entradas.nextLine();
                        Menu.clearBuffer(entradas);
                        auxServico.setDescricaoServico(descServico);
                        daoServico.editarServico(auxServico, "desc_srvc", idServico);
                    } 
                    
                    else if(campo == 3){
                        System.out.println("Insira o id do serviço: ");
                        int idServico = entradas.nextInt();
                        Menu.clearBuffer(entradas);
                        System.out.println("Insira o novo orçamento: ");
                        orcamento = entradas.nextDouble();
                        Menu.clearBuffer(entradas);
                        auxServico.setOrcamentoServico(orcamento);
                        daoServico.editarServico(auxServico, "orcamento", idServico);
                    }
                    
                    else if(campo == 4){
                        System.out.println("Insira o id do serviço: ");
                        int idServico = entradas.nextInt();
                        Menu.clearBuffer(entradas);
                        System.out.println("Insira o novo status do serviço: ");
                        statusServico = entradas.nextLine();
                        Menu.clearBuffer(entradas);
                        auxServico.setstatusServico(statusServico);
                        daoServico.editarServico(auxServico, "status_Servico", idServico);
                    } else {
                        System.out.println("Opçao invalida");
                    }
                    break;
                    case 4:
                        System.out.println("Insira o id do serviço a ser deletado: ");
                        int idServico = entradas.nextInt();
                        Menu.clearBuffer(entradas);
                        try {
                            daoServico.deletarServico(idServico);
                            System.out.println("Serviço deletado com sucesso.");
                        } catch (SQLException e) {
                            System.out.println("Erro ao deletar o serviço: " + e.getMessage());
                        }
                    break;
                    case 0:
                        System.out.println("\nRetornando para o menu principal");
                    break;
                    default:
                        System.out.println("\nOpção inválida");
                    break;
                    }
                }
            break;

            case 4:
            while(op2 != 0) {
                System.out.println("\nAGENDA\n");
                System.out.println("Selecione uma opção:");
                System.out.println("1 - Cadastrar agendamento");
                System.out.println("2 - Pesquisar os agendamentos de um dia");
                System.out.println("3 - Listar agendamentos por periodo");
                System.out.println("0 - Voltar\n");
                op2 = opcao.nextInt();
                Menu.clearBuffer(opcao);
                
            switch(op2) {
                case 1:
                    LocalDate data = null; 
                    System.out.println("Informe a data (yyyy-MM-dd): ");
                    String data1String = entradas.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    
                    try {
                        data = LocalDate.parse(data1String, formatter);
                        System.out.println("Data lida: " + data);
    
                        if (data.isBefore(LocalDate.now())) {
                            System.out.println("Não é possível agendar para datas retroativas.");
                        } else {
                            System.out.println("Informe o possível serviço:");
                            String servico = entradas.nextLine();
                            Menu.clearBuffer(entradas);
                            System.out.println("Insira o nome do cliente que agendou o serviço: ");
                            String nomeDoCliente = entradas.nextLine();
                            Menu.clearBuffer(entradas);
                            auxCliente.setNome(nomeDoCliente);
                            
                            java.sql.Date dataSql = java.sql.Date.valueOf(data);
                            
                            auxAgenda = new Agenda(dataSql, servico, nomeDoCliente); 
                            daoAgenda.cadastrarAgenda(auxAgenda);
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato de data inválido.");
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro ao processar a data.");
                    }
                    break;
                    
                case 2:
                    System.out.println("Digite a data (formato yyyy-MM-dd): ");
                    String dataString = entradas.nextLine();
                    
                    try {
                        LocalDate dataPesquisa = LocalDate.parse(dataString);
                        AgendaDAO.pesquisarNaAgenda(dataPesquisa);
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato de data inválido.");
                    }
                    break;
                    
                case 3:
                    System.out.println("Digite a data de início (formato yyyy-MM-dd): ");
                    String dataInicioString = entradas.nextLine();
                    System.out.println("Digite a data de fim (formato yyyy-MM-dd): ");
                    String dataFimString = entradas.nextLine();
                    
                    try {
                        LocalDate dataInicio = LocalDate.parse(dataInicioString);
                        LocalDate dataFim = LocalDate.parse(dataFimString);
                        
                        if (dataInicio.isAfter(dataFim)) {
                            System.out.println("A data de início deve ser anterior à data de fim.");
                        } else {
                            daoAgenda.listarAgenda(dataInicio, dataFim);
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato de data inválido.");
                    }
                    break;
                    
                case 0:
                    System.out.println("Voltando ao menu anterior");
                    break;
                    
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        break;
  
      case 0:
      System.out.println("\nEncerrando sistema\n");
      break;
      default:
      System.out.println("\nOpção inválida");
      break;
        }
      } while (op != 0);
    
    }

    static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }

    public static void limparTela() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Erro ao limpar a tela: " + e.getMessage());
        }
    }   
}