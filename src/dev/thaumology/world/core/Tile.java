package dev.thaumology.world.core;

import java.awt.Rectangle;
import java.awt.Shape;

import dev.thaumology.world.Action;
import dev.thaumology.world.Region;
import dev.thaumology.world.World;

public abstract class Tile {
	
	private int x;
	private int y;
	
	private Shape shape;
	private Action<? extends Tile> interactAction;
	private Region region;
	private World world;
	
	protected Tile(World world, int x, int y) {
		this.x = x;
		this.y = y;
		this.world = world;
		this.region = world.getRegionAt(x, y);
		this.shape = createShape();
	}
	
	public int getXPosition() {
		return x;
	}
	
	public int getYPosition() {
		return y;
	}
	
	protected void setXPosition(int x) {
		this.x = x;
	}
	
	protected void setYPosition(int y) {
		this.y = y;
	}
	
	public Shape getShape() {
		return shape;
	}
	
	public World getWorld() {
		return world;
	}
	
	public Rectangle getBounds() {
		//Return type as specific as possible, Rectangle extends Rectangle2D
		return shape.getBounds();
	}
	
	public abstract boolean hasCollision();
	protected abstract boolean forceHideInteractionHint();
	protected abstract Shape createShape();
	
	public boolean showInteractionHint() {
		return !(forceHideInteractionHint() || getInteractAction() == null);
	}
	
	public Region getCurrentRegion() {
		return region;
	}
	
	protected void setCurrentRegion(Region region) {
		this.region = region;
	}
	
	public Action<? extends Tile> getInteractAction() {
		return interactAction;
	}
}
