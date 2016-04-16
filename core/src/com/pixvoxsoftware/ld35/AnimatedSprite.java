package com.pixvoxsoftware.ld35;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class AnimatedSprite {
    private Animation walkAnimation;
    private Texture animationAtlas;
    private TextureRegion[] frames;
    private TextureRegion currentFrame;
    private float currentTime;
    private float width;
    private float height;

    private Vector2 position = new Vector2(0, 0);
    private Rectangle bounds = new Rectangle();

    public AnimatedSprite(FileHandle fileHandle, int frameCount, float frameTime) {
        animationAtlas = new Texture(fileHandle);
        System.out.println(animationAtlas.getWidth());
        System.out.println(animationAtlas.getHeight());
        TextureRegion tmp[][] = TextureRegion.split(animationAtlas, animationAtlas.getWidth() / frameCount, animationAtlas.getHeight());
        frames = new TextureRegion[frameCount];
        System.arraycopy(tmp[0], 0, frames, 0, frameCount);
        walkAnimation = new Animation(frameTime, frames);
        currentTime = 0f;
        width = animationAtlas.getWidth() / frameCount;
        height = animationAtlas.getHeight();
    }

    public AnimatedSprite(FileHandle fileHandle, int frameCount) {
        this(fileHandle, frameCount, 0.025f);
    }

    public void draw(SpriteBatch batch) {
        currentTime += Gdx.graphics.getDeltaTime();
        currentFrame = walkAnimation.getKeyFrame(currentTime, true);
        batch.draw(currentFrame, position.x, position.y);
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public void setX(float x) {
        position.x = x;
    }

    public void setY(float y) {
        position.y = y;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Rectangle getBoundingRectangle() {
        bounds.set(position.x, position.y, width, height);
        return bounds;
    }
}
