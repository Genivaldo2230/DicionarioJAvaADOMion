import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
/*
Entrega a Atividade 2 - Algoritmos e Programação II

Eu

Nome completo: Genivaldo Alves dos Anjos
declaramos que
todas as respostas são fruto de nosso próprio trabalho,
não copiamos respostas de colegas externos à equipe,
não disponibilizamos nossas respostas para colegas externos ao grupo e
não realizamos quaisquer outras atividades desonestas para nos beneficiar ou prejudicar outros.
*/


public class Dicionario {
    private String[] palavras;
    private int tamanho;

    /**
     * Construtor da classe Dicionario.
     * Inicializa o vetor de palavras com a capacidade especificada.
     *
     * @param capacidade capacidade do vetor de palavras
     */
    public Dicionario(int capacidade) {
        palavras = new String[capacidade];
        tamanho = 0;
    }

    /**
     * Realiza busca binária na lista de palavras para verificar se a palavra já existe.
     *
     * @param palavra palavra a ser buscada
     * @return true se a palavra existe no dicionário, false caso contrário
     */
    public boolean buscaBinaria(String palavra) {
        int inicio = 0;
        int fim = tamanho - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;

            int resultado = palavra.compareToIgnoreCase(palavras[meio]);

            if (resultado == 0) {
                return true; // palavra encontrada
            } else if (resultado < 0) {
                fim = meio - 1;
            } else {
                inicio = meio + 1;
            }
        }

        return false; // palavra não encontrada
    }

    /**
     * Insere a palavra no dicionário, caso ela ainda não exista.
     *
     * @param palavra palavra a ser inserida
     */
    public void inserirPalavra(String palavra) {
        if (!buscaBinaria(palavra)) {
            if (tamanho == palavras.length) {
                palavras = Arrays.copyOf(palavras, palavras.length * 2);
            }

            int posicao = tamanho;

            while (posicao > 0 && palavra.compareToIgnoreCase(palavras[posicao - 1]) < 0) {
                palavras[posicao] = palavras[posicao - 1];
                posicao--;
            }

            palavras[posicao] = palavra;
            tamanho++;
        }
    }

    /**
     * Imprime todas as palavras do dicionário em ordem alfabética, uma palavra por linha.
     * No final da lista, imprime o N de palavras diferentes no dicionário.
     * e quantidade de palavras Foram lidas total de N palavras nesse dicionario.
     */
    public void imprimirPalavras() {
        for (int i = 0; i < tamanho; i++) {
            System.out.println(palavras[i].toLowerCase());
        }
        System.out.println( );
        System.out.println("Total de palavras diferentes no dicionario: " + tamanho);
        System.out.println( );
    }

    public static void main(String[] args) {
        Dicionario dicionario = new Dicionario(1000);

        int contadorPalavras = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("arquivo/arquivo.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] palavrasLinha = linha.split("[^a-zA-Z]+");

                for (String palavra : palavrasLinha) {
                    if (palavra.length() > 0) {
                        dicionario.inserirPalavra(palavra);
                        contadorPalavras++;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            System.exit(1);
        }

        dicionario.imprimirPalavras();
        System.out.println("Foram lidas total de " + contadorPalavras + " palavras nesse dicionario.");
    }
}