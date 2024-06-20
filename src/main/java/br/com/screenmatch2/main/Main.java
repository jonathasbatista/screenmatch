package br.com.screenmatch2.main;

import br.com.screenmatch2.model.DadosEpisodio;
import br.com.screenmatch2.model.DadosSerie;
import br.com.screenmatch2.model.DadosTemporada;
import br.com.screenmatch2.service.ConsumoAPI;
import br.com.screenmatch2.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private ConverteDados conversor = new ConverteDados();
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=1d3a96ea";
    public void exibeMenu(){
        System.out.println("\nDigite o nome da série: ");
        var nomeSerie = scanner.nextLine();
        var json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ","+") + API_KEY);
        DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dadosSerie);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++){
            json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ","+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
        }
        temporadas.forEach(System.out::println);

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
    }
}
