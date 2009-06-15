package br.com.settech.compositepattern;

import java.util.Collection;

public interface CompositePattern<T> {

	public T getFather();
	
	public Collection<T> getChildren();
	
}
