package com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public  record DadosEpisodio( @JsonAlias("Title")String Titulo,
		@JsonAlias("Episode")Integer numeroEpisodio ,@JsonAlias("Released") String dataLancamento, 
		@JsonAlias("imdbRating")String avaliacao) {		
	
}
