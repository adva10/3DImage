package primitives;

public class Point2D {
	/***
	 * our fields
	 */
	protected Coordinate _x;
	protected Coordinate _y;

	public Point2D() {
		this._x = new Coordinate(0);
		this._y = new Coordinate(0);
	}

	/***
	 * copy constructor no.1
	 * 
	 * @param x
	 * @param y
	 */
	public Point2D(Coordinate x, Coordinate y) {
		super();
		this._x = new Coordinate(x);
		this._y = new Coordinate(y);
	}

	/***
	 * constructor with parameters
	 * 
	 * @param x
	 * @param y
	 */
	public Point2D(double x, double y) {
		super();
		this._x = new Coordinate(x);
		this._y = new Coordinate(y);
	}

	/***
	 * copy constructor no.2
	 * 
	 * @param x
	 * @param y
	 */
	public Point2D(Point2D other) {
		this._x = new Coordinate(other._x);
		this._y = new Coordinate(other._y);
	}

	/***
	 * get_x
	 * 
	 * @return
	 */
	public Coordinate get_x() {
		return new Coordinate(_x);
	}

	/***
	 * get_x
	 * 
	 * @return
	 */
	public void set_x(Coordinate x) {
		this._x = new Coordinate(x);
	}

	/***
	 * get_x
	 * 
	 * @return
	 */
	public Coordinate get_y() {
		return new Coordinate(_y);
	}

	/***
	 * set_x
	 * 
	 * @return
	 */

	public void set_y(Coordinate y) {
		this._y = new Coordinate(y);
	}

	/***
	 * check if this point2d equals to another object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point2D other = (Point2D) obj;
		if (_x == null) {
			if (other._x != null)
				return false;
		} else if (!_x.equals(other._x))
			return false;
		if (_y == null) {
			if (other._y != null)
				return false;
		} else if (!_y.equals(other._y))
			return false;
		return true;
	}

	/***
	 * to string - return the string of the point2d
	 */
	@Override
	public String toString() {
		return "[x=" + _x + ", y=" + _y + "]";
	}

}