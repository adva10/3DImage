package primitives;

public class Material {
	private double _kd; // Diffusion attenuation coefficient
	private double _ks; // Specular attenuation coefficient
	private double _kr; // Reflection
	private double _kt; // Refraction
	private double _nShininess; // Refraction index

	// ***************** Constructors ********************** //
	/***
	 * default constructor
	 */
	public Material() {
		super();
		this._kd = 1;
		this._ks = 1;
		this._nShininess = 19;
		this._kr = 0;
		this._kt = 0;
	}

	/***
	 * copy constructor
	 * 
	 * @param other
	 */
	public Material(Material other) {
		super();

		this._kd = other._kd;
		this._ks = other._ks;
		this._kr = other._kr;
		this._kt = other._kt;
		this._nShininess = other._nShininess;
	}

	/***
	 * constructor with parameters
	 * 
	 * @param kd
	 * @param ks
	 * @param kr
	 * @param kt
	 * @param nShininess
	 */
	public Material(double kd, double ks, double kr, double kt, double nShininess) {
		super();
		this._kd = kd;
		this._ks = ks;
		this._kd = kr;
		this._ks = kt;
		this._nShininess = nShininess;
	}

	// ***************** Getters/Setters ********************** //
	/***
	 * get _kd
	 * 
	 * @return _kd
	 */
	public double get_kd() {
		return _kd;
	}

	/***
	 * get _kr
	 * 
	 * @return _kr
	 */
	public double get_kr() {
		return _kr;
	}

	/***
	 * set new _kr
	 * 
	 * @param kr
	 */
	public void set_kr(double kr) {
		this._kr = kr;
	}

	/***
	 * returns kt
	 * 
	 * @return _kt
	 */
	public double get_kt() {
		return _kt;
	}

	/***
	 * set new kt
	 * 
	 * @param kt
	 */
	public void set_kt(double kt) {
		this._kt = kt;
	}

	/***
	 * set new kd
	 * 
	 * @param _kd
	 */
	public void set_kd(double _kd) {
		this._kd = _kd;
	}

	/***
	 * get ks
	 * 
	 * @return _ks
	 */
	public double get_ks() {
		return _ks;
	}

	/***
	 * set new ks
	 * 
	 * @param _ks
	 */
	public void set_ks(double _ks) {
		this._ks = _ks;
	}

	/***
	 * get _nShininess
	 * 
	 * @return _nShininess
	 */
	public double get_nShininess() {
		return _nShininess;
	}

	/***
	 * set new _nShininess
	 * 
	 * @param _nShininess
	 */
	public void set_nShininess(double _nShininess) {
		this._nShininess = _nShininess;
	}

	// Override functions
	/***
	 * compares between our material and another object return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Material other = (Material) obj;
		if (Double.doubleToLongBits(_kd) != Double.doubleToLongBits(other._kd))
			return false;
		if (Double.doubleToLongBits(_kr) != Double.doubleToLongBits(other._kr))
			return false;
		if (Double.doubleToLongBits(_ks) != Double.doubleToLongBits(other._ks))
			return false;
		if (Double.doubleToLongBits(_kt) != Double.doubleToLongBits(other._kt))
			return false;
		if (Double.doubleToLongBits(_nShininess) != Double.doubleToLongBits(other._nShininess))
			return false;
		return true;
	}

	/***
	 * returns a string with the information in our material
	 */
	@Override
	public String toString() {
		return "Material [_kd=" + _kd + ", _ks=" + _ks + ", _kr=" + _kr + ", _kt=" + _kt + ", _nShininess="
				+ _nShininess + "]";
	}

}