package primitives;

public class Coordinate {
	private double _coordinate;

	/***
	 * default constructor
	 */
	public Coordinate() {
		_coordinate = 0;
	}

	/***
	 * constructor with parameters
	 * @param coordinate - set the new value
	 */
	public Coordinate(double coordinate) {
		super();
		this._coordinate = coordinate;
	}

	/***
	 * copy constructor
	 * @param other - the coordinate to copy
	 */
	public Coordinate(Coordinate other) {
		this._coordinate = other.get_coordinate();
	}

	/***
	 * get coordinate
	 * @return
	 */
	public double get_coordinate() {
		return _coordinate;
	}

	
	/***
	 * set
	 * @param coordinate - set the value
	 */
	public void set_coordinate(double coordinate) {
		this._coordinate = coordinate;
	}

	
	/***
	 * check if its equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (Double.doubleToLongBits(_coordinate) != Double.doubleToLongBits(other._coordinate))
			return false;
		return true;
	}

	
	/***
	 * convert to string
	 */
	@Override
	public String toString() {
		return _coordinate + "";
	}

	
	/***
	 * add to exist coordinate
	 * @param other
	 */
	public void add(Coordinate other) {
		_coordinate += other._coordinate;
	}

	/***
	 * Subtract  from exist coordinate
	 * @param other
	 */
	public void subtract(Coordinate other) {
		_coordinate -= other._coordinate;
	}

}