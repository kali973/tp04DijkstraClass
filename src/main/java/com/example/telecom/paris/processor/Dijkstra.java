package com.example.telecom.paris.processor;

import java.util.Iterator;

import com.example.telecom.paris.*;
import com.example.telecom.paris.services.Graph;
import com.example.telecom.paris.services.MinDistance;
import com.example.telecom.paris.services.ShortestPaths;
import com.example.telecom.paris.services.BasicVertex;
import com.example.telecom.paris.services.BasicVertexesSet;

public class Dijkstra {
	
	public static void main( String[] args ) {
		
		final Graph testGraph = new Graph();
		
		final int nbNodes = 10;
		
		for ( int index = 0; index < nbNodes; index++ ) {
			testGraph.getVertexes().add( new BasicVertex( Integer.toString( index ), testGraph ) );
		}
		
		final Iterator<Vertex> vertexes = testGraph.getVertexes().iterator();
		final Vertex startVertex = vertexes.next();
		final Vertex endVertex = testGraph.getVertex( Integer.toString( nbNodes - 2 ) );
		Vertex pivotVertex = startVertex;
		
		while ( vertexes.hasNext() ) {
			testGraph.addEdge( pivotVertex, vertexes.next(), 1 );
		}
		
		final com.example.telecom.paris.ShortestPaths paths = findShortestPaths( 	testGraph,
														startVertex, 
														endVertex, 
														new BasicVertexesSet(),
														new MinDistance(),
														testGraph );
		
		for ( final Vertex vertex : paths.getShortestPath( startVertex, endVertex  ) ) {
			System.out.print(vertex.getLabel() + " -> " ); 
		}
	}
	
	public static com.example.telecom.paris.ShortestPaths findShortestPaths(com.example.telecom.paris.Graph graph,
																			Vertex startVertex,
																			Vertex endVertex,
																			ProcessedVertexesSet processedVertexes,
																			com.example.telecom.paris.MinDistance minDistance,
																			Distance distance ) {
		processedVertexes.add( startVertex );
		Vertex pivotVertex = startVertex;
		minDistance.setMinDistance(startVertex , 0 );
		
		for ( Vertex vertex : graph.getVertexes() ) {
			if ( !startVertex.equals( vertex ) ) {
				minDistance.setMinDistance( vertex, Integer.MAX_VALUE );
			}
		}
		
		final com.example.telecom.paris.ShortestPaths shortestPaths = new ShortestPaths();
		
		while ( !processedVertexes.contains( endVertex ) ) {
			for ( Vertex succVertex : pivotVertex.getSuccessors() ) {
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
