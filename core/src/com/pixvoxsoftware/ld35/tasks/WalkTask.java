package com.pixvoxsoftware.ld35.tasks;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.annotation.TaskAttribute;
import com.pixvoxsoftware.ld35.AnimatedSprite;
import com.pixvoxsoftware.ld35.WorldConstants;
import com.pixvoxsoftware.ld35.entities.Guard;

public class WalkTask extends LeafTask<Guard> {

    @TaskAttribute (required = true) public boolean positiveDirection = true;

    @Override
    public void start() {
        if (positiveDirection) {
            getObject().setTargetX(getObject().getSpawnX() + getObject().getStepsRight() * 32 / WorldConstants.PIXELS_PER_METER);
        } else {
            getObject().setTargetX(getObject().getSpawnX() - getObject().getStepsLeft() * 32 / WorldConstants.PIXELS_PER_METER);
        }
//        if (positiveDirection) {
//            ((AnimatedSprite)getObject().getSprite()).setMirroredVertically(true);
//        }
//        else {
//            ((AnimatedSprite)getObject().getSprite()).setMirroredVertically(false);
//        }
        getObject().setState(Guard.State.MOVING);
//        if (positiveDirection) {
//            ((AnimatedSprite)getObject().getSprite()).setMirroredVertically(true);
//        }
//        else {
//            ((AnimatedSprite)getObject().getSprite()).setMirroredVertically(false);
//        }
    }

    @Override
    public Status execute() {
        if (Math.abs(getObject().getTargetX() - getObject().getX()) < 0.05f) {
            return Status.SUCCEEDED;
        }
        return Status.RUNNING;
    }

    @Override
    public void end() {
        getObject().setState(Guard.State.IDLE);
    }

    @Override
    protected Task<Guard> copyTo(Task<Guard> task) {
        WalkTask walkTask = (WalkTask) task;
        walkTask.positiveDirection = positiveDirection;
        return walkTask;
    }
}
