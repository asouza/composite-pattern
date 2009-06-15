package br.com.settech.compositepattern;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

public class TestCompositeSchema {
	
	@Test
	public void testSelectPossibleFathers(){
		CompositePatternImpl impl1 = new CompositePatternImpl("impl1");
		CompositePatternImpl impl2 = new CompositePatternImpl("impl2");
		CompositePatternImpl impl3 = new CompositePatternImpl("impl3");
		CompositePatternImpl impl4 = new CompositePatternImpl("impl4");
		impl1.pai = impl2;
		impl2.filhos.add(impl1);
		impl1.filhos.add(impl3);
		impl3.pai = impl1;
		impl3.filhos.add(impl4);
		impl4.pai = impl3;
		Collection<CompositePatternImpl> list = new ArrayList<CompositePatternImpl>();
		list.add(impl1);
		list.add(impl2);
		list.add(impl3);
		list.add(impl4);
		CompositePatternHelper<CompositePatternImpl> helper = new CompositePatternHelper<CompositePatternImpl>(impl1);
		Collection<CompositePatternImpl> result =  helper.selectPossibleFathersIn(list);		
		Assert.assertEquals(1,result.size());
				
	}
	
	@Test
	public void testGetRelatives(){
		CompositePatternImpl impl1 = new CompositePatternImpl("impl1");
		CompositePatternImpl impl2 = new CompositePatternImpl("impl2");
		CompositePatternImpl impl3 = new CompositePatternImpl("impl3");		
		impl1.pai = impl2;
		impl2.pai = impl3;
		CompositePatternHelper<CompositePatternImpl> helper = new CompositePatternHelper<CompositePatternImpl>(impl1);
		ArrayList<CompositePatternImpl> result = new ArrayList<CompositePatternImpl>(helper.getRelativesList());
		Assert.assertEquals(2,result.size());
		Assert.assertEquals(impl3,result.get(0));
	}

	@Test
	public void testGetAllChildren(){
		CompositePatternImpl impl1 = new CompositePatternImpl("impl1");
		CompositePatternImpl impl2 = new CompositePatternImpl("impl2");
		CompositePatternImpl impl3 = new CompositePatternImpl("impl3");		
		CompositePatternImpl impl4 = new CompositePatternImpl("impl4");
		impl1.filhos.add(impl2);		
		impl2.filhos.add(impl3);
		impl3.filhos.add(impl4);
		impl2.pai = impl1;
		impl3.pai = impl2;
		impl4.pai = impl3;
		CompositePatternHelper<CompositePatternImpl> helper = new CompositePatternHelper<CompositePatternImpl>(impl1);
		Collection<CompositePatternImpl> result = helper.getAllChildren();
		System.out.println(result);
	}
	
	private class CompositePatternImpl implements CompositePattern<CompositePatternImpl>{
		
		Collection<CompositePatternImpl> filhos = new ArrayList<CompositePatternImpl>();
		CompositePatternImpl pai;
		String name;

		public CompositePatternImpl(String string) {
			// TODO Auto-generated constructor stub
			name = string;
		}

		public Collection<CompositePatternImpl> getChildren() {
			// TODO Auto-generated method stub
			return filhos;
		}

		public CompositePatternImpl getFather() {
			// TODO Auto-generated method stub
			return pai;
		}

		@Override
		public int hashCode() {
			final int PRIME = 31;
			int result = 1;
			result = PRIME * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final CompositePatternImpl other = (CompositePatternImpl) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return name;
		}
		
		
		
	}
}
