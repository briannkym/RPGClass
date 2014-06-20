/*
 *  Use with Brian's permission, else he exercises freedoms to sue you
 *  $1,000,000.00
 */
package sound;

import javax.sound.sampled.Clip;

/**
 *
 * @author Brian
 */
public class WAV implements Sound
{
    private Clip c;
    
    public WAV(Clip c)
    {
        this.c = c;
    }

    /**
     * @return the as
     */
    public Clip getC() {
        return c;
    }

	@Override
	public WAV getWAV() {
		return this;
	}

	@Override
	public MIDI getMIDI() {
		return null;
	}
    
}
