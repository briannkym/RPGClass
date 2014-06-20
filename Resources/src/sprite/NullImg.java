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
public class NullImg implements Img{

    private static NullImg n = new NullImg();
    
    private NullImg()
    {
    }
    
    public static NullImg getInstance()
    {
        return n;
    }
    
    @Override
    public BufferedImage getSlide()
    {
        return null;
    }

    @Override
    public void setSlide(int i)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setListener(ImgListener iL)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
