package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.CustomOpMode.CustomHardwareMap;
import org.firstinspires.ftc.teamcode.Movement.MovementLib;
import org.firstinspires.ftc.teamcode.SensorUtils.odsColor;
import org.firstinspires.ftc.teamcode.Movement.Constants.*;

/**
 * Created by josephodeh and evan on 11/30/17
 */

@Autonomous(name = "Red Corner", group = "OpModes")
public class RedCornerOpMode extends LinearOpMode {
CustomHardwareMap robot = CustomHardwareMap.getInstance();
    @Override

    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        //robot.getColorServo().setPosition(0.31); //Can't move during initialization

        waitForStart();
        //ColorSensor sensor = robot.getColorSensor();

        MovementLib.lowerCServo();
        sleep(100);
        double maxScanBrightness = MovementLib.Scan(this);

//        double result = 0;
//        for (int i = 0; i < 50 ; i++) {
//            int rColor = sensor.red();
//            int bColor = sensor.blue();
//            telemetry.addData("Blue", bColor);
//            telemetry.addData("Red", rColor);
//            telemetry.update();
//            result += rColor - bColor;


        if (maxScanBrightness >= 1.5) {
            MovementLib.forward(3, .2, this);
            MovementLib.raiseCServo();
            MovementLib.forward(21, .2, this);

        }
        else {
            MovementLib.forward(-3, .2, this);
            MovementLib.raiseCServo();
            MovementLib.forward(27, .2, this);
        }

        try {
            MovementLib.rotateRobot(-90, 52.43 , 1, this);
            MovementLib.forward(18, .75, this);
            MovementLib.rotateRobot(90, 52.43 , 1, this);
            MovementLib.rotateArm(-10, .4, this);
            MovementLib.openArm();
            MovementLib.rotateArm(32, .4, this);
            MovementLib.closeArm();
            MovementLib.rotateArm(-215, .4, this);
            MovementLib.openArm();
            sleep(500);
            MovementLib.rotateArm(60, .4, this);
            MovementLib.closeArm();
            MovementLib.rotateArm(110, .4, this);
            sleep(500);
            MovementLib.forward(15, .2, this);
            MovementLib.forward(-3, .2, this);

        } catch (NullPointerException NPE) {
            telemetry.addData("Error", NPE.getMessage());
            telemetry.update();
        }
    }
}