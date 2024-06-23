package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * AmbientLight class represents the ambient light in the scene
 * The ambient light is the light that is scattered in the scene
 * @Author Matan and Eitan
 */
public class AmbientLight extends Light {

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
        super(Ia.scale(Ka));
    }

    /**
     * Constructor for the ambient light based on the intensity and the attenuation factor
     * @param Ia the intensity of the ambient light
     * @param Ka the attenuation factor of the ambient light
     */
    public AmbientLight(Color Ia, double Ka) {
        super(Ia.scale(Ka));
    }







}
