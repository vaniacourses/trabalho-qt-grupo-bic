package interfaceUsuario.verificadores.dados;

import conta.ContaDiamond;
import conta.ContaPremium;
import conta.ContaStandard;
import conta.GerenciamentoCartao;
import interfaceUsuario.InterfaceUsuario;
import interfaceUsuario.MenuUsuarioConstantes;
import interfaceUsuario.exceptions.ValorInvalido;

import static interfaceUsuario.menus.MenuUsuario.DEPOSITO;
import static interfaceUsuario.menus.MenuUsuario.TRANSFERENCIA;
import static interfaceUsuario.verificadores.dados.VerificadorEntrada.*;

public class VerificadorTransacao {

    // CONSTRUTOR REMOVIDO PARA AUMENTAR A COBERTURA NOS TESTES

    public static boolean dadosTransacao(String entrada, String tipoOperacao, String tipoConta) throws ValorInvalido {
        int value = tentarConverterValor(entrada);

        if (TRANSFERENCIA.equals(tipoOperacao)) {
            return verificarEntradaValor(entrada, tipoOperacao);
        } else if (DEPOSITO.equals(tipoOperacao) && value > 0) {
            return validarRegrasDeposito(value, tipoConta);
        }
        return false;
    }

    private static boolean validarRegrasDeposito(int value, String tipoConta) throws ValorInvalido {
        double saldoTotal = InterfaceUsuario.getClienteAtual().getConta().getSaldoTotalDepositado();
        double novoTotal = saldoTotal + value;

        switch (tipoConta) {
            case STANDARD:
                if (value <= ContaStandard.DEPOSITO_MAXIMO && novoTotal <= ContaStandard.DEPOSITO_MAXIMO) {
                    return true;
                }
                throw new ValorInvalido("[ERRO] Limite excedido para Conta Standard (Max: " + ContaStandard.DEPOSITO_MAXIMO + ")");

            case PREMIUM:
                if (value <= ContaPremium.DEPOSITO_MAXIMO && novoTotal <= ContaPremium.DEPOSITO_MAXIMO) {
                    return true;
                }
                throw new ValorInvalido("[ERRO] Limite excedido para Conta Premium (Max: " + ContaPremium.DEPOSITO_MAXIMO + ")");

            case DIAMOND:
                if (value <= ContaDiamond.DEPOSITO_MAXIMO && novoTotal <= ContaDiamond.DEPOSITO_MAXIMO) {
                    return true;
                }
                throw new ValorInvalido("[ERRO] Limite excedido para Conta Diamond (Max: " + ContaDiamond.DEPOSITO_MAXIMO + ")");

            default:
                return false;
        }
    }

    private static int tentarConverterValor(String entrada) {
        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException exception) {
            return -1;
        }
    }

    public static boolean valorFatura(String s, MenuUsuarioConstantes tipoOperacao, GerenciamentoCartao carteira) throws ValorInvalido {
        if (tipoOperacao == MenuUsuarioConstantes.PAGAR_FATURA) {
            if (verificarEntradaValorParaFatura(s, MenuUsuarioConstantes.VERIFICAR_VALOR_SALDO)) {
                double valor = Double.parseDouble(s);
                if (valor > carteira.getFatura()) {
                    throw new ValorInvalido("[ERRO] Valor de pagamento maior que o valor da fatura");
                }
            }
        } else if (tipoOperacao == MenuUsuarioConstantes.AUMENTAR_FATURA) {
            if (verificarEntradaValorParaFatura(s, MenuUsuarioConstantes.NAO_VERIFICAR_VALOR_SALDO)) {
                double valor = Double.parseDouble(s);
                if (valor > carteira.getLimiteRestante()) {
                    throw new ValorInvalido("[ERRO] Valor inserido maior que o seu limite");
                }
            }
            return false;
        }
        return true;
    }

    public static boolean agendamentoTransacao(String[] s, String tipoConta) throws ValorInvalido {
        if (dadosTransacao(s[0], TRANSFERENCIA, tipoConta)) {
            return VerificadorData.verificarData(s[1]);
        }
        return false;
    }

    public static boolean verificarBoleto(String[] entrada) throws ValorInvalido {
        try {
            if (verificarEntradaValorPositivo(entrada[0]) || verificarEntradaValorPositivo(entrada[2])) {
                return false;
            }
        } catch (IllegalArgumentException e) {
            // Captura TipoInvalido ou NumberFormatException e trata como validação falha
            return false;
        }

        if (!VerificadorData.verificarData(entrada[1])) {
            return false;
        }

        try {
            Integer.parseInt(entrada[2]);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}