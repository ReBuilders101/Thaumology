package dev.thaumology.world;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * A Vertex is the endpoint of a linedef. 
 */
public class Vertex {
	
	protected Linedef l1, l2;
	protected Set<Linedef> otherLines;
	protected Set<Linedef> allLines; //calculated from otherlines + l1 + l2
	protected Map<Linedef, Double> angleMap;
	
	public Linedef getFirstLine() {
		return l1;
	}
	
	public Linedef getSecondLine() {
		return l2;
	}
	
	public Set<Linedef> getAllLines(){
		return allLines;
	}
	
	public Set<Linedef> getAllLinesExcept(Linedef exception){
		Set<Linedef> ret = new HashSet<>(allLines);
		ret.remove(exception);
		return ret;
	}
	
	public Set<Linedef> getNonSurfaceLines(){
		return otherLines;
	}
	
	public Linedef getOtherMainLine(Linedef mainLine) {
		if(mainLine == l1) return l2;
		if(mainLine == l2) return l1;
		return null;
	}
	
	public boolean hasNonSurfaceLines() {
		return !getNonSurfaceLines().isEmpty();
	}
	
	public boolean isSurfaceVertex() {
		return true;
	}
	
	public double getAngle(Linedef line) {
		return angleMap.get(line);
	}
	
	public double getAngle(Linedef from, Linedef to) {
		return getAngle(to) - getAngle(from);
	}
	
	public boolean hasLine(Linedef line) {
		return allLines.contains(line);
	}
	
	public Map<Linedef, Double> getAngleMap(){
		return angleMap;
	}
	
}
