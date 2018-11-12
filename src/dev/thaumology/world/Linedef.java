package dev.thaumology.world;

/**
 * A lindef describes a straight line connecting two vertices that has a defined orientation and length
 * Surface lines have to point to the right if the air side is up.
 */
public abstract class Linedef {

	private World world;
	private Vertex v1,v2;
	private double length, gravity;
	
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
	public boolean isSurfaceLine() {
		return true;
	}
	
	/**
	 * Describes the Angle of the down direction for tiles and entities relative to the linedef (straight down would be 270°)
	 */
	public double getGravityDirection() {
		return gravity;
	}
	
	/**
	 * 
	 * @return The vertex that is at one end of the line but not the one in the parameter. Null if parameter is not on linedef
	 */
	public Vertex getOtherVertex(Vertex v) {
		if(v == v1) return v2;
		if(v == v2) return v1;
		return null;
	}
}
