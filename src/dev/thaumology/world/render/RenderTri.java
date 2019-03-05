package dev.thaumology.world.render;

import dev.thaumology.world.core.Sector;

/**
 * A {@link RenderTri} represents a triangular surface region that is used for rendering.
 */
public class RenderTri {
	
	public RenderTri(UVtex v1, UVtex v2, UVtex v3, Sector sec) {
		vertices = new UVtex[3];
		
		vertices[0] = v1;
		vertices[1] = v2;
		vertices[2] = v3;
		sector = sec;
		
		vertexBuffer = new float[] {
			v1.getX(), v1.getY(), v1.getHeight(),
			v2.getX(), v2.getY(), v2.getHeight(),
			v3.getX(), v3.getY(), v3.getHeight()
		};
	}

	private final Sector sector;
	
	private final float[] vertexBuffer;
	private final UVtex[] vertices;
	
	public float[] getGlTriData() {
		return vertexBuffer;
	}
	
	public Sector getPropertySector() {
		return sector;
	}
	
	public UVtex getVertex(int index) {
		if(index < 0 || index > 2) return null;
		return vertices[index];
	}
	
	public UVtex getVertex1() {
		return vertices[0];
	}
	
	public UVtex getVertex2() {
		return vertices[1];
	}
	
	public UVtex getVertex3() {
		return vertices[2];
	}
	
	public float[] getVertexData() {
		return vertexBuffer;
	}
}
