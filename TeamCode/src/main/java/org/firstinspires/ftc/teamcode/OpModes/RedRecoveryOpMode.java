package org.firstinspires.ftc.teamcode.OpModes;

/**
 * Created by josephodeh on 11/27/17.
 */

//@Autonomous(name = "Red Recovery", group = "OpModes")
//public class RedRecoveryOpMode extends LinearOpMode {
//CustomHardwareMap robot = CustomHardwareMap.getInstance();
//    @Override
//    public void runOpMode() {
//        robot.init(hardwareMap);
//        telemetry.addData("Status", "Initialized");
//        telemetry.update();
//        robot.getColorServo().setPosition(0.31);
//
//        waitForStart();
//        //ColorSensor sensor = robot.getColorSensor();
//
//        MovementLib.lowerCServo();
//        sleep(100);
//        double result = 0;
//        for (int i = 0; i < 50 ; i++) {
//            int rColor = sensor.red();
//            int bColor = sensor.blue();
//            telemetry.addData("Blue", bColor);
//            telemetry.addData("Red", rColor);
//            telemetry.update();
//            result += rColor - bColor;
//        }
//
//        if (result/50 > 0) {
//            MovementLib.forward(3, .2, this);
//            MovementLib.raiseCServo();
//            MovementLib.forward(29, .2, this);
//
//        }
//        else {
//            MovementLib.forward(-3, .2, this);
//            MovementLib.raiseCServo();
//            MovementLib.forward(35, .2, this);
//        }
//
//        try {
//            MovementLib.rotateRobot(90, 52.43, .75, this);
//            MovementLib.rotateArm(-10, .4, this);
//            MovementLib.openArm();
//            MovementLib.rotateArm(32, .4, this);
//            MovementLib.closeArm();
//            MovementLib.rotateArm(-215, .4, this);
//            MovementLib.openArm();
//            sleep(500);
//            MovementLib.rotateArm(60, .4, this);
//            MovementLib.closeArm();
//            MovementLib.rotateArm(110, .4, this);
//            sleep(500);
//            MovementLib.forward(15, .2, this);
//            MovementLib.forward(-3, .2, this);
//
//        } catch (NullPointerException NPE) {
//            telemetry.addData("Error", NPE.getMessage());
//            telemetry.update();
//        }
//    }
//}