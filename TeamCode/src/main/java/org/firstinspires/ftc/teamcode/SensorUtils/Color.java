package org.firstinspires.ftc.teamcode.SensorUtils;

import org.firstinspires.ftc.teamcode.CustomOpMode.CustomHardwareMap;
import org.firstinspires.ftc.teamcode.CustomOpMode.LinearCustomOpMode;
import org.firstinspires.ftc.teamcode.Movement.MovementLib;

public class Color {
    public static final CustomHardwareMap ROBOT = CustomHardwareMap.instance;
    private Color() {
    }
    public static <Mode extends LinearCustomOpMode> void senseColor (int hue, Mode mode){
        //if (ROBOT.getColorSensor().argb() == hue){
            MovementLib.forward(20, .1, mode);
        }
    }
//}

