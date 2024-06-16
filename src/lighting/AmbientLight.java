package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * AmbientLight class represents the ambient light in the scene
 * The ambient light is the light that is scattered in the scene
 * @Author Matan and Eitan
 */
public class AmbientLight {

    /**
     * The intensity of the ambient light
     */
    final private Color intensity;

    /**
     * The default ambient light - set to black with intensity 0
     */
    public static AmbientLight NONE = new AmbientLight(Color.BLACK, 0);

    /**
     * Constructor for the ambient light based on the intensity and the attenuation factor
     * @param Ia the intensity of the ambient light
     * @param Ka the attenuation factor of the ambient light
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        intensity = Ia.scale(Ka);
    }

    /**
     * Constructor for the ambient light based on the intensity and the attenuation factor
     * @param Ia the intensity of the ambient light
     * @param Ka the attenuation factor of the ambient light
     */
    public AmbientLight(Color Ia, double Ka) {
        intensity = Ia.scale(Ka);
    }

    /**
     * AmbientLight getter - returns the intensity of the ambient light
     * @return the intensity of the ambient light
     */
    public Color getIntensity() {
        return intensity;
    }






}
