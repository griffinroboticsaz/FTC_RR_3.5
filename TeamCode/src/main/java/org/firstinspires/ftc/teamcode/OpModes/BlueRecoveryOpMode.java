package org.firstinspires.ftc.teamcode.OpModes;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.CustomOpMode.CustomHardwareMap;
import org.firstinspires.ftc.teamcode.Movement.MovementLib;
import org.firstinspires.ftc.teamcode.SensorUtils.odsColor;
import org.firstinspires.ftc.teamcode.Movement.Constants.*;

/**
 * Created by josephodeh on 11/28/17.
 */
//Change movements to blue side directions

@Autonomous(name = "Blue Recovery", group = "OpModes")
public class BlueRecoveryOpMode extends LinearOpMode {
CustomHardwareMap robot = CustomHardwareMap.getInstance();
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        robot.getColorServo().setPosition(0.31);

        waitForStart();

        MovementLib.lowerCServo();
        sleep(100);

//        double result = 0;
        //for (int i = 0; i < 50 ; i++) {
//        while (!isStopRequested()){
//            int rColor = sensor.red();
//            int bColor = sensor.blue();
//            telemetry.addData("Blue", bColor);
//            telemetry.addData("Red", rColor);
//            telemetry.update();
//            result += bColor - rColor;
//        }

        if (odsColor.getColorData() >= 1.5) {
            MovementLib.forward(3, .2, this);
            MovementLib.raiseCServo();
            MovementLib.forward(-30, .2, this);


        }
        else {
            MovementLib.forward(-3, .2, this);
            MovementLib.raiseCServo();
            MovementLib.forward(-24, .2, this);
        }

        try {
            MovementLib.rotateRobot(90, 52.43, .75, this);
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
            MovementLib.forward(20, .2, this);
            MovementLib.forward(-3, .2, this);

        } catch (NullPointerException NPE) {
            telemetry.addData("Error", NPE.getMessage());
            telemetry.update();
        }
    }
}
