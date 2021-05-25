package geometries;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Sphere extends Geometry {

	private double _radius;
	private Point3D _center;

	/***
	 * default constructor
	 */
	public Sphere() {
		_radius = 0;
		_center = new Point3D();
	}

	/***
	 * constructor
	 * 
	 * @param color
	 *            with parameters
	 * @param radius
	 *            of the Sphere
	 * @param center
	 *            of the Sphere
	 * @param material
	 *            of the Sphere
	 */
	public Sphere(Color color, double radius, Point3D center, Material material) {
		super(color, material);
		this._radius = radius;
		this._center = new Point3D(center);
	}

	/***
	 * copy constructor
	 * 
	 * @param another
	 *            Sphere
	 */
	public Sphere(Sphere other) {
		super(other._color, other._material);
		this._radius = other._radius;
		this._center = new Point3D(other._center);
	}

	/***
	 * 
	 * @return radius of the Sphere
	 */
	public double get_radius() {
		return _radius;
	}

	/***
	 * radius of the Sphere
	 * 
	 * @param radius
	 */
	public void set_radius(double radius) {
		this._radius = radius;
	}

	/***
	 * get center of the Sphere
	 * 
	 * @return _center
	 */
	public Point3D get_center() {
		return new Point3D(_center);
	}

	/***
	 * set center of the Sphere
	 * 
	 * @param center
	 */
	public void set_center(Point3D center) {
		this._center = new Point3D(center);
	}

	/***
	 * equals checks if our Sphere equals to another Object returns boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sphere other = (Sphere) obj;
		if (_center == null) {
			if (other._center != null)
				return false;
		} else if (!_center.equals(other._center))
			return false;
		if (Double.doubleToLongBits(_radius) != Double.doubleToLongBits(other._radius))
			return false;
		return true;
	}

	/***
	 * returns string with the data in our Sphere
	 */
	@Override
	public String toString() {
		return "Sphere [_radius=" + _radius + ", _center=" + _center + "]";
	}

	/***
	 * returns the normal to our Sphere
	 */
	@Override
	public Vector getNormal(Point3D point) {
		Vector vector = new Vector(_center, point);
		vector.normalize();
		return vector;

	}

	/***
	 * finds intersections point through our Sphere with ray r
	 */
	public List<Point3D> findIntersections(Ray r) {
		List<Point3D> intersections = new ArrayList<>();

		Vector L = new Vector(r.get_POO(), new Point3D(_center));
		Vector V = new Vector(new Vector(r.get_direction()));

		V.normalize();
		double tm = L.dotProduct(V);

		if (tm < 0)
			return intersections;

		double dis = Math.pow(Math.pow(L.length(), 2) - Math.pow(tm, 2), 0.5);
		double th = Math.pow(Math.pow(_radius, 2) - Math.pow(dis, 2), 0.5);
		double t1 = tm - th;
		double t2 = tm + th;

		// no intersections
		if (dis > _radius) {
			return new ArrayList<>();
		}

		// if its tangent
		if (t1 < 0 || t2 < 0)
			return new ArrayList<Point3D>();
		if (dis == _radius) {
			// the th equal to 0
			Vector v = new Vector(V);
			Point3D p = new Point3D(r.get_POO());

			v.scale(tm);
			p.add(v);
			intersections.add(p);
		}

		// in the cylinder meaning 2 intersections
		if (dis < _radius) {

			Vector v1 = new Vector(V);
			Point3D p1 = new Point3D(r.get_POO());
			Vector v2 = new Vector(V);
			Point3D p2 = new Point3D(r.get_POO());

			v1.scale(t1);
			p1.add(v1);
			v2.scale(t2);
			p2.add(v2);

			// add to array
			intersections.add(p1);
			intersections.add(p2);

		}
		return intersections;
	}

}

