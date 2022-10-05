package com.example.telecom.paris.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.telecom.paris.Vertex;

public class ShortestPaths implements com.example.telecom.paris.ShortestPaths {
	
	private final Map<Vertex, Vertex> previousEdges;
	
	public ShortestPaths() {
		previousEdges = new HashMap<>();
	}

	@Override
	public void setPrevious(Vertex vertexEnd, Vertex vertexPrevious) {
		previousEdges.put( vertexEnd, vertexPrevious );
	}

	@Override
	public List<Vertex> getShortestPath(	Vertex startVertex,
											Vertex endVertex ) {
		final List<Vertex> shortestPath = new ArrayList<>();
		Vertex previous = endVertex;
		
		do {
			shortestPath.add( 0, previous );
			previous = previousEdges.get( previous );
		} while ( previous != null && !startVertex.equals( previous ) ); 
		
		shortestPath.add( 0, previous );
		
		return shortestPath;
	}
}
