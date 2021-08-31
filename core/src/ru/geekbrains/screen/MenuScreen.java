package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Texture bgimg;
    private Vector2 pos;
    private Vector2 touch;
    private Vector2 v;
    private Vector2 dest;
    private Vector2 step;
    private final float speed = 1.5f / 60;

    @Override
    public void show() {
        super.show();
        img = new Texture("tie-small.png");
        bgimg = new Texture("wall.jpg");
        pos = new Vector2();
        touch = new Vector2();
        v = new Vector2();
        step = new Vector2();
        dest = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(bgimg, 0, 0, 1920, 1080);
        batch.draw(img, pos.x, pos.y);
        batch.end();
        if (!isHover()) {
            calculateStep();
            pos.add(step);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    public void calculateStep() {
        step = dest.cpy().sub(pos);
        step.x = step.x * speed ;
        step.y = step.y * speed;
    }

    public boolean isHover() {
        float x = dest.x;
        float y = dest.y;
        return x >= pos.x && x <= pos.x + img.getWidth()
                && y >= pos.y && y <= pos.y + img.getHeight();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        dest.set(touch);
        if (!isHover()) {
            calculateStep();
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        pos.set(touch);
        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                v.set(0, 5);
                break;
            case Input.Keys.DOWN:
                v.set(0, -5);
                break;
            case Input.Keys.RIGHT:
                v.set(5, 0);
                break;
            case Input.Keys.LEFT:
                v.set(-5, 0);
                break;
        }
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        v.setZero();
        return super.keyUp(keycode);
    }

}
