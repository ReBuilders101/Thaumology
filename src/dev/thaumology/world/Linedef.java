package dev.thaumology.world;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import dev.thaumology.io.LinedefConstructor;

/**
 * A linedef is a connection between two vertices. Linedefs act as borders for {@link Sector}s and can additionally trigger Actions.
 */
public abstract class Linedef {

	private final Vertex vertex1;
	private final Vertex vertex2;
	private final Sector sector1;
	private final Sector sector2;

	/**
	 * Creates a new Linedef. Since this class is abstract, this can only be used by implementing classes as a superclass constructor.
	 * The order of Vertices and Sectors does not matter. Sectors may be {@link SectorPrimer}s if they are initialized later.
	 * @param vertex1 The first {@link Vertex} that this linedef connects to
	 * @param vertex2 The second {@link Vertex} that this linedef connects to
	 * @param sector1 The first {@link Sector} that is bordered by this linedef
	 * @param sector2 The second {@link Sector} that is bordered by this linedef
	 */
	protected Linedef(Vertex vertex1, Vertex vertex2, Sector sector1, Sector sector2) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.sector1 = sector1;
		this.sector2 = sector2;
	}

	/**
	 * The first {@link Vertex} is the starting point of the linedef.
	 * @return The first Vertex
	 */
	public Vertex getFirstVertex() {
		return vertex1;
	}
	
	/**
	 * The second {@link Vertex} is the endpoint of the linedef.
	 * @return The second Vertex
	 */
	public Vertex getSecondVertex() {
		return vertex2;
	}
	
	/**
	 * Returns the {@link Vertex} associated with this linedef that is not equal to the vertex in the parameter.
	 * If the vertex in the parameter is neither the first nor the second vertex of this linedef, this 
	 * method returns <code>null</code>.
	 * @param vertex The {@link Vertex} that should not be returned
	 * @return The {@link Vertex} that is not the one in the parameter
	 */
	public Vertex getOtherVertex(Vertex vertex) {
		if(vertex == vertex1) return vertex2;
		if(vertex == vertex2) return vertex1;
		return null;
	}
	
	/**
	 * This method calculates the square of the length of this linedef, assuming that the linedef connects
	 * the two vertices in a straight line. The result of this method is recalculated for every call.
	 * @return The squared length of this linedef
	 * @see #getLength()
	 */
	public double getLengthSq() {
		int dx = getDx();
		int dy = getDy();
		return (dx * dx) + (dy * dy);
	}
	
	/**
	 * This method calculates the length of this linedef, assuming that the linedef connects
	 * the two vertices in a straight line. The result of this method is recalculated for every call.<br>
	 * Whenever possible, {@link #getLengthSq()} should be used instead.
	 * @return The length of this linedef
	 * @see #getLengthSq()
	 */
	public double getLength() {
		return Math.sqrt(getLengthSq());
	}

	/**
	 * Calculates the delta-x value for this linedef, that is the difference between the second vertex' x-coordinate and the
	 * first vertex' x-coordinate. Note that the sign of the result depends on the order of vertices in this linedef. 
	 * @return The delta-x length for this line
	 */
	public int getDx() {
		return vertex2.getX() - vertex1.getX();
	}
	
	/**
	 * Calculates the delta-y value for this linedef, that is the difference between the second vertex' y-coordinate and the
	 * first vertex' y-coordinate. Note that the sign of the result depends on the order of vertices in this linedef. 
	 * @return The delta-y length for this line
	 */
	public int getDy() {
		return vertex2.getY() - vertex1.getY();
	}
	
	/**
	 * A linedef separates two sectors, which are saved on the linedef. The order of the two sectors does not matter.
	 * @return The first sector bordered by this linedef
	 */
	public Sector getFirstSector() {
		return sector1;
	}
	
	/**
	 * A linedef separates two sectors, which are saved on the linedef. The order of the two sectors does not matter.
	 * @return The second sector bordered by this linedef
	 */
	public Sector getSecondSector() {
		return sector2;
	}
	
	/**
	 * Returns the {@link Sector} associated with this linedef that is not equal to the sector in the parameter.
	 * If the sector in the parameter is neither the first nor the second sector of this linedef, this 
	 * method returns <code>null</code>.
	 * @param sector The {@link Sector} that should not be returned
	 * @return The {@link Sector} that is not the one in the parameter
	 */
	public Sector getOtherSector(Sector sector) {
		if(sector == sector1) return sector2;
		if(sector == sector2) return sector1;
		return null;
	}
	
	/**
	 * Adds this linedef to a {@link GeneralPath}. Implementation must provide their own code, which can add any amount
	 * of line segments to the path.<br>
	 * The added segments <b>should not</b> make the path discontinuous, they should connect the starting and ending vertex
	 * without interruption.
	 * @param path The {@link GeneralPath} that should be used to create a {@link Sector} or {@link Shape}
	 * @param invert If <code>false</code>, the path will be at the position of the starting {@link Vertex} when this method is called,
	 * if this parameter is <code>true</code>, the path will be at the ending {@link Vertex} when this method is called
	 */
	public abstract void addToPath(GeneralPath path, boolean invert);
	
	/**
	 * The bounding rectangle describes the smallest rectangular area that contains the whole line.
	 * This means that only points inside the bounding rectangle can touch the line.
	 * @return A bounding rectangle for this linedef
	 */
	public abstract Rectangle2D getBounds();
	
	/**
	 * The actual length of the {@link Linedef} can be different from the length returned by {@link #getLength()},
	 * when the Linedef does not describe a straight line. In this case, this method will return the length of the (curved) line.
	 * @return The length of the actual line
	 */
	public abstract double getPathLength();
	
	/////////Static parts//////////////
	
	private static final Map<Integer, LinedefConstructor> constructorMap = new HashMap<>();
	
	/**
	 * Attempts to find a modded method that can convert a saved NBT-tag to a {@link Linedef} instance.
	 * This method will only be called for typeIds that are unknown to the unmodified game.
	 * @param typeId A saved identifier number that maps to a runtime type of {@link Linedef}
	 * @return The {@link LinedefConstructor} used to create a linedef of that type. 
	 */
	public static LinedefConstructor getConstructorFor(int typeId) {
		return constructorMap.get(typeId);
	}
	
	/**
	 * Registers a modded method that can convert a saved NBT-tag to a {@link Linedef} instance for
	 * a certain typeId.
	 * This method will only be used for typeIds that are unknown to the unmodified game.
	 * @param constructor The {@link LinedefConstructor} used to create a linedef of that type.
	 * @param typeId A saved identifier number that maps to a runtime type of {@link Linedef} 
	 */
	public static void registerConstructor(LinedefConstructor constructor, int typeId) {
		constructorMap.put(typeId, constructor);
	}
	
	
}
