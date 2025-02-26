package servidor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingUtilities;
import org.json.JSONObject;

public class RelogioLeilao {

    private ScheduledExecutorService scheduler;
    private int tempoRestante;
    private GerenciadorLances gerenciadorLances;

    public RelogioLeilao(GerenciadorLances gerenciadorLances) {
        this.gerenciadorLances = gerenciadorLances;
    }

    public void iniciarContagem(int duracaoSegundos, Runnable atualizarTempo, Runnable aoEncerrar, String itemEmLeilao, InterfaceGraficaServidor interfaceGrafica) {
        cancelarContagem(); // Garante que qualquer contagem anterior seja cancelada
        scheduler = Executors.newSingleThreadScheduledExecutor();
        tempoRestante = duracaoSegundos;

        System.out.println("Relógio do leilão iniciado por " + duracaoSegundos + " segundos.");

        scheduler.scheduleAtFixedRate(() -> {
            tempoRestante--;

            // Atualiza o tempo restante na interface (usa SwingUtilities.invokeLater para thread correta)
            SwingUtilities.invokeLater(() -> atualizarTempo.run());

            // Envia o tempo restante via multicast para a interface gráfica
            JSONObject dadosTimer = new JSONObject();
            dadosTimer.put("remetente", "servidordeLeilao");
            dadosTimer.put("tempo", tempoRestante);
            interfaceGrafica.enviar("timer", dadosTimer);

            // Quando o tempo acabar, executa o callback e cancela a contagem
            if (tempoRestante <= 0) {
               

                // Executa o fechamento do leilão na thread do Swing
                SwingUtilities.invokeLater(() -> {
                    aoEncerrar.run(); // Chama o método para finalizar o leilão
                });

                // Cancela o scheduler
                cancelarContagem();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public void cancelarContagem() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdownNow(); // Cancela todas as tarefas agendadas
            System.out.println("[SERVIDOR] Relógio do leilão cancelado.");
        }
    }

    public int getTempoRestante() {
        return tempoRestante;
    }
    // teste
}
