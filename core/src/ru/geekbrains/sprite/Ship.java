package ru.geekbrains.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class Ship extends Sprite {

    private static final float HEIGHT = 0.1f;
    private static final float V_LEN = 0.02f;
    private Vector2 v;
    private Rect worldBounds;
    private Vector2 destination;

    public Ship(Texture texture) {
        super(new TextureRegion(texture));
        destination = new Vector2();
        v = new Vector2();
        this.pos.set(0f, -1f);
    }

    @Override
    public void resize(Rect worldBounds) {
        System.out.println("I am on : " + pos.x + " " + pos.y);
        this.worldBounds = worldBounds;
        setHeightProportion(HEIGHT);

    }

    @Override
    public void update(float delta) {
        System.out.println("I am on : " + pos.x + " " + pos.y);
        if (!pos.equals(destination) && destination.x != 0) {
            v.set(destination.cpy().sub(this.pos)).setLength(V_LEN);
            float length = destination.dst(pos);
            if (length > V_LEN) {
                pos.add(v);
            } else {
                pos.set(destination);
            }
        }
        checkAndHandleBounds();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x > 0) {
            destination.set(worldBounds.getRight(), pos.y);
        } else if (touch.x < 0) {
            destination.set(worldBounds.getLeft(), pos.y);
        }
        return super.touchDown(touch, pointer, button);
    }


    private void checkAndHandleBounds() {
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
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.RIGHT:
                destination.set(worldBounds.getRight(), pos.y);
                break;
            case Input.Keys.LEFT:
                destination.set(worldBounds.getLeft(), pos.y);
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        v.setZero();
        return false;
    }
}
