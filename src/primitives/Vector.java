package primitives;

public class Vector {
	private Point3D _head;

	// ***************** Constructors ********************** //

	/***
	 * default constructor
	 */
	public Vector() {
		super();
	}

	/***
	 * constructor with parameters- 2 points (create vector from 
	 * the substrcation from 2 points)
	 * @param p1 - first point
	 * @param p2 - second point
	 */
	public Vector(Point3D p1, Point3D p2) {
		Coordinate x = new Coordinate(p2.get_x().get_coordinate() - p1.get_x().get_coordinate());
		Coordinate y = new Coordinate(p2.get_y().get_coordinate() - p1.get_y().get_coordinate());
		Coordinate z = new Coordinate(p2.get_z().get_coordinate() - p1.get_z().get_coordinate());
		_head = new Point3D(x, y, z);
	}

	/***
	 * constructor with one point
	 * @param head
	 */
	public Vector(Point3D head) {
		super();
		this._head = new Point3D(head);
	}

	/***
	 * constructor with 3 double that represent point 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector(double x, double y, double z) {
		_head = new Point3D(x, y, z);
	}

	/***
	 * copy constructor
	 * @param other
	 */
	public Vector(Vector other) {
		super();
		this._head = new Point3D(other._head);
	}

	// ***************** Getters/Setters ********************** //
	/***
	 * get point of vector
	 * @return
	 */
	public Point3D get_head() {
		return new Point3D(_head);
	}

	/***
	 * set new point to vector
	 * @param head
	 */
	public void set_head(Point3D head) {
		this._head = new Point3D(head);
	}

	/***
	 * check if 2 vectors equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (_head == null) {
			if (other._head != null)
				return false;
		} else if (!_head.equals(other._head))
			return false;
		return true;
	}

	/***
	 * convert to string
	 */
	@Override
	public String toString() {
		return "Vector [head=" + _head + "]";
	}

	
	/***
	 *@ return the length of vector
	 */
	public double length() {
		return Math.pow((Math.pow(_head.get_x().get_coordinate(), 2)) + (Math.pow(_head.get_y().get_coordinate(), 2))
				+ (Math.pow(_head.get_z().get_coordinate(), 2)), 0.5);
	}

	/***
	 * normalize the vector
	 */
	public void normalize() {
		scale(1 / length());
	}

	/***
	 * @param other -vector, add it to the exist vector
	 */
	public void add(Vector other) {
		_head.add(other);
	}

	/***
	 * @param other-vector,subtract it to from exist vector
	 */
	public void subtract(Vector other) {
		_head.subtract(other);
	}

	/***
	 * @param scalingFactor - scale x,y,z of point head
	 */
	public void scale(double scalingFactor) {
		_head._x.set_coordinate(_head.get_x().get_coordinate() * scalingFactor);
		_head._y.set_coordinate(_head.get_y().get_coordinate() * scalingFactor);
		_head._z.set_coordinate(_head.get_z().get_coordinate() * scalingFactor);
	}

	/***
	 * cross product that return the normal
	 * @param other
	 * @return the normal to the 2 vector
	 */
	public Vector crossProduct(Vector other) {
		Vector v = new Vector(new Point3D(
				new Coordinate(this._head.get_y().get_coordinate() * other._head.get_z().get_coordinate()
						- this._head.get_z().get_coordinate() * other._head.get_y().get_coordinate()),
				new Coordinate(-this._head.get_x().get_coordinate() * other._head.get_z().get_coordinate()
						+ this._head.get_z().get_coordinate() * other._head.get_x().get_coordinate()),
				new Coordinate(this._head.get_x().get_coordinate() * other._head.get_y().get_coordinate()
						- this._head.get_y().get_coordinate() * other._head.get_x().get_coordinate())));
		return v;
	}

	/***
	 * dot product between 2 vector
	 * @param other
	 * @return a number - double
	 */
	public double dotProduct(Vector other) {
		double result = 0;
		result += other._head.get_x().get_coordinate() * this._head.get_x().get_coordinate();
		result += other._head.get_y().get_coordinate() * this._head.get_y().get_coordinate();
		result += other._head.get_z().get_coordinate() * this._head.get_z().get_coordinate();
		return result;
	}

	/***
	 * move the vector by adding to the point scalingFactor
	 * @param scalingFactor 
	 */
	public void move(double scalingFactor) {
		_head._x.set_coordinate(_head.get_x().get_coordinate() + scalingFactor);
		_head._y.set_coordinate(_head.get_y().get_coordinate() + scalingFactor);
		_head._z.set_coordinate(_head.get_z().get_coordinate() + scalingFactor);
	}

}