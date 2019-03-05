package dev.thaumology.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import dev.thaumology.world.core.Entity;
import dev.thaumology.world.core.Sector;
import dev.thaumology.world.core.Tile;

/**
 * A Region is a part of a world with a fixed size, a fixed basic world structure and a dynamic list of entities.
 */
public class Region {
	
	private List<Tile> tiles, tilesView;
	private List<Entity> entities, entitiesView;
	private List<Room> rooms, roomsView;
	private List<Sector> sectors, sectorsView;
	
	
	public Region(Collection<Tile> tiles, Collection<Entity> initialEntities, Collection<Room> rooms, Collection<Sector> sectors) {
		this.tiles   = tiles   == null ? new ArrayList<>() : new ArrayList<>(tiles  );
		this.rooms   = rooms   == null ? new ArrayList<>() : new ArrayList<>(rooms  );
		this.sectors = sectors == null ? new ArrayList<>() : new ArrayList<>(sectors);
		this.entities = initialEntities == null ? new ArrayList<>() : new ArrayList<>(initialEntities);
		intiViews();
	}
	
	public List<Tile> getTiles() {
		return tilesView; 
	}
	
	public List<Entity> getEntities() {
		return entitiesView;
	}
	
	public List<Room> getRooms() {
		return roomsView;
	}
	
	public List<Sector> getSectors() {
		return sectorsView;
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void removeEntity(Entity e) {
		entities.remove(e);
	}
	
	private void intiViews() {
		tilesView = Collections.unmodifiableList(tiles);
		entitiesView = Collections.unmodifiableList(entities);
		roomsView = Collections.unmodifiableList(rooms);
		sectorsView = Collections.unmodifiableList(sectors);
	}
	
}
