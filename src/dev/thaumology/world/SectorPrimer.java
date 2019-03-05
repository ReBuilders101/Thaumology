package dev.thaumology.world;

import dev.thaumology.world.core.Sector;

public class SectorPrimer extends Sector{

	private final int id;
	private boolean isInitialized;
	
	public SectorPrimer(int id) {
		super(null);
		this.id = id;
		this.isInitialized = false;
	}
	
	public void init(Region region) {
		if(isInitialized) return;
		//Code
		isInitialized = true;
	}
	
	protected int getId() {
		return id;
	}
	
}
