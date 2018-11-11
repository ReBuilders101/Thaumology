package dev.thaumology.world;

/**
 * A lindef describes a straight line connecting two vertices that has a defined orientation and length
 */
public abstract class Linedef {

	private World world;
	private Vertex v1,v2;
	private double length;
	
	/**
	 * The first {@link Vertex} is the starting point of the linedef.
	 * @return The first Vertex
	 */
	public Vertex getFirstVertex() {
		return v1;
	}
	
	/**
	 * The second {@link Vertex} is the endpoint of the linedef.
	 * @return The second Vertex
	 */
	public Vertex getSecondVertex() {
		return v2;
	}
	
	/**
	 * @return The length of this linedef
	 */
	public double getLength() {
		return length;
	}
	
	/**
	 * @return The {@link World} that contains this linedef
	 */
	public World getWorld() {
		return world;
	}
	
	/**
	 * A surface line is a line that is a walkable surface for entities
	 * @return If this line is a surface line
	 */
	public abstract boolean isSurfaceLine();
	
}
