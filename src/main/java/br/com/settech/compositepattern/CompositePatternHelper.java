package br.com.settech.compositepattern;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Class that helps with usual methods when we have a composite pattern
 * 
 * @author Alberto Pc
 * 
 * @param <T>
 */
public class CompositePatternHelper<T extends CompositePattern> {

	private T target;

	public CompositePatternHelper(T target) {
		super();
		this.target = target;
	}

	/**
	 * Choose the possible fathers for some compositeItm.
	 * 
	 * @param target
	 * @param candidates
	 * @return possible fathers
	 */
	public Collection<T> selectPossibleFathersIn(Collection<T> candidates) {
		Collection<T> fathers = new ArrayList<T>();
		for (T candidate : candidates) {
			if (!candidate.equals(target)) {
				if (candidate.getFather() == null) {
					fathers.add(candidate);
				} else {
					if (checkIfCandidateIsNotATargetChild(candidate, target,
							true)) {
						fathers.add(candidate);
					}
				}
			}
		}
		return fathers;
	}

	/**
	 * If a target has a father and grandfather, the return is a list ordered
	 * from grandfather to father...
	 * 
	 * @param target
	 * @return collection with higher relatives
	 */
	public Collection<T> getRelativesList() {
		Collection<T> relatives = new ArrayList<T>();
		getRelativesList((T) target.getFather(), relatives);
		return relatives;
	}
	
	

	private void getRelativesList(T target, Collection<T> relatives) {
		// TODO Auto-generated method stub
		if (target.getFather() != null) {
			getRelativesList((T) target.getFather(), relatives);
		}
		relatives.add(target);

	}

	private boolean checkIfCandidateIsNotATargetChild(T candidate, T target,
			boolean stillNeedToBeChecked) {
		// TODO Auto-generated method stub
		if (stillNeedToBeChecked && target.getChildren().contains(candidate)) {
			stillNeedToBeChecked = false;
		}
		for (T child : (Collection<T>) target.getChildren()) {
			if (stillNeedToBeChecked && child.getChildren().size() > 0) {
				stillNeedToBeChecked = checkIfCandidateIsNotATargetChild(
						candidate, child, stillNeedToBeChecked);
			}
		}
		return stillNeedToBeChecked;
	}

	/**
	 * 
	 * @param deep 
	 * @return
	 */
	public Collection<T> getAllChildren() {
		// TODO Auto-generated method stub
		Collection<T> children = new ArrayList<T>();
		getAllChildren(target,children);
		return children;
	}
	
	

	private void getAllChildren(T target,Collection<T> children) {
		// TODO Auto-generated method stub		
		for(T child : (Collection<T>)target.getChildren()){
			children.add(child);
			if(child.getChildren().size()>0){
				getAllChildren(child,children);				
			}						
		}
	}

}
