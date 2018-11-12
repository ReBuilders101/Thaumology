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
	protected boolean chs;
	
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
	
	public boolean isChunkSeparator() {
		return chs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allLines == null) ? 0 : allLines.hashCode());
		result = prime * result + ((angleMap == null) ? 0 : angleMap.hashCode());
		result = prime * result + (chs ? 1231 : 1237);
		result = prime * result + ((l1 == null) ? 0 : l1.hashCode());
		result = prime * result + ((l2 == null) ? 0 : l2.hashCode());
		result = prime * result + ((otherLines == null) ? 0 : otherLines.hashCode());
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
		Vertex other = (Vertex) obj;
		if (allLines == null) {
			if (other.allLines != null)
				return false;
		} else if (!allLines.equals(other.allLines))
			return false;
		if (angleMap == null) {
			if (other.angleMap != null)
				return false;
		} else if (!angleMap.equals(other.angleMap))
			return false;
		if (chs != other.chs)
			return false;
		if (l1 == null) {
			if (other.l1 != null)
				return false;
		} else if (!l1.equals(other.l1))
			return false;
		if (l2 == null) {
			if (other.l2 != null)
				return false;
		} else if (!l2.equals(other.l2))
			return false;
		if (otherLines == null) {
			if (other.otherLines != null)
				return false;
		} else if (!otherLines.equals(other.otherLines))
			return false;
		return true;
	}
	
}
