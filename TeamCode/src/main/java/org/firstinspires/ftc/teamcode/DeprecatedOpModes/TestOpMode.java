package org.firstinspires.ftc.teamcode.DeprecatedOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.I2cDevice;

import org.firstinspires.ftc.teamcode.ModernRoboticsI2cColorSensor2;

/**
 * Created by Griffins on 9/11/2017.
 */
@TeleOp(name = "TestOpMode", group = "TeleOp")
//@Disabled
public class TestOpMode extends OpMode{
    ModernRoboticsI2cColorSensor2 colorx;
    private boolean blue;

    @Override
    public void init() {
        I2cDevice colori2c = hardwareMap.i2cDevice.get("colorsensorname");
        colorx = new ModernRoboticsI2cColorSensor2(colori2c.getI2cController(), colori2c.getPort());
    }

    @Override
    public void loop() {
        int cnumber = colorx.colorNumber();
        telemetry.addData("Colornumber: ",cnumber);

        if (cnumber == 3) {
            blue = true;
            telemetry.addData("Blue", blue);
        }
        else {
            blue = false;
            telemetry.addData("Red", !blue);

        }
    }
}