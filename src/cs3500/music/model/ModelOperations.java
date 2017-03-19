package cs3500.music.model;

import java.util.List;

// HOMEWORK 6 UPDATES:
//    - getState (returns the String representation of the model in columnar form)
//      was moved to the view, so was stuff for Meter (turns out is probs useless)
//    -
//

/**
 * The public-facing interface for the Model for my Music Editor.
 */
public interface ModelOperations {

  /**
   * The instrument to be used as default (should nothing be specified).
   */
  Instrument defaultInstrument = Instrument.SINE;

  Meter defaultMeter = Meter.FOUR_FOUR;

  /**
   * Adds the given note to the Model representation starting with the specified beat.
   *
   * @param note      the Note to be added
   * @param beatStart the timeStamp to add the note to
   * @throws IllegalArgumentException if there is already a note taking up this space
   */
  void addNote(Note note, int beatStart) throws IllegalArgumentException;

  /**
   * Removes the Note that starts at the given timeStamp.
   *
   * @param beatStart the given int timeStamp
   * @param pitch     Pitch
   * @param octave    the Octave
   * @throws IllegalArgumentException if there is no specified note beginning at the given
   *                                  timeStamp
   */
  void removeNoteStartingAt(int beatStart, Pitch pitch, Octave octave)
          throws IllegalArgumentException;

  /**
   * Removes all Notes of the given Pitch and Octave (regardless of volume or duration).
   *
   * @param pitch  the pitch desired to be removed
   * @param octave the octave desired to be removed
   */
  void removeAllOf(Pitch pitch, Octave octave);

  /**
   * Returns the current Meter of the Track.
   *
   * @return the meter of the Track as a Meter
   */
  Meter getMeter();

  /**
   * Sets the current Meter of the Track to the given Meter.
   */
  void setMeter(Meter newMeter);

  /**
   * Starts the editor (and creates all the data structures) but doesn't fill it with anything.
   * If called more than once, simply erases everything (except the save)
   * and returns to the initial state.
   */
  void startEditor();

  /**
   * Saves a copy of the current stored Notes in this Model.
   */
  void save();

  /**
   * Retrieves the last copy of stored Notes in this Model and restores it (but nothing else).
   *
   * @throws IllegalStateException if there is no saves
   */
  void retrieve() throws IllegalStateException;

  /**
   * Returns whether this is equal to that Object.
   *
   * @param o that Object
   * @return a boolean representing equality
   */
  boolean equals(Object o);

  /**
   * Gets the hashcode of this ModelOperations.
   *
   * @return the hashcode
   */
  int hashCode();

  /**
   * Merges thatTrack with the stored Track into one Track
   * and stores it.
   *
   * @param thatTrack the Track being merged into this Model
   */
  void mergeWith(Track thatTrack);

  /**
   * Combines thatTrack with the stored Track by starting thatTrack
   * at the next beat after the stored Track ends.
   *
   * @param thatTrack the Track being appended to the end of the stored Track
   */
  void combineConsecutively(Track thatTrack);

  /**
   * Gets all the Notes at the given beat in an ArrayList.
   *
   * @param beat the given beat
   * @return All the Notes at the given beat in an ArrayList
   * @throws IllegalArgumentException if beat points to invalid
   */
  List<Note> getAllAtBeat(int beat);

  /**
   * Gets all the Notes of the specified pitch at the given beat in an ArrayList.
   *
   * @param beat the given beat
   * @return All the Notes at the given beat in an ArrayList
   * @throws IllegalArgumentException if beat points to invalid
   */
  List<Note> getAllAtBeat(int beat, Pitch pitch);

  /**
   * Stores thatTrack as a new Track(completely replaces the stored Track).
   *
   * @param thatTrack the Track to be stored
   */
  void overwriteWith(Track thatTrack);

}
