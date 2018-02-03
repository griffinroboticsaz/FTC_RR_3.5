package org.firstinspires.ftc.teamcode.Debugger;

/**
 * Created by Justin on 2/3/2018.
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.CustomOpMode.CustomHardwareMap;
import org.firstinspires.ftc.teamcode.Movement.Timer;

import static org.firstinspires.ftc.teamcode.Movement.Constants.*;


@TeleOp(name = "Debugger", group = "OpModes")
class DebugOp extends OpMode {

    private CustomHardwareMap chwmap = CustomHardwareMap.getInstance();

    private DcMotor left;
    private DcMotor right;

    private DcMotor feederRight;
    private DcMotor feederLeft;

    private DcMotor rot;
    private DcMotor lift;

    private Servo colorServo;
    private Servo armServo;

    private OpticalDistanceSensor colorSensor;

    private double cServoPos = COLOR_SERVO_RAISED;
    private double armServoPos = OPEN_ARM_POSITION;

    private final Timer colorServoTimer = new Timer();
    private final Timer armServoTimer = new Timer();

    @Override
    public void init() {
        chwmap.init(hardwareMap);
        telemetry.addData("", chwmap.message.toString());
        telemetry.update();
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
        colorServoTimer.updateDeltaTime();
        armServoTimer.updateDeltaTime();

        if(gamepad1.a && colorServoTimer.getDeltaTime() > 20 && cServoPos < 1){
            cServoPos += .01;
            colorServoTimer.resetTimer();
        }else if (gamepad1.b && colorServoTimer.getDeltaTime() > 20 && cServoPos > 0){
            cServoPos -= .01;
            colorServoTimer.resetTimer();
        }
        if(gamepad1.x && armServoTimer.getDeltaTime() > 20 && armServoPos < 1){
            armServoPos += .01;
            armServoTimer.resetTimer();
        }else if (gamepad1.y && armServoTimer.getDeltaTime() > 20 && armServoPos > 0){
            armServoPos -= .01;
            armServoTimer.resetTimer();
        }

        armServo.setPosition(armServoPos);
        colorServo.setPosition(cServoPos);


        telemetry.addData("Current Color Servo Position:" , cServoPos);
        telemetry.addData("Current arm Servo Position:" , armServoPos);
        telemetry.update();


    }

    public void logError(){

    }
}
