package org.firstinspires.ftc.teamcode.Movement;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.CustomOpMode.CustomHardwareMap;
import org.firstinspires.ftc.teamcode.SensorUtils.GyroUtils;
import org.firstinspires.ftc.teamcode.SensorUtils.odsColor;

import static org.firstinspires.ftc.teamcode.Movement.Constants.*;

public class MovementLib {
    public static final CustomHardwareMap ROBOT = CustomHardwareMap.instance;

    private MovementLib() {
    }

    public static void forward(double inches, double speed, LinearOpMode mode) {
        mode.telemetry.addData("Working!", "");
        int counts = EncoderUtils.calcCounts(inches);

        ROBOT.getRight().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ROBOT.getLeft().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        ROBOT.getRight().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ROBOT.getLeft().setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        ROBOT.getRight().setTargetPosition(-counts * 4);
        ROBOT.getLeft().setTargetPosition(-counts * 4);

        ROBOT.getRight().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ROBOT.getLeft().setMode(DcMotor.RunMode.RUN_TO_POSITION);

        ROBOT.getRight().setPower(speed);
        ROBOT.getLeft().setPower(speed);

        while (ROBOT.getRight().isBusy() && ROBOT.getLeft().isBusy() && !mode.isStopRequested()) {
            mode.telemetry.addData("Counts", counts);
            mode.telemetry.addData("Counts Left", ROBOT.getLeft().getCurrentPosition());
            mode.telemetry.addData("Counts Right", ROBOT.getRight().getCurrentPosition());
            mode.telemetry.update();
        }

        ROBOT.getRight().setPower(0);
        ROBOT.getLeft().setPower(0);

        ROBOT.getRight().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ROBOT.getLeft().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Rotates the robot the number of degrees given using a gyro. As the robot approaches the target angle, the rate of rotation slows<p>
     * to ensure the robot does not over-rotate. The rate of deceleration is determined by the derivative of a sin function with the period<p>
     * double the target angle using {@link GyroUtils#calcTurnSpeed(double, double)}. Tbe function (@link GyroUtils#calcAngleTurned(CustomHardwareMap, long)})<p>that controls the deceleration is roughly
     * cos ((90/target) * angleTurned) {@link GyroUtils#calcAngleTurned(CustomHardwareMap, long)}. This also takes a generic "Mode" as an <p>
     * argument. "Mode" must extend {@link LinearOpMode}. This ensures that whatever OpMode is passed, it with have <p>
     * the fields from {@link LinearOpMode}.<p>
     * A custom hardware map must be passed ({@link  CustomHardwareMap}) so that the gyro and the driveMotors can be accessed.
     *
     * @param angle  the number of degrees to be turned by the robot
     * @param speed  the speed at which to turn the robot {0.0, 1.0}
     * @param mode   the OpMode using the method
     * @throws IllegalArgumentException Thrown if the argument angles = 0.
     * @see GyroUtils#calcTurnSpeed(double, double)
     * @see GyroUtils#calcAngleTurned(CustomHardwareMap, long)
     */
    //Todo: redo JavaDocs
    public static void rotate(double angle, double speed, LinearOpMode mode) {
        if (angle == 0) {
            throw new IllegalArgumentException("Angle cannot be 0!");
        }

        double currentAngle = 0;

        long lastTime = 0;
        long currentTime;
        long deltaTime;

        String motorReversed;
        DcMotor right = ROBOT.getRight();
        DcMotor left = ROBOT.getLeft();
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        double motorPower;

        if (angle != Math.abs(angle)) {
            motorReversed = "left";
        } else {
            motorReversed = "right";
        }
        while (currentAngle <= Math.abs(angle)) {
            currentTime = System.currentTimeMillis();
            if (lastTime == 0) {
                lastTime = currentTime;
                deltaTime = 0;
            } else {
                deltaTime = currentTime - lastTime;
                lastTime = currentTime;
            }
            currentAngle += GyroUtils.calcAngleTurned(ROBOT, deltaTime);
            motorPower = ((GyroUtils.calcTurnSpeed(currentAngle, angle))) * speed < .25 ? .25 : (GyroUtils.calcTurnSpeed(currentAngle, angle) * speed);
            mode.telemetry.addData("speed", motorPower);
            mode.telemetry.addData("gyro", currentAngle);
            mode.telemetry.addData("start", speed);

            mode.telemetry.update();
            if (motorReversed.equals("left")) {
                left.setPower(motorPower);
                right.setPower(-motorPower);
            } else {
                right.setPower(motorPower);
                left.setPower(-motorPower);
            }
            if (Math.abs(currentAngle) >= Math.abs(angle)) {
                right.setPower(0);
                left.setPower(0);

                right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                break;
            }
            if (mode.isStopRequested()) {
                break;
            }
        }

        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    public static void rotateRobot(double degrees, double Radius, double speedMultiplier, LinearOpMode mode) {
        //Step One: Distance = Pie * Radius * degrees / 180
        double Distance = (Math.PI * Radius * degrees) / 180;

        //Step Two: EncoderCounts = Distance * CountsPerDegree / Circumference * (Radius * Gear Ratio)
        int counts =(int)(EncoderUtils.calcCounts(Distance) * 1.4f);

        //Step Three: Set New Position
        DcMotor leftMotor = ROBOT.left;

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

//        if(Math.abs(degrees) == degrees){
//            leftMotor.setTargetPosition(counts);
//        } else {
//            leftMotor.setTargetPosition(-counts );
//        }

        leftMotor.setTargetPosition(counts);
        if(Math.abs(degrees) == degrees)
            leftMotor.setDirection(DcMotor.Direction.REVERSE);


        //Step Four: Tell Motors to get to position
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Step Four A: Set speed
        leftMotor.setPower(speedMultiplier);

        while (leftMotor.isBusy() && !mode.isStopRequested()) {
            mode.telemetry.addData("Counts", counts);
            mode.telemetry.addData("Counts Left", ROBOT.getLeft().getCurrentPosition());
            mode.telemetry.update();
        }

        leftMotor.setPower(0);
        leftMotor.setDirection(DcMotor.Direction.FORWARD);


    }
    public static void rotateArm(double angle, double speed, LinearOpMode mode) {
        ROBOT.getRot().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ROBOT.getRot().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int countsPerRotation = 1120;
        int counts = (int) (5 * angle * countsPerRotation / 360);
        ROBOT.getRot().setTargetPosition(counts);
        ROBOT.getRot().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ROBOT.getRot().setPower(speed);
        while (ROBOT.getRot().isBusy() && !mode.isStopRequested()) {
            mode.telemetry.addData("Moving rotation arm to", counts);
            mode.telemetry.addData("Current Position", ROBOT.getRot().getCurrentPosition());
            mode.telemetry.update();
        }
        ROBOT.getRot().setPower(0);
        ROBOT.getRot().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
//
//    public static double Scan(LinearOpMode mode) {
//        double currentBrightness;
//        double maxBrightness = 0;
//        for (int i = 0; i < 10; i++) {
//            ROBOT.getColorServo().setPosition(ROBOT.getColorServo().getPosition() - 0.004);
//            mode.sleep(100);
//            mode.telemetry.addData("Color Data", maxBrightness);
//            currentBrightness = odsColor.getColorData();
//            mode.telemetry.update();
//            if (currentBrightness > maxBrightness) {
//                maxBrightness = currentBrightness;
//            }
//        }
//        return maxBrightness;
//    }

    public static boolean scanRed(LinearOpMode mode){
        Timer scanTimer = new Timer();
        ColorSensor colorSensor = ROBOT.getColorSensor();
        int redColor = 0;
        for(int i = 0; i < 20; i++) {
            mode.sleep(25);
            redColor += colorSensor.red();
            redColor -= colorSensor.blue();
        }
        return redColor > 0;

    }

    public static void closeArm() {
        ROBOT.getArm().setPosition(CLOSED_ARM_POSITION);
    }

    public static void openArm() {
        ROBOT.getArm().setPosition(OPEN_ARM_POSITION);
    }

    public static void lowerCServo() {
        ROBOT.getColorServo().setPosition(COLOR_SERVO_LOWERED);
    }

    public static void raiseCServo() {
        ROBOT.getColorServo().setPosition(COLOR_SERVO_RAISED);
    }
}
