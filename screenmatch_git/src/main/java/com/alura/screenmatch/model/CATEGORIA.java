package com.alura.screenmatch.model;

// Usado pelo campo de dados 'genero'  na classe Serie.
public enum CATEGORIA {
	ACAO("Action"), // Chama o construtor.
	ROMANCE("Romance"),
	COMEDIA("Comedy"),
	DRAMA("Drama"),
	CRIME("Crime");
	
	private String categoriaOmdb; // Cada constante tem sua própria cópia desta variável de instância.

	private CATEGORIA(String categoriaOmdb) {
		this.categoriaOmdb = categoriaOmdb;
	}
	
	public static CATEGORIA fromString(String texto) {
		for(CATEGORIA categoria: CATEGORIA.values()) { //CATEGORIA.VALUES() Devolve um array com os constantes.
			if(categoria.categoriaOmdb.equalsIgnoreCase(texto)) return categoria;
		}
		throw new IllegalArgumentException("nenhuma categoria foi encontrada".toUpperCase());
	}
	
}
