package com.example.telecom.paris.services;

import com.example.telecom.paris.interfaces.ProcessedVertexesSet;
import com.example.telecom.paris.interfaces.Vertex;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MinDistance implements com.example.telecom.paris.interfaces.MinDistance {

    private final Map<Vertex, Integer> minDistances;

    public MinDistance() {
        minDistances = new HashMap<>();
    }

    @Override
    public void setMinDistance(Vertex vertex,
                               int distance) {
        minDistances.put(vertex, distance);
    }

    @Override
    public int getMinDistance(Vertex vertex) {
        return minDistances.get(vertex);
    }

    @Override
    public Vertex getMinDistanceVertex(ProcessedVertexesSet processedVertexes,
                                       Set<Vertex> vertexes) {
        int minDistance = Integer.MAX_VALUE;
        Vertex minDistVertex = null;

        for (final Vertex vertex : vertexes) {
            if (!processedVertexes.contains(vertex)) {
                final int minDistanceVirtex = getMinDistance(vertex);

                if (minDistanceVirtex < minDistance) {
                    minDistance = minDistanceVirtex;
                    minDistVertex = vertex;
                }
            }
        }

        return minDistVertex;
    }
}
