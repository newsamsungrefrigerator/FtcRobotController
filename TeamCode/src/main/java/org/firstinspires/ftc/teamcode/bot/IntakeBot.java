package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class IntakeBot extends FourWheelDriveBot {
    public DcMotor intake = null;

    //two positions of the wobble servo
    final double wobblePinched = 0.9;
    final double wobbleOpened = 0.5;

    final int[] armPositions = new int[]{100, 300, 600};
    int armPosIndex = 0;

    public boolean isOpen = true;
    long lastToggleDone = 0;
    long timeSinceToggle = 0;
    long lastPosSwitch = 0;
    long timeSincePosSwitch = 0;

    public IntakeBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        intake = hwMap.get(DcMotor.class, "intake");
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void startIntake() {
        intake.setPower(0.3);
    }

    public void stopIntake() {
        intake.setPower(0);
    }

}
