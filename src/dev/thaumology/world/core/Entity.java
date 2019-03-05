package dev.thaumology.world.core;

import dev.thaumology.world.Action;
import dev.thaumology.world.Region;
import dev.thaumology.world.World;

public abstract class Entity extends Tile implements CameraViewpoint, Tickable {
	
	private AI ai;
	private Action<? extends Entity> deathAction;
	
	protected Entity(World world, int x, int y, AI ai) {
		super(world, x, y);
		this.ai = ai;
		this.ai.enable(this);
	}

	@Override
	public void tick(long tickNumber) {
		ai.controlEntityOnTick(this);
		//possibly more code
	}
	
	public AI getEntityAI() {
		return ai;
	}
	
	public Action<? extends Entity> getDeathAction() {
		return deathAction;
	}
	
	public void changeAI(AI newAI) {
		this.ai.disable(this);
		this.ai = newAI;
		this.ai.enable(this);
	}
	
	protected void setPositionAndUpdate(int x, int y) {
		setXPosition(x);
		setYPosition(y);
		updateRegion();
	}
	
	protected void updateRegion() {
		Region target = getWorld().getRegionAt(getXPosition(), getYPosition());
		if(target != getCurrentRegion()) {
			getCurrentRegion().removeEntity(this);
			setCurrentRegion(target);
			getCurrentRegion().addEntity(this);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Action<? extends Entity> getInteractAction() {
		return (Action<? extends Entity>) super.getInteractAction();
	}
	
}
