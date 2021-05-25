package elements;

import java.awt.Color;

import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight {

  private Vector _direction;

  // ***************** Constructors ********************** //

  /***
   * default constructor
   */
  public SpotLight() {
    super();
    this._direction = new Vector(0, 0, 1);
  }

  /***
   * copy constructor copies other
   * 
   * @param other
   *            another Spot light
   */
  public SpotLight(SpotLight other) {
    super();
    this._direction = new Vector(other._direction);
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
   * @param _direction
   *            - direction of spot light
   */
  public SpotLight(Color c, Point3D _position, double _Kc, double _Kl, double _Kq, Vector _direction) {
    super(c, _position, _Kc, _Kl, _Kq);
    this._direction = new Vector(_direction);
  }

  // ***************** Getters/Setters ********************** //
  /***
   * get direction of spot light
   * 
   * @return
   */
  public Vector get_direction() {
    return new Vector(_direction);
  }

  /***
   * set direction
   * 
   * @param direction
   */
  public void set_direction(Vector direction) {
    this._direction = new Vector(direction);
  }

  // override functions
  /***
   * compares between our spot light and another object return boolean
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    SpotLight other = (SpotLight) obj;
    if (_direction == null) {
      if (other._direction != null)
        return false;
    } else if (!_direction.equals(other._direction))
      return false;
    return true;
  }

  /***
   * returns a string with the information in our spot light
   */
  @Override
  public String toString() {
    return "SpotLight [_direction=" + _direction + "]";
  }

  // other function

  /***
   * get intensity of light in point
   * 
   * @param point3d
   *            point
   * @return Color in point
   */
  @Override
  public Color getIntensity(Point3D p) // return the light intensity at p //
                      // point
  {
    Vector l = getL(p);
    double dis = l.length();

    double k = (_Kc + _Kl * dis + _Kq * Math.pow(dis, 2));

    l.normalize();
    _direction.normalize();

    double d = _direction.dotProduct(l);

    if (d < 0) {
      d = d * (-1);
    }
    if (1 / k > 1) {
      k = 1;
    }

    double red = super.getIntensity(p).getRed() * d / k;
    double green = super.getIntensity(p).getGreen() * d / k;
    double blue = super.getIntensity(p).getBlue() * d / k;

    if (red > 255)
      red = 255;
    if (green > 255)
      green = 255;
    if (blue > 255)
      blue = 255;

    return new Color((int) red, (int) green, (int) blue);

  }

}