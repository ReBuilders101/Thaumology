package dev.thaumology.io;

import dev.thaumology.world.Linedef;
import dev.thaumology.world.World;

public class LinedefPrimer extends Linedef{

	int vert1Id, vert2Id;
	
	public LinedefPrimer(int vertex1Id, int vertex2Id, double length, double gravity) {
		super(length, gravity);
		vert1Id = vertex1Id;
		vert2Id = vertex2Id;
	}
	
	public void process(World world) {
		setWorldAndvertices(world, world.getVertex(vert1Id), world.getVertex(vert2Id));
	}
	
}
