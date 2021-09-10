package ru.geekbrains.pool;

import ru.geekbrains.base.Ship;
import ru.geekbrains.base.SpritesPool;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.EnemyShip;

public class EnemyPool extends SpritesPool<EnemyShip> {

    private final BulletPool bulletPool;
    private final Rect worldBounds;

    public EnemyPool(BulletPool bulletPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(bulletPool, worldBounds);
    }

    public void crashTest(Ship mainShip) {
        for (Object currentObject : activeObjects) {
            if (currentObject instanceof EnemyShip) {
                EnemyShip enemyShip = (EnemyShip) currentObject;
                if (enemyShip.checkForCrash(mainShip)) {
                    enemyShip.explode();
                }
            }
        }
    }
}
