package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Camera {

	private Point3D _p0;
	private Vector _vUp;
	private Vector _vToward;
	private Vector _vRight;

	/***
	 * empty constructor
	 */
	public Camera() {
		super();
		this._p0 = new Point3D(0, 0, 0);
		this._vUp = new Vector(0, 1, 0);
		this._vToward = new Vector(0, 0, -1);
		_vRight = new Vector(_vToward.crossProduct(_vUp));
	}

	/***
	 * 
	 * @param p0 - point of center of
	 * @param vUp - vector up from the center
	 * @param vToward - vector toward from the center
	 */
	public Camera(Point3D p0, Vector vUp, Vector vToward) {
		super();
		this._p0 = new Point3D(p0);
		this._vUp = new Vector(vUp);
		this._vToward = new Vector(vToward);
		_vRight = new Vector(_vToward.crossProduct(_vUp));
	}

	
	/***
	 * p0 with default of (0,0,0)
	 * and calculate vRight with cross product
	 * @param vUp - vector up from the center
	 * @param vToward
	 */
	public Camera(Vector vUp, Vector vToward) {
		super();
		this._p0 = new Point3D(0, 0, 0);
		this._vUp = new Vector(vUp);
		this._vToward = new Vector(vToward);
		_vRight = new Vector(_vToward.crossProduct(_vUp));
	}

	
	/***
	 * copy constructor
	 * @param other
	 */
	public Camera(Camera other) {
		super();
		this._p0 = new Point3D(other._p0);
		this._vUp = new Vector(other._vUp);
		this._vRight = new Vector(other._vRight);
		this._vToward = new Vector(other._vToward);
	}

	/***
	 * get p0 - center point of camera
	 * @return
	 */
	public Point3D get_p0() {
		return new Point3D(_p0);
	}

	/***
	 * set p0- center point of camera
	 * @param p0
	 */
	public void set_p0(Point3D p0) {
		this._p0 = new Point3D(p0);
	}

	/***
	 * @return vUp - vector up from the center
	 */
	public Vector get_vUp() {
		return new Vector(_vUp);
	}

	/***
	 * set vUp - vector up from the center
	 * @param vUp
	 */
	public void set_vUp(Vector vUp) {
		this._vUp = new Vector(vUp);
	}

	/***
	 * @return vRight - vector right from the center 
	 */
	public Vector get_vRight() {
		return new Vector(_vRight);
	}

	
	/***
	 * set vRight - vector right from the center 
	 * @return - nothing
	 */
	public void set_vRight(Vector vRight) {
		this._vRight = new Vector(vRight);
	}

	
	/***
	 * @return vToward - vector toward from the center 
	 * 
	 */
	
	public Vector get_vToward() {
		return new Vector(_vToward);
	}

	
	/***
	 * set vToward - vector toward from the center 
	 * @return- nothing
	 */
	public void set_vToward(Vector vToward) {
		this._vToward = new Vector(vToward);
	}

	/***
	 * for printing - change to string
	 */
	@Override
	public String toString() {
		return "Camera [p0=" + _p0 + ", vUp=" + _vUp + ", vRight=" + _vRight + ", vToward=" + _vToward + "]";
	}

	
	/***
	 * 
	 * @param Nx - The width of each pixel 
	 * @param Ny - The height of each pixel 
	 * @param x - number of pixels in a row 
	 * @param y - number of pixels in a column
	 * @param screenDist - Distance from the main screen
	 * @param screenWidth - Screen width
	 * @param screenHeight - Screen height
	 * @param rayNum - the number of rays we sent from pixel(there are 5 rays from each pixel)
	 * @return Ray
	 */
	public Ray constructRayThroughPixel(int Nx, int Ny, double x, double y, double screenDist, double screenWidth,
			double screenHeight, int rayNum) {
		// movX,movY change the location on the pixel right or up
		int movX = 1;
		int movY = 1;
		//the up left side of the pixel
		if (rayNum == 0) {
			movX = 0;
			movY = 0;
		}
		//the up right side of the pixel
		if (rayNum == 1) {
			movX = 2;
			movY = 0;
		}
		//the center of the pixel
		if (rayNum == 2) {
			movX = 1;
			movY = 1;
		}
		//the down left side of the pixel
		if (rayNum == 3) {
			movX = 0;
			movY = 2;
		}
		//the down right of the pixel
		else if (rayNum == 4) {
			movX = 2;
			movY = 2;
		}

		Point3D pc = new Point3D(_p0);// defining PC frame center
		Vector dVTo = new Vector(_vToward);
		dVTo.scale(screenDist);
		pc.add(dVTo);

		double rx = screenWidth / Nx; // Rx
		double ry = screenHeight / Ny; // Ry

		Vector newVRight = new Vector(_vRight);
		Vector newVUp = new Vector(_vUp);
		newVRight.normalize();
		newVUp.normalize();

		newVRight.scale((x - ((double) Nx) / 2) * rx + ((rx / 2) * movX));
		newVUp.scale((y - (double) Ny / 2) * ry + ((ry / 2) * movY));

		Point3D p = new Point3D(pc);

		p.subtract(newVUp);
		p.add(newVRight);

		Vector v = new Vector(p);
		v.subtract(new Vector(_p0));
		v.normalize(); // Normalize the direction vector of the ray

		return new Ray(p, v);
	}
}
