package com.lidersis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextToExcelConverter {

    public static void main(String[] args) {
        String arquivoTexto = "C:\\Users\\gustavo.fernandes\\Desktop\\AuditoriaLidersis Descktop Path.txt";
        String arquivoCSV = "tabela.csv";

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

        // Criar um novo arquivo CSV
        FileWriter writer = new FileWriter(arquivoCSV);

        // Converter cada linha em uma linha CSV
        while ((linha = reader.readLine()) != null) {
            String[] colunas = linha.split("\t|(?<=\\s{2,})");

            // Preencher cada célula com o valor da coluna correspondente
            for (int i = 0; i < colunas.length; i++) {
                String valorColuna = colunas[i];
                if (!valorColuna.isEmpty()) {
                    writer.append(valorColuna);
                }
                if (i < colunas.length - 1) {
                    writer.append(",");
                }
            }
            writer.append("\n");
        }

        reader.close();
        writer.close();
    }
}