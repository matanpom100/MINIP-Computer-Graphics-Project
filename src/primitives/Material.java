package primitives;

public class Material {

    /**
     * The material's diffuse and specular coefficients
     */
    public Double3 kD = Double3.ZERO;
    public Double3 kS = Double3.ZERO;

    /**
     * The material's transparency and reflection coefficients
     */
    public Double3 kT = Double3.ZERO;
    /*
    * The material's refraction coefficient
     */
    public Double3 kR = Double3.ZERO;


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

    /**
     * Set the material's reflection coefficient as Double3
     * @param kT
     * @return this
     */
    public Material setkT(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * Set the material's transparency coefficient as Double3
     * @param kT
     * @return this
     */
    public Material setkT(Double3 kT) {
        this.kT = kT;
        return this;
    }


    /**
     * Set the material's reflection coefficient as double
     * @param kR
     * @return this
     */
    public Material setkR(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    /**
     * Set the material's reflection coefficient as Double3
     * @param kR
     * @return this
     */
    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;
    }



}
