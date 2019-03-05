package dev.thaumology.start;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.jnbt.CompoundTag;
import org.jnbt.NBTInputStream;
import org.jnbt.Tag;

import dev.thaumology.io.error.LevelFormatException;
import dev.thaumology.render.SingleRegionRenderScene;
import dev.thaumology.world.RegionManager;
import dev.thaumology.world.World;
import lb.simplebase.core.Framework;
import lb.simplebase.core.FrameworkStateException;
import lb.simplebase.core.InvalidSceneException;

public class ThaumologyMain {

	public static final File TEST_FILE = new File("C:/jtest/th_eng_test.tnbt");
	public static final boolean ATTEMPT_READ_DEBUG = false;
	
	public static void main(String[] args) throws FrameworkStateException, InvalidSceneException {
		//Read the test file
		FileInputStream fis = null;
		NBTInputStream nis = null;
		Tag mainTag = null;
		
		if(ATTEMPT_READ_DEBUG) {
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
		}
		
		Framework.getFramework().init(30, 20, "TestRenderer", new Dimension(800, 600), false, true);
		Framework.getFramework().registerScene(new SingleRegionRenderScene());
		Framework.getFramework().start(false, "scene");
		
	}

}
