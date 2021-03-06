package org.firstinspires.ftc.teamcode.OpModes;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.CustomOpMode.CustomHardwareMap;
import org.firstinspires.ftc.teamcode.Movement.Timer;

import static org.firstinspires.ftc.teamcode.Movement.Constants.*;

/**
 * Created by Griffins on 9/30/2017.
 */
@TeleOp(name = "manual", group = "OpModes")
//@Disabled
public class ManualOpMode extends OpMode {
    private boolean highspeed = true;
    private boolean openArm = true;

    private static final float highSpeedmultiplyer = 1;
    private static final float lowSpeedmultiplyer = .5f;

    private float speedMultiplyer = highSpeedmultiplyer;

    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor liftMotor;
    private DcMotor leftFeeder;
    private DcMotor rightFeeder;
    private DcMotor Rotator;
    private Servo armMotor;
    private Servo colorServo;
    private double liftDirection;
    private double directionDown = 0.2;
    private double directionUp = -0.667;
    private float armPosition = 0.7f;
    private double colorPosition = 0.25;
    private int feederSwitch;


    CustomHardwareMap chwMap = CustomHardwareMap.getInstance();

    public Timer speedToggleTimer;
    public Timer armToggleTimer;

    private enum Device {
        FEEDERS, LEFTDRIVE, RIGHTDRIVE, ROTATOR,
    }

    @Override
    public void init() {
        chwMap.init(hardwareMap);
        try {
            leftMotor = chwMap.getLeft();
            rightMotor = chwMap.getRight();
            liftMotor = chwMap.getLift();
            leftFeeder = chwMap.getLeftFeeder();
            rightFeeder = chwMap.getRightFeeder();
            colorServo = chwMap.getColorServo();
            ColorSensor colorSensor = chwMap.getColorSensor();
            armMotor = chwMap.getArm();
            Rotator = chwMap.getRot();
            rightMotor.setDirection(DcMotor.Direction.FORWARD);
            leftMotor.setDirection(DcMotor.Direction.REVERSE);
        }catch(NullPointerException NPE){
            telemetry.addData("Error", NPE.getMessage());
            telemetry.addData("right", rightMotor.toString());
            telemetry.addData("left", leftMotor.toString());
        }
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        armMotor.setPosition(OPEN_ARM_POSITION);
        colorServo.setPosition(COLOR_SERVO_RAISED);

        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        speedToggleTimer = new Timer();
        armToggleTimer = new Timer();
    }

    @Override
    public void loop() {

        speedToggleTimer.updateDeltaTime();
        armToggleTimer.updateDeltaTime();

        if (gamepad1.left_stick_button && speedToggleTimer.getDeltaTime() >= 350) {
            if (highspeed) {
                highspeed = false;
            } else {
                highspeed = true;
            }
            speedToggleTimer.resetTimer();
        }

        boolean isTriggerDown = gamepad1.right_trigger != 0 || gamepad1.left_trigger != 0;

        if (highspeed && !isTriggerDown){
            speedMultiplyer = highSpeedmultiplyer;
        }else {
            speedMultiplyer = lowSpeedmultiplyer;
        }

        if (gamepad1.right_stick_button && armToggleTimer.getDeltaTime() >= 200) {
            if (!openArm) {
                armMotor.setPosition(OPEN_ARM_POSITION);
                openArm = true;
            } else {
                armMotor.setPosition(CLOSED_ARM_POSITION);
                openArm = false;
            }
            armToggleTimer.resetTimer();

        }
        if (gamepad1.y){
            feederSwitch = -1;
        }else {
            feederSwitch = 1;
        }


        telemetry.addData("Current Arm Position: ", armPosition);


        leftMotor.setPower(power(Device.LEFTDRIVE));
        rightMotor.setPower(power(Device.RIGHTDRIVE));
        leftFeeder.setPower(-power(Device.FEEDERS));
        rightFeeder.setPower(power(Device.FEEDERS) * feederSwitch);

        if (gamepad1.right_bumper) {liftDirection = directionUp;}
        else if (gamepad1.left_bumper) {liftDirection = directionDown;}
        else {liftDirection = 0;}

        liftMotor.setPower(liftDirection);
        Rotator.setPower(power(Device.ROTATOR));

        telemetry.addData("rotation power" , Rotator.getPower());
    }

    private double power(Device device){
        switch (device){
            case LEFTDRIVE: return ((-gamepad1.left_stick_y + gamepad1.left_stick_x)) * speedMultiplyer; // changed the direction on these
            case RIGHTDRIVE: return ((-gamepad1.left_stick_y - gamepad1.left_stick_x)) * speedMultiplyer; //2/6/18
            case FEEDERS: return (gamepad1.right_trigger - gamepad1.left_trigger);
            case ROTATOR: return -gamepad1.right_stick_y*.8;
            default: throw new IllegalArgumentException("There is no such motor!");
        }
    }

}
