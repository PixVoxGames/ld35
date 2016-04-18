package com.pixvoxsoftware.ld35.tasks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.MathUtils;
import com.pixvoxsoftware.ld35.Loggers;
import com.pixvoxsoftware.ld35.entities.Guard;
import sun.rmi.runtime.Log;

public class StandTask extends LeafTask<Guard> {

    public float time;
    public float timeToWait;

    @Override
    public void start() {
        time = 0f;
        timeToWait = MathUtils.random(2f, 5f);
        getObject().setState(Guard.State.IDLE);
    }

    @Override
    public Status execute() {
        Loggers.game.debug("Idling");
        time += Gdx.graphics.getDeltaTime();
        if (time < timeToWait) {
            return Status.RUNNING;
        }
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<Guard> copyTo(Task<Guard> task) {
        StandTask standTask = (StandTask) task;
        standTask.time = time;
        standTask.timeToWait = timeToWait;
        return standTask;
    }
}
