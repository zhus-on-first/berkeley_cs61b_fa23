package game2048;

/** Represents the image of a numbered tile on a 2048 board.
 *  @author P. N. Hilfinger.
 */
public class Tile {

    private boolean hasMerged;

    /** A new tile with VALUE as its value at (ROW, COL).  This
     *  constructor is private, so all tiles are created by the
     *  factory method create. */
    private Tile(int value, int col, int row) {
        this._value = value;
        this._row = row;
        this._col = col;
        this._next = null;
        this.hasMerged = false;
    }

    /** Returns whether the tile has merged. */
    public boolean hasMerged() {
        return hasMerged;
    }

    /** Sets the hasMerged property. */
    public void setHasMerged(boolean hasMerged) {
        this.hasMerged = hasMerged;
    }

    /** Return my current row. */
    public int row() {
        return _row;
    }

    /** Return my current column. */
    public int col() {
        return _col;
    }

    /** Return the value supplied to my constructor. */
    public int value() {
        return _value;
    }

    /** Return my next state.  Before I am moved or merged, I am my
     *  own successor. */
    public Tile next() {
        return _next == null ? this : _next;
    }

    /** Set my next state when I am moved or merged. */
    public void setNext(Tile otherTile) {
        _next = otherTile;
    }

    /** Return a new tile at (ROW, COL) with value VALUE. */
    public static Tile create(int value, int col, int row) {
        return new Tile(value, col, row);
    }

    /** Return the distance in rows or columns between me and my successor
     *  tile (0 if I have no successor). */
    public int distToNext() {
        if (_next == null) {
            return 0;
        } else {
            return Math.max(Math.abs(_row - _next.row()),
                            Math.abs(_col - _next.col()));
        }
    }

    @Override
    public String toString() {
        return String.format("%d@(%d, %d)", value(), col(), row());
    }

    /** My value. */
    private final int _value;

    /** My last position on the board. */
    private final int _row, _col;

    /** Successor tile: one I am moved to or merged with. */
    private Tile _next;
}
