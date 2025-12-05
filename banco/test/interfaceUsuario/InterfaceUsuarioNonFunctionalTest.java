package interfaceUsuario;

import conta.Conta;
import conta.ContaStandard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InterfaceUsuarioNonFunctionalTest {

    @Test
    public void deposito_DeveExecutarEmMenosDe100ms() throws Exception {

        Conta conta = new ContaStandard();

        long inicio = System.currentTimeMillis();

        // operação real do sistema
        conta.aumentarSaldo(500.0);

        long fim = System.currentTimeMillis();
        long duracao = fim - inicio;

        assertTrue(duracao < 100,
                "A operação deve ser concluída em menos de 100 ms. Tempo medido: " + duracao + "ms");
    }
}
