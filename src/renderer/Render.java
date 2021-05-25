package renderer;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import elements.Light;
import geometries.FlatGeometry;
import geometries.Geometry;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

public class Render {

	private Scene _scene;
	private ImageWriter _imageWriter;

	private int RECURSION_LEVEL = 3;

	// ********* Constructors ************** //

	/***
	 * Default Constructor
	 */
	public Render() {
		// Default Constructor
		super();
		this._scene = new Scene();
	}

	/***
	 * copy Constructor
	 * 
	 * @param other
	 */
	public Render(Render other) {
		// copy Constructor
		super();
		this._scene = new Scene(other._scene);
		this._imageWriter = new ImageWriter(other._imageWriter);
	}

	/***
	 * Constructor with parameters
	 * 
	 * @param _scene
	 * @param _imageWriter
	 */
	public Render(Scene _scene, ImageWriter _imageWriter) {
		// Constructor with parameters
		super();
		this._scene = new Scene(_scene);
		this._imageWriter = _imageWriter;
	}

	/***
	 * return _scene (not new __scene but the real one)
	 * 
	 * @return _scene
	 */
	// ********* Getters/Setters ************** //
	public Scene get_scene() {
		return _scene;
	}

	/***
	 * set value to _scene
	 * 
	 * @param _scene
	 */
	public void set_scene(Scene _scene) {
		this._scene = new Scene(_scene);
	}

	public ImageWriter get_imageWriter() {
		return _imageWriter;
	}

	/***
	 * set value to _imageWriter
	 * 
	 * @param _imageWriter
	 */
	public void set_imageWriter(ImageWriter _imageWriter) {
		this._imageWriter = new ImageWriter(_imageWriter);
	}

	// ********* Functions ************** //
	/***
	 * this function calculates the color in point in the scene
	 * 
	 * @param geometry
	 * @param point
	 * @param inRay
	 *            the ray through point
	 * @param level
	 * @return color in point
	 */
	private Color calcColor(Geometry geometry, Point3D point, Ray inRay, int level) {

		if (level == RECURSION_LEVEL) {
			return new Color(0, 0, 0); // black
		}

		Color ambientLight = _scene.get_AmbientLight().getIntensity();
		Color emissionLight = geometry.get_color();

		int red = 0;
		int green = 0;
		int blue = 0;

		Color refectedLight = Color.black;
		Color refractedLight = Color.black;
		Color diffuseLight = Color.black;
		Color specularLight = Color.black;
		Color reflectedColor = Color.black;
		Color refractedColor = Color.black;

		for (Iterator<Light> iter = _scene.get_lights().iterator(); iter.hasNext();) {

			Light element = iter.next();
			if (!occluded(element, point, geometry)) {
				diffuseLight = calcDiffuseComp(geometry.get_material().get_kd(), geometry.getNormal(point),
						element.getL(point), element.getIntensity(point));

				specularLight = calcSpecularComp(geometry.get_material().get_ks(),
						new Vector(point, _scene.get_camera().get_p0()), geometry.getNormal(point), element.getL(point),
						geometry.get_material().get_nShininess(), element.getIntensity(point));

				red += diffuseLight.getRed() + specularLight.getRed();
				green += diffuseLight.getGreen() + specularLight.getGreen();
				blue += diffuseLight.getBlue() + specularLight.getBlue();

			}
		}

		// Recursive call for a reflected ray
		Ray reflectedRay = constructReflectedRay(geometry.getNormal(point), point, inRay);
		Map<Geometry, Point3D> reflectedEntry = findClosestIntersection(reflectedRay);

		if (reflectedEntry != null) {
			for (Entry<Geometry, Point3D> entry : reflectedEntry.entrySet()) {
				if (!(entry.getValue() instanceof FlatGeometry) && (entry.getValue() != null))
					// make sure that the reflectedColor does change outside of
					// the if
					reflectedColor = calcColor(entry.getKey(), entry.getValue(), reflectedRay, level + 1);
			}
		}

		double kr = geometry.get_material().get_kr();
		if (kr > 1)
			kr = 1;
		if (kr < 0)
			kr = 0;

		refectedLight = new Color((int) (kr * reflectedColor.getRed()), (int) (kr * reflectedColor.getGreen()),
				(int) (kr * reflectedColor.getBlue()));

		// Recursive call for a refracted ray
		Ray refractedRay = constructRefractedRay(geometry.getNormal(point), point, inRay);

		Map<Geometry, Point3D> refractedEntry = findClosestIntersection(refractedRay);
		if (refractedEntry != null) {
			for (Entry<Geometry, Point3D> entry : refractedEntry.entrySet()) {
				if (!(entry.getValue() instanceof FlatGeometry) && (entry.getValue() != null))
					refractedColor = calcColor(entry.getKey(), entry.getValue(), refractedRay, level + 1);
			}
		}

		double kt = geometry.get_material().get_kt();

		if (kt < 0)
			kt = 0;
		if (kt > 1)
			kt = 1;

		refractedLight = new Color((int) (refractedColor.getRed() * kt), (int) (kt * refractedColor.getGreen()),
				(int) (kt * refractedColor.getBlue()));

		red += refractedLight.getRed() + refectedLight.getRed() + ambientLight.getRed() + emissionLight.getRed();
		green += refractedLight.getGreen() + refectedLight.getGreen() + ambientLight.getGreen()
				+ emissionLight.getGreen();
		blue += refractedLight.getBlue() + refectedLight.getBlue() + ambientLight.getBlue() + emissionLight.getBlue();

		if (red > 255)
			red = 255;
		if (green > 255)
			green = 255;
		if (blue > 255)
			blue = 255;

		if (red < 0)
			red = 0;
		if (green < 0)
			green = 0;
		if (blue < 0)
			blue = 0;

		return new Color(red, green, blue);
	}

