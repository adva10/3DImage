package elements;

import java.awt.Color;

import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light {

	private Vector _direction;

	// ***************** Constructors ********************** //
	/***
	 * default constructor
	 */
	public DirectionalLight() {
		super();
		this._direction = new Vector(0, 0, 1);
	}

	/***
	 * copy constructor
	 * @param other
	 */
	public DirectionalLight(DirectionalLight other) {
		super();
		this._direction = new Vector(other._direction);
	}

	
	/***
	 * constructor with new direction
	 * @param _direction
	 */
	public DirectionalLight(Vector _direction) {
		super();
		this._direction = new Vector(_direction);
	}

	// ***************** Getters/Setters ********************** //

	/***
	 * @return the direction of light
	 */
	public Vector get_direction() {
		return new Vector(_direction);
	}

	/***
	 * set new vector
	 * @param direction
	 */
	public void set_direction(Vector direction) {
		this._direction = new Vector(direction);
	}

	// override functions

	/***
	 * check if 2 directional light is equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DirectionalLight other = (DirectionalLight) obj;
		if (_direction == null) {
			if (other._direction != null)
				return false;
		} else if (!_direction.equals(other._direction))
			return false;
		return true;
	}

	/***
	 * convert to string
	 */
	@Override
	public String toString() {
		return "DirectionalLight [_direction=" + _direction + "]";
	}

	/***
	 * return the light intensity at p 
	 */
	public Color getIntensity(Point3D p) 
	{
		return _color;
	}

	/***
	 * return the light's direction
	 */
	public Vector getL(Point3D point)
	{
		return new Vector(_direction);
	}

}