package dev.thaumology.io;

import org.jnbt.Tag;

import dev.thaumology.world.Linedef;
import dev.thaumology.world.VertexCache;

@FunctionalInterface
public interface LinedefConstructor {

	public Linedef construct(Tag tag, VertexCache cache);
	
}
