/*The MIT License (MIT)

Copyright (c) 2014 Brian Nakayama

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/

package sound;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Brian
 */
public class SoundUpload {
    
    private File f;
    private static List<SoundUpload> suList = new LinkedList<SoundUpload>();

    private Map<String, Sound> sound = new HashMap<String, Sound>();
    private FileFilter fs = new FileFilter() {
        @Override
        public boolean accept(File f) {
            if (f.getAbsolutePath().endsWith(".mid")) {
                return true;
            }

            if (f.getAbsolutePath().endsWith(".wav")) {
                return true;
            }

            return false;
        }
    };
    
    public static SoundUpload getInstance(File f)
    {
        Iterator<SoundUpload> Isu = suList.iterator();
        if (Isu.hasNext()) {
                SoundUpload obj;

                do {
                    obj = Isu.next();
                    if(obj.getFile().getAbsolutePath()==f.getAbsolutePath())
                    {
                        return obj;
                    }
                } while (Isu.hasNext());
            }
        SoundUpload su = new SoundUpload(f);
        suList.add(su);
        return su;
    }

    private SoundUpload(File f) {
        this.f = f;
        if (f.isDirectory()) {
            File[] sounds = f.listFiles(fs);

            for (int i = 0; i < sounds.length; i++) {
                Sound bS = null;

                if (sounds[i].getName().endsWith(".mid")) {
                    try {
                        FileInputStream is = new FileInputStream(sounds[i]);
                        Sequence Seq = MidiSystem.getSequence(is);
                        bS = new MIDI(Seq);
                        is.close();
                    } catch (Exception e) {
                        System.out.println("Error loading midi: " + sounds[i].getName());
                    }
                } else if (sounds[i].getName().endsWith(".wav")) {
                    try {
                        AudioInputStream as = AudioSystem.getAudioInputStream(sounds[i]);
                        Clip c = AudioSystem.getClip();
                        c.open(as);
                        bS = new WAV(c);
                        as.close();
                    } catch (Exception e) {
                        System.out.println("Error loading wav:" + sounds[i].getName());

                    }
                }

                if (bS != null) {
                    sound.put(sounds[i].getName(), bS);
                }
            }

        }
    }
    

    public Sound getSound(String fileName) {
        return sound.get(fileName);
    }

    /**
     * @return the f
     */
    public File getFile()
    {
        return f;
    }
}
