package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class sets up the nitty gritty details for a refreshing frame in use
 * for a game or other simulation. Here's a quick example for how to use iProjector:
 *
 * public class yourClass implements iInterface
 * {
 * private iProjector ip;
 * private BufferedImage bi;
 * private jPanel jp;
 *
 * public yourClass()
 * {
 * bi = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
 * ip = new GProjector(10f, bi, "Test", this);
 * jp = ip.init(new Dimension(400, 400));
 * //or just ip.init() for full screen.
 * }
 *
 * public void iUpdate()
 * {
 * //Code that updates bi, goes here.
 * }
 * }
 */
/**
 * The GProjector class simply takes an image, and projects it in either full
 * screen mode, or in a JPanel for a given FPS value.
 *
 * @author Brian Nakayama
 * @version 1.6 Bug fix to the way thread was created.
 * @since 1.5 Several small convenience fixes have been made.
 */
public class Projector implements Runnable
{
    // Informs the Thread loop whether to continue running or not.

    private volatile boolean bRun = true;
    // Informs the user if the Projector is running.
    private volatile boolean bIsRunning = false;
    // A Container for the JFrame used in FSM.
    private Container c;
    // The Dimensions of the Projection.
    private Dimension d;
    // The desired Frames per Second.
    private volatile float fFps;
    // The GFeed object that updates the Image/Slide.
    private Pinterface iI;
    // The Image/Slide, to be Projected.
    private volatile Image ISlide;
    // The JFrame for FSM mode.
    private JFrame jf;
    // The JPanel that holds the Image Projection.
    private JPanel jp;
    // The Thread that loops the Projection.
    private volatile Thread t;
    // A title for FSM mode.
    private String sTitle;
    // The toolkit gets the dimensions of the screen for FSM mode.
    private Toolkit tk;

    /**
     * Creates the GProjector Object
     *
     * @param fFps
     *           A float representing the desired Frames per second.
     * @param ISlide
     *           The Image, or "slide", to be projected.
     * @param sTitle
     *           A String representing the title of the projection(FSM only)
     * @param iI
     *           The GFeed object that'll receive updates.
     */
    public Projector(float fFps, Image ISlide, String sTitle, Pinterface iI)
    {
        this.fFps = fFps;
        this.ISlide = ISlide;
        this.sTitle = sTitle;
        this.iI = iI;
    }

    /**
     * Simply returns if iProjector is running.
     *
     * @return A boolean defining the running status.
     */
    public boolean isRunning()
    {
        return bIsRunning;
    }

    /**
     * Say that you wanted to switch updates between two different interfaces,
     * one should use this to set a new one.
     * @param iI The new interface to receive updates
     */
    public void setInterface(Pinterface iI)
    {
        this.iI=iI;
    }

    /**
     * Sets the Frames per Second float variable.
     *
     * @param fFps
     *           The new FPS rate
     */
    public void setFPS(float fFps)
    {
        this.fFps = fFps;
    }


    /**
     * Changes the Image/Slide pointer of the projection. I only recommend this
     * if you need the Image being replaced, otherwise just update the original
     * Image.
     *
     * @param ISlide
     *           The new Image, or Slide, pointer
     */
    public void setSlide(Image ISlide)
    {
        this.ISlide = ISlide;
    }

    /**
     * Returns if the projection is full screen or not.
     * @return True if it is full screen
     */
    public boolean isFS()
    {
        return jf != null;
    }

    /**
     * Initiates a Full Screen Mode(FSM) Projection. Pass in a frame..
     * @param jf Just for reference.
     * @return a created JPanel, again for reference.
     */
    public JPanel init(JFrame jf)
    {
        this.jf=jf;
        tk = Toolkit.getDefaultToolkit();
        d = tk.getScreenSize();
        c = jf.getContentPane();
        
        if(jp==null)
        {
            jp = new JPanel();
        }

        jp.setPreferredSize(d);
        c.setLayout(new BorderLayout());
        c.add(jp, BorderLayout.NORTH);

        jf.setTitle(sTitle);
        jf.setUndecorated(true);
        jf.setIgnoreRepaint(true);
        jf.pack();
        jf.setResizable(false);
        jf.setVisible(true);

        if(!bIsRunning)
        {
            resume();
        }
        return jp;
    }

    /**
     * Initiates a projection on a JPanel of a certain size.
     *
     * @param d
     *           The size of the JPanel.
     * @return Returns the JPanel that will receive the Projections.
     */
    public JPanel init(Dimension d)
    {
        if(jp==null)
        {
            jp = new JPanel();
        }


        jp.setPreferredSize(d);
        this.d = d;

        if(!bIsRunning)
        {
            resume();
        }
        
        return jp;
    }

    /*
     * A graphics update, fits the Image to either the screen(FSM) or the
     * JPanel(Not FSM).
     */
    private void paintUpdate()
    {
        d = jp.getSize();
        Graphics g;
        try {
            g = jp.getGraphics();
            if ((g != null) && (ISlide != null)) {
                g.drawImage(ISlide, 0, 0, d.width, d.height, null);
                Toolkit.getDefaultToolkit().sync();
                g.dispose();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Resumes the Projection thread if stopped.
     */
    public void resume()
    {
        if(!bIsRunning)
        {
            bRun = true;
            t = new Thread(this);
            t.start();
        }
    }

    /*
     * Overrides the Threads run method to update the Projection, and for
     * greatest accuracy the iInterface.
     */
    public void run()
    {
        do {
            bIsRunning = true;
            long lTime = System.nanoTime();
            iI.iUpdate((BufferedImage)ISlide);
            paintUpdate();
            lTime = System.nanoTime() - lTime;
            try {
                /*
                 * The sleep method of Thread accepts milliseconds, while lTime is
                 * currently in nanoseconds. by dividing 1 second(1000f) by fFps, and
                 * subtracting the time it takes to update in milliseconds, we can
                 * make the Projection consistent.
                 */
                long lSleep = (long) (1000f / fFps) - (lTime / 1000000l);
                if (lSleep > 0) {
                    Thread.sleep(lSleep);
                }
                else
                {
                    System.out.println("Frame Rate Failure: " + lSleep);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (bRun);
        bIsRunning = false;
    }

    /**
     * Stops the Projection thread.
     */
    public void stop()
    {
        bRun = false;
    }

    /**
     * Closes the full screen; however, it does not stop or end the application.
     */
    public void closeFS()
    {
        stop();
        jf.dispose();
        jf = null;
    }
}
