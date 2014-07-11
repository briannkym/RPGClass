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

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.Clip;

/**
 *
 * @author Megan
 */
public class TrackPlayer {
    private Sequencer sequencer;
    private static TrackPlayer player = new TrackPlayer();

    private TrackPlayer() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
        } catch (Exception e) {
            System.out.println("Midi sequencer was unavailable =(.");
        }
    }

    public static TrackPlayer getPlayer() {
        return player;
    }
    
    public void play(Sound s){
    	if(s.getWAV() != null){
    		play(s.getWAV());
    	} else if (s.getMIDI() != null){
    		play(s.getMIDI());
    	}
    }
    
    public void play(MIDI mi) {
    	play(mi, Sequencer.LOOP_CONTINUOUSLY);
    }
    
    public void play(MIDI mi, int count){
    	try {
            sequencer.setSequence(mi.getSeq());
            sequencer.setLoopCount(count);
            sequencer.setLoopStartPoint(0);
            sequencer.start();
        } catch (InvalidMidiDataException ex) {
            System.out.println("Error starting sequence =P.");
        }
    }

    public void play(WAV wav){
    	play(wav, 0);
    }
    
    public void play(WAV wav, int count) {
        Clip c = wav.getC();
        c.stop();
        c.loop(count);
        c.setFramePosition(0);
        c.start();
    }
}
