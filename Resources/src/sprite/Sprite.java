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
public class Sprite implements Img{

    private BufferedImage bI;

    public Sprite(BufferedImage bI)
    {
        this.bI=bI;
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
