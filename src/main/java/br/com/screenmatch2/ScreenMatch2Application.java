package br.com.screenmatch2;

import br.com.screenmatch2.model.DadosSerie;
import br.com.screenmatch2.service.ConsumoAPI;
import br.com.screenmatch2.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMatch2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatch2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();
		var json = consumoAPI.obterDados("https://www.omdbapi.com/?t=naruto&apikey=1d3a96ea");
		System.out.println("\nDados em formato JSON: "+ json);

		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println("\nDados convertidos: "+ dados +"\n");
	}
}
