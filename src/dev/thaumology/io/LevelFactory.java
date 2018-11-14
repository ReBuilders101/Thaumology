package dev.thaumology.io;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.jnbt.CompoundTag;
import org.jnbt.ListTag;
import org.jnbt.Tag;

import dev.thaumology.world.Entity;
import dev.thaumology.world.Linedef;
import dev.thaumology.world.Tile;
import dev.thaumology.world.Vertex;
import dev.thaumology.world.World;

public final class LevelFactory {
	private LevelFactory(){}
	
	public static final String VERTICES_LIST = "Vertices";
	public static final String LINEDEFS_LIST = "Linedefs";
	public static final String TILES_LIST    = "Tiles";
	public static final String ENTITIES_LIST = "Entities";
	
	
	public static World createWorld(Tag tag) throws LevelFormatException{
		World world = createRawWorld(tag);
		if(world != null) world.initPrimers();
		return world;
	}
	
	public static World createRawWorld(Tag tag) throws LevelFormatException{
		final CompoundTag ctag = Validate.type(tag, CompoundTag.class, 
				new LevelFormatException("Top level tag is not a compound tag", tag),
				new LevelFormatException("Top level tag is null"));
		//VERTICES
		final Tag verts0 = Validate.getTag(ctag, VERTICES_LIST,
				new LevelFormatException("Vertices subtag does not exist"));
		final ListTag verts = Validate.listTagOf(verts0, CompoundTag.class, 
				new LevelFormatException("Vertices subtag is not a list", verts0),
				new LevelFormatException("Vertices subtag is not a list of compounds", verts0));
		//LINEDEFS
		final Tag lines0 = Validate.getTag(ctag, LINEDEFS_LIST,
				new LevelFormatException("Linedefs subtag does not exist"));
		final ListTag lines = Validate.listTagOf(lines0, CompoundTag.class, 
				new LevelFormatException("Linedefs subtag is not a list", lines0),
				new LevelFormatException("Linedefs subtag is not a list of compounds", lines0));
		//TILES
		final Tag tiles0 = Validate.getTag(ctag, TILES_LIST,
				new LevelFormatException("Tiles subtag does not exist"));
		final ListTag tiles = Validate.listTagOf(tiles0, CompoundTag.class, 
				new LevelFormatException("Tiles subtag is not a list", tiles0),
				new LevelFormatException("Tiles subtag is not a list of compounds", tiles0));
		//ENTITIES
		final Tag entis0 = Validate.getTag(ctag, ENTITIES_LIST,
				new LevelFormatException("Entities subtag does not exist"));
		final ListTag entis = Validate.listTagOf(entis0, CompoundTag.class, 
				new LevelFormatException("Entities subtag is not a list", entis0),
				new LevelFormatException("Entities subtag is not a list of compounds", entis0));
		final List<Linedef> linedefList = new ArrayList<>();
		final List<Vertex>        vertexList = new ArrayList<>();
		final List<Tile>          tileList = new ArrayList<>();
		final List<Entity>        entityList = new ArrayList<>();
		
		//Can't use foreach() because of exceptions
		for(Tag line : lines.getValue()) {
			linedefList.add(createLinedef(line));
		}
		for(Tag vert : verts.getValue()) {
			vertexList.add(createVertex(vert, linedefList::get));
		}
		
		//Do Tiles and Entities later
		
		return null;
	}
	
	public static Linedef createLinedef(Tag tag) throws LevelFormatException{
		return null;
	}
	
	public static Vertex createVertex(Tag tag, Function<Integer, Linedef> lineSupplier) throws LevelFormatException{
		return null;
	}
	
}
