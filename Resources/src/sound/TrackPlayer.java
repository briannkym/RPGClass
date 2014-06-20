/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
    	play(wav, 1);
    }
    
    public void play(WAV wav, int count) {
        Clip c = wav.getC();
        c.stop();
        c.loop(count);
        c.setFramePosition(0);
        c.start();
    }
}
