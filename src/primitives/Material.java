package primitives;

public class Material {

    /**
     * The material's diffuse coefficient
     */
    public Double3 kD = Double3.ZERO;
    public Double3 kS = Double3.ZERO;

    /**
     * The material's shininess coefficient
     */
    public int nShininess = 0;

    /**
     * Set the material's diffuse coefficient as Double3
     * @param kD
     * @return this
     */
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Set the material's diffuse coefficient as double
     * @param kD
     * @return this
     */
    public Material setkD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * Set the material's specular coefficient as Double3
     * @param kS
     * @return this
     */
    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Set the material's specular coefficient as double
     * @param kS
     * @return this
     */
    public Material setkS(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Set the material's shininess coefficient
     * @param nShininess
     * @return this
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
