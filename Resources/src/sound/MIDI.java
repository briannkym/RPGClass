/*
 *  Use with Brian's permission, else he exercises freedoms to sue you
 *  $1,000,000.00
 */
package sound;

import javax.sound.midi.Sequence;

/**
 *
 * @author Brian
 */
public class MIDI implements Sound
{
    private Sequence Seq;
    
    public MIDI (Sequence Seq){
        this.Seq = Seq;
    }

    /**
     * @return the Seq
     */
    public Sequence getSeq() {
        return Seq;
    }

	@Override
	public WAV getWAV() {
		return null;
	}

	@Override
	public MIDI getMIDI() {
		return this;
	}
    
    
}
