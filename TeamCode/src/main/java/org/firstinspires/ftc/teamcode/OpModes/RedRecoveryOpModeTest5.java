package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.CustomOpMode.CustomHardwareMap;
import org.firstinspires.ftc.teamcode.Movement.MovementLib;

@Autonomous(name = "Red Recovery Test 5", group = "OpModes")
public class RedRecoveryOpModeTest5 extends LinearOpMode {
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
        sleep(200);

//        double result = 0;
//        for (int i = 0; i < 50 ; i++) {
//            int rColor = sensor.red();
//            int bColor = sensor.blue();
//            telemetry.addData("Blue", bColor);
//            telemetry.addData("Red", rColor);
//            telemetry.update();
//            result += rColor - bColor;
//        }
        boolean isRed = MovementLib.scanRed(this);


        if (isRed) {
            MovementLib.forward(3, .2, this);
            MovementLib.raiseCServo();
            MovementLib.forward(29, .2, this);
        }
        else {
            MovementLib.forward(-3, .2, this);
            MovementLib.raiseCServo();
            MovementLib.forward(35, .2, this);
        }

        try {
            MovementLib.rotateRobot(-90, 52.43, .75, this);
            MovementLib.rotateArm(-25, .1, this);
            MovementLib.openArm();
            MovementLib.rotateArm(32, .1, this);
            MovementLib.closeArm();
            MovementLib.rotateArm(215, .1, this);   //change signs
            MovementLib.openArm();
            sleep(500);
            MovementLib.rotateArm(-60, .1, this);
//            MovementLib.closeArm();
//            MovementLib.rotateArm(110, .1, this);
//            sleep(500);
//            MovementLib.forward(15, .2, this);
//            MovementLib.forward(-3, .2, this);

        } catch (NullPointerException NPE) {
            telemetry.addData("Error", NPE.getMessage());
            telemetry.update();
        }
    }
}