package dev.thaumology.io;

import org.jnbt.CompoundTag;
import org.jnbt.ListTag;
import org.jnbt.Tag;

public final class Validate {
	
	public static <T,E extends Exception> T notNull(T obj, E ex) throws E{
		if(obj == null) throw ex;
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R extends T,E extends Exception> R type(T obj, Class<R> type, E ex)throws E{
		notNull(obj, ex);
		if(!(obj.getClass() == type)) throw ex;
		return (R) obj;
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R extends T,E extends Exception, F extends Exception> R type(T obj, Class<R> type, E ex, F nullEx)throws E, F{
		notNull(obj, nullEx);
		if(!(obj.getClass() == type)) throw ex;
		return (R) obj;
	}
	
	public static <E extends Exception> ListTag listTagOf(Tag tag, Class<? extends Tag> elementType, E ex) throws E{
		type(tag, ListTag.class, ex);
		if(!(((ListTag) tag).getType() == elementType)) throw ex;
		return (ListTag) tag;
	}
	
	public static <E extends Exception, F extends Exception> ListTag listTagOf(Tag tag, Class<? extends Tag> elementType, E listEx, F elementEx) throws E, F{
		type(tag, ListTag.class, listEx);
		if(!(((ListTag) tag).getType() == elementType)) throw elementEx;
		return (ListTag) tag;
	}
	
	public static <T,R extends T> R typeOrDefault(T obj, Class<R> type, R def){
		try {
			return type(obj, type, new Exception());
		} catch (Exception e) {
			return def;
		}
	}
	
	public static <E extends Exception> Tag getTag(CompoundTag parent, String name, E nullEx) throws E {
		return notNull(parent.getValue().get(name), nullEx);
	}
	
	public static <E extends Exception, F extends Exception, R extends Tag> R getTag(CompoundTag parent, String name, Class<R> type, E nullEx, F typeEx) throws E, F {
		return type(getTag(parent, name, nullEx), type, typeEx);
	}
	
	public static <E extends Exception, F extends Exception, G extends Exception> ListTag getListTagOf(CompoundTag parent, String name, Class<? extends Tag> elementType, E nullEx, F typeEx, G elementEx) throws E, F, G {
		return listTagOf(
				getTag(parent, name, ListTag.class, nullEx, typeEx)
				, elementType, typeEx, elementEx);
	}
	
}
