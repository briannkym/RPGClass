/*
 *  Use with Brian's permission, else he exercises freedoms to sue you
 *  $1,000,000.00
 */

package sprite;

/**
 *
 * @author Brian
 */
public class NullListener implements ImgListener{

    private static NullListener nL= new NullListener();
    
    private NullListener()
    {}
    
    public static NullListener getInstance()
    {
        return nL;
    }
    
    public void slideEnd()
    {}

}
