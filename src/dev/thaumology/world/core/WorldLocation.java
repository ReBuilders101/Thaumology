package dev.thaumology.world.core;

import java.awt.Point;

import dev.thaumology.world.Region;
import dev.thaumology.world.World;

public class WorldLocation extends Location {

	protected final World world;
	
	public WorldLocation(int x, int y, World world) {
		super(x, y);
		this.world = world;
	}

	public World getWorld() {
		return world;
	}
	
	public Region getRegion() {
		return world.getRegionAt(x, y);
	}
	
	public WorldLocation forWorld(World newWorld) {
		return new WorldLocation(x, y, newWorld);
	}
	
	@Override
	public WorldLocation moveX(int amount) {
		return new WorldLocation(x + amount, y, world);
	}
	
	@Override
	public WorldLocation moveY(int amount) {
		return new WorldLocation(x, y + amount, world);
	}
	
	@Override
	public WorldLocation move(int xAmount, int yAmount) {
		return new WorldLocation(x + xAmount, y + yAmount, world);
	}
	
	@Override
	public WorldLocation move(Location offset) {
		return new WorldLocation(x + offset.x, y + offset.y, world);
	}
	
	
	
	public static WorldLocation of(int x, int y, World world) {
		return new WorldLocation(x, y, world);
	}
	
	public static WorldLocation of(Point point, World world) {
		return new WorldLocation(point.x, point.y, world);
	}
	
	public static WorldLocation forWorld(Location location, World world) {
		return new WorldLocation(location.x, location.y, world);
	}
	
	public static WorldLocation moveX(WorldLocation location, int amount) {
		return new WorldLocation(location.x + amount, location.y, location.world);
	}
	
	public static WorldLocation moveY(WorldLocation location, int amount) {
		return new WorldLocation(location.x, location.y + amount, location.world);
	}
	
	public static WorldLocation move(WorldLocation location, int xAmount, int yAmount) {
		return new WorldLocation(location.x + xAmount, location.y + yAmount, location.world);
	}
	
	public static WorldLocation move(WorldLocation location, Location offset) {
		return new WorldLocation(location.x + offset.x, location.y + offset.y, location.world);
	}
}
