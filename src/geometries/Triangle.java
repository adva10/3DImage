package geometries;

import java.awt.Color;
import java.util.ArrayList;

import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Triangle extends Geometry implements FlatGeometry {

	private Point3D _p1;
	private Point3D _p2;
	private Point3D _p3;

	/***
	 * constructor
	 * 
	 * @param color
	 *            - color of geometry
	 * @param p1
	 *            - first point
	 * @param p2
	 *            - second point
	 * @param p3
	 *            - third point
	 * @param material
	 */
	public Triangle(Color color, Point3D p1, Point3D p2, Point3D p3, Material material) {
		super(color, material);
		this._p1 = new Point3D(p1);
		this._p2 = new Point3D(p2);
		this._p3 = new Point3D(p3);
	}

	/***
	 * empty constructor
	 */
	public Triangle() {
		super();
		_p1 = new Point3D();
		_p2 = new Point3D();
		_p3 = new Point3D();
	}

	/***
	 * copy constructor
	 * 
	 * @param other
	 */
	public Triangle(Triangle other) {
		super(other.get_color(), other.get_material());
		this._p1 = new Point3D(other._p1);
		this._p2 = new Point3D(other._p2);
		this._p3 = new Point3D(other._p3);
	}

	/***
	 * @return p1 - point 1 on triangle
	 */
	public Point3D get_p1() {
		return new Point3D(_p1);
	}

	/***
	 * set p1 - point 1 on triangle
	 */
	public void set_p1(Point3D p1) {
		this._p1 = new Point3D(p1);
	}

	/***
	 * @return p2 - point 2 on triangle
	 */
	public Point3D get_p2() {
		return new Point3D(_p2);
	}

	/***
	 * set p2 - point 2 on triangle
	 */
	public void set_p2(Point3D p2) {
		this._p2 = new Point3D(p2);
	}

	/***
	 * @return p3 - point 3 on triangle
	 */
	public Point3D get_p3() {
		return new Point3D(_p3);
	}

	/***
	 * @param p3
	 *            - point 3 on triangle
	 */
	public void set_p3(Point3D p3) {
		this._p3 = new Point3D(p3);
	}

	/***
	 * find normal at some point on triangle
	 * 
	 * @param p
	 *            - the point e want our normal start
	 */
	@Override
	public Vector getNormal(Point3D p) {
		Vector v1 = new Vector(_p1, _p2);
		Vector v2 = new Vector(_p1, _p3);
		Vector v = v2.crossProduct(v1);
		v.normalize();
		v.scale(-1);
		return v;
	}

	/***
	 * finds intersections point through our Triangle with ray
	 */
	public ArrayList<Point3D> findIntersections(Ray ray) {

		ArrayList<Point3D> list = new ArrayList<Point3D>(); // our list of
															// intersection
															// points

		// finding out if there is intersection points on the triangle's plane
		Point3D h1 = new Point3D(_p2);
		Point3D h2 = new Point3D(_p3);
		h1.subtract(_p1);
		h2.subtract(_p1);
		Vector u1 = new Vector(h1);
		Vector u2 = new Vector(h2);
		Plane plane = new Plane(_color, _p1, u1.crossProduct(u2), new Material());
		list = plane.findIntersections(ray);

		if (!list.isEmpty()) {

			Point3D p = new Point3D(list.get(0));// the first intersection point
			Vector pv = new Vector(p, (ray.get_POO())); // creating vector from
														// the ray's starting
														// point

			Vector v1 = new Vector(_p1);
			v1.subtract(new Vector(ray.get_POO()));
			Vector v2 = new Vector(_p2);
			v2.subtract(new Vector(ray.get_POO()));
			Vector v3 = new Vector(_p3);
			v3.subtract(new Vector(ray.get_POO()));

			Vector n1 = new Vector(v1.crossProduct(v2));
			Vector n2 = new Vector(v2.crossProduct(v3));
			Vector n3 = new Vector(v3.crossProduct(v1));

			n1.normalize();
			n2.normalize();
			n3.normalize();

			if (!((pv.dotProduct(n1) > 0 && pv.dotProduct(n2) > 0 && pv.dotProduct(n3) > 0)
					|| (pv.dotProduct(n1) < 0 && pv.dotProduct(n2) < 0 && pv.dotProduct(n3) < 0))) {
				list.remove(p);
			}
		}
		return list;
	}

	/***
	 * change to string
	 */
	@Override
	public String toString() {
		return "Triangle [p1=" + _p1 + ", p2=" + _p2 + ", p3=" + _p3 + "]";
	}

}
