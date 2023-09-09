package controller;

import endereços.EnderecosServidor;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ControllerPing {
    public ControllerPing() {
        super();
    }

    private String os() {
        String os = System.getProperty("os.name");
        return os;
    }

    public void ping(String endereco) {
        String processo = (os().contains("Linux")) ? "ping -c 10 " + EnderecosServidor.getServerAddress(endereco) : "";

        if (processo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Executável apenas em Linux, sinto muito.");
            return;
        }

        try {
            long tempInicial = System.nanoTime();
            Process p = Runtime.getRuntime().exec(processo);
            InputStream flu = p.getInputStream();
            InputStreamReader ler = new InputStreamReader(flu);
            BufferedReader buffer = new BufferedReader(ler);
            String linha = buffer.readLine();
            String ultLinha = "";

            JOptionPane.showMessageDialog(null, "Pinging " + endereco + "...");

            while (linha != null) {
                ultLinha = linha;
                linha = buffer.readLine();
            }

            buffer.close();
            ler.close();
            flu.close();

            long tempFinal = System.nanoTime();
            double tempTotal = (tempFinal - tempInicial) / Math.pow(10, 9);

            if (os().contains("Linux")) {
                String[] parts = ultLinha.split("/");
                if (parts.length >= 5) {
                    String pingResult = parts[4];
                    JOptionPane.showMessageDialog(null, endereco + " - PING médio é de " + pingResult + " ms");
                    JOptionPane.showMessageDialog(null, endereco + " - Tempo total: " + tempTotal + " s");
                } else {
                    JOptionPane.showMessageDialog(null, endereco + " - ERRO - ");
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
}