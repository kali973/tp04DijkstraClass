package com.example.telecom.paris.services;

import java.util.Set;

import com.example.telecom.paris.Vertex;
import com.example.telecom.paris.Graph;

public class BasicVertex implements Vertex {
	
	private final String name;
	
	private final Graph graph;

	public BasicVertex( final String name,
						final Graph graph ) {
		this.name = name;
		this.graph = graph;
	}

	@Override
	public Set<Vertex> getSuccessors() {
		return graph.getSuccessors( this );
	}

	@Override
	public String getLabel() {
		return name;
	}
}
