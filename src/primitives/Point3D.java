package primitives;

public class Point3D extends Point2D {

	protected Coordinate _z;

	/***
	 * Default constructor sends to the super
	 */
	public Point3D() {
		super(0, 0);
		this._z = new Coordinate(0);
	}

	/***
	 * constructor with parameters Coordinate
	 * 
	 * @param x
	 *            first coordinate
	 * @param y
	 *            second coordinate
	 * @param z
	 *            third coordinate
	 */
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		super(x, y);
		this._z = new Coordinate(z);
	}

	/***
	 * constructor with double parameters
	 * 
	 * @param x
	 *            double
	 * @param y
	 *            double
	 * @param z
	 *            double
	 */
	public Point3D(double x, double y, double z) {
		super(x, y);
		this._z = new Coordinate(z);
	}

	/***
	 * copy constructor
	 * 
	 * @param other
	 */
	public Point3D(Point3D other) {
		super(other._x, other._y);
		this._z = new Coordinate(other._z.get_coordinate());
	}

	/***
	 * return Coordinate z
	 * 
	 * @return _z
	 */
	public Coordinate get_z() {
		return new Coordinate(_z);
	}

	/***
	 * set _z a new value
	 * 
	 * @param z
	 */
	public void set_z(Coordinate z) {
		this._z = new Coordinate(z);
	}

	/***
	 * checks if our Point3D equals Object return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point3D other = (Point3D) obj;
		if (_z == null) {
			if (other._z != null)
				return false;
		} else if (!_z.equals(other._z))
			return false;
		return true;
	}

	/***
	 * returns a string with the information in our Point3D
	 */
	@Override
	public String toString() {
		return super.toString() + "[z=" + _z + "]";
	}

	/***
	 * add Vector to our Point3D
	 * 
	 * @param vector
	 *            return
	 */
	public void add(Vector vector) {
		super._x.add(vector.get_head().get_x());
		super._y.add(vector.get_head().get_y());
		_z.add(vector.get_head().get_z());
	}

	/***
	 * add Point3D p to our Point3D
	 * 
	 * @param Point3D
	 *            p
	 */
	public void add(Point3D p) {
		super._x.add(p.get_x());
		super._y.add(p.get_y());
		_z.add(p.get_z());
	}

	/***
	 * subtract vector from our Point3D
	 * 
	 * @param vector
	 */
	public void subtract(Vector vector) {
		super._x.subtract(vector.get_head().get_x());
		super._y.subtract(vector.get_head().get_y());
		_z.subtract(vector.get_head().get_z());
	}

	/***
	 * subtract Point3D p from our Point3D
	 * 
	 * @param p
	 */
	public void subtract(Point3D p) {
		super._x.subtract(p.get_x());
		super._y.subtract(p.get_y());
		_z.subtract(p.get_z());
	}

	/***
	 * calculate the distance between point and our Point3D
	 * 
	 * @param point
	 * @return
	 */
	public double distance(Point3D point) {
		return Math.sqrt(Math.pow(_x.get_coordinate() - point._x.get_coordinate(), 2)
				+ Math.pow(_y.get_coordinate() - point._y.get_coordinate(), 2)
				+ Math.pow(_z.get_coordinate() - point._z.get_coordinate(), 2));

	}
}