package dev.thaumology.nodebuilder;

import java.util.Iterator;
import java.util.List;

import dev.thaumology.world.Linedef;

public class LineIterator implements Iterable<LineIteratorItem>{
	
	protected List<LineIteratorItem> items;
	protected Linedef line;
	
	public List<LineIteratorItem> getItems(){
		return items;
	}
	
	public Linedef getLinedef() {
		return line;
	}

	@Override
	public Iterator<LineIteratorItem> iterator() {
		return items.iterator();
	}
	
}
