package server;

import java.io.Serializable;

public class Food implements Serializable{
	private final String type;
	private final String name;
	private final String unit;
	private final float kal;
	
	public Food(String type,String name, String unit, float kal) {
		this.type = type;
		this.name = name;
		this.unit = unit;
		this.kal = kal;
	}

	
	public String getType() {
		return type;
	}


	public String getName() {
		return name;
	}


	public String getUnit() {
		return unit;
	}


	public float getKal() {
		return kal;
	}
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(kal);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Food other = (Food) obj;
		if (Float.floatToIntBits(kal) != Float.floatToIntBits(other.kal))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
	}
	
}
