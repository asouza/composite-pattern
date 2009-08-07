package br.com.settech.compositepattern;

import org.junit.Test;

public class TestIllegalState {

	@Test(expected=IllegalStateException.class)
	public void callAllChildren(){
		new CompositePatternHelper().getAllChildren();
	}
	
	@Test(expected=IllegalStateException.class)
	public void callRelativesList(){
		new CompositePatternHelper().getRelativesList();
	}
	
	@Test(expected=IllegalStateException.class)
	public void callPossibleFathers(){
		new CompositePatternHelper().selectPossibleFathersIn(null);
	}	
}
