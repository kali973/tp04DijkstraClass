package com.example.telecom.paris.interfaces;

import java.util.Set;

public interface Vertex {

    Set<Vertex> getSuccessors();

    String getLabel();
}
