package org.firstinspires.ftc.teamcode.Movement;

/**
 * Created by brianroper on 1/27/18.
 */

public class Timer {
    private long thisTime, lastTime, deltaTime;

    public Timer() {
        thisTime = System.currentTimeMillis();
        lastTime = 0;
        deltaTime = 0;
    }

    public void updateDeltaTime(){
        lastTime = thisTime;
        thisTime = System.currentTimeMillis();
        deltaTime += thisTime - lastTime;
    }

    public void resetTimer(){
        deltaTime = 0;
    }

    public long getDeltaTime() {
        return deltaTime;
    }
}
