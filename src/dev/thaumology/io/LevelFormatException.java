package dev.thaumology.io;

import java.io.PrintWriter;

import org.jnbt.Tag;

public class LevelFormatException extends Exception{
	private static final long serialVersionUID = -2691212623128254021L;
	private static final String NO_ERR_MSG = "No error message";
	
	private Tag erroredTag;
	
	public LevelFormatException() {
		this(NO_ERR_MSG, null);
	}

	public LevelFormatException(String message) {
		this(message, null);
	}

	public LevelFormatException(Tag erroredTag) {
		this(NO_ERR_MSG, erroredTag);
	}

	public LevelFormatException(String message, Tag erroredTag) {
		super(message);
		this.erroredTag = erroredTag;
	}
	
	public Tag getErroredTag() {
		return erroredTag;
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		s.println("Errored Tag: " + erroredTag.toString());
		super.printStackTrace(s);
	}
	
}
