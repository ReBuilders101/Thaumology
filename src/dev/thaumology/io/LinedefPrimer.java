package dev.thaumology.io;

import java.util.function.Function;

import dev.thaumology.world.Linedef;
import dev.thaumology.world.Vertex;
import dev.thaumology.world.World;

public class LinedefPrimer extends Linedef{

	int vert1Id, vert2Id;
	
	public LinedefPrimer(int vertex1Id, int vertex2Id, double length, double gravity) {
		super(length, gravity);
		vert1Id = vertex1Id;
		vert2Id = vertex2Id;
	}
	
	public void process(World world, Function<Integer, Vertex> vertexSupplier) {
		setWorldAndvertices(world, vertexSupplier.apply(vert1Id), vertexSupplier.apply(vert2Id));
	}
	
}
