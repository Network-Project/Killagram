package server;

import java.io.Serializable;

public class Exercise implements Serializable {
	private final String name;
	private final float MET;
	
	public Exercise(String name, float mET) {
		super();
		this.name = name;
		MET = mET;
	}
	public String getName() {
		return name;
	}
	public float getMET() {
		return MET;
	} 
}
