package com.alura.screenmatch;

import java.text.DecimalFormat;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alura.screenmatch.model.Episodio;
import com.alura.screenmatch.servico.ConsultarApi;
import com.alura.screenmatch.servico.FiltrarDados;
import com.alura.screenmatch.utilidades.ItensUteis;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner ler = new Scanner(System.in);
		DecimalFormat f = new DecimalFormat("##.000");
		System.out.println("\n\n>>>>>>>>>>>> projeto screenmath - séries do omdb <<<<<<<<<<<\n\n".toUpperCase());
		System.out.println("Entre com nome da série que você quer: ".toUpperCase());
		String nomeDaSerie = ler.nextLine();
		var consultaApi = new ConsultarApi();
		FiltrarDados filtro = new FiltrarDados();
		List<Episodio> listaDeEpisodios = ItensUteis.buscarEpisodios(nomeDaSerie, filtro, consultaApi);		
		System.out.println("\nEntre com o ano da série que você quer: ".toUpperCase());
		int ano = ler.nextInt();
		ler.nextLine();
		ItensUteis.buscarEpisodiosPorDataLancamento(ano, listaDeEpisodios ).forEach(System.out::println);
		System.out.println("\nEntre com o título do episódio: ".toUpperCase());
		var titulo = ler.nextLine();
		ItensUteis.encontrarEpisodio(listaDeEpisodios, titulo);
		System.out.println("\n>>>>>>>>>>>>>>>>>>> média da avaliação das temporadas <<<<<<<<<<<<<<<<<<<<\n".toUpperCase());
		System.out.println(ItensUteis.obterMediaDaAvaliacaoDeCadaTemporada(listaDeEpisodios));
		System.out.println("\n>>>>>>>>>>>>>>>>>>> estatísticas <<<<<<<<<<<<<<<<<<<<\n".toUpperCase());
		System.out.println(ItensUteis.obterEstatisticas(listaDeEpisodios));
		DoubleSummaryStatistics estatisticas = ItensUteis.obterEstatisticas(listaDeEpisodios);
		System.out.println("\n quantidade: ".toUpperCase()+ estatisticas.getCount());
		System.out.println("\n mínimo: ".toUpperCase()+ f.format(estatisticas.getMin()));
		System.out.println("\n máximo: ".toUpperCase()+ f.format(estatisticas.getMax()));
		System.out.println("\n média: ".toUpperCase()+ f.format(estatisticas.getAverage()));
		ler.close();		
	}

}


/*
 * DadosDaSerie dadosSerie = converter.obterDados(str, DadosDaSerie.class);
 * String str="";
 * 
 * str = consultaApi.obterDados(
 * "http://www.omdbapi.com/?t=gilmore+girls&apikey=7b2e191f"); DadosDaSerie
 * dadosSerie = converter.obterDados(str, DadosDaSerie.class);
 * System.out.println(); System.out.println("String JSON SERIE: " + str);
 * System.out.println();
 * System.out.println("JSON DESSERIALIZADO - DADOS SÉRIE: " + dadosSerie); //str
 * = consultaApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1"+
 * "&episode=1&apikey=7b2e191f"); DadosEpisodio dadosEpisodio =
 * converter.obterDados(str, DadosEpisodio.class); // Filtra a string Json str.
 * System.out.println("\n"); System.out.println("String JSON EPISODIO: " + str);
 * System.out.println();
 * System.out.println("JSON DESSERIALIZADO - DADOS EPISODIO: " + dadosEpisodio);
 * 
 * /* Buscar todas temporadas de uma série(no caso gilmore girls) na api omdb.
 * List<DadosTemporada> temporadas = new ArrayList<>(); for(int i=1; i<
 * (dadosSerie.totalTemporadas()+1); i++) { str =
 * consultaApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season="+i+
 * "&apikey=7b2e191f"); DadosTemporada dadosTemporadas =
 * converter.obterDados(str, DadosTemporada.class);
 * temporadas.add(dadosTemporadas); }
 * System.out.println(">>>>>>>>> dados temporadas <<<<<<<<".toUpperCase());
 * System.out.println(); System.out.println(temporadas); System.out.println();
 * System.out.println(); temporadas.forEach(System.out::println);
 * System.out.println(">>>>>>>>>>>>>><<<<<<<<<<<<<<<");
 * temporadas.forEach((temp)-> System.out.println(temp));
 */

/*
 * var str = consultaApi.obterDados(
 * "http://www.omdbapi.com/?t=gilmore+girls&Seasons=1&apikey=7b2e191f");
 * System.out.println(); System.out.println(str); str =
 * consultaApi.obterDados("http://www.omdbapi.com/?i=tt3896198&apikey=7b2e191f")
 * ; System.out.println(); System.out.println(str); str =
 * consultaApi.obterDados("https://coffee.alexflipnote.dev/random.json");
 * System.out.println(); System.out.println(str);
 */