package interfaceUsuario;

import cliente.Cliente;
import conta.Conta;
import interfaceUsuario.dados.*;

public class InterfaceUsuario {
	private static DadosConta dadosConta;
	private static DadosCartao dadosCartao;
	private static Cliente clienteAtual;
	private static DadosTransacao dadosTransacao;
	private static DadosChavesPix dadosChavePix;
	private static DadosBoleto dadosBoleto;

	public static DadosTransacao getDadosTransacao() {
		return dadosTransacao;
	}

	public static void setDadosTransacao(DadosTransacao dadosTransacao) {
		InterfaceUsuario.dadosTransacao = dadosTransacao;
	}

	public static DadosBoleto getDadosBoleto() {
		return dadosBoleto;
	}

	public static void setDadosBoleto(DadosBoleto dadosBoleto) {
		InterfaceUsuario.dadosBoleto = dadosBoleto;
	}

	public static DadosChavesPix getDadosChavePix() {
		return dadosChavePix;
	}

	public static void setDadosChavePix(DadosChavesPix dadosChavePix) {
		InterfaceUsuario.dadosChavePix = dadosChavePix;
	}

	public static Cliente getClienteAtual() {
		return clienteAtual;
	}

	public static void setClienteAtual(Cliente clienteAtual) {
		InterfaceUsuario.clienteAtual = clienteAtual;
	}

	public static Conta usuarioAtualConta() {
		return clienteAtual.getConta();
	}

	public static DadosConta getDadosConta() {
		return dadosConta;
	}

	public static void setDadosConta(DadosConta dadosConta) {
		InterfaceUsuario.dadosConta = dadosConta;
	}

	public static DadosCartao getDadosCartao() {
		return dadosCartao;
	}

	public static void setDadosCartao(DadosCartao dadosCartao) {
		InterfaceUsuario.dadosCartao = dadosCartao;
	}

	// pra permitir testes funcionais reais com o system lambda
	public static double lerValor(String mensagem) {
	    System.out.println(mensagem);
	    java.util.Scanner sc = new java.util.Scanner(System.in);
	    return sc.nextDouble();
	}
	
}

