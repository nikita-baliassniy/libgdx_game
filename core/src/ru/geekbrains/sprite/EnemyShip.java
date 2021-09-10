package ru.geekbrains.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Ship;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;

public class EnemyShip extends Ship {

    private Vector2 normalSpeed;
    private final Vector2 primarySpeed = new Vector2(0, -0.5f);
    private int explosionIndex = -1;
    private TextureRegion[] normalRegions;

    public EnemyShip(BulletPool bulletPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.bulletV = new Vector2();
        this.bulletPos = new Vector2();
        this.normalSpeed = new Vector2();
    }

    @Override
    public void update(float delta) {
        if (explosionIndex < 0) {
            super.update(delta);
            this.bulletPos.set(pos.x, pos.y - getHalfHeight());
            if (this.getTop() <= worldBounds.getTop()) {
                v.set(normalSpeed);
            }
        } else if (explosionIndex < explosionRegions.length) {
            v.set(0, 0);
            this.setRegions(explosionRegions);
            this.setFrame(explosionIndex);
            explosionIndex++;
        } else {
            destroy();
            this.explosionIndex = -1;
            this.setRegions(normalRegions);
            this.setFrame(0);
            v.set(primarySpeed);
        }
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            TextureRegion bulletRegion,
            TextureRegion[] explosionRegions,
            float bulletHeight,
            Vector2 bulletV,
            int bulletDamage,
            float reloadInterval,
            Sound bulletSound,
            float height,
            int hp
    ) {
        this.regions = regions;
        this.normalRegions = regions;
        this.v0.set(v0);
        this.bulletRegion = bulletRegion;
        this.explosionRegions = explosionRegions;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(bulletV);
        this.bulletDamage = bulletDamage;
        this.reloadInterval = reloadInterval;
        this.bulletSound = bulletSound;
        setHeightProportion(height);
        this.hp = hp;
        normalSpeed.set(v0);
        v.set(primarySpeed);
    }

    public boolean checkForCrash(Ship anotherShip) {
        if (!isOutside(worldBounds)) {
            return (this.getBottom() <= anotherShip.getTop()
                    && (this.getRight() >= anotherShip.getLeft() || this.getLeft() <= anotherShip.getRight()));
        }
        return false;
    }

    public void explode() {
        if (explosionIndex < 0) {
            this.explosionIndex = 0;
        }
    }
}
