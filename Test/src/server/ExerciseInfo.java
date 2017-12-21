package server;

import java.io.Serializable;

public class ExerciseInfo implements Serializable{
	private final String id;
	private final String date; // format(yyyy-mm-dd)
	private final String name;
	private final float time;
	private float kal = 0;
	public String getId() {
		return id;
	}
	public String getDate() {
		return date;
	}
	public String getName() {
		return name;
	}
	public float getTime() {
		return time;
	}
	public float getKal() {
		return kal;
	}
	
	public void setKal(float kal) {
		this.kal = kal;
	}
	public ExerciseInfo(String id, String date, String name, float time) {
		super();
		this.id = id;
		this.date = date;
		this.name = name;
		this.time = time;
	}
	
	public ExerciseInfo(String id, String date, String name, float time, float kal) {
		super();
		this.id = id;
		this.date = date;
		this.name = name;
		this.time = time;
		this.kal = kal;
	}
	
}
