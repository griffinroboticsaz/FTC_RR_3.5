package org.firstinspires.ftc.teamcode.Debugger;

/**
 * Created by Justin on 2/3/2018.
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.CustomOpMode.CustomHardwareMap;
import org.firstinspires.ftc.teamcode.Movement.Timer;

import static org.firstinspires.ftc.teamcode.Movement.Constants.*;


@TeleOp(name = "Debugger", group = "OpModes")
public class DebugOp extends OpMode {

    CustomHardwareMap chwmap = CustomHardwareMap.getInstance();

    DcMotor left;
    DcMotor right;

    DcMotor feederRight;
    DcMotor feederLeft;

    DcMotor rot;
    DcMotor lift;

    Servo colorServo;
    Servo armServo;

    ColorSensor colorSensor;

    double cServoPos = COLOR_SERVO_RAISED;
    double armServoPos = OPEN_ARM_POSITION;

    Timer colorServoTimer = new Timer();
    Timer armServoTimer = new Timer();

    @Override
    public void init() {
        chwmap.init(hardwareMap);
        left = chwmap.getLeft();
        right = chwmap.getRight();
        feederLeft = chwmap.getLeftFeeder();
        feederRight= chwmap.getRightFeeder();
        rot = chwmap.getRot();
        lift = chwmap.getLift();

        colorServo = chwmap.getColorServo();
        armServo =  chwmap.getArm();

        colorSensor = chwmap.getColorSensor();
    }

    @Override
    public void loop() {

        if(gamepad1.a && colorServoTimer.getDeltaTime() > 20){
            cServoPos += .01;
            colorServoTimer.resetTimer();
        }else if (gamepad1.b && colorServoTimer.getDeltaTime() > 20){
            cServoPos -= .01;
            colorServoTimer.resetTimer();
        }
        if(gamepad1.x && armServoTimer.getDeltaTime() > 20){
            armServoPos += .01;
            armServoTimer.resetTimer();
        }else if (gamepad1.y && armServoTimer.getDeltaTime() > 20){
            armServoPos -= .01;
            armServoTimer.resetTimer();
        }


        telemetry.addData("Current Color Servo Position:" , cServoPos);
        telemetry.addData("Current arm Servo Position:" , armServoPos);
        telemetry.update();


    }

    public void logError(){

    }
}
