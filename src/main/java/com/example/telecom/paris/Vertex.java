package com.example.telecom.paris;

import java.util.Set;

public interface Vertex {

	Set<Vertex> getSuccessors();
	
	String getLabel();
}
