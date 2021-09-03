package ru.geekbrains.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.Background;
import ru.geekbrains.sprite.Logo;


public class MenuScreen extends BaseScreen {

    private Texture img;
    private Texture bg;
    private Vector2 dest;

    private Background background;
    private Logo logo;

    @Override
    public void show() {
        super.show();
        dest = new Vector2();
        img = new Texture("tie-small.png");
        bg = new Texture("wall.jpg");
        background = new Background(bg);
        logo = new Logo(img);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        logo.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
        bg.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        dest.set(touch);
        return super.touchDown(touch, pointer, button);
    }

    private void update(float delta) {
        if (!logo.pos.equals(dest)) {
            logo.move(dest);
        }
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        logo.draw(batch);
        batch.end();
    }
}
