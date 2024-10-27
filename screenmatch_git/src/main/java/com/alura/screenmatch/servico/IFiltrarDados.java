package com.alura.screenmatch.servico;

public interface IFiltrarDados {
	<T> T obterDados(String strJson, Class<T> classe);	
}


/* 
 * String strJson -> String no formato Json(serial) que será convertida para objeto java conforme 
 * classe DadosDaSerie do pacote model.
 * Class<T> classe -> objeto java para o qual será convertida.
 */