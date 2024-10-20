package com.alura.screenmatch.servico;

public interface IConverterDados {
	<T> T obterDados(String strJson, Class<T> classe);	
}


/* 
 * String strJson -> String no formato Json(serial) que será convertida para objeto java conforme 
 * classe DadosDaSerie do pacote model.
 * Class<T> classe -> objeto java que recebe o resultado da conversão.
 */