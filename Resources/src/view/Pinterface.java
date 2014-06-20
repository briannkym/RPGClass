package view;

import java.awt.image.BufferedImage;

/**
 * For uses with the iProjector, which calls this method before Projecting. In a
 * class implementing this, the iUpdate should contain any calculations required
 * for updating the Image/Slide, of iProjector.
 *
 * @author Brian Nakayama
 *
 */
public interface Pinterface
{
    /**
     * Override this in an object that you want to receive updates from the projector.
     * @param ISlide
     */
	public void iUpdate(BufferedImage ISlide);
}
