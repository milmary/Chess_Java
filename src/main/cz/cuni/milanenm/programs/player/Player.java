package main.cz.cuni.milanenm.programs.player;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import main.cz.cuni.milanenm.programs.manage.Cell;
import main.cz.cuni.milanenm.programs.pieces.Piece;

/**
 * Represents a player in the game, containing the player's team and pieces.
 */
public class Player {
    private final Team team;
    private final CopyOnWriteArrayList<Piece> pieces;
    public Cell lastMoveFrom;
    public Cell lastMoveTo;

    /**
     * Constructs a Player object with a specified team.
     * @param team The team (BLACK or WHITE) the player belongs to.
     */
    public Player(Team team) {
        this.team = team;
        pieces = new CopyOnWriteArrayList<>();
    }

    /**
     * Gets the team of the player.
     * @return The team (BLACK or WHITE) the player belongs to.
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Gets the list of pieces owned by the player.
     * @return A list of pieces owned by the player.
     */
    public List<Piece> getPieces() {
        return pieces;
    }
}