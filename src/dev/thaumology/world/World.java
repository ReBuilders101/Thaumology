package dev.thaumology.world;

import java.util.ArrayList;
import java.util.List;

import dev.thaumology.world.core.Vertex;

/**
 * Represents a World with all Entities, Tiles, Linedefs and Vertices
 */
public abstract class World {
	
	private VertexCache vertices;
	private RegionCache regions;
	
//	private List<ITickUpdate> loadedTickables;
	
	private boolean isInitialized = false;
	
	public World() {
	}
	
	public void init() {
		if(isInitialized) return;
		regions = createRegionCache();
		vertices = VertexCache.create();
		isInitialized = true;
	}
	
	public Region getRegionAt(int x, int y) {
		return null;
	}
	
	public abstract RegionCache createRegionCache();
	
	public Vertex getVertexAt(int x, int y) {
		return null;
	}
	
}
