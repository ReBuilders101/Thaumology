package dev.thaumology.world;

import java.util.List;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Represents a World with all Entities, Tiles, Linedefs and Vertices
 */
public abstract class World {
	
	private BiMap<Vertex,  Integer> vertexMap;
	private BiMap<Linedef, Integer> linedefMap;
	private BiMap<Tile,    Integer> tileMap;
	private BiMap<Entity,  Integer> entityMap;
	
	private boolean isInitialized = false;
	
	public World(List<Vertex> orderedVertices, List<Linedef> orderedLinedefs, 
			List<Tile> orderedTiles, List<Entity> orderedEntities) {
		
		vertexMap = HashBiMap.create(orderedVertices.size());
		for(int i = 0; i < orderedVertices.size(); i++) {
			vertexMap.put(orderedVertices.get(i), i);
		}
		
		linedefMap = HashBiMap.create(orderedLinedefs.size());
		for(int i = 0; i < orderedLinedefs.size(); i++) {
			linedefMap.put(orderedLinedefs.get(i), i);
		}
		
		tileMap = HashBiMap.create(orderedTiles.size());
		for(int i = 0; i < orderedTiles.size(); i++) {
			tileMap.put(orderedTiles.get(i), i);
		}
		
		entityMap = HashBiMap.create(orderedEntities.size());
		for(int i = 0; i < orderedEntities.size(); i++) {
			entityMap.put(orderedEntities.get(i), i);
		}
	}
	
	public void init() {
		if(isInitialized) return;
		//Might still need this method
		isInitialized = true;
	}
	
	public Vertex getVertex(int index) {
		return vertexMap.inverse().get(index);
	}
	
	public Linedef getLinedef(int index) {
		return linedefMap.inverse().get(index);
	}
	
	public Tile getTile(int index) {
		return tileMap.inverse().get(index);
	}
	
	public Entity getEntity(int index) {
		return entityMap.inverse().get(index);
	}
	
	public int getIndexOf(Vertex vertex) {
		return vertexMap.get(vertex);
	}
	
	public int getIndexOf(Linedef linedef) {
		return linedefMap.get(linedef);
	}
	
	public int getIndexOf(Tile tile) {
		return tileMap.get(tile);
	}
	
	public int getIndexOf(Entity entity) {
		return entityMap.get(entity);
	}
}
