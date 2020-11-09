package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class LEDBot extends FourWheelDriveBot{
    public Servo LEDControl = null;
    final double pattern1 = 0.6695;

    public LEDBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);

        LEDControl = hwMap.servo.get("LED");

        LEDControl.setPosition(pattern1);
    }

    public void switchPattern(double input) {
        LEDControl.setPosition(input);
    }
}
