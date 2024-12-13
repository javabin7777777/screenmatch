package com.alura.screenmatch.model;

// Usado pelo campo de dados 'genero'  na classe Serie.
public enum CATEGORIA {
	ACAO("Action"), // Chama o construtor.
	ROMANCE("Romance"),
	COMEDIA("Comedy"),
	DRAMA("Drama"),
	CRIME("Crime");
	
	private String categoriaOmdb; 
	private CATEGORIA(String categoriaOmdb) {
		this.categoriaOmdb = categoriaOmdb;
	}
	
	public static CATEGORIA fromString(String texto) {
		for(CATEGORIA categoria: CATEGORIA.values()) { 
			if(categoria.categoriaOmdb.equalsIgnoreCase(texto)) return categoria;
		}
		throw new IllegalArgumentException("nenhuma categoria foi encontrada".toUpperCase());
	}
	
}

//Cada constante tem sua própria cópia desta variável de instância.
//CATEGORIA.VALUES() Devolve um array com os constantes.
