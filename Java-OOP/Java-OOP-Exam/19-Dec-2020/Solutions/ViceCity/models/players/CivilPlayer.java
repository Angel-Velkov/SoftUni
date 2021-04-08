package viceCity.models.players;

public class CivilPlayer extends BasePlayer {
    private static final int DEFAULT_LIFE_POINTS = 50;

    protected CivilPlayer(String name) {
        super(name, DEFAULT_LIFE_POINTS);
    }
}
