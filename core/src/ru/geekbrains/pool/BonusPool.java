package ru.geekbrains.pool;

import ru.geekbrains.base.Bonus;
import ru.geekbrains.base.SpritesPool;
import ru.geekbrains.math.Rect;

public class BonusPool extends SpritesPool<Bonus> {

    private final Rect worldBounds;

    public BonusPool(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }

    @Override
    protected Bonus newObject() {
        return new Bonus(worldBounds);
    }
}
