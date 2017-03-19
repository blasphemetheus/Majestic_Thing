package cs3500.music.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Represents the implementation of a Track.
 */
public class TrackImp implements Track {
  //Track will know the starting beats of Notes, will assign onset, sustain or rest.

  private Instrument instrument;
  private Map<Integer, List<Note>> map;
  // INVARIANT: All Mappings lead to a Note or Notes

  private int currentLength;

  /**
   * Constructs a Track given an Instrument.
   *
   * @param instrument the given Instrument
   */
  TrackImp(Instrument instrument) {
    Objects.requireNonNull(instrument);

    this.instrument = instrument;
    map = new Hashtable<Integer, List<Note>>();
    currentLength = 0;
  }

  /**
   * Constructs a Track given a Track.
   *
   * @param thatTrack the given Track
   */
  TrackImp(Track thatTrack) {
    Objects.requireNonNull(instrument);
    Objects.requireNonNull(map);

    this.instrument = thatTrack.getInstrument();
    map = thatTrack.getMap();
    currentLength = thatTrack.numBeats();
  }

  @Override
  public Note getLowestNote() throws IllegalArgumentException {
    Set<Integer> keySet = this.map.keySet();

    Note lowest = null;

    // runs through the key values
    for (Integer integer : keySet) {
      // pulls out the list of Notes7.+
      List<Note> list = map.get(integer);
      // runs through the list of Notes
      for (Note note : list) {
        if (lowest == null) {
          lowest = note;
        } else {
          if (note.isLower(lowest)) {
            lowest = note;
          }
        }
      }
    }

    return lowest;
  }

  @Override
  public Note getHighestNote() throws IllegalArgumentException {
    Set<Integer> keySet = this.map.keySet();

    Note highest = null;

    // runs through the key values
    for (Integer integer : keySet) {
      // pulls out the list of Notes7.+
      List<Note> list = map.get(integer);
      // runs through the list of Notes
      for (Note note : list) {
        if (highest == null) {
          highest = note;
        } else {
          if (note.isHigher(highest)) {
            highest = note;
          }
        }
      }
    }
    return highest;
  }

  @Override
  public String getState() {
    String output = "";
    Note lowestNote = this.getLowestNote();
    Note highestNote = this.getHighestNote();
    List<Note> encountered = new ArrayList<>();
    List<Integer> encounteredBeats = new ArrayList<>();

    // DEALS WITH FIRST LINE -- SHOULD GENERATE
    // if empty, lowestNote and highestNote should return null values
    if (this.isEmpty()) {
      output.concat(this.firstLine(null, null));
    } else {
      output.concat(this.firstLine(lowestNote, highestNote));
      // if this branch ever returns null lowestnote and null highestNote
      // then I screwed up in getLowestNote() and getHighestNote();
    }

    int totalBeats = this.numBeats();
    for (int currentBeat = 0; currentBeat < totalBeats; currentBeat += 1) {
      if (this.map.containsKey(currentBeat)) {
        for (Note tempNote : this.map.get(currentBeat)) {
          encountered.add(tempNote);
        }
        encountered.addAll(this.map.get(currentBeat));
        encounteredBeats.add(currentBeat);
      }

      // the intro to every line but the first
      output.concat(this.getPaddedInt(currentBeat, totalBeats));
      output.concat(this.genPerBeat(currentBeat, lowestNote, highestNote, encountered));
    }
    return output;
  }

  /**
   * Returns a String representing the notes in the beat from lowest note to highest.
   * (in five space blocks with the important char in the middle spot).
   * The designations can either be (blank), X (onset), or Sustain (|).
   *
   * @param currentBeat the currentBeat
   * @param lowestNote  the lowest note
   * @param highestNote the highest note
   * @param encountered the List of Notes encountered so far
   * @return String repping the notes from lowest to highest (occuring) on this beat
   */
  private String genPerBeat(int currentBeat, Note lowestNote, Note highestNote,
                            List<Note> encountered) {

    List<Note> startingNow = new ArrayList<>();
    List<Note> sustainingNow = new ArrayList<>();

    for (Note note : encountered) {
      BeatState beatState = note.getBeatState(currentBeat);
      if (beatState == BeatState.ONSET) {
        // StartingNow should be empty every time
        startingNow.add(note);
      } else {
        if (beatState == BeatState.SUSTAIN) {
          sustainingNow.add(note);
        }
        if (beatState == BeatState.REST) {
          encountered.remove(note);
        }
      }
    }

    if (!startingNow.isEmpty()) {
      throw new IllegalArgumentException(
              "Hold up, this shouldn't happen, notes shouldn't be "
                      + "starting and then starting again");
    }

    startingNow.addAll(this.map.get(currentBeat));

    Pitch lowPitch = lowestNote.getPitch();
    Octave lowOctave = lowestNote.getOctave();

    Pitch highPitch = highestNote.getPitch();
    Octave highOctave = highestNote.getOctave();
    String output = "";


    boolean firstTime = true;

    // the while loop is to make sure that t
    do {
      String oneBlock = "";

      for (Note note : startingNow) {
        if (note.getPitch() == lowPitch && note.getOctave() == lowOctave) {
          oneBlock.concat(this.generateBlock(BeatState.ONSET));
          break;
        }
      }

      for (Note note : sustainingNow) {
        if (note.getPitch() == lowPitch && note.getOctave() == lowOctave) {
          oneBlock.concat(this.generateBlock(BeatState.SUSTAIN));
          break;
        }
      }

      if (oneBlock.length() == 0) {
        oneBlock.concat(this.generateBlock(BeatState.REST));
      } else {
        if (oneBlock.length() != 5) {
          throw new IllegalArgumentException("something went wrong here: Beat: " + currentBeat);
        }
      }

      if (!firstTime) {
        // changes lowPitch and lowOctave (up one increment each time
        // (!! until it is equal to the high pitch and octave)
        Note tempNote = new Note(lowPitch, lowOctave, 1);
        tempNote.getOneHigher();
        lowPitch = tempNote.getPitch();
        lowOctave = tempNote.getOctave();
      }
      firstTime = false;
      output.concat(oneBlock);
    } while (lowPitch != highPitch && lowOctave != highOctave);

    return output;
  }

