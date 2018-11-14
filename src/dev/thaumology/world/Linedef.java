package dev.thaumology.world;

/**
 * A lindef describes a straight line connecting two vertices that has a defined orientation and length
 * Surface lines have to point to the right if the air side is up.
 */
public abstract class Linedef {

	private World world;
	private Vertex v1,v2;
	private double length, gravity;
	
	protected Linedef(double length, double gravity) {
		this.length = length;
		this.gravity = gravity;
	}
	
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

	protected void setWorldAndvertices(World world, Vertex v1, Vertex v2) {
		this.world = world;
		this.v1 = v1;
		this.v2 = v2;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(gravity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(length);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((v1 == null) ? 0 : v1.hashCode());
		result = prime * result + ((v2 == null) ? 0 : v2.hashCode());
		result = prime * result + ((world == null) ? 0 : world.hashCode());
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
		Linedef other = (Linedef) obj;
		if (Double.doubleToLongBits(gravity) != Double.doubleToLongBits(other.gravity))
			return false;
		if (Double.doubleToLongBits(length) != Double.doubleToLongBits(other.length))
			return false;
		if (v1 == null) {
			if (other.v1 != null)
				return false;
		} else if (!v1.equals(other.v1))
			return false;
		if (v2 == null) {
			if (other.v2 != null)
				return false;
		} else if (!v2.equals(other.v2))
			return false;
		if (world == null) {
			if (other.world != null)
				return false;
		} else if (!world.equals(other.world))
			return false;
		return true;
	}
}
