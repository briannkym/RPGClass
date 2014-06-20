/*
 *  Use with Brian's permission, else he exercises freedoms to sue you
 *  $1,000,000.00
 */

package sprite;

import java.awt.image.BufferedImage;

/**
 *
 * @author Brian
 */
public interface Img
{
    public BufferedImage getSlide();
    public void setSlide(int i);
    public void setListener(ImgListener iL);
}
