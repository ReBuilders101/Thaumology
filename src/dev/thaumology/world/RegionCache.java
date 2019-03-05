package dev.thaumology.world;

import java.util.Map;

//chunkprovider
public abstract class RegionCache {

	private Map<Integer, Region> loadedRegions;
	
	public void loadRegion(short x, short y) {
		
	}
	
	public void unloadRegion(short x, short y) {
		
	}

	public abstract Region provideRegion(short x, short y, boolean requestOrGenerate);
	
}
