package com.alura.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.OptionalDouble;

public class Serie {
	private String titulo;
	private String ano;
	private LocalDate anoLancamento;
	private  CATEGORIA genero;
	private String atores;
	private String sinopse;
	private String poster;		
	private Double avaliacao;
	private Integer totalTemporadas;
	
	public Serie(DadosDaSerie dadosSerie) {
		this.titulo = dadosSerie.Titulo();
		this.ano = dadosSerie.ano();		
		this.genero = CATEGORIA.fromString(dadosSerie.genero().split(",")[0].trim());
		this.atores = dadosSerie.atores();
		this.sinopse = dadosSerie.sinopse();
		this.poster = dadosSerie.poster();
		this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.Avaliacao())).orElse(0);// Caso o valor de avaliacao vier N/A .
		this.totalTemporadas = dadosSerie.totalTemporadas();
		
		try {
			this.anoLancamento = LocalDate.parse(dadosSerie.anoLancamento());
			System.out.println(">>>>>>> "+this.anoLancamento);
		} catch (DateTimeParseException ex) {
			this.anoLancamento = null;
		}
	}

	@Override
	public String toString() {
		return "genero = " + genero +", titulo = " + titulo + ", ano = " + ano + ", anoLancamento = " + anoLancamento 
				+ ",atores = " + atores + ",sinopse = " + sinopse + ", poster = " + poster + ", avaliacao = " + avaliacao
				+ ", totalTemporadas = " + totalTemporadas;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public LocalDate getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(LocalDate anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public CATEGORIA getGenero() {
		return genero;
	}

	public void setGenero(CATEGORIA genero) {
		this.genero = genero;
	}

	public String getAtores() {
		return atores;
	}

	public void setAtores(String atores) {
		this.atores = atores;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Integer getTotalTemporadas() {
		return totalTemporadas;
	}

	public void setTotalTemporadas(Integer totalTemporadas) {
		this.totalTemporadas = totalTemporadas;
	}	
	
}
