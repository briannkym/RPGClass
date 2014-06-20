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
public class Animation implements Img{

    private BufferedImage[] bI;
    private int i=0;
    private ImgListener iL = NullListener.getInstance();

    public Animation(BufferedImage[] bI)
    {
        this.bI = bI;
    }

    public BufferedImage getSlide()
    {
        BufferedImage rB=bI[i];

        if(i<bI.length-1)
        {
            i++;
        }
        else
        {
            iL.slideEnd();
            i=0;
        }
        
        return rB;
    }

    public void setSlide(int i)
    {
        this.i = i;
    }

    public void setListener(ImgListener iL)
    {
        this.iL=iL;
    }

}
