package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class Logo extends Sprite {

    private static final float V_LEN = 0.02f;
    private Vector2 v;
    private Rect worldBounds;

    public Logo(Texture texture) {
        super(new TextureRegion(texture));
        v = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(worldBounds.getHeight() / (float) 10);
    }

    public void move(Vector2 destination) {
        Vector2 tmp = new Vector2();
        tmp.set(destination);
        v.set(tmp.cpy().sub(this.pos)).setLength(V_LEN);
        float length = tmp.sub(this.pos).len();
        if (length > V_LEN) {
            pos.add(v);
            if (this.getTop() > worldBounds.getTop()) {
                this.setTop(worldBounds.getTop());
            }
            if (this.getLeft() < worldBounds.getLeft()) {
                this.setLeft(worldBounds.getLeft());
            }
            if (this.getRight() > worldBounds.getRight()) {
                this.setRight(worldBounds.getRight());
            }
            if (this.getBottom() < worldBounds.getBottom()) {
                this.setBottom(worldBounds.getBottom());
            }
        } else {
            this.pos.set(destination);
        }

    }
}
