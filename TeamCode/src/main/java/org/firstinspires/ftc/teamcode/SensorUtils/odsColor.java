package org.firstinspires.ftc.teamcode.SensorUtils;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.CustomOpMode.CustomHardwareMap;

/**
 * Created by brianroper on 1/27/18.
 */

public class odsColor {
    private static CustomHardwareMap chwmap = CustomHardwareMap.getInstance();

    private static ColorSensor colorSensor = chwmap.getColorSensor();


    public static double getColorData() {

        double maxBrightness = 0;

        double currentBrightness;


        //Get color brightness
//        for (int i = 0; i < 100; i++) {
//            currentBrightness = colorSensor.getRawLightDetected();
//            if (maxBrightness <= currentBrightness){
//                maxBrightness = currentBrightness;
//            }
//        }
//        // compare Brightness to max brightness
//        return maxBrightness;
        return maxBrightness;
    }}
