package com.alura.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
	private Integer numeroDaTemporada;
	private String Titulo;
	private Integer numeroEpisodio;
	private LocalDate dataLancamento;
	private Double avaliacao;

	public Episodio(Integer numeroDaTemporada, DadosEpisodio episodios) {
		this.numeroDaTemporada = numeroDaTemporada;
		Titulo = episodios.Titulo();
		this.numeroEpisodio = episodios.numeroEpisodio();
		//System.out.println("aqui"+episodios.dataLancamento());
		
		try {
			this.dataLancamento = LocalDate.parse(episodios.dataLancamento());
			//System.out.println("aqui-"+this.dataLancamento);
		} catch (DateTimeParseException ex) {
			this.dataLancamento = null;
		}
		
		try {
			this.avaliacao = Double.parseDouble(episodios.avaliacao());
		} catch (NumberFormatException ex) {
			this.avaliacao = 0.0;
		}
		
	}

	/**
	 * @return the numeroDaTemporada
	 */
	public Integer getNumeroDaTemporada() {
		return numeroDaTemporada;
	}

	/**
	 * @param numeroDaTemporada the numeroDaTemporada to set
	 */
	public void setNumeroDaTemporada(Integer numeroDaTemporada) {
		this.numeroDaTemporada = numeroDaTemporada;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return Titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	/**
	 * @return the numeroEpisodio
	 */
	public Integer getNumeroEpisodio() {
		return numeroEpisodio;
	}

	/**
	 * @param numeroEpisodio the numeroEpisodio to set
	 */
	public void setNumeroEpisodio(Integer numeroEpisodio) {
		this.numeroEpisodio = numeroEpisodio;
	}

	/**
	 * @return the dataLancamento
	 */
	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	/**
	 * @param dataLancamento the dataLancamento to set
	 */
	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	/**
	 * @return the avaliacao
	 */
	public Double getAvaliacao() {
		return avaliacao;
	}

	/**
	 * @param avaliacao the avaliacao to set
	 */
	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}

	@Override
	public String toString() {
		return "Episodio [numeroDaTemporada= " + numeroDaTemporada + ", Titulo= " + Titulo + ", numeroEpisodio= "
				+ numeroEpisodio + ", dataLancamento= " + dataLancamento + ", avaliacao= " + avaliacao + "]\n";
	}

}
