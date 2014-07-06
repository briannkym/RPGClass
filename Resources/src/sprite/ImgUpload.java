/*
 * TODO: need to add images into the test case using animations from Jonny.
 *
 *  Use with Brian's permission, else he exercises freedoms to sue you
 *  $1,000,000.00
 */
package sprite;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 *
 * @author Brian
 */
public class ImgUpload
{
    private File f;
    private static List<ImgUpload> iuList = new LinkedList<ImgUpload>();
    private Map<String, Img> img = new HashMap<String, Img>();

    private FileFilter fs = new FileFilter()
    {

        @Override
        public boolean accept(File f)
        {
            if (f.getAbsolutePath().endsWith(".jpg")) {
                return true;
            }

            if (f.getAbsolutePath().endsWith(".png")) {
                return true;
            }

            return false;
        }
        
    };

    private ImgUpload(File f)
    {
        this.f = f;
        if (f.isDirectory()) {
            File[] images = f.listFiles(fs);
            for (int i = 0; i < images.length; i++) {
                BufferedImage bI = null;
                try {
                    bI = ImageIO.read(images[i]);
                } catch (IOException ex) {
                    System.out.println("Could not read in the following file:" + images[i].
                            getName());
                }
                
                File temp = new File(images[i].getAbsolutePath() + ".txt");
                if (temp.exists()) {
                    try {
                        BufferedReader br =
                                new BufferedReader(
                                new FileReader(temp));
                        String s[] = br.readLine().split(",");
                        int width = Integer.parseInt(s[0]);
                        int height = Integer.parseInt(s[1]);
                        int x_wid = bI.getWidth() / width;
                        int y_wid = bI.getHeight() / height;
                        BufferedImage[] bIA = new BufferedImage[width*height];

                        for (int x = 0; x < width; x++) {
                            for (int y = 0; y < height; y++) {
                                bIA[y*width + x] = bI.getSubimage(x * x_wid, y * y_wid, x_wid , y_wid);
                            }
                        }
                        img.put(images[i].getName(), new Animation(bIA));

                        br.close();
                    } catch (Exception ex) {
                        System.out.println("Could not read in this animation txt: " + temp.
                                getName());
                    }

                }
                else {
                    
                    img.put(images[i].getName(), new Sprite(bI));
                }
            }

        }
    }
    
    public static ImgUpload getInstance(File f)
    {
        Iterator<ImgUpload> Imu = iuList.iterator();
        if (Imu.hasNext()) {
                ImgUpload obj;

                do {
                    obj = Imu.next();
                    if(obj.getFile().getAbsolutePath()==f.getAbsolutePath())
                    {
                        return obj;
                    }
                } while (Imu.hasNext());
            }
        ImgUpload iu = new ImgUpload(f);
        iuList.add(iu);
        return iu;
    }

    public Img getImg(String fileName)
    {
        Img i = img.get(fileName);
        if(i == null)
        {
            return NullImg.getInstance();
        }
        return i;
    }
    
    /**
     * @return the f
     */
    public File getFile()
    {
        return f;
    }
}
