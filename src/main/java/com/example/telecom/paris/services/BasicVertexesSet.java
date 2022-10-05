package com.example.telecom.paris.services;

import java.util.HashSet;
import java.util.Set;

import com.example.telecom.paris.ProcessedVertexesSet;
import com.example.telecom.paris.Vertex;

public class BasicVertexesSet implements ProcessedVertexesSet {
	
	final Set<Vertex> processedVertexes;
	
	public BasicVertexesSet() {
		processedVertexes = new HashSet<>();
	}

	@Override
	public void add(Vertex vertex) {
		processedVertexes.add( vertex );
	}

	@Override
	public boolean contains(Vertex vertex) {
		return processedVertexes.contains( vertex );
	}
}
