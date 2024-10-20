package com.alura.screenmatch.utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.alura.screenmatch.model.DadosDaSerie;
import com.alura.screenmatch.model.DadosTemporada;
import com.alura.screenmatch.servico.ConsultarApi;
import com.alura.screenmatch.servico.FiltrarDados;

//endereço modelo: https://www.omdbapi.com/?t=gilmore+girls&season="+1+"&apikey=7b2e191f"

public class ItensUteis {
	Scanner ler = new Scanner(System.in);
	private static final String end_1 = " https://www.omdbapi.com/?t=";
	private static final String end_2 = "&apikey=7b2e191f";
	private static final String end_3 = "&season=";

	// Buscar todas temporadas de uma série(no caso,entrada pelo usuário) na api omdb.
	public static void ObterTemporadasDaSerie(String nomeDaSerie, FiltrarDados filtro, ConsultarApi buscarNaApi) {
		List<DadosTemporada> temporadas = new ArrayList<>();
		String enderecoDeBusca;
		String str;
		/*
		 * System.out.println("\nEntre com nome da série que você quer: "); String
		 * nomeDaSerie = ler.nextLine(); String enderecoDeBusca =
		 * end_1.concat(nomeDaSerie.replace(" ", "+")).concat(end_2); var buscarNaApi =
		 * new ConsultarApi(); String str = buscarNaApi.obterDados(enderecoDeBusca); //
		 * Devolve uma string Json. System.out.println("\n str\n\n"); ConverterDados
		 * converter = new ConverterDados();
		 */

		enderecoDeBusca = end_1.concat(nomeDaSerie.replace(" ", "+")).concat(end_2);
		str = buscarNaApi.obterDados(enderecoDeBusca); // Devolve uma string Json.
		System.out.println("\nstring json dados da série:  \n".toUpperCase() + str);
		DadosDaSerie dadosSerie = filtro.obterDados(str, DadosDaSerie.class); // dadosSerie contém,entre outras,a quantidade de temporadas.
		System.out.println("\n dados da série( filtrados ): \n\n".toUpperCase() + dadosSerie);
		enderecoDeBusca = end_1.concat(nomeDaSerie.replace(" ", "+")).concat(end_3);
		for (int i = 1; i < (dadosSerie.totalTemporadas() + 1); i++) {
			str = buscarNaApi.obterDados(enderecoDeBusca.concat(String.valueOf(i)).concat(end_2));
			DadosTemporada dadosTemporadas = filtro.obterDados(str, DadosTemporada.class);
			temporadas.add(dadosTemporadas);
		}

		temporadas.forEach(System.out::println);

	}
}
