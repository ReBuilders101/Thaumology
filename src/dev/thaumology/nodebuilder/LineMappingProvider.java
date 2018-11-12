package dev.thaumology.nodebuilder;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import dev.thaumology.math.MathUtils;
import dev.thaumology.world.Linedef;
import dev.thaumology.world.Vertex;
import dev.thaumology.world.World;

public class LineMappingProvider {
	
	public static final int CHUNK_DISTANCE = 3;
	
	protected World world;
	protected Map<Linedef, LineIterator> iterators;
	
	public LineIterator getIteratorFor(Linedef line) {
		if(!iterators.containsKey(line)) createAndRegisterIterator(line);
		return iterators.get(line);
	}
	
	protected void createAndRegisterIterator(Linedef line) {
		Queue<QueuedLinedef> toProcess = new LinkedList<>(); //LinkedList is the simplest Queue type
		Set<Vertex>  processedV = new HashSet<>(); //Hashset for fast contains() method, also no duplicates
		Set<Linedef> processedL = new HashSet<>(); //Hashset for fast contains() method, also no duplicates
		Deque<LineIteratorItem> result = new LinkedList<>(); //Deque representing a Stack
		
		//Create root
		LineIteratorItem root = new LineIteratorItem(line, line.getLength(), 0, MathUtils.DEGREE_90, 0, 0);
		//Both vertices
		line.getFirstVertex().getAllLinesExcept(line).forEach(l -> 
			toProcess.add(new QueuedLinedef(l, line, root, line.getFirstVertex(), CHUNK_DISTANCE)));
		line.getSecondVertex().getAllLinesExcept(line).forEach(l -> 
			toProcess.add(new QueuedLinedef(l, line, root, line.getSecondVertex(), CHUNK_DISTANCE)));
		//add them to the sets
		processedV.add(line.getFirstVertex());
		processedV.add(line.getSecondVertex());
		processedL.add(line);
		
		//Build until toProcess is empty
		QueuedLinedef current; //Currently polled line
		while((current = toProcess.poll()) != null) { 
			//Bulild current line into lineiteratoritem
			LineIteratorItem newItem = generateItem(current);
			result.push(newItem);
			//Add more lines
			Vertex unprocessed = current.getOtherVertex();
			if(processedV.contains(unprocessed)) {
				System.out.println("Vertex processing (Debug)!"); //log
			} else {
				int chd = unprocessed.isChunkSeparator() ?  current.getChunkDistance() - 1 : current.getChunkDistance(); //lower distance at separators
				if(chd > 0) { //If more chunks should be loaded
					//Add all lines
					for(Linedef l : unprocessed.getAllLinesExcept(current.getLineToProcess())) {
						if(processedL.contains(l)) {
							System.out.println("Linedef processing (Debug)!"); //log
						} else {
							toProcess.add(new QueuedLinedef(l, line, newItem, unprocessed, chd)); // add to build queue
						}
					}
				} else {
					System.out.println("Reached CHunk distance (Debug)!"); //log
				}
			}
		}
	}
	
	protected LineIteratorItem generateItem(QueuedLinedef qline) {
		double angle0 = qline.getConnectingVertex().getAngle(qline.getConnectingLine(), qline.getLineToProcess());
		double angle1 = qline.getConnectingItem().getAngle();
		double angle2 = angle0 + angle1 - MathUtils.DEGREE_180; 
		double dx = Math.cos(angle2) * qline.getLineToProcess().getLength();
		double dy = Math.sin(angle2) * qline.getLineToProcess().getLength();
		double x = qline.getConnectingItem().getX() + qline.getConnectingItem().getDx();
		double y = qline.getConnectingItem().getY() + qline.getConnectingItem().getDy();
		double g = 0; //TODO
		return new LineIteratorItem(qline.getLineToProcess(), dx, dy, g, x, y);
	}
	
}
