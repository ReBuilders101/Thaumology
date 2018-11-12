package dev.thaumology.nodebuilder;

import dev.thaumology.world.Linedef;

/**
 * This object describes a {@link Linedef} from the persperctive of another linedef (the one that the LineIterator belongs to)
 */
public class LineIteratorItem {
	
	private Linedef line;
	private double contextDx, contextDy, contextRotation, contextX, contextY, contextAngle;
	
	/**
	 * The {@link Linedef} that is described by this {@link LineIteratorItem}
	 * @return The contained linedef
	 */
	public Linedef getLinedef() {
		return line;
	}
	
	public LineIteratorItem(Linedef line, double contextDx, double contextDy, double contextRotation, double contextX,
			double contextY) {
		this.line = line;
		this.contextDx = contextDx;
		this.contextDy = contextDy;
		this.contextRotation = contextRotation;
		this.contextX = contextX;
		this.contextY = contextY;
		this.contextAngle = Math.atan2(contextDy, contextDx);
	}

	/**
	 * The delta-X size of this line form the {@link LineIterator}'s perspective. The delta-X size 
	 * is the difference between the x-Coordinate of the endpoint and the x-Coordinate of the staring point
	 * @return The delta-X value
	 */
	public double getDx() {
		return contextDx;
	}
	
	/**
	 * The delta-Y size of this line form the {@link LineIterator}'s perspective. The delta-Y size 
	 * is the difference between the y-Coordinate of the endpoint and the y-Coordinate of the staring point
	 * @return The delta-Y value
	 */
	public double getDy() {
		return contextDy;
	}
	
	/**
	 * The x-Coordinate of this line from the {@link LineIterator}'s perspective, when the Iterator's first Vertex is the origin.
	 * @return The x value
	 */
	public double getX() {
		return contextX;
	}
	
	/**
	 * The y-Coordinate of this line from the {@link LineIterator}'s perspective, when the Iterator's first Vertex is the origin.
	 * @return The y value
	 */
	public double getY() {
		return contextY;
	}
	
	/**
	 * Returns the angle that describes the direction of this {@link Linedef}'s gravity relative to the
	 * {@link LineIterator}'s gravity direction
	 * @return The rotated gravity angle
	 */
	public double getGravityRotation() {
		return contextRotation;
	}
	
	/**
	 * The Angle (dy/dx) of this line
	 */
	public double getAngle() {
		return contextAngle;
	}
	
}
