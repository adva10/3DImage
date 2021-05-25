package elements;

import java.awt.Color;

import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light {

	protected Point3D _position;
	protected double _Kc, _Kl, _Kq;

	// ************* Constructors ****************** //

	/***
	 * default constractor
	 */
	public PointLight() {
		super();
		this._position = new Point3D();
		this._Kc = 0.1;
		this._Kl = 0.1;
		this._Kq = 0.1;
	}

	/***
	 * constractor with parametrers
	 * 
	 * @param c
	 *            - color
	 * @param position
	 *            - location of the light in secne Attenuation factors:
	 * @param Kc
	 * @param Kl
	 * @param Kq
	 */
	public PointLight(Color c, Point3D position, double Kc, double Kl, double Kq) {
		super(c);
		this._position = new Point3D(position);
		this._Kc = Kc;
		this._Kl = Kl;
		this._Kq = Kq;
	}

	// ************* Getters/Setters ****************** //
	/***
	 * @return the position of light
	 */
	public Point3D get_position() {
		return new Point3D(_position);
	}

	/***
	 * set new point of position
	 * @param _position
	 */
	public void set_position(Point3D _position) {
		this._position = new Point3D(_position);
	}

	//getters
	public double get_Kc() {
		return _Kc;
	}

	public void set_Kc(double _Kc) {
		this._Kc = _Kc;
	}

	public double get_Kl() {
		return _Kl;
	}

	public void set_Kl(double _Kl) {
		this._Kl = _Kl;
	}

	public double get_Kq() {
		return _Kq;
	}
	
	//setters

	public void set_Kq(double _Kq) {
		this._Kq = _Kq;
	}

	@Override
	public String toString() {
		return "PointLight [_position=" + _position + ", _Kc=" + _Kc + ", _Kl=" + _Kl + ", _Kq=" + _Kq + "]";
	}

	/***
	 * get Intensity of light in point3d point
	 */
	@Override
	public Color getIntensity(Point3D point) {

		double dis = point.distance(_position);

		double k = _Kc + _Kl * dis + _Kq * Math.pow(dis, 2);

		if (k < 1) { // if 1/k is bigger than 1
			k = 1;
		}

		double red = _color.getRed() / k;
		double green = _color.getGreen() / k;
		double blue = _color.getBlue() / k;

		if (red > 255)
			red = 255;
		if (green > 255)
			green = 255;
		if (blue > 255)
			blue = 255;

		return new Color((int) red, (int) green, (int) blue);
	}

	/***
	 * return vector l between position and point
	 */
	@Override
	public Vector getL(Point3D point) {
		Vector l = new Vector(_position, point);
		l.normalize();
		return l;
	}

}