  /**
   * Generates a block to represent the state of a note or rest.
   *
   * @param beatEx the example beatState
   * @return a String block (5 char) representing the BeatState
   */
  private String generateBlock(BeatState beatEx) {
    switch (beatEx) {
      case ONSET:
        return "  X  ";
      case SUSTAIN:
        return "  |  ";
      case REST:
        return "     ";
      default:
        throw new IllegalArgumentException("Not a valid BeatState yo: " + beatEx);
    }
  }

  /**
   * Returns the padded Int and space that starts every line of gameState (but the first).
   *
   * @param currentBeat the currentBeat
   * @param totalBeats  the total Beats
   * @return the padded int for the given beat
   */

  private String getPaddedInt(int currentBeat, int totalBeats) {
    return String.format("% d", currentBeat, this.digitsOf(totalBeats));
    // TODO I don't understand what's happening here exactly
  }

  /**
   * Returns the number of digits to the left of the dot for the given integer.
   *
   * @param number The given integer
   * @return the number of digits to the left of the dot for the given number
   */
  public static int digitsOf(int number) {
    String stringNum = Integer.toString(number);
    return stringNum.length();
  }

  /**
   * Returns the first line of the output given the lowest and highest notes.
   * (can be null to represent a lack of notes).
   *
   * @param lowestNote  the lowest note (if null, no such note)
   * @param highestNote the highest note (if null, no such note)
   * @return the Note Name Line.
   */
  private String firstLine(Note lowestNote, Note highestNote) {

    // concatenates the correct amount of padded spaces
    String spaces = "";
    for (int i = 0; i < digitsOf(currentLength); i++) {
      spaces.concat(" ");
    }

    String output = "";
    output.concat(spaces);

    if (lowestNote == null || highestNote == null) {
      // the duration of 1 in the created notes is dummy data, the notes are just there to give
      // default range of output between A4 to G#4 (almost one octave)
      output.concat(this.noteRange(new Note(Pitch.A, Octave.FOUR, 1),
              new Note(Pitch.G$A, Octave.FOUR, 1)));
    } else {
      output.concat(this.noteRange(lowestNote, highestNote));
    }

    output.concat("\n");
    return output;
  }

  /**
   * Returns the String representing the pitches from the lowest pitch of the lowest octave.
   * to the highest pitch of the highest octave.
   *
   * @param lowNote  the lowNote
   * @param highNote the highNote
   * @return the range as a String (each pitch represented as a 5 string batch)
   */
  protected String noteRange(Note lowNote, Note highNote) {
    Pitch lowPitch = lowNote.getPitch();
    Octave lowOctave = lowNote.getOctave();

    Pitch highPitch = highNote.getPitch();
    Octave highOctave = highNote.getOctave();

    String output = "";

    output.concat(this.pitchOctaveString(lowPitch, lowOctave));

    Note note = new Note(lowPitch, lowOctave, 1);

    if (note.getOctave() == highOctave && note.getPitch() == highPitch) {
      return output;
    }

    while (true) {
      note = note.getOneHigher();
      output.concat(this.pitchOctaveString(note.getPitch(), note.getOctave()));

      if (note.getOctave() == highOctave && note.getPitch() == highPitch) {
        break;
      }
    }

    return output;
  }

  /**
   * Returns a five-character String representing the given Pitch and Octave (with chars cntered).
   *
   * @param pitch  the given pitch
   * @param octave the given octave
   * @return a representative five-character string
   */
  protected String pitchOctaveString(Pitch pitch, Octave octave) {
    String raw = "";
    raw = raw + pitch.toString();
    raw = raw + octave.toString();

    String output = "";

    switch (raw.length()) {
      case 2:
        output.concat(" " + " " + raw + " ");
        break;
      case 3:
        output.concat(" " + raw + " ");
        break;
      case 4:
        output.concat(" " + raw);
        break;
      default:
        throw new IllegalArgumentException(
                "This string has an invalid length (not between 2 and 4 long: " + raw);
    }
    return output;
  }

