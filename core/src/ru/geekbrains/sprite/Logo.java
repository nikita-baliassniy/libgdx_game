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
    private Vector2 destination;

    public Logo(Texture texture) {
        super(new TextureRegion(texture));
        destination = new Vector2();
        v = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(worldBounds.getHeight() / (float) 10);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.destination = touch;
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (!pos.equals(destination)) {
            v.set(destination.cpy().sub(this.pos)).setLength(V_LEN);
            float length = destination.dst(pos);
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

}
