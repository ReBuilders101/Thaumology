package dev.thaumology.start;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.jnbt.CompoundTag;
import org.jnbt.NBTInputStream;
import org.jnbt.Tag;

import dev.thaumology.io.LevelFactory;
import dev.thaumology.io.LevelFormatException;
import dev.thaumology.world.World;

public class ThaumologyMain {

	public static final File TEST_FILE = new File("C:/jtest/th_eng_test.tnbt");
	
	public static void main(String[] args) {
		//Read the test file
		FileInputStream fis = null;
		NBTInputStream nis = null;
		Tag mainTag = null;
		try {
			fis = new FileInputStream(TEST_FILE);
			nis = new NBTInputStream(fis);
			mainTag = nis.readTag();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Exception while reading file content!\nPossible format errors!");
			System.exit(1);
		} finally {
			try {
				if(fis != null) fis.close();
				if(nis != null) nis.close();
			} catch(IOException ex) {
				ex.printStackTrace();
				System.err.println("WARNING: Exception while closing File Stream(s)!\nStreams may be unclosed!");
				System.exit(1);
			}
		}
		
		@SuppressWarnings("unused")
		World testWorld;
		try {
			testWorld = LevelFactory.createWorld(mainTag);
		} catch (LevelFormatException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
