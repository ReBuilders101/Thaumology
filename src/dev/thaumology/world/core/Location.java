package dev.thaumology.world.core;

import java.awt.Dimension;
import java.awt.Point;

public class Location {

	protected final int x;
	protected final int y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Location moveX(int amount) {
		return new Location(x + amount, y);
	}
	
	public Location moveY(int amount) {
		return new Location(x, y + amount);
	}
	
	public Location move(int xAmount, int yAmount) {
		return new Location(x + xAmount, y + yAmount);
	}
	
	public Location move(Location offset) {
		return new Location(x + offset.x, y + offset.y);
	}
	
	public int getDx(Location otherLocation) {
		return otherLocation.x - x;
	}
	
	public int getDy(Location otherLocation) {
		return otherLocation.y - y;
	}
	
	public Dimension getDistanceDimensions(Location otherLocation) {
		return new Dimension(getDx(otherLocation), getDy(otherLocation));
	}
	
	public int getDistanceSq(Location otherLocation) {
		//Cache the delta values locally so they are not calculated twice
		final int dx = getDx(otherLocation);
		final int dy = getDy(otherLocation);
		return (dx * dx) + (dy * dy);
	}
	
	public double getDistance(Location otherLocation) {
		return Math.sqrt(getDistanceSq(otherLocation));
	}
	
	
	
	
	public static Location of(int x, int y) {
		return new Location(x, y);
	}
	
	public static Location of(Point point) {
		return new Location(point.x, point.y);
	}
	
	public static Location moveX(Location location, int amount) {
		return new Location(location.x + amount, location.y);
	}
	
	public static Location moveY(Location location, int amount) {
		return new Location(location.x, location.y + amount);
	}
	
	public static Location move(Location location, int xAmount, int yAmount) {
		return new Location(location.x + xAmount, location.y + yAmount);
	}
	
	public static Location move(Location location, Location offset) {
		return new Location(location.x + offset.x, location.y + offset.y);
	}
	
	public static int getDx(Location location1, Location location2) {
		return location2.x - location1.x;
	}
	
	public static int getDy(Location location1, Location location2) {
		return location2.y - location1.y;
	}
	
	public static Dimension getDistanceDimensions(Location location1, Location location2) {
		return new Dimension(getDx(location1, location2), getDy(location1, location2));
	}
	
	public static int getDistanceSq(Location location1, Location location2) {
		//Cache the delta values locally so they are not calculated twice
		final int dx = getDx(location1, location2);
		final int dy = getDy(location1, location2);
		return (dx * dx) + (dy * dy);
	}
	
	public static double getDistance(Location location1, Location location2) {
		return Math.sqrt(getDistanceSq(location1, location2));
	}
	
}
