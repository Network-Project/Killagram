package server;

import java.io.Serializable;

public class IntakeInfo implements Serializable{
	private final String id;
	private final String date; // format(yyyy-mm-dd)
	private final String type;
	private final String name;
	private final String unit;
	private final int quantity;
	private final float kal;
	
	public String getId() {
		return id;
	}
	public String getDate() {
		return date;
	}
	public String getName() {
		return name;
	}
	public int getQuantity() {
		return quantity;
	}
	public float getKal() {
		return kal;
	}
	
	
	public String getType() {
		return type;
	}
	
	
	public String getUnit() {
		return unit;
	}
	
	public IntakeInfo(String id, String date, String type, String name, String unit, int quantity, float kal) {
		super();
		this.id = id;
		this.date = date;
		this.type = type;
		this.name = name;
		this.unit = unit;
		this.quantity = quantity;
		this.kal = kal;
	}
	
}
