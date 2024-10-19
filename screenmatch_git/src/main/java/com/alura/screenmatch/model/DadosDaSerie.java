package com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosDaSerie(@JsonAlias("Title") String Titulo,@JsonAlias("imdbRating") String Avaliacao,
		@JsonAlias("totalSeasons") Integer totalTemporadas) {

}

/*
 * @JsonIgnoreProperties(ignoreUnknown = true) -> Outros campos da string Json recebida,serão ignorados.
 * 
 */
 