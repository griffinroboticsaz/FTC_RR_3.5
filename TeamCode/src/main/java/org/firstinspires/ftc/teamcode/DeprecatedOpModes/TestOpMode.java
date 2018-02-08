package org.firstinspires.ftc.teamcode.DeprecatedOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.I2cDevice;

import org.firstinspires.ftc.teamcode.CustomOpMode.CustomHardwareMap;
import org.firstinspires.ftc.teamcode.Movement.MovementLib;

/**
 * Created by Griffins on 9/11/2017.
 */
@TeleOp(name = "TestOpMode", group = "TeleOp")
//@Disabled
public class TestOpMode extends LinearOpMode{
    private boolean blue;
    CustomHardwareMap chwMap = CustomHardwareMap.getInstance();
    ColorSensor colorSensor;

    @Override
    public void runOpMode() {
        //int blueNumber = colorSensor.blue();
        //int redNumber = colorSensor.red();
//        telemetry.addData("Colornumber: ",cnumber);
//
//        if (cnumber == 3) blue = true;
//        else if (cnumber == 10) blue = false;

//        telemetry.addData("Blue", blueNumber);
//        telemetry.addData("Red", redNumber);
        chwMap.init(hardwareMap);
        colorSensor = chwMap.getColorSensor();
        waitForStart();
       boolean isRed = MovementLib.scanRed(this);

            telemetry.addData("isRed: " , isRed);

        telemetry.update();
    }
}