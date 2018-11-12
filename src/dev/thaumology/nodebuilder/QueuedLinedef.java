package dev.thaumology.nodebuilder;

import dev.thaumology.world.Linedef;
import dev.thaumology.world.Vertex;

/**
 * Saves addittional data for a linedef while nodebuilding
 */
public class QueuedLinedef {

	private Linedef lineToProcess;
	private Linedef rootLine;
	private LineIteratorItem connecting;
	private Vertex connectingVertex;
	private int chunkDistance;
	
	public QueuedLinedef(Linedef lineToProcess, Linedef rootLine, LineIteratorItem connecting,
			Vertex connectingVertex, int chunkDistance) {
		this.lineToProcess = lineToProcess;
		this.rootLine = rootLine;
		this.connecting = connecting;
		this.connectingVertex = connectingVertex;
		this.chunkDistance = chunkDistance;
	} 
	
	public Linedef getLineToProcess() {
		return lineToProcess;
	}

	public Linedef getRootLine() {
		return rootLine;
	}

	public LineIteratorItem getConnectingItem() {
		return connecting;
	}

	public Linedef getConnectingLine() {
		return connecting.getLinedef();
	}
	
	public Vertex getConnectingVertex() {
		return connectingVertex;
	}
	
	public Vertex getOtherVertex() {
		return lineToProcess.getOtherVertex(connectingVertex);
	}
	
	public int getChunkDistance() {
		return chunkDistance;
	}
	
}
