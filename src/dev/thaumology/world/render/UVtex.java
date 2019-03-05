package dev.thaumology.world.render;

import dev.thaumology.world.VertexCache;
import dev.thaumology.world.core.Vertex;

/**
 * Represents a {@link Vertex} that additionally stores UV coordinates for rendering
 * A Vertex instance is encapsulated.
 */
public class UVtex {

	/**
	 * Creates a new {@link UVtex} form {@link Vertex} data and UV coordinate data.
	 * @deprecated It is recommendet to use the {@link #UVtex(Vertex, int, int)} constructor instead, to avoid unnecessary Vertex instances.
	 * @param x The x-coordinate of the UVtex/Vertex
	 * @param y The y-coordinate of the UVtex/Vertex
	 * @param height The height of the UVtex/Vertex (z-coordinate)
	 * @param u The u texture coordinate of the UVtex
	 * @param v The v texture coordinate of the UVtex
	 * @param vc The {@link VertexCache} that may contain a {@link Vertex} with the required data. If an entry does not exist, a new Vertex insctance is created, but not added to the cache. This parameter may be <code>null</code>.
	 */
	@Deprecated
	public UVtex(int x, int y, int height, int u, int v, VertexCache vc) {
		if(vc == null) {
			this.vertex = new Vertex(x, y, height);
		} else {
			this.vertex = vc.getOrNewVertex(x, y, height); //Don't create entries for UVtexes
		}
		this.u = u;
		this.v = v;
	}
	
	/**
	 * Creates a new {@link UVtex} a {@link Vertex} and UV coordinate data.
	 * @param vertex The {@link Vertex} that contains the x, y and height information for the UVtex
	 * @param u The u texture coordinate of the UVtex
	 * @param v The v texture coordinate of the UVtex
	 */
	public UVtex(Vertex vertex, int u, int v) {
		this.vertex = vertex;
		this.u = u;
		this.v = v;
	}
	
	private final int u; 
	private final int v;
	private final Vertex vertex;
	
	/**
	 * Returns the u texture coordinate of the UVtex.
	 * @return The u texture coordinate of the UVtex
	 */
	public int getU() {
		return u;
	}
	
	/**
	 * Returns the v texture coordinate of the UVtex.
	 * @return The v texture coordinate of the UVtex
	 */
	public int getV() {
		return v;
	}
	
	/**
	 * Returns the {@link Vertex} that is contained in this UVtex instance.
	 * @return The vertex used by this UVtex
	 */
	public Vertex getVertex() {
		return vertex;
	}
	
	/**
	 * Returns the x-coordinate of this UVtex. Equal to {@link #getVertex()}<code>.getX()</code>
	 * @return The x-coordinate of this UVtex
	 */
	public int getX() {
		return vertex.getX();
	}
	
	/**
	 * Returns the y-coordinate of this UVtex. Equal to {@link #getVertex()}<code>.getY()</code>
	 * @return The y-coordinate of this UVtex
	 */
	public int getY() {
		return vertex.getY();
	}
	
	/**
	 * Returns the height (z-coordinate) of this UVtex. Equal to {@link #getVertex()}<code>.getHeight()</code>
	 * @return The height of this UVtex
	 */
	public int getHeight() {
		return vertex.getHeight();
	}
	
}
