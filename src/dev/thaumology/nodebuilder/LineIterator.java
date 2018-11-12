package dev.thaumology.nodebuilder;

import java.util.Deque;
import java.util.Iterator;
import dev.thaumology.world.Linedef;

public class LineIterator implements Iterable<LineIteratorItem>{
	
	protected Deque<LineIteratorItem> items;
	protected Linedef line;
	
	public Deque<LineIteratorItem> getItems(){
		return items;
	}
	
	public Linedef getLinedef() {
		return line;
	}

	@Override
	public Iterator<LineIteratorItem> iterator() {
		return items.iterator();
	}

	public LineIterator(Deque<LineIteratorItem> items, Linedef line) {
		this.items = items;
		this.line = line;
	}
	
}
