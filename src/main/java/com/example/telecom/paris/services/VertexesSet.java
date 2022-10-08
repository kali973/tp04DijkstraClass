package com.example.telecom.paris.services;

import com.example.telecom.paris.interfaces.ProcessedVertexesSet;
import com.example.telecom.paris.interfaces.Vertex;

import java.util.HashSet;
import java.util.Set;

public class VertexesSet implements ProcessedVertexesSet {

    final Set<Vertex> processedVertexes;

    public VertexesSet() {
        processedVertexes = new HashSet<>();
    }

    @Override
    public void add(Vertex vertex) {
        processedVertexes.add(vertex);
    }

    @Override
    public boolean contains(Vertex vertex) {
        return processedVertexes.contains(vertex);
    }
}
