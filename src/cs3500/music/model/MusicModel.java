package cs3500.music.model;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

import cs3500.music.util.*;

/**
 * Represents the model for an instance of the Music Editor.
 */
public final class MusicModel implements ModelOperations {
  private Track currentTrack;
  private Meter currentMeter;
  // Stores stuff for save and retrieve
  Stack<Track> stackTrack;

  /**
   * Constructs a MusicModel (storing the default Meter and Track).
   */
  public MusicModel() {
    this.startEditor();
    this.stackTrack = new Stack<Track>();
  }

  @Override
  public void overwriteWith(Track thatTrack) {
    Objects.requireNonNull(thatTrack);
    this.currentTrack = thatTrack;
  }

  @Override
  public Meter getMeter() {
    return this.currentMeter;
  }

  @Override
  public void setMeter(Meter newMeter) {
    this.currentMeter = newMeter;
  }

  @Override
  public void startEditor() {
    this.setMeter(Meter.FOUR_FOUR);
    this.overwriteWith(new TrackImp(Instrument.SINE));
  }

  @Override
  public void save() {
    stackTrack.push(this.currentTrack);
  }

  @Override
  public void retrieve() throws IllegalStateException {
    if (stackTrack.isEmpty()) {
      throw new IllegalStateException("No Track to Retrieve");
    }
    this.overwriteWith(this.stackTrack.pop());
  }

  //All the rest are delegations basically, some just make sure null isn't passed in

  @Override
  public void addNote(Note note, int beatStart) throws IllegalArgumentException {
    Objects.requireNonNull(note);
    this.currentTrack.addNote(note, beatStart);
  }

  @Override
  public void removeNoteStartingAt(int beatStart, Pitch pitch, Octave octave)
          throws IllegalArgumentException {
    Objects.requireNonNull(pitch);
    Objects.requireNonNull(octave);
    this.currentTrack.removeNoteStartingAt(beatStart, pitch, octave);
  }

  @Override
  public void removeAllOf(Pitch pitch, Octave octave) {
    Objects.requireNonNull(pitch);
    Objects.requireNonNull(octave);
    this.currentTrack.removeAllOf(pitch, octave);
  }

  @Override
  public void mergeWith(Track thatTrack) {
    Objects.requireNonNull(thatTrack);
    this.currentTrack.mergeWith(thatTrack);
  }

  @Override
  public void combineConsecutively(Track thatTrack) {
    Objects.requireNonNull(thatTrack);
    this.currentTrack.combineConsecutively(thatTrack);
  }

  @Override
  public List<Note> getAllAtBeat(int beat) {
    return this.currentTrack.getAllAtBeat(beat);
  }

  @Override
  public List<Note> getAllAtBeat(int beat, Pitch pitch) {
    Objects.requireNonNull(pitch);
    return this.currentTrack.getAllAtBeat(beat, pitch);
  }


  /**
   * The builder class for my MusicModel.
   */
  public static final class Builder implements CompositionBuilder<ModelOperations> {

    @Override
    public ModelOperations build() {

      ModelOperations model = new MusicModel();
      return null;
    }

    @Override
    public CompositionBuilder<ModelOperations> setTempo(int tempo) {
      return null;
    }

    @Override
    public CompositionBuilder<ModelOperations> addNote(int start, int end, int instrument, int pitch, int volume) {
      return null;
    }
    // FILL IN HERE
  }
}
