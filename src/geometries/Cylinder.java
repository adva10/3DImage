package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Geometry {
	private double _radius;
	private Point3D _axisPoint;
	private Vector _axisDirection;

	// ***************** Constructors ********************** //
	/***
	 * constructor with parameters
	 * 
	 * @param radius
	 * @param axisPoint
	 * @param axisDirection
	 */
	public Cylinder(double radius, Point3D axisPoint, Vector axisDirection) {
		super();
		this._radius = radius;
		this._axisPoint = new Point3D(axisPoint);
		this._axisDirection = new Vector(axisDirection);
	}

	/***
	 * copy constructor
	 * 
	 * @param other
	 */
	public Cylinder(Cylinder other) {
		super();
		this._radius = other._radius;
		this._axisPoint = new Point3D(other._axisPoint);
		this._axisDirection = new Vector(other._axisDirection);
	}

	/***
	 * default constructor
	 */
	public Cylinder() {
		super();
		_radius = 0;
	}

	// ***************** Getters/Setters ********************** //

	public double get_radius() {
		return _radius;
	}

	public void set_radius(double radius) {
		this._radius = radius;
	}

	public Point3D get_axisPoint() {
		return new Point3D(_axisPoint);
	}

	public void set_axisPoint(Point3D axisPoint) {
		this._axisPoint = new Point3D(axisPoint);
	}

	public Vector get_axisDirection() {
		return new Vector(_axisDirection);
	}

	public void set_axisDirection(Vector axisDirection) {
		this._axisDirection = new Vector(axisDirection);
	}

	@Override
	public String toString() {
		return "Cylinder [_radius=" + _radius + ", _axisPoint=" + _axisPoint + ", _axisDirection=" + _axisDirection
				+ "]";
	}

	/***
	 * compares between our Cylinder and another object return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cylinder other = (Cylinder) obj;
		if (_axisDirection == null) {
			if (other._axisDirection != null)
				return false;
		} else if (!_axisDirection.equals(other._axisDirection))
			return false;
		if (_axisPoint == null) {
			if (other._axisPoint != null)
				return false;
		} else if (!_axisPoint.equals(other._axisPoint))
			return false;
		if (Double.doubleToLongBits(_radius) != Double.doubleToLongBits(other._radius))
			return false;
		return true;
	}

	public Vector getNormal(Point3D point) {
		return null;
	}

	public List<Point3D> findIntersections(Ray r) {
		return null;
	}
}
