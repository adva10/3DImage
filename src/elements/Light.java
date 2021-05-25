package elements;

import java.awt.Color;

import primitives.Point3D;
import primitives.Vector;

public abstract class Light {

	protected Color _color;

	/***
	 * default constructor
	 */
	public Light() {
		super();
		this._color = Color.WHITE;
	}

	/***
	 * copy constructor copies other
	 * 
	 * @param other
	 *            another light
	 */
	public Light(Light other) {
		super();
		this._color = other._color;
	}

	/***
	 * constructor with parameter
	 * 
	 * @param color
	 *            of the light
	 */
	public Light(Color color) {
		super();
		this._color = color;
	}

	// ***************** Getters/Setters ********************** //
	/***
	 * get color of light
	 * 
	 * @return
	 */
	public Color get_color() {
		return _color;
	}

	/***
	 * set color of light
	 * 
	 * @param _color
	 */
	public void set_color(Color _color) {
		this._color = _color;
	}

	// override functions
	/***
	 * returns a string with the information in our light
	 */
	@Override
	public String toString() {
		return "Light [_color=" + _color + "]";
	}

	/***
	 * compares between our light and another object return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Light other = (Light) obj;
		if (_color == null) {
			if (other._color != null)
				return false;
		} else if (!_color.equals(other._color))
			return false;
		return true;
	}

	/***
	 * Abstract function get intensity of light in point
	 * 
	 * @param point3d
	 *            point
	 * @return Color in point
	 */
	public abstract Color getIntensity(Point3D point);

	/***
	 * Abstract function get l
	 * 
	 * @param point
	 * @return Vector l
	 */
	public abstract Vector getL(Point3D point);
}
