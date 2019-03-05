package dev.thaumology.world;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import dev.thaumology.world.core.Entity;
import dev.thaumology.world.core.Sector;
import dev.thaumology.world.core.Tile;
import dev.thaumology.world.core.WorldLocation;

public class Room extends Region {

	private Set<WorldLocation> loadedPositions;
	private int preloadRegionFactor;
	
	public Room(Collection<Tile> tiles, Collection<Entity> initialEntities, Collection<Room> rooms,
			Collection<Sector> sectors, Collection<WorldLocation> loadLocations, int preloadFactor) {
		super(tiles, initialEntities, rooms, sectors);
		this.loadedPositions = loadLocations == null ? new HashSet<>() : new HashSet<>(loadLocations);
		this.preloadRegionFactor = preloadFactor;
	}

}
