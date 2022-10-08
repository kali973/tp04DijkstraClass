package com.example.telecom.paris.processor;

import com.example.telecom.paris.interfaces.Distance;
import com.example.telecom.paris.interfaces.ProcessedVertexesSet;
import com.example.telecom.paris.services.*;

import java.util.Iterator;

public class Dijkstra {

    public static void main(String[] args) {

        final Graph testGraph = new Graph();

        final int nbNodes = 10;

        for (int index = 0; index < nbNodes; index++) {
            testGraph.getVertexes().add(new Vertex(Integer.toString(index), testGraph));
        }

        final Iterator<com.example.telecom.paris.interfaces.Vertex> vertexes = testGraph.getVertexes().iterator();
        final com.example.telecom.paris.interfaces.Vertex startVertex = vertexes.next();
        final com.example.telecom.paris.interfaces.Vertex endVertex = testGraph.getVertex(Integer.toString(nbNodes - 2));
        com.example.telecom.paris.interfaces.Vertex pivotVertex = startVertex;

        while (vertexes.hasNext()) {
            testGraph.addEdge(pivotVertex, vertexes.next(), 1);
        }

        final com.example.telecom.paris.interfaces.ShortestPaths paths = findShortestPaths(testGraph,
                startVertex,
                endVertex,
                new VertexesSet(),
                new MinDistance(),
                testGraph);

        for (final com.example.telecom.paris.interfaces.Vertex vertex : paths.getShortestPath(startVertex, endVertex)) {
            System.out.print(vertex.getLabel() + " -> ");
        }
    }

    public static com.example.telecom.paris.interfaces.ShortestPaths findShortestPaths(com.example.telecom.paris.interfaces.Graph graph,
                                                                                       com.example.telecom.paris.interfaces.Vertex startVertex,
                                                                                       com.example.telecom.paris.interfaces.Vertex endVertex,
                                                                                       ProcessedVertexesSet processedVertexes,
                                                                                       com.example.telecom.paris.interfaces.MinDistance minDistance,
                                                                                       Distance distance) {
        processedVertexes.add(startVertex);
        com.example.telecom.paris.interfaces.Vertex pivotVertex = startVertex;
        minDistance.setMinDistance(startVertex, 0);

        for (com.example.telecom.paris.interfaces.Vertex vertex : graph.getVertexes()) {
            if (!startVertex.equals(vertex)) {
                minDistance.setMinDistance(vertex, Integer.MAX_VALUE);
            }
        }

        final com.example.telecom.paris.interfaces.ShortestPaths shortestPaths = new ShortestPaths();

        while (!processedVertexes.contains(endVertex)) {
            for (com.example.telecom.paris.interfaces.Vertex succVertex : pivotVertex.getSuccessors()) {
                int currentDistance = minDistance.getMinDistance(pivotVertex) + distance.getDistance(pivotVertex, succVertex);

                if (currentDistance < minDistance.getMinDistance(succVertex)) {
                    minDistance.setMinDistance(succVertex, currentDistance);
                }

                shortestPaths.setPrevious(succVertex, pivotVertex);
            }

            pivotVertex = minDistance.getMinDistanceVertex(processedVertexes, graph.getVertexes());
            processedVertexes.add(pivotVertex);
        }
        return shortestPaths;
    }
}
