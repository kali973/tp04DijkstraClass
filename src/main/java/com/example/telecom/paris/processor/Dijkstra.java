package com.example.telecom.paris.processor;

import java.util.Iterator;

import com.example.telecom.paris.*;
import com.example.telecom.paris.services.Graph;
import com.example.telecom.paris.services.MinDistance;
import com.example.telecom.paris.services.ShortestPaths;
import com.example.telecom.paris.services.Vertex;
import com.example.telecom.paris.services.VertexesSet;

public class Dijkstra {
	
	public static void main( String[] args ) {
		
		final Graph testGraph = new Graph();
		
		final int nbNodes = 10;
		
		for ( int index = 0; index < nbNodes; index++ ) {
			testGraph.getVertexes().add( new Vertex( Integer.toString( index ), testGraph ) );
		}
		
		final Iterator<com.example.telecom.paris.Vertex> vertexes = testGraph.getVertexes().iterator();
		final com.example.telecom.paris.Vertex startVertex = vertexes.next();
		final com.example.telecom.paris.Vertex endVertex = testGraph.getVertex( Integer.toString( nbNodes - 2 ) );
		com.example.telecom.paris.Vertex pivotVertex = startVertex;
		
		while ( vertexes.hasNext() ) {
			testGraph.addEdge( pivotVertex, vertexes.next(), 1 );
		}
		
		final com.example.telecom.paris.ShortestPaths paths = findShortestPaths( 	testGraph,
														startVertex, 
														endVertex, 
														new VertexesSet(),
														new MinDistance(),
														testGraph );
		
		for ( final com.example.telecom.paris.Vertex vertex : paths.getShortestPath( startVertex, endVertex  ) ) {
			System.out.print(vertex.getLabel() + " -> " ); 
		}
	}
	
	public static com.example.telecom.paris.ShortestPaths findShortestPaths(com.example.telecom.paris.Graph graph,
																			com.example.telecom.paris.Vertex startVertex,
																			com.example.telecom.paris.Vertex endVertex,
																			ProcessedVertexesSet processedVertexes,
																			com.example.telecom.paris.MinDistance minDistance,
																			Distance distance ) {
		processedVertexes.add( startVertex );
		com.example.telecom.paris.Vertex pivotVertex = startVertex;
		minDistance.setMinDistance(startVertex , 0 );
		
		for ( com.example.telecom.paris.Vertex vertex : graph.getVertexes() ) {
			if ( !startVertex.equals( vertex ) ) {
				minDistance.setMinDistance( vertex, Integer.MAX_VALUE );
			}
		}
		
		final com.example.telecom.paris.ShortestPaths shortestPaths = new ShortestPaths();
		
		while ( !processedVertexes.contains( endVertex ) ) {
			for ( com.example.telecom.paris.Vertex succVertex : pivotVertex.getSuccessors() ) {
				int currentDistance = minDistance.getMinDistance( pivotVertex ) + distance.getDistance( pivotVertex, succVertex );
				
				if ( currentDistance < minDistance.getMinDistance( succVertex ) ) {
					minDistance.setMinDistance( succVertex, currentDistance );
				}
				
				shortestPaths.setPrevious( succVertex, pivotVertex );
			}
			
			pivotVertex = minDistance.getMinDistanceVertex( processedVertexes, graph.getVertexes() );
			processedVertexes.add( pivotVertex );
		}
		return shortestPaths;
	}
}
