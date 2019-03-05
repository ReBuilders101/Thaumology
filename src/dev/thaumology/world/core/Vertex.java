package dev.thaumology.world.core;

/**
 * A Vertex represents a node in a 2D terrain, saving the x and y coordinate as well as the terrain height at this point
 */
public class Vertex {
	
	private final int x;
	private final int y;
	private final int height;

	/**
	 * Creates a new {@link Vertex} with these values
	 * @param x The x-coordinate of the Vertex
	 * @param y The y-coordinate of the Vertex
	 * @param height The height of the terrain at the position of the Vertex
	 */
	public Vertex(int x, int y, int height) {
		this.x = x;
		this.y = y;
		this.height = height;
	}

	/**
	 * @return The x-coordinate of this Vertex
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return The y-coordinate of this Vertex
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return The terrain height at the coordinates of this Vertex
	 */
	public int getHeight() {
		return height;
	}
}
