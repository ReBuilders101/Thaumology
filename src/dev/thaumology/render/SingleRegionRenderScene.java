package dev.thaumology.render;

import java.awt.Graphics2D;

import javax.swing.JComponent;

import dev.thaumology.world.Region;
import lb.simplebase.scene.Scene;

public class SingleRegionRenderScene extends Scene{

	private Region region;
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g, int width, int height) {
		
	}

	@Override
	public String getName() {
		return "scene";
	}

	@Override
	public JComponent getOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}

}
