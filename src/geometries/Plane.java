package geometries;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Plane extends Geometry implements FlatGeometry
{
	private Point3D _p1;
	private Point3D _p2;
	private Point3D _point;		
	private Vector _normal;

	// ***************** Constructors ********************** //
	/***
	 * default constructor
	 */
	public Plane() 
	{
		super(Color.white,new Material());
		_p1 = new Point3D();
		_p2 = new Point3D();
		_point = new Point3D();		
		_normal = new Vector();
	}
	
	/***
	 * copy constructor
	 * @param other - the plane we want to copy
	 */
	public Plane(Plane other) 
	{
		super(other.get_color(),other.get_material());
		this._p1 = new Point3D(other._p1);
		this._p2 = new Point3D(other._p2);
		this._point = new Point3D(other._point);
		this._normal = new Vector(other.getNormal(other._point));
	}
	
	/***
	 * constructor
	 * @param color - color of plane
	 * @param p1 - point 1
	 * @param p2 - point 2
	 * @param _point - point
	 * @param _material 
	 */
	
	public Plane(Color color, Point3D p1, Point3D p2, Point3D _point, Material _material)
	{
		super(color, _material);
		this._p1 = new Point3D(p1);
		this._p2 = new Point3D(p2);
		this._point = new Point3D(_point);
		this._normal = new Vector(this.getNormal(_point));
	}
	
	/***
	 * 
	 * @param color
	 * @param point
	 * @param direction
	 * @param _material
	 */
	public Plane(Color color, Point3D point, Vector direction, Material _material) 
	{
		super(color,_material);
		this._point = new Point3D(point);
		this._normal = new Vector(direction);
	}
	
	/***
	 * 
	 */
	@Override
	public String toString() {
		return "Plane [" + "point=" + _point + ", normal=" + _normal + "]";
	}
	
	
	/***
	 * check if 2 planes are the same*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plane other = (Plane) obj;
		if (_normal == null) {
			if (other._normal != null)
				return false;
		} else if (!_normal.equals(other._normal))
			return false;
		if (_p1 == null) {
			if (other._p1 != null)
				return false;
		} else if (!_p1.equals(other._p1))
			return false;
		if (_p2 == null) {
			if (other._p2 != null)
				return false;
		} else if (!_p2.equals(other._p2))
			return false;
		if (_point == null) {
			if (other._point != null)
				return false;
		} else if (!_point.equals(other._point))
			return false;
		return true;
	}
	
	// ***************** Getters/Setters ********************** //
	/***
	 * 
	 * @return
	 */
	public Point3D get_p1() {
		return new Point3D(_p1);
	}
	
	/***
	 * 
	 * @param p1
	 */
	public void set_p1(Point3D p1) {
		this._p1 = new Point3D(p1);
	}
	
	/***
	 * 
	 * @return
	 */
	public Point3D get_p2() {
		return new Point3D(_p2);
	}
	
	/***
	 * 
	 * @param p2
	 */
	public void set_p2(Point3D p2) {
		this._p2 = new Point3D(p2);
	}
	
	/***
	 * 
	 * @return
	 */
	public Point3D get_point() {
		return new Point3D(_point);
	}
	
	/***
	 * 
	 * @param point
	 */
	public void set_point(Point3D point) {
		this._point = new Point3D(point);
	}

	
	// override functions
	
	/***
	 * 
	 */
	@Override
	public Vector getNormal(Point3D point)
	{
		if (_normal != new Vector() && _normal != null) // if we already have the normal
		{
			return new Vector(_normal);
		}
		Point3D _p4 = new Point3D(
				(_p1.get_x().get_coordinate() - _p2.get_x().get_coordinate()),
				(_p1.get_y().get_coordinate() - _p2.get_y().get_coordinate()),
				(_p1.get_z().get_coordinate() - _p2.get_z().get_coordinate()));
		
		Point3D _p5 = new Point3D(
				(point.get_x().get_coordinate() - _point.get_x().get_coordinate()),
				(point.get_y().get_coordinate() - _point.get_y().get_coordinate()),
				(point.get_z().get_coordinate() - _point.get_z().get_coordinate()));
		
		Point3D _p6 = new Point3D(
				(point.get_x().get_coordinate() - _p2.get_x().get_coordinate()),
				(point.get_y().get_coordinate() - _p2.get_y().get_coordinate()),
				(point.get_z().get_coordinate() - _p2.get_z().get_coordinate()));
		
		Vector v = new Vector(_p4);
		if (_p5.equals(new Point3D(0,0,0)))
		{
			Vector u = new Vector(_p6);
			Vector w = v.crossProduct(u);
			w.normalize();
			return w;
		}
		else
		{
			Vector u = new Vector(_p5);
			Vector w = v.crossProduct(u);
			w.normalize();
			return w;
		}
	}
	
	/***
	 * 
	 */
	public ArrayList<Point3D> findIntersections(Ray r) {
		ArrayList<Point3D> interSection = new ArrayList<>();

		if (r.get_direction().dotProduct(_normal) == 0) {
			return interSection;
		}

		Vector v = new Vector(r.get_direction());
		Vector n = this.getNormal(this.get_point());
		Vector psubq = new Vector(r.get_POO());

		psubq.subtract(new Vector(this.get_point()));
		n.scale(-1); // -n

		double t = n.dotProduct(psubq); // -n*(p0 - q0)

		if (t * r.get_direction().dotProduct(n) > 0)
			return interSection;

		double ndotv = _normal.dotProduct(v);
		t /= ndotv; // n * v

		v.scale(t);

		Point3D p = r.get_POO();
		p.add(v);

		interSection.add(p);

		return interSection;

	}
	
	

	

}



