package com.alura.screenmatch.servico;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FiltrarDados implements IFiltrarDados {
	private ObjectMapper conversor = new ObjectMapper();

	@Override
	public <T> T obterDados(String strJson, Class<T> classe) {
		
		try {
			return conversor.readValue(strJson, classe);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

/* converter fromJson para objeto java(USO DA RECORD),faz uso da classe  ObjectMapper*/