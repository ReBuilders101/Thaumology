package dev.thaumology.texture;

public interface GroundMaterial {

	//Texturing
	public TileableTexture getBaseTexture();
	public Texture[] getDecorationTextures();
	public Texture getRandomDecorationTexture();
	
	//Behavior
	public double getWalkSpeedModifier();
	public double getAccelerationSpeedModifier();
	
}
