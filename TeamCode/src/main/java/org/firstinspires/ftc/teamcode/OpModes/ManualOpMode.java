package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
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
    boolean highspeed = true;
    boolean openArm = true;

    private static final float highSpeedmultiplyer = 1;
    private static final float lowSpeedmultiplyer = .5f;

    float speedMultiplyer = highSpeedmultiplyer;

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
    private CustomHardwareMap chwMap = CustomHardwareMap.getInstance();
    //private float armPosition = 0.7f;

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
            //colorSensor = chwMap.getColorSensor();
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

        if (gamepad1.left_stick_button && speedToggleTimer.getDeltaTime() >= 200) {
            if (highspeed) {
                highspeed = false;
                speedMultiplyer = lowSpeedmultiplyer;
            } else {
                highspeed = true;
                speedMultiplyer = highSpeedmultiplyer;
            }
            speedToggleTimer.resetTimer();
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

        if (gamepad1.a) {
            armMotor.setPosition(OPEN_ARM_POSITION);
            openArm = true;
        } else if(gamepad1.b) {
            armMotor.setPosition(CLOSED_ARM_POSITION);
            openArm = false;
        }

        //telemetry.addData("Current Arm Position: ", armPosition);


        leftMotor.setPower(power(Device.LEFTDRIVE));
        rightMotor.setPower(power(Device.RIGHTDRIVE));
        leftFeeder.setPower(-power(Device.FEEDERS));
        rightFeeder.setPower(power(Device.FEEDERS));

        if (gamepad1.right_bumper) {liftDirection = directionUp;}
        else if (gamepad1.left_bumper) {liftDirection = directionDown;}
        else {liftDirection = 0;}

        liftMotor.setPower(liftDirection);
        Rotator.setPower(power(Device.ROTATOR));

        telemetry.addData("rotation power" , Rotator.getPower());

        if (gamepad1.x) {
            colorServo.setPosition(COLOR_SERVO_RAISED);
        } else if (gamepad1.y) {
            colorServo.setPosition(COLOR_SERVO_LOWERED);
        }
    }

    private double power(Device device){
        switch (device){
            case LEFTDRIVE: return ((-gamepad1.left_stick_y + gamepad1.left_stick_x)) * speedMultiplyer;
            case RIGHTDRIVE: return ((-gamepad1.left_stick_y - gamepad1.left_stick_x)) * speedMultiplyer;
            case FEEDERS: return (gamepad1.right_trigger - gamepad1.left_trigger);
            case ROTATOR: return -gamepad1.right_stick_y;
            default: throw new IllegalArgumentException("There is no such motor!");
        }
    }

}
