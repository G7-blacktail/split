package com.lidersis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextToCSVConverter {

    public static void main(String[] args) {
        String arquivoTexto = "C:\\Users\\gustavo.fernandes\\Desktop\\AuditoriaLidersis Descktop Path.txt";
        String arquivoCSV = "tabelaNewSplit.csv";

        try {
            converterTextoParaCSV(arquivoTexto, arquivoCSV);
            System.out.println("Conversão concluída com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao converter o arquivo: " + e.getMessage());
        }
    }

    public static void converterTextoParaCSV(String arquivoTexto, String arquivoCSV) throws IOException {
        // Ler o arquivo de texto
        BufferedReader reader = new BufferedReader(new FileReader(arquivoTexto));
        String linha;

        // Criar uma lista para armazenar as linhas do CSV
        List<List<String>> linhasCSV = new ArrayList<>();

        // Converter cada linha em uma lista de valores
        while ((linha = reader.readLine()) != null) {
            String[] colunas = linha.split(" {2,}");

            // Criar uma nova lista para armazenar os valores da linha atual
            List<String> valoresLinha = new ArrayList<>();

            // Adicionar apenas os valores não vazios às células
            for (String valorColuna : colunas) {
                if (!valorColuna.isEmpty()) {
                    valoresLinha.add(valorColuna);
                }
            }

            // Adicionar a linha à lista de linhas do CSV
            linhasCSV.add(valoresLinha);
        }

        reader.close();

        // Criar um novo arquivo CSV
        FileWriter writer = new FileWriter(arquivoCSV);

        // Escrever as linhas do CSV
        for (List<String> valoresLinha : linhasCSV) {
            for (int i = 0; i < valoresLinha.size(); i++) {
                writer.append(valoresLinha.get(i));
                if (i < valoresLinha.size() - 1) {
                    writer.append(",");
                }
            }
            writer.append("\n");
        }

        writer.close();
    }
}

