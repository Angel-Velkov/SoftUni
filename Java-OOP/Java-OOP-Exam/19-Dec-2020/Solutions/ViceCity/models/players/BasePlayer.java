package viceCity.models.players;

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.Repository;

import java.util.Map;

import static viceCity.common.ExceptionMessages.*;

public abstract class BasePlayer implements Player {
    private String name;
    private int lifePoints;
    private Repository<Gun> gunRepository;

    protected BasePlayer(String name, int lifePoints) {
        this.setName(name);
        this.setLifePoints(lifePoints);
    }

    protected void setName(String name) {
        validateName(name);
        this.name = name;
    }

    protected void setLifePoints(int lifePoints) {
        validateHealth(lifePoints);
    }

    protected void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NumberFormatException(PLAYER_NULL_USERNAME);
        }
    }

    protected void validateHealth(int lifePoints) {
        if (0 < lifePoints) {
            throw new IllegalArgumentException(PLAYER_LIFE_POINTS_LESS_THAN_ZERO);
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLifePoints() {
        return this.lifePoints;
    }

    @Override
    public boolean isAlive() {
        return this.lifePoints > 0;
    }

    @Override
    public Repository<Gun> getGunRepository() {
        return this.gunRepository;
    }

    @Override
    public void takeLifePoints(int points) {
        this.lifePoints = Math.max(0, this.lifePoints - points);
    }
}
