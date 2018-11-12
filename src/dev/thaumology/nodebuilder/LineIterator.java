package dev.thaumology.nodebuilder;

import java.util.Deque;
import java.util.Iterator;
import dev.thaumology.world.Linedef;

public class LineIterator implements Iterable<LineIteratorItem>{
	
	protected Deque<LineIteratorItem> items;
	protected Linedef line;
	
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
	
	public LineIteratorItem peek() {
		return items.peek();
	}
	
	public LineIteratorItem pop() {
		return items.pop();
	}
	
}
