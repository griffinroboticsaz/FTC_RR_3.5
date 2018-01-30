/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.CustomOpMode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 * <p>
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 * <p>
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 * <p>
 * Motor channel:  Left  drive motor:        "left"
 * Motor channel:  Right drive motor:        "right"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class CustomHardwareMap {
    /* Public OpMode members. */
    public DcMotor left = null;
    public DcMotor right = null;

    public DcMotor lift = null;
    public DcMotor rot = null;

    public DcMotor leftFeeder = null;
    public DcMotor rightFeeder = null;

    public Servo arm = null;
    public Servo colorServo = null;

    public BNO055IMU gyroscope = null;

    public ColorSensor colorSensor = null;

    public static CustomHardwareMap getInstance() {
        return instance;
    }

    public static final CustomHardwareMap instance = new CustomHardwareMap();

    /* local OpMode members. */
    HardwareMap hwMap = null;

    private ElapsedTime period = new ElapsedTime();

    /* Constructor */
    private CustomHardwareMap() {

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        left = hwMap.get(DcMotor.class, "left");
        right = hwMap.get(DcMotor.class, "right");
        lift = hwMap.get(DcMotor.class, "lift");
        leftFeeder = hwMap.get(DcMotor.class, "leftFeeder");
        rightFeeder = hwMap.get(DcMotor.class, "rightFeeder");
        colorSensor = hwMap.get(ColorSensor.class, "colorSensor");
        colorServo = hwMap.get(Servo.class, "colorServo");
        arm = hwMap.get(Servo.class, "arm");
        rot = hwMap.get(DcMotor.class, "rot");

       /* BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES; //changed from radians
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;

        gyroscope = hwMap.get(BNO055IMU.class, "imu");
        gyroscope.initialize(parameters);*/

        left.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        right.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        lift.setDirection(DcMotor.Direction.REVERSE);
        rot.setDirection(DcMotor.Direction.FORWARD);

        // Set all motors to zero power
        left.setPower(0);
        right.setPower(0);
        lift.setPower(0);
        leftFeeder.setPower(0);
        rightFeeder.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rot.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.
        arm = hwMap.get(Servo.class, "arm");
        arm.setPosition(.8);


    }

    public DcMotor getLeft() {
        return left;
    }

    public DcMotor getRight() {
        return right;
    }

    public DcMotor getLift() {
        return lift;
    }

    public Servo getArm() {
        return arm;
    }

    public DcMotor getRot() {
        return rot;
    }

    public DcMotor getLeftFeeder() {
        return leftFeeder;
    }

    public DcMotor getRightFeeder() {
        return rightFeeder;
    }

    public BNO055IMU getGyroscope() {
        return gyroscope;
    }

    public Servo getColorServo() {
        return colorServo;
    }

    public ColorSensor getColorSensor() {
        return colorSensor;
    }
}