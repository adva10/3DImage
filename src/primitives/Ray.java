package primitives;

public class Ray {
	private Point3D _POO;
	private Vector _direction;

	/***
	 * default constructor
	 */
	public Ray() {
		super();
		_POO = new Point3D();
		_direction = new Vector();
	}

	/***
	 * constructor with parameters
	 * 
	 * @param POO
	 * @param direction
	 */
	public Ray(Point3D POO, Vector direction) {
		super();
		this._POO = new Point3D(POO);
		this._direction = new Vector(direction);
	}

	/***
	 * copy constructor
	 * 
	 * @param POO
	 * @param direction
	 */
	public Ray(Ray other) {
		super();
		this._POO = new Point3D(other._POO);
		this._direction = new Vector(other._direction);
	}

	/***
	 * get _poo of the ray
	 * 
	 * @return
	 */
	public Point3D get_POO() {
		return new Point3D(_POO);
	}

	/***
	 * set _poo of the ray
	 * 
	 * @param POO
	 */
	public void set_POO(Point3D POO) {
		this._POO = new Point3D(POO);
	}

	/***
	 * get_direction of the ray
	 * 
	 * @return
	 */
	public Vector get_direction() {
		return new Vector(_direction);
	}

	/***
	 * set_direction of the ray
	 * 
	 * @return
	 */
	public void set_direction(Vector direction) {
		this._direction = new Vector(direction);
	}

	/***
	 * equals - checks if this ray is equal to another object
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		if (_POO == null) {
			if (other._POO != null)
				return false;
		} else if (!_POO.equals(other._POO))
			return false;
		if (_direction == null) {
			if (other._direction != null)
				return false;
		} else if (!_direction.equals(other._direction))
			return false;
		return true;
	}

	/***
	 * to string - returns the string of this ray
	 */
	@Override
	public String toString() {
		return "Ray [POO=" + _POO + ", direction=" + _direction + "]";
	}

}