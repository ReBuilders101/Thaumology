package dev.thaumology.world;

/*
 * A Vertex is the endpoint of a linedef. 
 */
public class Vertex {
	
	public double x,y;
	
	public Vertex(double xCoord, double yCoord) {
		x = xCoord;
		y = yCoord;
	}

	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
}
