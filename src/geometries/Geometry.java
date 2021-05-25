package geometries;

import java.awt.Color;
import java.util.List;

import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public abstract class Geometry {

	protected Color _color;
	protected Material _material;

	/***
	 * default constructor
	 */
	public Geometry() {
		_color = new Color(0, 0, 0);
		_material = new Material();
	}

	/***
	 * constructor with parameters
	 * @param color - color of geometry
	 * @param material
	 */
	public Geometry(Color color, Material material) {
		super();
		this._color = color;
		_material = new Material(material);

	}

	/***
	 * @return the metrial of geometry
	 */
	public Material get_material() {
		return new Material(_material);
	}

	/***
	 * set new material
	 * @param _material
	 */
	public void set_material(Material _material) {
		this._material = new Material(_material);
	}

	/***
	 * @return color of material
	 */
	public Color get_color() {
		return _color;
	}

	/***
	 * set new color
	 * @param _color
	 */
	public void set_color(Color _color) {
		this._color = _color;
	}

	/***
	 * check if its equal	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Geometry other = (Geometry) obj;
		if (_color == null) {
			if (other._color != null)
				return false;
		} else if (!_color.equals(other._color))
			return false;
		if (_material == null) {
			if (other._material != null)
				return false;
		} else if (!_material.equals(other._material))
			return false;
		return true;
	}

	/***
	 * convert to string
	 */
	@Override
	public String toString() {
		return "Geometry [_color=" + _color + ", _material=" + _material + "]";
	}

	public abstract Vector getNormal(Point3D point);

	public abstract List<Point3D> findIntersections(Ray r);

	// public abstract List<Point3D> findInterSection(); // returns max one
	// point
}
