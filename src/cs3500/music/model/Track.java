package cs3500.music.model;

import java.util.List;
import java.util.Map;

/**
 * Representing a Track, the class where I store the data structure and the meter.
 */
public interface Track {
  /**
   * Returns the state of the Track with the specifications set out in the Model.
   *
   * @return the state of the Track (notes) via a String
   */
  String getState();

  /**
   * Changes the instrument of this Track to the given instrument.
   *
   * @param instrument the given instrument
   */
  void changeInstrument(Instrument instrument);

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
   * @param beatStart the int representing the start of the beat
   * @param pitch     the Pitch of the specified note
   * @param octave    the Octave of the specified note
   * @throws IllegalArgumentException if there is no spec note beginning at the given timeStamp
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
   * Returns all the Notes at the given beat in a List.
   *
   * @param beat the given beat
   * @return All the Notes at the given beat in a List.
   * @throws IllegalArgumentException if beat points to invalid
   */
  List<Note> getAllAtBeat(int beat);

  /**
   * Returns all the Notes of the specified pitch at the given beat in a List.
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

  /**
   * Returns the number of Beats in the Track.
   *
   * @return the number of Beats in the Track
   */
  int numBeats();

  /**
   * Returns a Note stored with the highest Tone.
   *
   * @return the highest Note
   * @throws IllegalArgumentException if there are no stored notes
   */
  Note getLowestNote() throws IllegalArgumentException;

  /**
   * Returns a Note stored of the lowest tone.
   *
   * @return the lowest note
   * @throws IllegalArgumentException if there are no stored notes
   */
  Note getHighestNote() throws IllegalArgumentException;

  /**
   * Returns whether this has any Notes stored (regardless of Key values stored).
   *
   * @return if this isEmpty
   */
  boolean isEmpty();

  /**
   * Gets the Instrument of the Track.
   *
   * @return the instrument
   */
  Instrument getInstrument();

  /**
   * Gets the Map representation of beats and Notes.
   *
   * @return the map of beats and Notes
   */
  Map<Integer, List<Note>> getMap();
}
