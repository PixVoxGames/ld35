package com.pixvoxsoftware.ld35.tasks;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.annotation.TaskAttribute;
import com.pixvoxsoftware.ld35.Loggers;
import com.pixvoxsoftware.ld35.entities.Guard;

public class WalkTask extends LeafTask<Guard> {

    @TaskAttribute (required = true) public float direction = 1f;

    @Override
    public void start() {
        getObject().setTargetX(getObject().getX() + getObject().getStepsRight()*direction);
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
        WalkTask walkTask = (WalkTask) task;
        walkTask.direction = direction;
        return walkTask;
    }
}