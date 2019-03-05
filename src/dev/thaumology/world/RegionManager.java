package dev.thaumology.world;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.jnbt.CompoundTag;
import org.jnbt.IntTag;
import org.jnbt.ListTag;
import org.jnbt.NBTInputStream;
import org.jnbt.Tag;

import dev.thaumology.io.Validate;
import dev.thaumology.io.error.LevelFormatException;
import dev.thaumology.world.core.Vertex;
import lb.simplebase.error.ErrorReport;

/**
 * The LevelFactory provides methods for loading regions from NBT data.
 * Every world has its own {@link RegionManager} that is responsible for loading, unloading and saving regions.
 */
public class RegionManager {
	

	private static final String REGION_VERTICES_LIST = "Vertices";
	private static final String REGION_LINEDEFS_LIST = "Linedefs";
	private static final String TILES_LIST    = "Tiles";
	private static final String ENTITIES_LIST = "Entities";
	
	private Tag tag;
	private VertexCache cache;
	private World world;
	private ErrorReport report;
	
	public RegionManager(World usedWorld) {
		
	}
	
	
	public void loadRegion(int x, int y) {
		
	}
	
	public void saveRegion(int x, int y) {
		
	}
	
	/**
	 * Creates a Linedef from the NBT data. Additionally to the NDT tag, this method also requires a source for {@link Vertex}
	 * instances, which have to be read with the {@link #createVertex(Tag)} method separately. The vertex will be requested
	 * from the supplier by an integer index.
	 * @param tag The NBT tag
	 * @param vertexSupplier The source for vertex instances
	 * @return The created {@link Linedef}
	 * @throws LevelFormatException When the tag does not have the correct format
	 */
	private Linedef createLinedef(Tag tag, Function<Integer, Vertex> vertexSupplier) throws LevelFormatException{
		
		return null;
	}
	
	/**
	 * 
	 * @param tag The NBT tag
	 * @return The created Vertex
	 * @throws LevelFormatException
	 */
	private Vertex createVertex(Tag tag) throws LevelFormatException {
		final CompoundTag cTag = Validate.type(tag, CompoundTag.class,
				new LevelFormatException("Vertex Tag is not a compund Tag", tag),
				new LevelFormatException("Vertex Tag is does not exist", tag));
		final IntTag x = Validate.getTag(cTag, "x", IntTag.class,
				new LevelFormatException("Vertex x Tag does not exist"),
				new LevelFormatException("Vertex x Tag is not an integer Tag"));
		final IntTag y = Validate.getTag(cTag, "y", IntTag.class,
				new LevelFormatException("Vertex y Tag does not exist"),
				new LevelFormatException("Vertex y Tag is not an integer Tag"));
		//Is vertex on chunk edge?
		if(Region.isOnEdge(x.getValue(), y.getValue())) {
			return cache.getOrCreateVertex(x.getValue(), y.getValue());
		} else {
			return new Vertex(x.getValue(), y.getValue());
		}
		
	}
	
	/////////////////////////////////////STATIC MEMBERS///////////////////////////////////////////////
	
	/**
	 * Creates a <code>long</code> that encodes the {@link Region}'s <code>x</code> and <code>y</code> coordinates
	 * into a single number. This number can be used to identify a Region by its coordinates and is used for saving
	 * the Region as an NBT structure.<br>
	 * The <code>x</code>-coordinate is saved in the left (or most significant) 32 bits of the <code>long</code> number,
	 *  while the <code>y</code>-coordinate is saved in the right (or least significant) 32 bits of the <code>long</code> number.  
	 * @param x The Region's <code>x</code>-coordinate
	 * @param y The Region's <code>y</code>-coordinate
	 * @return The encoded Number
	 */
	public static long encodeRegionPos(int x, int y) {
		return (((long) x) << 32) | ((long) y);
	}
	
	/**
	 * Decodes the <code>x</code>-coordinate form the encoded {@link Region} position. The <code>x</code>-coordinate is
	 * saved in the left (or most significant) 32 bits of the number.
	 * @param pos The encoded <code>x</code> and <code>y</code> coordinates
	 * @return The <code>x</code>-coordinate
	 */
	public static int parseRegionX(long pos) {
		return (int) ((pos >>> 32) & 0xFFFFFFFF);
	}
	
	/**
	 * Decodes the <code>y</code>-coordinate form the encoded {@link Region} position. The <code>y</code>-coordinate is
	 * saved in the right (or least significant) 32 bits of the number.
	 * @param pos The encoded <code>x</code> and <code>y</code> coordinates
	 * @return The <code>y</code>-coordinate
	 */
	public static int parseRegionY(long pos) throws NumberFormatException{
		return (int) (pos & 0xFFFFFFFF);
	}

	//Methods go to WorldFactory
	
	/**
	 * Creates a new LevelFactory Instance that will read the level data from the specified NBT-Tag. 
	 * @param tag The {@link Tag} to use as the data source
	 * @return A {@link RegionManager} instance for this {@link Tag}
	 * @throws NullPointerException When the tag is null
	 */
	public static RegionManager createFor(Tag tag) throws NullPointerException{
		if(tag == null) {
			throw new NullPointerException("Parameter 'tag' was null, cannot create LevelFactory");
		} else {
			return new RegionManager(tag);
		}
	}
	
	/**
	 * Creates a new LevelFactory Instance that uses the specified {@link InputStream} as a source for the level data.
	 * This method creates a new {@link NBTInputStream} form this InputStream and the reads a NBT-{@link Tag} from the stream.
	 * This Tag is then used th the {@link RegionManager#createFor(Tag)} method to create a new LevelFactory
	 * @param stream The InputStream to read the data from
	 * @return A {@link RegionManager} instance
	 * @throws IOException When the {@link NBTInputStream} could not be created from this {@link InputStream}; <br>
	 * or when a {@link Tag} cannot be read from the stream (e.g. incorrect/corrupted data); <br>
	 * or when the NBTInputStream cannot be closed properly. 
	 */
	public static RegionManager createFor(InputStream stream) throws IOException {
		final NBTInputStream inStream = new NBTInputStream(stream);
		final Tag tag = inStream.readTag();
		RegionManager lf = createFor(tag);
		inStream.close();
		return lf;
	}
	
	/**
	 * Creates a new LevelFactory Instance that uses the specified {@link File} as a source for the level data.
	 * This method creates a {@link FileInputStream} to this file and then uses the {@link RegionManager#createFor(InputStream)}
	 * method to create a new instance
	 * No checks whether this file exists are made; when the file does not exist, a {@link FileNotFoundException}
	 * is thrown
	 * @param file The file to read the data from
	 * @return A {@link RegionManager} instance
	 * @throws IOException When the {@link RegionManager#createFor(InputStream)} that is used with a {@link FileInputStream} throws
	 * an exception,<br>or when the used FileInputStream could not be closed properly.
	 * @throws FileNotFoundException When the specified file was not found. 
	 */
	public static RegionManager createFor(File file) throws IOException, FileNotFoundException {
		//Create stream. No need to close on exception, because stream will never be opened when the file is not found
		final FileInputStream fis = new FileInputStream(file);
		final RegionManager lf = createFor(fis); //Use the stream in other method; save in variable because stream has to be closed before returning
		fis.close(); //Then close the stream
		return lf;
	}
	
	
	
	
}
