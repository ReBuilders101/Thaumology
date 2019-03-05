package dev.thaumology.world;

import java.util.HashMap;
import java.util.Map;

import dev.thaumology.world.core.Vertex;

/** 
 * The {@link VertexCache} stores the {@link Vertex} instances during level creation that have to be used by more than one {@link Region}.
 * These are usually on the region edge or corner. Vertices that are not shared across regions are not stored here to save space.
 * To find a vertex by its coordinates, use {@link World#getVertexAt(int, int)}. This class should only be used while building the level structure.
 * Once a vertex has been added for a pair of coordinates, it cannot be changed or removed.<br>All methods are thread-safe.
 */
public class VertexCache {

	private Map<Long, Vertex> vertexMap;
	
	/**
	 * Creates a new vertex cache with an initial vertex capacity of 16.<br>
	 * The created vertex cache is empty.
	 */
	public VertexCache() {
		vertexMap = new HashMap<>();
	}
	
	/**
	 * Creates a new vertex cache with an initial capacity of <code>1.4 * expectedSize</code>.
	 * This means that the map will never be rehashed as long as its actual size is below or equal to the expected size,
	 * while also having good lookup performance with a load factor of <code>0.75</code>.<br>
	 * <i>Note: The factor <code>1.4</code> is a rough approximation of <code>1.333...</code>, which is the inverse of the load factor <code>0.75</code>.</i>
	 * The created vertex cache is empty.<br>
	 * <i><b>The implementation using the modified load factor may change in the future.</b></i>
	 * @param initialCapacity The initial vertex capacity
	 */
	public VertexCache(int expectedSize) {
		vertexMap = new HashMap<>((int) (expectedSize * 1.4));
	}
	
	/**
	 * Returns a {@link Vertex} from the cache or creates a new entry in the cache when it does not yet exist
	 * The newly created entry is then returned.<br>
	 * This method is thread-safe.
	 * @param x The x-coordinate of the vertex
	 * @param y The y-coordinate of the vertex
	 * @param height The height information that is stored in the {@link Vertex}. Used when a new Instance has to be created.
	 * @return The vertex at these coordinates
	 */
	public Vertex getOrCreateVertex(int x, int y, int height) {
		//read index
		long index = RegionManager.encodeRegionPos(x, y);
		//if value present -> return, no sync because values are immutable once set
		if(vertexMap.containsKey(index)) {
			return vertexMap.get(index);
		} else { //else: synchronize creation
			synchronized (this) {
				//check again: maybe another thread that was also creating entry has executed while this one was waiting
				if(vertexMap.containsKey(index)) {
					return vertexMap.get(index);
				} else { //if that is not the case, create entry
					Vertex vert = new Vertex(x, y, height);
					vertexMap.put(index, vert); //add created vertex to cache
					return vert; //return the created Vertex (lock will be released correctly, I looked it up on SO)
				}
			}
		}
	}
	
	/**
	 * Attempts to find a {@link Vertex} in this cache for the specified index.<br>
	 * The index contains information about the coordinates of the vertex, the {@link RegionManager#encodeRegionPos(int, int)} method
	 * is used for encoding the coordinates into the index.<br>
	 * If no mapping exists for this index, this method returns <code>null</code>.
	 * @param index The index of the requested vertex
	 * @return The requested vertex for this index
	 */
	public Vertex getVertex(long index) {
		return vertexMap.get(index);
	}
	
	/**
	 * Returns a {@link Vertex} from the cache or creates a new instance that will <b>not</b> be added to the cache
	 * The newly created instance is then returned.<br>
	 * This method is thread-safe.
	 * @param x The x-coordinate of the vertex
	 * @param y The y-coordinate of the vertex
	 * @param height The height information that is stored in the {@link Vertex}. Used when a new Instance has to be created.
	 * @return The vertex at these coordinates
	 */
	public Vertex getOrNewVertex(int x, int y, int height) {
		Vertex vertex = vertexMap.get(RegionManager.encodeRegionPos(x, y));
		return vertex == null ? new Vertex(x, y, height) : vertex;
	}
	
	/**
	 * Attempts to find a {@link Vertex} in this cache for the specified coordinates.<br>
	 * If no mapping exists for these coordinates, this method returns <code>null</code>.
	 * @param x The x-coordinate of the vertex
	 * @param y The y-coordinate of the vertex
	 * @return The vertex at these coordinates
	 */
	public Vertex getVertex(int x, int y) {
		return vertexMap.get(RegionManager.encodeRegionPos(x, y));
	}
	
	/**
	 * The current size of the vertex cache; or the amount of vertices saved in this cache.<br>
	 * This number is the size of the underlying {@link HashMap}.
	 * @return The vertex cache size
	 */
	public int getCurrentSize() {
		return vertexMap.size();
	}
}
