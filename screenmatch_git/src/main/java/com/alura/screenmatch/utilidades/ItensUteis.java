package com.alura.screenmatch.utilidades;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.alura.screenmatch.model.DadosDaSerie;
import com.alura.screenmatch.model.DadosTemporada;
import com.alura.screenmatch.model.Episodio;
import com.alura.screenmatch.model.Serie;
import com.alura.screenmatch.servico.ConsultarApi;
import com.alura.screenmatch.servico.FiltrarDados;

//endereço modelo: https://www.omdbapi.com/?t=gilmore+girls&season="+1+"&apikey=7b2e19101f"

public class ItensUteis {
	private static final String end_1 = "https://www.omdbapi.com/?t=";
	private static final String end_2 = "&apikey=7b2e191f";
	private static final String end_3 = "&season=";
	private static List<DadosTemporada> temporadas;
	private static DadosDaSerie dadosSerie; // tipo record .
	private static List<Serie> serie = new ArrayList<>();
	private static Scanner entrada = new Scanner(System.in);

	/*
	 * @Override public String toString() { return "ItensUteis [getClass()=" +
	 * getClass() + ", hashCode()=" + hashCode() + ", toString()=" +
	 * super.toString() + "]"; }
	 */

	// Buscar todas temporadas de uma série(no caso,entrada pelo usuário) na api Omdb.
	private static List<DadosTemporada> ObterTemporadasDaSerie(String nomeDaSerie, FiltrarDados filtro,
		ConsultarApi buscarNaApi) {
		
		temporadas = new ArrayList<>();
		String enderecoDeBusca = "";
		String str = "";
		//String strAux = nomeDaSerie;
		nomeDaSerie = nomeDaSerie.replace(" ", "+");
		enderecoDeBusca = end_1.concat(nomeDaSerie).concat(end_2);
		str = buscarNaApi.obterDados(enderecoDeBusca); // Devolve uma string Json com todos dados da série.
		
		/*
		 *  System.out.println("\nstring json dos dados da série ".toUpperCase() + //
		 * strAux.toUpperCase() + ":\n" + str);
		 */
		
		// Filtra os dados da série de acordo com a classe(record) DadosDaSerie(aqui tem o total de temporadas).
		dadosSerie = filtro.obterDados(str, DadosDaSerie.class);
		
		/*
		 * DadosEpisodio ep = new DadosEpisodio("", -1, -1, "", "");
		 * System.out.println("\ndados filtrados da série ".toUpperCase() +
		 * strAux.toUpperCase() + ":\n" + dadosSerie); 
		 * System.out.println(enderecoDeBusca);
		 */		
		
		enderecoDeBusca = end_1.concat(nomeDaSerie).concat(end_3); 
		for (int i = 1; i < (dadosSerie.totalTemporadas() + 1); i++) {
			str = buscarNaApi.obterDados(enderecoDeBusca.concat(String.valueOf(i)).concat(end_2));
			DadosTemporada dadosTemporadas = filtro.obterDados(str, DadosTemporada.class);
			temporadas.add(dadosTemporadas);
		}
		/*
		System.out.println("\n\n>>>>>>>>> temporadas da série ".toUpperCase() + strAux.toUpperCase() + " <<<<<<<<<\n");
		temporadas.forEach(System.out::println);
		System.out.println();
		*/

		return temporadas;

		/*
		 * System.out.println(
		 * "\n<<<<<<<<<<<<<<<<<<temporadas>>>>>>>>>>>>>>>>>>>>>>>>>>>S\n".toUpperCase())
		 * ; for(int i=0; i < temporadas.size(); i++) { System.out.println();
		 * System.out.println(temporadas.get(i).episodios()); }
		 * System.out.println("\n"); temporadas.forEach((temp)->
		 * {System.out.println();System.out.println(temp);});
		 * System.out.println("\n\n>>>>>>>>> temporadas da série ".toUpperCase()+strAux.
		 * toUpperCase()+" <<<<<<<<<\n"); temporadas.forEach(System.out::println); *
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

	}
	
	public static void exibirMenu(FiltrarDados filtro, ConsultarApi buscarNaApi) {		
		while (true) {			
			System.out.println("\nEntre com a sua opção");
			System.out.println("\n1.dados da série".toUpperCase());
			System.out.println("2.dados dos episódios".toUpperCase());
			System.out.println("3.listar séries".toUpperCase());
			System.out.println("0.sair\n".toUpperCase());
			int opcao = entrada.nextInt();
			entrada.nextLine();
			if (opcao == 0)
				break;
			if(opcao == 1) {
				System.out.println("Entre com o nome da série: ");
				String nomeDaSerie = entrada.nextLine();				
				ObterTemporadasDaSerie(nomeDaSerie, filtro, buscarNaApi);
				serie.add(new Serie(dadosSerie));
				System.out.println("\ndados da série ".toUpperCase()+nomeDaSerie.toUpperCase()+"\n"+dadosSerie+"\n");
				// transformou a lista dadosSerie para objeto da classe Serie,e impressão deste objeto.
				System.out.println("\ndados da série(objeto Serie) ".toUpperCase()+nomeDaSerie.toUpperCase()
				+"\n"+new Serie(dadosSerie)+"\n");			
				//System.out.println("\ndados da série(lista) ".toUpperCase()+nomeDaSerie.toUpperCase()+"\n"+serie+"\n");
			}
			if(opcao == 2) {
				System.out.println("Entre com o nome da série: ");
				String nomeDaSerie = entrada.nextLine();					
				System.out.println("\ndados dos episódios da série ".toUpperCase()+nomeDaSerie.toUpperCase()+"\n"
				+buscarEpisodios(nomeDaSerie, filtro, buscarNaApi)+"\n");
			}
			if(opcao == 3) listarSeries();
		}
		System.out.println("\naté mais,tchau.".toUpperCase());
		entrada.close();
	}	

	private static void listarSeries() {
		System.out.println("\n >>>>>>> lista das séries <<<<<<<<\n".toUpperCase());
		//System.out.println(serie+"\n");
		serie.stream()
		.sorted(Comparator.comparing(Serie::getGenero))
		.forEach(System.out::println);
	
		/*		
		System.out.println(serie.stream()
		.map(elemento -> Comparator.comparing(Serie::getGenero))
		.collect(Collectors.toList()));
		imprimir: [java.util.Comparator$$Lambda/0x00000257c10c7ec0@6b2e0f78, 
		java.util.Comparator$$Lambda/0x00000257c10c7ec0@240f6c41] Uma lista de referências.
		*/	
	}

