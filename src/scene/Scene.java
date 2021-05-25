package scene;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import elements.AmbientLight;
import elements.Camera;
import elements.Light;
import geometries.Geometry;

public class Scene {
	private String _name;
	private Color _colorBackGround;
	private List<Geometry> _geomatryScene;
	private double _distance;
	private AmbientLight _AmbientLight;
	private Camera _camera;
	private List<Light> _lights;

	/***
	 * empty constructor
	 */
	public Scene() {
		super();
		this._name = new String("empty Constractor scene");
		this._colorBackGround = Color.black;
		this._geomatryScene = new ArrayList<Geometry>();
		this._distance = 150;
		this._AmbientLight = new AmbientLight();
		this._camera = new Camera();
		this._lights = new ArrayList<Light>();
	}

	/***
	 * constructor using fields
	 * 
	 * @param _name
	 * @param _colorBackGround
	 * @param _geomatryScene
	 * @param _distance
	 * @param _ambientLight
	 * @param _camera
	 * @param _lights
	 */
	public Scene(String _name, Color _colorBackGround, List<Geometry> _geomatryScene, double _distance,
			AmbientLight _ambientLight, Camera _camera, List<Light> _lights) {
		super();
		this._name = new String(_name);
		this._colorBackGround = _colorBackGround;
		this._distance = _distance;
		this._AmbientLight = new AmbientLight(_ambientLight);
		this._geomatryScene = new ArrayList<Geometry>(_geomatryScene);
		this._camera = new Camera(_camera);
		this._lights = new ArrayList<Light>(_lights);

	}

	/***
	 * copy constructor
	 * 
	 * @param other
	 */
	public Scene(Scene other) {
		super();
		this._name = new String(other._name);
		this._colorBackGround = other._colorBackGround;
		this._geomatryScene = new ArrayList<Geometry>(other._geomatryScene);
		this._distance = other._distance;
		this._AmbientLight = new AmbientLight();
		this._camera = new Camera(other._camera);
		this._lights = new ArrayList<Light>(other._lights);
	}

	/***
	 * convert to string - for printing
	 */
	@Override
	public String toString() {
		return "Scene [_name=" + _name + ", _colorBackGround=" + _colorBackGround + ", _geomatryScene=" + _geomatryScene
				+ ", _distance=" + _distance + ", _AmbientLight=" + _AmbientLight + ", _camera=" + _camera
				+ ", _lights=" + _lights + "]";
	}

	/***
	 * for checking if 2 scenes are the same
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Scene other = (Scene) obj;
		if (_AmbientLight == null) {
			if (other._AmbientLight != null)
				return false;
		} else if (!_AmbientLight.equals(other._AmbientLight))
			return false;
		if (_camera == null) {
			if (other._camera != null)
				return false;
		} else if (!_camera.equals(other._camera))
			return false;
		if (_colorBackGround == null) {
			if (other._colorBackGround != null)
				return false;
		} else if (!_colorBackGround.equals(other._colorBackGround))
			return false;
		if (Double.doubleToLongBits(_distance) != Double.doubleToLongBits(other._distance))
			return false;
		if (_geomatryScene == null) {
			if (other._geomatryScene != null)
				return false;
		} else if (!_geomatryScene.equals(other._geomatryScene))
			return false;
		if (_lights == null) {
			if (other._lights != null)
				return false;
		} else if (!_lights.equals(other._lights))
			return false;
		if (_name == null) {
			if (other._name != null)
				return false;
		} else if (!_name.equals(other._name))
			return false;
		return true;
	}

	/***
	 * @return AmbientLight
	 */
	public AmbientLight get_AmbientLight() {
		return new AmbientLight(_AmbientLight);
	}

	/***
	 * set AmbientLight
	 * 
	 * @param ambientLight
	 */
	public void set_AmbientLight(AmbientLight ambientLight) {
		this._AmbientLight = new AmbientLight(ambientLight);
	}

	/***
	 * @return name of scene
	 */
	public String get_name() {
		return new String(_name);
	}

	/***
	 * set name
	 */
	public void set_name(String name) {
		this._name = new String(name);
	}

	/***
	 * @return colorBackGround
	 */
	public Color get_colorBackGround() {
		return _colorBackGround;
	}

	/*** 
	 * set colorBackGround
	 */
	public void set_colorBackGround(Color colorBackGround) {
		this._colorBackGround = colorBackGround;
	}

	/***
	 * @return
	 */
	public List<Geometry> get_geomatryScene() {
		return new ArrayList<Geometry>(_geomatryScene);
	}

	public void set_geomatryScene(List<Geometry> geomatryScene) {
		this._geomatryScene = new ArrayList<Geometry>(geomatryScene);
	}

	public double get_distance() {
		return _distance;
	}

	public void set_distance(double _distance) {
		this._distance = _distance;
	}

	public Camera get_camera() {
		return new Camera(_camera);
	}

	public void set_camera(Camera _camera) {
		this._camera = new Camera(_camera);
	}

	public List<Light> get_lights() {
		return _lights;
	}

	public void set_lights(List<Light> _lights) {
		this._lights = new ArrayList<Light>(_lights);
	}

	public void addGeometry(Geometry g) {
		_geomatryScene.add(g);
	}

	public Iterator<Geometry> getGomatriesIterator() {
		return _geomatryScene.iterator();
	}

	public void addLight(Light l) {
		_lights.add(l);
	}

	public Iterator<Light> getLightsIterator() {
		return _lights.iterator();

	}

}
