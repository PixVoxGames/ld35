package com.pixvoxsoftware.ld35;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;

public class AnimatedSprite extends Sprite{
    private Animation walkAnimation;
    private Texture animationAtlas;
    private TextureRegion[] frames;
    private TextureRegion currentFrame;
    private float currentTime;
    private float width;
    private float height;
    private boolean mirroredVertically = false;

    private Vector2 position = new Vector2(0, 0);

    public AnimatedSprite(FileHandle fileHandle, int frameCount, float frameTime) {
        animationAtlas = new Texture(fileHandle);
        TextureRegion tmp[][] = TextureRegion.split(animationAtlas, animationAtlas.getWidth() / frameCount, animationAtlas.getHeight());
        frames = new TextureRegion[frameCount];
        System.arraycopy(tmp[0], 0, frames, 0, frameCount);
        walkAnimation = new Animation(frameTime, frames);
        currentTime = 0f;
        width = animationAtlas.getWidth() / frameCount / WorldConstants.PIXELS_PER_METER;
        height = animationAtlas.getHeight() / WorldConstants.PIXELS_PER_METER;
    }

    public AnimatedSprite(FileHandle fileHandle, int frameCount) {
        this(fileHandle, frameCount, 0.025f);
    }

    @Override
    public void draw(Batch batch) {
        currentTime += Gdx.graphics.getDeltaTime();
        currentFrame = walkAnimation.getKeyFrame(currentTime, true);
        if (mirroredVertically) {
            batch.draw(currentFrame, position.x + width, position.y, -width, height);
        } else {
            batch.draw(currentFrame, position.x, position.y, width, height);
        }
    }

    @Override
    public float getX() {
        return position.x;
    }

    @Override
    public float getY() {
        return position.y;
    }
    @Override
    public void setX(float x) {
        position.x = x;
    }
    @Override
    public void setY(float y) {
        position.y = y;
    }

    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    public boolean isMirroredVertically() {
        return mirroredVertically;
    }

    public void setMirroredVertically(boolean mirroredVertically) {
        this.mirroredVertically = mirroredVertically;
    }
}
