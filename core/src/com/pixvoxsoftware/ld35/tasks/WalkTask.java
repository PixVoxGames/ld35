package com.pixvoxsoftware.ld35.tasks;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.pixvoxsoftware.ld35.entities.Guard;

public class WalkTask extends LeafTask<Guard> {

    @Override
    public void start() {
        getObject().setTargetX(getObject().getX() + getObject().getStepsRight()*32f);
        getObject().setState(Guard.State.MOVING);
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
        return (WalkTask) task;
    }
}
