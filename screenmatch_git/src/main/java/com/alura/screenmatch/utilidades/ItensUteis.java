package com.alura.screenmatch.utilidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.alura.screenmatch.model.DadosDaSerie;
import com.alura.screenmatch.model.DadosEpisodio;
import com.alura.screenmatch.model.DadosTemporada;
import com.alura.screenmatch.model.Episodio;
import com.alura.screenmatch.servico.ConsultarApi;
import com.alura.screenmatch.servico.FiltrarDados;

//endereço modelo: https://www.omdbapi.com/?t=gilmore+girls&season="+1+"&apikey=7b2e19101f"

public class ItensUteis {
	private static final String end_1 = "https://www.omdbapi.com/?t=";
	private static final String end_2 = "&apikey=7b2e191f";
	private static final String end_3 = "&season=";

	// Buscar todas temporadas de uma série(no caso,entrada pelo usuário) na api
	// omdb.
	private static List<DadosTemporada> ObterTemporadasDaSerie(String nomeDaSerie, FiltrarDados filtro,
			ConsultarApi buscarNaApi) {
		List<DadosTemporada> temporadas = new ArrayList<>();
		String enderecoDeBusca = "";
		String str = "";
		String strAux = nomeDaSerie;
		nomeDaSerie = nomeDaSerie.replace(" ", "+");
		enderecoDeBusca = end_1.concat(nomeDaSerie).concat(end_2);
		// System.out.println(enderecoDeBusca);
		str = buscarNaApi.obterDados(enderecoDeBusca); // Devolve uma string Json.
		System.out.println("\nstring json dos dados da série ".toUpperCase() + strAux.toUpperCase() + ":\n" + str);

		// dadosSerie contém,entre outras,a quantidade de temporadas.
		DadosDaSerie dadosSerie = filtro.obterDados(str, DadosDaSerie.class);

		System.out.println("\ndados filtrados da série ".toUpperCase() + strAux.toUpperCase() + ":\n" + dadosSerie);
		enderecoDeBusca = end_1.concat(nomeDaSerie).concat(end_3);
		// System.out.println(enderecoDeBusca);
		for (int i = 1; i < (dadosSerie.totalTemporadas() + 1); i++) {
			str = buscarNaApi.obterDados(enderecoDeBusca.concat(String.valueOf(i)).concat(end_2));
			DadosTemporada dadosTemporadas = filtro.obterDados(str, DadosTemporada.class);
			temporadas.add(dadosTemporadas);
		}
		/*
		 * System.out.println("\n\n>>>>>>>>> temporadas da série ".toUpperCase()+strAux.
		 * toUpperCase()+" <<<<<<<<<\n"); temporadas.forEach(System.out::println);
		 * 
		 * System.out.
		 * println("\n >>>>>>>>>>Título de cada Episódio de cada temporada <<<<<<<<<<<<<<<\nS"
		 * .toUpperCase()); temporadas.forEach( (temporada)
		 * ->temporada.episodios().forEach((episodio)->
		 * System.out.println(episodio.Titulo()))); * System.out.
		 * println("\n >>>>>>>>>>Título de cada Episódio de cada temporada <<<<<<<<<<<<<<<\n"
		 * .toUpperCase()); for(int i=0; i< dadosSerie.totalTemporadas(); i++) {
		 * List<DadosEpisodio> temp = temporadas.get(i).episodios(); for(int x = 0; x <
		 * temp.size(); x++ ) { System.out.println(temp.get(x).Titulo()); } }
		 */
		return temporadas;
	}

	public static List<Episodio> buscarEpisodios(String nomeDaSerie, FiltrarDados filtro, ConsultarApi buscarNaApi) {

		List<DadosTemporada> temporadas = ObterTemporadasDaSerie(nomeDaSerie, filtro, buscarNaApi);
		String strAux = nomeDaSerie;

		System.out.println("\n>>>>>>>> episódios,de todas temporadas da série ".toUpperCase() + strAux.toUpperCase()
				+ " <<<<<<<<\n");

		// Obter lista de episodios de todas temporadas.
		List<DadosEpisodio> listaDeEpisodios = temporadas.stream()
				.flatMap((temporada) -> temporada.episodios().stream()).collect(Collectors.toList());

		listaDeEpisodios.forEach(System.out::println);

		// Obter os cincos episódios de melhor avaliação de uma série.
		System.out.println("\n>>>>>>>> os cincos melhores episódios,de todas temporadas da série ".toUpperCase()
				+ strAux.toUpperCase() + " <<<<<<<<\n");

		// Há episodios com avaliacao N/A .
		listaDeEpisodios.stream().filter((episodio) -> !episodio.avaliacao().equalsIgnoreCase("n/a"))
				.sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed()).limit(5)
				.forEach(System.out::println);

