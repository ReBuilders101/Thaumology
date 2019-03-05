package dev.thaumology.world.core;

import java.awt.geom.Area;

import dev.thaumology.texture.GroundMaterial;

public class Sector {

	protected Area sectorArea;
	protected GroundMaterial material;
	
	public Sector(Area sectorArea, GroundMaterial material) {
		this.sectorArea = sectorArea;
		this.material = material;
	}
	
	protected Sector(GroundMaterial material) {
		this.material = material;
	}
	
	protected void setProperties(Area sectorArea) {
		this.sectorArea = sectorArea;
	}
	
	public Area getSectorArea() {
		return sectorArea;
	}
	
	public GroundMaterial getGroundMaterial() {
		return material;
	}
	
}

