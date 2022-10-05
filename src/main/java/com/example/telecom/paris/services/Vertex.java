package com.example.telecom.paris.services;

import java.util.Set;

import com.example.telecom.paris.interfaces.Graph;

public class Vertex implements com.example.telecom.paris.interfaces.Vertex {
	
	private final String name;
	
	private final Graph graph;

	public Vertex(final String name,
				  final Graph graph ) {
		this.name = name;
		this.graph = graph;
	}

	@Override
	public Set<com.example.telecom.paris.interfaces.Vertex> getSuccessors() {
		return graph.getSuccessors( this );
	}

	@Override
	public String getLabel() {
		return name;
	}
}
