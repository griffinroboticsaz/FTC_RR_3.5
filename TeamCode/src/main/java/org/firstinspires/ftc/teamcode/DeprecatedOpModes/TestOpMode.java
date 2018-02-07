package org.firstinspires.ftc.teamcode.DeprecatedOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.I2cDevice;

import org.firstinspires.ftc.teamcode.CustomOpMode.CustomHardwareMap;

/**
 * Created by Griffins on 9/11/2017.
 */
@TeleOp(name = "TestOpMode", group = "TeleOp")
//@Disabled
public class TestOpMode extends OpMode{
    private boolean blue;
    CustomHardwareMap chwMap = CustomHardwareMap.getInstance();
    ColorSensor colorSensor;

    @Override
    public void init() {
        chwMap.init(hardwareMap);
        colorSensor = chwMap.getColorSensor();
    }

    @Override
    public void loop() {
//        int cnumber = colorx.colorNumber();
//        telemetry.addData("Colornumber: ",cnumber);
//
//        if (cnumber == 3) blue = true;
//        else if (cnumber == 10) blue = false;

        telemetry.addData("Blue", blue);
        telemetry.addData("Red", !blue);

        telemetry.update();
    }
}