package dev.thaumology.world;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

public class LinedefStraight extends Linedef{

	/**
	 * Creates a new {@link LinedefStraight}. This is an implementation of the {@link Linedef} class that represents a straight line.
	 * The order of Vertices and Sectors does not matter. Sectors may be {@link SectorPrimer}s if they are initialized later.
	 * @param vertex1 The first {@link Vertex} that this linedef connects to
	 * @param vertex2 The second {@link Vertex} that this linedef connects to
	 * @param sector1 The first {@link Sector} that is bordered by this linedef
	 * @param sector2 The second {@link Sector} that is bordered by this linedef
	 */
	public LinedefStraight(Vertex vertex1, Vertex vertex2, Sector sector1, Sector sector2) {
		super(vertex1, vertex2, sector1, sector2);
	}

	/**
	 * Adds this linedef to a {@link GeneralPath}. 
	 * This method only adds one straight segment from first to second vertex (or reversed, depending on the <code>inverted</code> parameter).
	 * @param path The {@link GeneralPath} that should be used to create a {@link Sector} or {@link Shape}
	 * @param invert If <code>false</code>, the path will be at the position of the starting {@link Vertex} when this method is called,
	 * if this parameter is <code>true</code>, the path will be at the ending {@link Vertex} when this method is called
	 */
	@Override
	public void addToPath(GeneralPath path, boolean invert) {
		if(invert) {
			path.lineTo(getFirstVertex().getX(), getFirstVertex().getY());
		} else {
			path.lineTo(getSecondVertex().getX(), getSecondVertex().getY());
		}
	}

	/**
	 * The bounding rectangle describes the smallest rectangular area that contains the whole line.
	 * This means that only points inside the bounding rectangle can touch the line.<br>
	 * The returned rectangle will have the first and second {@link Vertex} in opposing corners. 
	 * @return A bounding rectangle for this linedef
	 */
	@Override
	public Rectangle2D getBounds() {
		return new Rectangle(getFirstVertex().getX(), getFirstVertex().getY(), getDx(), getDy());
	}

	/**
	 * The actual length of the {@link Linedef}, which is equal to the result of {@link #getLength()}, because this line is straight.<br>
	 * Use {@link #getLengthSq()} instead wherever possible.
	 * @return The length of the actual line
	 */
	@Override
	public double getPathLength() {
		return getLength();
	}

}
