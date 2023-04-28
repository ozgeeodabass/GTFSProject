package ime.gtfs.entities;

import java.io.Serializable;

public class ShapeId implements Serializable {
	
	private int shapeId;
	private double shapePtLon;
	private double shapePtLat;
	private int shapePtSequence;
	
	public ShapeId(int shapeId, double shapePtLon, double shapePtLat, int shapePtSequence) {
		super();
		this.shapeId = shapeId;
		this.shapePtLon = shapePtLon;
		this.shapePtLat = shapePtLat;
		this.shapePtSequence = shapePtSequence;
	}
	
	

	public ShapeId() {
		super();
	}



	public int getShapeId() {
		return shapeId;
	}

	public void setShapeId(int shapeId) {
		this.shapeId = shapeId;
	}

	public double getShapePtLon() {
		return shapePtLon;
	}

	public void setShapePtLon(double shapePtLon) {
		this.shapePtLon = shapePtLon;
	}

	public double getShapePtLat() {
		return shapePtLat;
	}

	public void setShapePtLat(double shapePtLat) {
		this.shapePtLat = shapePtLat;
	}

	public int getShapePtSequence() {
		return shapePtSequence;
	}

	public void setShapePtSequence(int shapePtSequence) {
		this.shapePtSequence = shapePtSequence;
	}
	
	

}