	/***
	 * printing the grid
	 * 
	 * @param interval
	 */
	public void printGrid(int interval) {
		for (int i = 0; i < _imageWriter.getNx(); i++) {
			for (int j = 0; j < _imageWriter.getNy(); j++) {

				if (i % 50 == 0 || j % 50 == 0) {
					 _imageWriter.writePixel(i, j, Color.WHITE);
				}
			}
		}
	}

	/***
	 * get Scene Ray Intersections
	 * 
	 * @param ray
	 * @return intersectionPoints
	 */
	private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray) {
		// finding the Intersections points through the geometries in our scene
		// (with ray)
		Vector d = ray.get_direction();
		d.normalize();
		ray.set_direction(d);

		Iterator<Geometry> geometries = _scene.getGomatriesIterator();

		Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry, List<Point3D>>();// returning
		// map of each geometry and it's intersection point
		while (geometries.hasNext()) {
			Geometry geometry = geometries.next();
			List<Point3D> geometryIntersectionPoints = geometry.findIntersections(ray);
			if (!geometryIntersectionPoints.isEmpty()) {
				intersectionPoints.put(geometry, geometryIntersectionPoints);
			}
		}
		return intersectionPoints;
	}

	/***
	 * get Closest Point to geomatry
	 * 
	 * @param intersectionPoints
	 * @return closest Distance Point
	 */
	private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> intersectionPoints) {
		// find the closest point to the geometry out of list of point3d
		double distance = Double.MAX_VALUE;
		Point3D P0 = _scene.get_camera().get_p0();
		Map<Geometry, Point3D> minDistancePoint = new HashMap<Geometry, Point3D>();

		for (Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet()) {
			for (Point3D point : entry.getValue()) {
				if (P0.distance(point) < distance) {
					minDistancePoint.clear();
					minDistancePoint.put(entry.getKey(), new Point3D(point));
					distance = P0.distance(point);
				}
			}
		}
		return minDistancePoint;

	}

	/***
	 * renderImage - building the photo - 5 rays through each pixel
	 */
	public void renderImage() {

		// building the image itself

		boolean[][] viewArray = new boolean[500][500];
		for (int i = 0; i < _imageWriter.getNx(); i++) {
			for (int j = 0; j < _imageWriter.getNy(); j++) {
				Ray ray = _scene.get_camera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(), i, j,
						_scene.get_distance(), _imageWriter.getWidth(), _imageWriter.getHeight(), 2);

				Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);
				// if there is an intersection Point improving running time
				if (intersectionPoints.isEmpty()) {
					viewArray[i][j] = false;
				} else {
					viewArray[i][j] = true;
				}
			}
		}

		int g, r, b;
		// again for each pixel -
		for (int i = 0; i < _imageWriter.getHeight(); i++) {

			for (int j = 0; j < _imageWriter.getWidth(); j++) {

				// every time we reset our green, blue and red (for each pixel
				// in viewArray)

				g = 0;
				b = 0;
				r = 0;

				Color color = _scene.get_colorBackGround(); // default will be
				// the backGround
				// color

				if (viewArray[i][j] == true) {
					for (int w = 0; w < 5; w++) { // for each ray

						Ray ray = this._scene.get_camera().constructRayThroughPixel(_imageWriter.getNx(),
								_imageWriter.getNy(), i, j, _scene.get_distance(), _imageWriter.getWidth(),
								_imageWriter.getHeight(), w);

						Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);

						if (intersectionPoints.isEmpty()) {

							color = this._scene.get_colorBackGround();
							r += color.getRed();
							g += color.getGreen();
							b += color.getBlue();

						} else {

							Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints); // if
																										// not
																										// flat
																										// Geometry

							for (Entry<Geometry, Point3D> entry : closestPoint.entrySet()) {

								color = calcColor(entry.getKey(), entry.getValue(), ray, 0);
								r += color.getRed();
								g += color.getGreen();
								b += color.getBlue();
							}
						}
					}
				}

				// Divide by 5 because there are 5 rays through each pixel and
				// we want the average
				r = (int) (r / 5);
				g = (int) (g / 5);
				b = (int) (b / 5);

				// making sure that the colors won't pass the borders of Color
				if (r > 255) {
					r = 255;
				}
				if (g > 255) {
					g = 255;
				}
				if (b > 255) {
					b = 255;
				}

				this._imageWriter.writePixel(i, j, new Color(r, g, b));

			}
		}
	}

	/***
	 * calculating Diffuse light
	 * 
	 * @param kd
	 * @param normal
	 * @param vecL
	 * @param intensity
	 * @return
	 */
	private Color calcDiffuseComp(double kd, Vector normal, Vector vecL, Color intensity) {
		// calculating the diffuse light
		// KD(N · Li)
		normal.normalize();
		vecL.normalize();

		double n = normal.dotProduct(vecL);

		n *= kd;

		if (n < 0) {
			n *= -1; // if the dotProduct is negative we want absolute value
		}

		if (n > 1) {
			n = 1;
		}

		int red = (int) (intensity.getRed() * n);
		int green = (int) (intensity.getGreen() * n);
		int blue = (int) (intensity.getBlue() * n);

		return new Color(red, green, blue);

	}

	/***
	 * calculating Specular light
	 * 
	 * @param ks
	 * @param v
	 * @param normal
	 * @param vecL
	 * @param shininess
	 * @param intensity
	 * @return color in pixel
	 */
	private Color calcSpecularComp(double ks, Vector v, Vector normal, Vector vecL, double shininess, Color intensity) {

		// KS(V·R)^n

		v.normalize();
		normal.normalize();
		vecL.normalize();
		Vector R = new Vector(calcR(vecL, normal));
 
		R.normalize();
		double VR = v.dotProduct(R);

		if (VR < 0) {// if the dotProduct is negative
			VR = 0;
		}
		if (VR > 1) {
			VR = 1;
		}

		double withShine = Math.pow(VR, shininess) * ks;

		if (withShine < 0) {
			withShine = 0;
		}
		if (withShine > 1) {
			withShine = 1;
		}
		int red = (int) (intensity.getRed() * withShine);
		int green = (int) (intensity.getGreen() * withShine);
		int blue = (int) (intensity.getBlue() * withShine);

		if (red > 255) {
			red = 255;
		}
		if (green > 255) {
			green = 255;
		}
		if (blue > 255) {
			blue = 255;
		}

		return new Color(red, green, blue);
	}

	/***
	 * helper function to calculate R for calColor
	 * 
	 * @param vecL
	 * @param normal
	 * @return R
	 */
	private Vector calcR(Vector vecL, Vector normal) {
		// helper func to simplify the calculation if R
		// D – 2(D· N) N

		Vector L = new Vector(vecL);
		Vector N = new Vector(normal);

		L.normalize();
		N.normalize();

		double dn2 = L.dotProduct(N) * 2;

		N.scale(dn2);
		L.subtract(N);

		return L;
	}

	/***
	 * checks if the geometry gets covered by another geometry
	 * 
	 * @param element
	 * @param point
	 * @param geometry
	 * @return if the object is shadowed or not
	 */
	boolean occluded(Light element, Point3D point, Geometry geometry) {

		Vector lightDirection = element.getL(point);
		lightDirection.scale(-1);

		Point3D geometryPoint = new Point3D(point);
		Vector epsVector = new Vector(geometry.getNormal(point));
		epsVector.normalize();
		epsVector.scale(2);

		geometryPoint.add(epsVector.get_head());
		Ray lightRay = new Ray(geometryPoint, lightDirection);
		Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(lightRay);
		// Flat geometry cannot self intersect so no need to check it
		if (geometry instanceof FlatGeometry) {
			intersectionPoints.remove(geometry);
		}

		for (Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet()) {
			if (entry.getKey().get_material().get_kt() == 0)
				return true;
		}

		return false;
	}

	/***
	 * construct Reflected Ray from point "point"
	 * 
	 * @param n
	 * @param point
	 * @param inRay
	 * @return the Reflected Ray
	 */
	private Ray constructReflectedRay(Vector n, Point3D point, Ray inRay) {
		Vector help = new Vector(inRay.get_direction());
		n.scale(2 * (inRay.get_direction().dotProduct(n)));
		help.subtract(n);
		Point3D p = new Point3D(point);
		p.add(help);
		return new Ray(p, help);
	}

	/***
	 * construct Refracted Ray through point
	 * 
	 * @param n
	 * @param point
	 * @param inRay
	 * @return refracted Ray
	 */
	private Ray constructRefractedRay(Vector n, Point3D point, Ray inRay) {
		point.add(inRay.get_direction());
		Ray refractedRay = new Ray(point, new Vector(inRay.get_direction()));
		return refractedRay;
	}

	/***
	 * should find Closest Intersection point with the ray
	 * 
	 * @param ray
	 * @return Map<Geometry,Point3D> Closest Intersection
	 */
	private Map<Geometry, Point3D> findClosestIntersection(Ray ray) {
		Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);
		return getClosestPoint(intersectionPoints);
	}

}