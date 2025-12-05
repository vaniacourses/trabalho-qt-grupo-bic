package interfaceUsuario;

import conta.Conta;
import conta.ContaStandard;
import interfaceUsuario.menus.MenuUsuario;
import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static org.junit.jupiter.api.Assertions.*;

public class InterfaceUsuarioFunctionalTest {

    @Test
    public void deposito_ComSystemLambda_DeveAumentarSaldo() throws Exception {

        Conta conta = new ContaStandard();

        String saida = tapSystemOut(() ->
                withTextFromSystemIn("200").execute(() -> {

                    double valor = InterfaceUsuario.lerValor("Digite o valor do depósito:");

                    conta.aumentarSaldo(valor);
                })
        );

        assertTrue(saida.contains("Digite o valor do depósito:"));
        assertEquals(200.0, conta.getSaldo());
    }


    @Test
    public void guardarDinheiro_ComSystemLambda_DeveAtualizarValores() throws Exception {

        Conta conta = new ContaStandard();
        conta.aumentarSaldo(500.0);

        String saida = tapSystemOut(() ->
                withTextFromSystemIn("100").execute(() -> {

                    double valor = InterfaceUsuario.lerValor("Valor a guardar:");

                    conta.setDinheiroGuardado(valor, MenuUsuario.GUARDAR);
                })
        );

        assertTrue(saida.contains("Valor a guardar:"));
        assertEquals(400.0, conta.getSaldo());
        assertEquals(100.0, conta.getDinheiroGuardado());
    }

}
