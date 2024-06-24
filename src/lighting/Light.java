package lighting;

import primitives.Color;

/**
    * Light class represents a light in the scene.
    * @Author Eitan and Matan
 */
abstract class Light {

    /**
        * The intensity of the light.
     **/
    protected Color intensity;

    /**
        * Get the intensity of the light.
        * @return The intensity of the light.
     */
    public Color getIntensity() {
        return intensity;
    }

    /**
        * Constructor for the Light class.
        * @param intensity The intensity of the light.
     */
    public Light(Color intensity) {
        this.intensity = intensity;
    }
}
