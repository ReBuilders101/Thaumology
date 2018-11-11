package dev.thaumology.world;

import java.util.Map;

import dev.thaumology.nodebuilder.LineIterator;

public class LineMappingProvider {
	
	protected World world;
	protected Map<Linedef, LineIterator> iterators;
	
	public LineIterator getIteratorFor(Linedef line) {
		if(!iterators.containsKey(line)) createAndRegisterIterator(line);
		return iterators.get(line);
	}
	
	protected void createAndRegisterIterator(Linedef line) {
		
	}
	
}
