package dev.thaumology.io;

import java.util.function.Consumer;

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
	public static <T,R extends T,E extends Exception> R type(T obj, Class<R> type, E ex, Consumer<E> handler){
		notNull(obj, ex, handler);
		if(!(obj.getClass() == type)) handler.accept(ex);
		return (R) obj;
	}
	
	public static <E extends Exception> ListTag listTagOf(Tag tag, Class<? extends Tag> elementType, E ex) throws E{
		type(tag, ListTag.class, ex);
		if(!(((ListTag) tag).getType() == elementType)) throw ex;
		return (ListTag) tag;
	}
	
	
	public static <T,E extends Exception> T notNull(T obj, E ex, Consumer<E> handler){
		if(obj == null) handler.accept(ex);
		return obj;
	}
	
	public static <E extends Exception> ListTag listTagOf(Tag tag, Class<? extends Tag> elementType, E ex, Consumer<E> handler){
		type(tag, ListTag.class, ex, handler);
		if(!(((ListTag) tag).getType() == elementType)) handler.accept(ex);
		return (ListTag) tag;
	}
	
	public static <T,R extends T> R typeOrDefault(T obj, Class<R> type, R def){
		try {
			return type(obj, type, new Exception());
		} catch (Exception e) {
			return def;
		}
	}
	
}
