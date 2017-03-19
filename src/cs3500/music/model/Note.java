package cs3500.music.model;

import java.util.Objects;

/**
 * Represents a Note, which has pitch, octave, duration and volume.
 */
public class Note {

  // DEFAULT IS -1 UNLESS PLACED, THEN IS WHATEVER PLACED
  private int beatStart;

  //private double volume;

  private Pitch pitch;

  private Octave octave;

  /**
   * From the documentation: A note’s duration is measured in beats,
   * and can in general be any integer multiple of any integer power of two beats long.
   */
  private int duration;
  //INVARIANT: can be any integer multiple of any integer power of two - beats long

  /**
   * Constructs a Note.
   *
   * @param pitch    the given pitch
   * @param octave   the given octave
   * @param duration the given int duration
   */
  Note(Pitch pitch, Octave octave, int duration) {
    Objects.requireNonNull(pitch);
    Objects.requireNonNull(octave);
    // default volume is 1 (range is from 0 to 1, can be any double to two decimal points)
    //this.volume = 1.0;

    this.pitch = pitch;

    this.octave = octave;

    this.validateDuration(duration);
    this.duration = duration;

    this.beatStart = -1;
  }

  /**
   * Gets the pitch.
   *
   * @return the Pitch
   */
  public Pitch getPitch() {
    return pitch;
  }

  /**
   * Gets the octave.
   *
   * @return the octave
   */
  public Octave getOctave() {
    return octave;
  }

  /**
   * Gets the int duration.
   *
   * @return the int duration
   */
  public int getDuration() {
    return duration;
  }

  /**
   * Checks whether the given duration is a valid duration (ie not negative, not 0, an integer).
   *
   * @param duration the given int duration
   * @throws IllegalArgumentException if the given int is invalid
   */
  private void validateDuration(int duration) throws IllegalArgumentException {
    if (duration <= 0) {
      throw new IllegalArgumentException("Invalid Duration: " + duration);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Note)) {
      return false;
    }

    Note note = (Note) o;

    if (duration != note.duration) {
      return false;
    }
    if (pitch != note.pitch) {
      return false;
    }
    return octave == note.octave;
  }

  @Override
  public int hashCode() {
    int result = pitch.hashCode();
    result = 31 * result + octave.hashCode();
    result = 31 * result + duration;
    return result;
  }

  /**
   * Returns whether this Note is higher than the given Note.
   *
   * @param thatNote the given Note
   * @return a boolean representing whether this is higher than thatNote
   */
  public boolean isHigher(Note thatNote) {
    Objects.requireNonNull(thatNote);

    // that > this
    if (thatNote.getOctave().toInt() > this.getOctave().toInt()) {
      return false;
    } else {
      // that < this
      if (thatNote.getOctave().toInt() < this.getOctave().toInt()) {
        return true;
      } else {
        // octaves are equal
        return thatNote.getPitch().getOrderVal() < this.getPitch().getOrderVal();
      }
    }
  }

  /**
   * Returns whether this Note is lower than the given Note.
   *
   * @param that the given Note
   * @return whether this is lower than that Note
   */
  public boolean isLower(Note that) {
    Objects.requireNonNull(that);

    // that > this
    if (that.getOctave().toInt() > this.getOctave().toInt()) {
      return true;
    } else {
      // that < this
      if (that.getOctave().toInt() < this.getOctave().toInt()) {
        return false;
      } else {
        // octaves are equal
        return that.getPitch().getOrderVal() > this.getPitch().getOrderVal();
      }
    }

  }

  /**
   * Sets where the Note starts.
   *
   * @param beatStart the int where the Note starts.
   */
  public void setBeatStart(int beatStart) {
    this.beatStart = beatStart;
  }

  /**
   * Returns where the Note starts.
   *
   * @return the int where the Note starts
   */
  public int getBeatStart() {
    return this.beatStart;
  }

  /**
   * Returns a copy of this note just one value higher in Tone (same duration).
   *
   * @return a copy of this note one note higher
   */
  public Note getOneHigher() {
    Octave octave = this.getOctave();
    if (this.pitch == Pitch.G$A) {
      octave = Octave.oneHigher(this.getOctave());
    }
    Note newNote = new Note(Pitch.oneHigher(this.getPitch()), octave, this.getDuration());
    return newNote;
  }

  /**
   * Returns the BeatState of this Note.
   */
  public BeatState getBeatState(int currentBeat) {
    if (this.beatStart == -1) {
      throw new IllegalStateException("Can't call isOngoing on an unplaced Note: " + currentBeat);
    }

    if (this.beatStart == currentBeat) {
      return BeatState.ONSET;
    }
    if (this.beatStart < currentBeat) {
      if (this.beatStart + duration >= currentBeat) {
        return BeatState.SUSTAIN;
      }
    }

    return BeatState.REST;
  }
}