		System.out.println("\n>>>>>>>> os episódios,e a temporada em que eles estão,da série ".toUpperCase()
				+ strAux.toUpperCase() + " <<<<<<<<\n");

		List<Episodio> episodios = temporadas.stream()
				.flatMap(temporada -> temporada.episodios().stream()
						.map(episodio -> new Episodio(temporada.numeroTemporada(), episodio)))
				.collect(Collectors.toList());

		episodios.forEach(System.out::println);

		// collect(Collectors.toList()) -> cria uma lista mutável
		// toList() -> cria uma lista imutável..

		return episodios; // Lista com todos episodios de todas temporadas de uma serie.
	}

	public static List<Episodio> obterEpisodiosPorDataLancamento(int ano, List<Episodio> listaDeEpisodios) {

	//	List<Episodio> listaDeEpisodios = buscarEpisodios(nomeDaSerie, filtro, buscarNaApi);
		LocalDate data = LocalDate.of(ano, 1, 1);
		System.out.println("\ndata entrada: ".toUpperCase() + data + "\n");
		List<Episodio> listaEpisodioPorDataLancamento = listaDeEpisodios.stream()
				.filter(episodio -> episodio.getDataLancamento().isAfter(data) && episodio.getDataLancamento() != null)
				.collect(Collectors.toList());

		return listaEpisodioPorDataLancamento;

	}
}

/*
 * episodio.getDataLancamento() != null -> para o caso vier data com N/A que
 * será atribuído 'null' na classe Episodio. 
 * isAfter(data) -> o que está depois
 * de data. collect(Collectors.toList()) -> produz uma lista mutável. toList()
 * -> produz uma lista imutável.
 */
//Scanner ler = new Scanner(System.in);
/*
 * System.out.println("\nEntre com nome da série que você quer: "); String
 * nomeDaSerie = ler.nextLine(); String enderecoDeBusca =
 * end_1.concat(nomeDaSerie.replace(" ", "+")).concat(end_2); var buscarNaApi =
 * new ConsultarApi(); String str = buscarNaApi.obterDados(enderecoDeBusca); //
 * Devolve uma string Json. System.out.println("\n str\n\n"); ConverterDados
 * converter = new ConverterDados();*
 */

/*
 * Dados de uma série em particular { "Title": "Gilmore Girls", "Year":
 * "2000–2007", "Rated": "TV-PG", "Released": "05 Oct 2000", "Runtime":
 * "57S min", "Genre": "Comedy, Drama", "Director": "N/A", "Writer":
 * "Amy Sherman-Palladino", "Actors":
 * "Lauren Graham, Alexis Bledel, Keiko Agena", "Plot":
 * "A dramedy centering around the relationship between a thirtysomething single mother and her teen daughter living in Stars Hollow, Connecticut."
 * , "Language": "English", "Country": "United States", "Awards":
 * "Won 1 Primetime Emmy. 23 wins & 85 nominations total", "Poster":
 * "https://m.media-amazon.com/images/M/MV5BMzM2OGZjMTItNzc2OS00YzA4LWE0ZTUtYzE5NjU0MTIyZjcyXkEyXkFqcGc@._V1_SX300.jpg",
 * "Ratings": [ { "Source": "Internet Movie Database", "Value": "8.2/10" } ],
 * "Metascore": "N/A", "imdbRating": "8.2", "imdbVotes": "153,310", "imdbID":
 * "tt0238784", "Type": "series", "totalSeasons": "7", "Response": "True" }
 */

/*
 * List<String> nomes =
 * ArrayList("maça","côco","uva","pera","goiaba","melão","abacaxi"."laranja",
 * "abacate","mamão")
 * 
 * nomes.stream().sorted().limit(4).filter(fruta ->
 * fruta.startsWith("a")).map(fruta ->
 * fruta.toUpperCase()).forEaxh(System.out::println)
 * 
 * stream -> Faz um fluxo com lista nomes. sorted -> ordena este fluxo em ordem
 * alfabética. limit -> limita este fluxo para 4 elementos. filter -> filtra
 * este fluxo de 4 elementos,selecionando os elementos que começam com a letra
 * "a". foreEach -> imprimi estes elementos.
 */
