package elements;

import java.awt.Color;

import primitives.Point3D;

public class AmbientLight {
	private Color _color;
	private double _Ka;

	/***
	 * default constructor
	 */
	public AmbientLight() {
		super();
		this._color = new Color(255, 255, 255);
		this._Ka = 0.1;
	}

	/***
	 * constructor with parameters
	 * 
	 * @param color
	 *            - color of the ambient light
	 * @param Ka
	 *            - coefficient of light
	 */
	public AmbientLight(Color color, double Ka) {
		super();
		this._color = color;
		this._Ka = Ka;
	}

	/***
	 * constructor with parameters of color
	 * 
	 * @param color1
	 *            red
	 * @param color2
	 *            green
	 * @param color3
	 *            blue
	 */
	public AmbientLight(int color1, int color2, int color3) {
		super();
		this._color = new Color(color1, color2, color3);
		this._Ka = 0.1;
	}

	/***
	 * copy constructor
	 * 
	 * @param other
	 */
	public AmbientLight(AmbientLight other) {
		super();
		this._color = other._color;
		this._Ka = other._Ka;
	}

	/***
	 * get _color of ambient light
	 * 
	 * @return
	 */
	public Color get_color() {
		return _color;
	}

	/***
	 * set _color of ambient light
	 * 
	 * @param _color
	 */
	public void set_color(Color _color) {
		this._color = _color;
	}

	/***
	 * get coefficient of light
	 * 
	 * @return ka
	 */
	public double get_Ka() {
		return _Ka;
	}

	/***
	 * set coefficient of light
	 * 
	 * @param _Ka
	 */
	public void set_Ka(double _Ka) {
		this._Ka = _Ka;
	}

	/***
	 * returns a string with the information in our Ambient light
	 */
	@Override
	public String toString() {
		return "AmbientLight [color=" + _color + ", Ka=" + _Ka + "]";
	}

	/***
	 * compares between our Ambient light and another object return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AmbientLight other = (AmbientLight) obj;
		if (Double.doubleToLongBits(_Ka) != Double.doubleToLongBits(other._Ka))
			return false;
		if (_color == null) {
			if (other._color != null)
				return false;
		} else if (!_color.equals(other._color))
			return false;
		return true;
	}

	/***
	 * get Intensity of ambient light
	 * 
	 * @return
	 */
	public Color getIntensity() {
		Color color;

		double red = _color.getRed() * _Ka;
		if (red > 255) {
			red = 255;
		}

		double green = _color.getGreen() * _Ka;
		if (green > 255) {
			green = 255;
		}

		double blue = _color.getBlue() * _Ka;
		if (blue > 255) {
			blue = 255;
		}

		color = new Color((int) red, (int) green, (int) blue);

		return color;
	}
}