	private static List<Episodio> buscarEpisodios(String nomeDaSerie, FiltrarDados filtro, ConsultarApi buscarNaApi) {

		List<DadosTemporada> temporadas = ObterTemporadasDaSerie(nomeDaSerie, filtro, buscarNaApi);
		
		List<Episodio> episodios = temporadas.stream()
				.flatMap(temporada -> temporada.episodios().stream()
						.map(episodio -> new Episodio(temporada.numeroTemporada(), episodio)))
				.collect(Collectors.toList());
		
		return episodios; // Lista com todos episodios de todas temporadas de uma serie.
		
		/*
		Obter a lista de episodios.
		List<DadosEpisodio> listaDeEpisodios = temporadas.stream()
				.flatMap((temporada) -> temporada.episodios().stream()).collect(Collectors.toList());

		System.out.println("\n>>>>>>>> os episódios,e a temporada em que eles estão,da série ".toUpperCase()
				+ nomeDaSerie.toUpperCase() + " <<<<<<<<\n");

	
		 * Conversão da listaDeEpisodios(DadosEpisodio) para lista
		 * episodios(Episodio),para que possa ser inserido o numero da temporada do
		 * episodio,resultando em uma nova lista de objetos,pois,os atributos da
		 * listaDeEpisodios são todos 'final',pelo fato de serem oriundos de uma classe
		 * tipo 'record'.Esta última não possui setters,somente getters.		
		
		episodios.forEach(System.out::println);

		System.out.println("lista de episódios: \n".toUpperCase()+episodios);

		Obter os cincos episódios de melhor avaliação de uma série,através da lista
		Episodios(tipo Episodio) .
		System.out.println("\n>>>>>>>> os cincos melhores episódios,de todas temporadas da série ".toUpperCase()
			+ nomeDaSerie.toUpperCase() + " <<<<<<<<\n");

		 Uso de println .
			System.out.println(
				episodios.stream().sorted(Comparator.comparing(Episodio::getAvaliacao).reversed()).limit(5) + "\n");

		Uso do foreach .Aqui não foi filtrado,pois avaliação foi mapeada para 0.0,ao
		ser criados os objetos da classe Episodio.
		
		episodios.stream().sorted(Comparator.comparing(Episodio::getAvaliacao).reversed()).limit(5)
				.forEach(System.out::println);
		

		 Obter os cincos episódios de melhor avaliação de uma série,através da lista
		 através da lista listaDeEpisodios(tipo DadosEpisodio) .
		 System.out.println("\n>>>>>>>> os cincos melhores episódios,de todas temporadas da série ".toUpperCase()
				+ nomeDaSerie.toUpperCase() + " <<<<<<<<\n");
		
		listaDeEpisodios.stream().filter((episodio) -> !episodio.avaliacao().equalsIgnoreCase("n/a")) // Há episodios
																										// com avaliacao
																										// N/A .
				.sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed()).limit(5)
				.forEach(System.out::println);
		*/

		
	}

