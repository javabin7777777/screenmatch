package com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosDaSerie(
		@JsonAlias("Title") String Titulo,
		@JsonAlias("Year") String ano,
		@JsonAlias("Released") String anoLancamento,
		@JsonAlias("Genre") String genero,
		@JsonAlias("Actors") String atores,
		@JsonAlias("Plot") String sinopse,
		@JsonAlias("Poster") String poster,		
		@JsonAlias("imdbRating") String Avaliacao,
		@JsonAlias("totalSeasons") Integer totalTemporadas) {

}

/*
 * @JsonIgnoreProperties(ignoreUnknown = true) -> Outros campos da string Json recebida,serão ignorados.
 * "Title": "Gilmore Girls",
  "Year": "2000–2007",
  "Rated": "TV-PG",
  "Released": "05 Oct 2000",
  "Runtime": "57S min",
  "Genre": "Comedy, Drama",
  "Director": "N/A",
  "Writer": "Amy Sherman-Palladino",
  "Actors": "Lauren Graham, Alexis Bledel, Keiko Agena",
  "Plot": "A dramedy centering around the relationship between a thirtysomething single mother and her teen daughter living in Stars Hollow, Connecticut.",
  "Language": "English",
  "Country": "United States",
  "Awards": "Won 1 Primetime Emmy. 23 wins & 85 nominations total",
  "Poster": "https://m.media-amazon.com/images/M/MV5BMzM2OGZjMTItNzc2OS00YzA4LWE0ZTUtYzE5NjU0MTIyZjcyXkEyXkFqcGc@._V1_SX300.jpg",
  "Ratings": [
    {
      "Source": "Internet Movie Database",
      "Value": "8.2/10"
    }
  ],
  "Metascore": "N/A",
  "imdbRating": "8.2",
  "imdbVotes": "153,310",
  "imdbID": "tt0238784",
  "Type": "series",
  "totalSeasons": "7",
 */
 