  @Override
  public void addNote(Note note, int beatStart) throws IllegalArgumentException {
    Objects.requireNonNull(note);
    this.validateNonNegative(beatStart);
    List<Note> noteHashSet = map.get(beatStart);

    if (map.containsKey(beatStart)) {
      List<Note> old = new ArrayList<Note>(map.get(beatStart));
      if (!old.contains(note)) {
        old.add(note);
      }
      map.replace(beatStart, old);
    } else {
      List newList = new ArrayList<Note>();
      newList.add(note);
      map.putIfAbsent(beatStart, newList);
    }
  }

  /**
   * Validates that the num is not Negative.
   *
   * @param num the given num
   * @throws IllegalArgumentException if num is negative
   */
  private void validateNonNegative(int num) throws IllegalArgumentException {
    if (num < 0) {
      throw new IllegalArgumentException("Shouldn't be negative: " + num);
    }
  }

  // does this have a key corresponding to beatStart
  private boolean hasKey(int beatStart) {
    return this.map.containsKey(beatStart);
  }

  @Override
  public void removeNoteStartingAt(int beatStart, Pitch pitch, Octave octave)
          throws IllegalArgumentException {

    if (!this.hasKey(beatStart)) {
      throw new IllegalArgumentException("Invalid beatStart (not within range): " + beatStart);
    }

    // if the map has the key and the corresponding list is not empty,
    // removes note of specified pitch and octave
    if (this.map.get(beatStart).isEmpty()) {
      throw new IllegalArgumentException("There is no stored Note to remove at: " + beatStart);
    }

    List<Note> listOfNOte = this.map.get(beatStart);

    for (Note note : listOfNOte) {
      //TODO could just do listOfNOte.contains instead in that if
      if (note.getOctave() == octave && note.getPitch() == pitch) {
        if (this.map.get(beatStart).size() == 1) {
          this.map.remove(beatStart);
        } else {
          List<Note> newList = new ArrayList<>();
          newList.remove(note);
          this.map.replace(beatStart, newList);
        }
      }
    }
  }


  @Override
  public void removeAllOf(Pitch pitch, Octave octave) {
    //TODO NONESSENTIAL
  }

  @Override
  public void mergeWith(Track thatTrack) {
    //TODO
  }

  @Override
  public void combineConsecutively(Track thatTrack) {
    //TODO
  }

  @Override
  public List<Note> getAllAtBeat(int beat) {
    return null;
  }

  @Override
  public List<Note> getAllAtBeat(int beat, Pitch pitch) {
    List<Note> output = new ArrayList<>();
    List<Note> atBeat = this.getAllAtBeat(beat);
    for (Note note : atBeat) {
      if (note.getPitch().equals(pitch)) {
        output.add(note);
      }
    }
    return output;
  }

  @Override
  public void overwriteWith(Track thatTrack) {

    Track trackImp = new TrackImp(thatTrack);

    this.changeInstrument(thatTrack.getInstrument());
    this.changeMap(thatTrack.getMap());
  }

  /**
   * Changes the map to the given map.
   *
   * @param map the given map
   */
  private void changeMap(Map<Integer, List<Note>> map) {
    Objects.requireNonNull(map);
    this.map = map;
  }

  @Override
  public void changeInstrument(Instrument instrument) {
    Objects.requireNonNull(instrument);
    this.instrument = instrument;
  }

  @Override
  public int numBeats() {
    return this.getLastEndOfNote();
  }

  /**
   * Returns the last start of a note, or -1 if there isn't one.
   */
  private int getLastEndOfNote() {
    Set<Integer> setKey = this.map.keySet();
    if (this.isEmpty()) {
      return -1;
    }
    int max = -1;
    for (Integer integer : setKey) {
      max = Math.max(max, integer);
    }
    // This will create a bug, pulls out the last starting note not the last ending one

    if (max == -1) {
      throw new IllegalArgumentException("Error Error, there was nothing: " + setKey + max);
    }

    return max + this.map.get(max).get(0).getDuration() - 1;
    // TODO probably a bug-filled travesty
  }

  @Override
  public boolean isEmpty() {
    return this.map.isEmpty();
  }

  @Override
  public Instrument getInstrument() {
    return getInstrument();
  }

  @Override
  public Map<Integer, List<Note>> getMap() {
    return this.map;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TrackImp)) {
      return false;
    }

    TrackImp trackImp = (TrackImp) o;

    if (instrument != trackImp.instrument) {
      return false;
    }
    return map.equals(trackImp.map);
  }

  @Override
  public int hashCode() {
    int result = instrument.hashCode();
    result = 31 * result + map.hashCode();
    return result;
  }
}
