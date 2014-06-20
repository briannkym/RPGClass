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
public class ColorImg implements Img
{

    private BufferedImage bI;

    public ColorImg(int iColor, int width, int height)
    {
        bI = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bI.setRGB(x, y, iColor);
            }
        }
    }

    public BufferedImage getSlide()
    {
        return bI;
    }

    public void setSlide(int i)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setListener(ImgListener iL)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
