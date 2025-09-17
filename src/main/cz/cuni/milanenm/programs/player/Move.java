package main.cz.cuni.milanenm.programs.player;

import main.cz.cuni.milanenm.programs.manage.Cell;

/**
 * Represents a move in the game, containing the source and destination cells and the player making the move.
 */
public class Move {

    public Cell from, to;
    public Player player;

    /**
     * Constructor to initialize a move.
     * @param from The starting cell of the move.
     * @param to The ending cell of the move.
     * @param player The player making the move.
     */
    public Move(Cell from, Cell to, Player player) {
        this.from = from;
        this.to = to;
        this.player = player;
    }

    @Override
    public String toString() {
        return String.format("Move from (%d, %d) to (%d, %d) by a Player %d",
                from.getX(), from.getY(), to.getX(), to.getY(), player.getTeam() == Team.WHITE ? 1 : 2);
    }
}