	public static List<Episodio> buscarEpisodiosPorDataLancamento(int ano, List<Episodio> listaDeEpisodios) {
		LocalDate data = LocalDate.of(ano, 1, 1);
		System.out.println("\ndata entrada: ".toUpperCase() + data + "\n");
		List<Episodio> listaEpisodioPorDataLancamento = listaDeEpisodios.stream()
				.filter(episodio -> episodio.getDataLancamento() != null && episodio.getDataLancamento().isAfter(data))
				.collect(Collectors.toList());
		return listaEpisodioPorDataLancamento;
	}

	public static void encontrarEpisodio(List<Episodio> listaDeEpisodios, String titulo) {
		Optional<Episodio> episodioEncontrado = listaDeEpisodios.stream()
				.filter(elemento -> elemento.getTitulo().toUpperCase().contains(titulo.toUpperCase())).findFirst();// Encontra
																													// a
																													// primeira
																													// ocorrência.
		if (episodioEncontrado.isPresent()) {
			// System.out.println("\n"+episodioEncontrado.get().getTitulo()+" encontrado na
			// temporada - "
			// .toUpperCase()+episodioEncontrado.get().getNumeroDaTemporada());

			System.out.println("\ntitulo encontrado: ".toUpperCase() + episodioEncontrado);
		} else
			System.out.println("\ntitulo não encontrado: ".toUpperCase());
	}

	public static Map<Integer, Double> obterMediaDaAvaliacaoDeCadaTemporada(List<Episodio> episodios) {
		var Simbolo = new DecimalFormatSymbols(Locale.ENGLISH);
		DecimalFormat f = new DecimalFormat("##.####", Simbolo);
		Map<Integer, Double> avaliacaoPorTemporada = episodios.stream()
				.filter(elemento -> elemento.getAvaliacao() > 0.0).collect(Collectors.groupingBy(
						Episodio::getNumeroDaTemporada, Collectors.averagingDouble(Episodio::getAvaliacao)));

		// Aproximação dos valores das médias.
		for (Integer chave : avaliacaoPorTemporada.keySet()) {
			// Double media = Double.parseDouble(f.format(avaliacaoPorTemporada.get(chave)).replace(",","."));
			Double media = Double.parseDouble(f.format(avaliacaoPorTemporada.get(chave)));
			avaliacaoPorTemporada.put(chave, media);
		}

		return avaliacaoPorTemporada;
	}

	public static DoubleSummaryStatistics obterEstatisticas(List<Episodio> episodios) {
		DoubleSummaryStatistics estatisticas = episodios.stream().filter(elemento -> elemento.getAvaliacao() > 0.0)
				.collect(Collectors.summarizingDouble(Episodio::getAvaliacao));
		return estatisticas;
	}
}

	

/*
 * collect(Collectors.toList()) -> cria uma lista mutável 
 * toList() -> cria uma lista imutável.
 * 
 * System.out.println("\n>>>>>>>> episódios,de todas temporadas da série "
 * .toUpperCase() + strAux.toUpperCase() + " <<<<<<<<\n"); Obter lista de
 * episodios de todas temporadas.
 * 
 * listaDeEpisodios.forEach(System.out::println);
 * 
 * 
 * episodio.getDataLancamento() != null -> para o caso vier data com N/A que
 * será atribuído 'null' na classe Episodio. isAfter(data) -> o que está depois
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
 * fruta.toUpperCase()).forEach(System.out::println)
 * 
 * stream -> Faz um fluxo com lista nomes. sorted -> ordena este fluxo em ordem
 * alfabética. limit -> limita este fluxo para 4 elementos. filter -> filtra
 * este fluxo de 4 elementos,selecionando os elementos que começam com a letra
 * "a". foreEach -> imprimi estes elementos.
 */
