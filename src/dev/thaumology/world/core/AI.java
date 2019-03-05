package dev.thaumology.world.core;

public abstract class AI {

	public abstract void enable(Entity entity);
	public abstract void controlEntityOnTick(Entity entity);
	public abstract void disable(Entity entity);
	
}
