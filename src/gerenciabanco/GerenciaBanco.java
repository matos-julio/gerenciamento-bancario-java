package gerenciabanco;

import java.util.Scanner;

public class GerenciaBanco {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Passo 1: Ler dados iniciais do cliente
        System.out.println("\n=== Banco Javabank ===");

        System.out.print("Informe seu nome: ");
        String nome = scanner.nextLine();

        System.out.print("Informe seu sobrenome: ");
        String sobrenome = scanner.nextLine();

        System.out.print("Informe seu CPF: ");
        String cpf = scanner.nextLine();

        // Criar o cliente
        Cliente cliente = new Cliente(nome, sobrenome, cpf);

        int opcao;
        do {
            // Passo 2: Exibir menu
            System.out.println("\n=== Menu ===");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = lerInteiroPositivo(scanner);

            // Passo 3: Tratar escolha com switch
            switch (opcao) {
                case 1:
                    System.out.println("\033[0;32mSaldo atual: R$ " + String.format("%.2f", cliente.consultarSaldo()) + "\033[0m"); // mostra o saldo em verde
                    break;
                case 2:
                    System.out.print("Informe o valor para depositar: ");
                    double deposito = lerValorPositivo(scanner);
                    cliente.depositar(deposito);
                    System.out.println("Depósito realizado com sucesso.");
                    break;
                case 3:
                    System.out.print("Informe o valor para sacar: ");
                    double saque = lerValorPositivo(scanner);
                    // Verificando se há saldo suficiente para o saque
                    if (cliente.consultarSaldo() >= saque) {
                        cliente.sacar(saque);
                        System.out.println("Saque realizado com sucesso.");
                    } else {
                        System.out.println("Erro: Saldo insuficiente para realizar o saque.");
                    }
                    break;
                case 4:
                    System.out.println("\033[0;33mEncerrando o programa. Obrigado por usar nosso sistema!\033[0m"); // texto de despedida em amarelo
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 4);

        scanner.close();
    }

    // Método para ler um inteiro positivo
    private static int lerInteiroPositivo(Scanner scanner) {
        int numero;
        while (true) {
            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();
                if (numero >= 1) {
                    break;
                } else {
                    System.out.print("Por favor, insira um número positivo: ");
                }
            } else {
                System.out.print("Entrada inválida. Tente novamente: ");
                scanner.next(); // limpar buffer
            }
        }
        return numero;
    }

    // Método para ler um valor positivo para depósito/saque
    private static double lerValorPositivo(Scanner scanner) {
        double valor;
        while (true) {
            if (scanner.hasNextDouble()) {
                valor = scanner.nextDouble();
                if (valor > 0) {
                    break;
                } else {
                    System.out.print("Valor deve ser maior que 0. Tente novamente: ");
                }
            } else {
                System.out.print("Entrada inválida. Tente novamente: ");
                scanner.next(); // limpar buffer
            }
        }
        return valor;
    }
}

class Cliente {

    // Atributos privados (encapsulamento)
    private String nome;
    private String sobrenome;
    private String cpf;
    private double saldo;

    // Construtor para inicializar o cliente
    public Cliente(String nome, String sobrenome, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.saldo = 0.0;  // saldo inicial é zero
    }

    // Método para consultar saldo
    public double consultarSaldo() {
        return saldo;
    }

    // Método para depositar
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    // Método para sacar
    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
        }
    }

    // Getters para nome, sobrenome, cpf (se precisar exibir)
    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getCpf() {
        return cpf;
    }
}
