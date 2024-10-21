package com.alura.screenmatch.utilidades;

import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;

import com.alura.screenmatch.model.DadosDaSerie;
import com.alura.screenmatch.model.DadosEpisodio;
import com.alura.screenmatch.model.DadosTemporada;
import com.alura.screenmatch.servico.ConsultarApi;
import com.alura.screenmatch.servico.FiltrarDados;

//endereço modelo: https://www.omdbapi.com/?t=gilmore+girls&season="+1+"&apikey=7b2e1910f"

public class ItensUteis {
	private static final String end_1 = "https://www.omdbapi.com/?t=";
	private static final String end_2 = "&apikey=7b2e1910f";
	private static final String end_3 = "&season=";

	// Buscar todas temporadas de uma série(no caso,entrada pelo usuário) na api omdb.
	public static void ObterTemporadasDaSerie(String nomeDaSerie, FiltrarDados filtro, ConsultarApi buscarNaApi) {
		List<DadosTemporada> temporadas = new ArrayList<>();
		String enderecoDeBusca="";
		String str="";	
		String strAux = nomeDaSerie;
		nomeDaSerie=nomeDaSerie.replace(" ","+");
		enderecoDeBusca = end_1.concat(nomeDaSerie).concat(end_2);
		//System.out.println(enderecoDeBusca);
		str = buscarNaApi.obterDados(enderecoDeBusca); // Devolve uma string Json.
		System.out.println("\nstring json dados da série:\n".toUpperCase() + str);
		DadosDaSerie dadosSerie = filtro.obterDados(str, DadosDaSerie.class); // dadosSerie contém,entre outras,a quantidade de temporadas.
		System.out.println("\ndados da série( filtrados ):\n".toUpperCase() + dadosSerie);
		enderecoDeBusca = end_1.concat(nomeDaSerie).concat(end_3);
		//System.out.println(enderecoDeBusca);
		for (int i = 1; i < (dadosSerie.totalTemporadas() + 1); i++) {
			str = buscarNaApi.obterDados(enderecoDeBusca.concat(String.valueOf(i)).concat(end_2));
			DadosTemporada dadosTemporadas = filtro.obterDados(str, DadosTemporada.class);
			temporadas.add(dadosTemporadas);
		}
		System.out.println("\n\n>>>>>>>>> temporadas da série ".toUpperCase()+strAux.toUpperCase()+" <<<<<<<<<\n");
		temporadas.forEach(System.out::println);
		
		System.out.println("\n >>>>>>>>>>Título de cada Episódio de cada temporada <<<<<<<<<<<<<<<\nS".toUpperCase());
		temporadas.forEach( (temporada) -> temporada.episodios().forEach((episodio)->
		System.out.println(episodio.Titulo())));
		
		System.out.println("\n >>>>>>>>>>Título de cada Episódio de cada temporada <<<<<<<<<<<<<<<\n".toUpperCase());
		for(int i=0; i< dadosSerie.totalTemporadas(); i++) {
			List<DadosEpisodio> temp = temporadas.get(i).episodios();
			for(int x = 0; x < temp.size(); x++ ) {
				System.out.println(temp.get(x).Titulo());
			}
		}

	}
}

//Scanner ler = new Scanner(System.in);
/*
 * System.out.println("\nEntre com nome da série que você quer: "); String
 * nomeDaSerie = ler.nextLine(); String enderecoDeBusca =
 * end_1.concat(nomeDaSerie.replace(" ", "+")).concat(end_2); var buscarNaApi =
 * new ConsultarApi(); String str = buscarNaApi.obterDados(enderecoDeBusca); //
 * Devolve uma string Json. System.out.println("\n str\n\n"); ConverterDados
 * converter = new ConverterDados();
 */
