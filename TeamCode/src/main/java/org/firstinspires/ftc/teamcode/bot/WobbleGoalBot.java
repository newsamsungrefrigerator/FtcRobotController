package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class WobbleGoalBot extends ShooterBot {
    public Servo wobblePinch = null;
    public DcMotor wobbleArm = null;

    //two positions of the wobble servo
    final double wobblePinched = 0.9;
    final double wobbleOpened = 0.5;

    final int[] armPositions = new int[]{0, 300, 600};
    int armPosIndex = 0;

    public boolean isOpen = true;
    long lastToggleDone = 0;
    long timeSinceToggle = 0;

    public WobbleGoalBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        wobblePinch = hwMap.get(Servo.class, "wobblePinch");
        wobblePinch.setPosition(wobbleOpened);
        wobbleArm = hwMap.get(DcMotor.class, "wobbleArm");
        wobbleArm.setPower(0);
        wobbleArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wobbleArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    //call openPinch() to open the arm
    public void openPinch() {
        wobblePinch.setPosition(wobbleOpened);
    }

    //call closeArm() to close the arm
    public void closePinch() {
        wobblePinch.setPosition(wobblePinched);
    }

    public void toggleWobble(boolean button) {
        timeSinceToggle = System.currentTimeMillis() - lastToggleDone;
        if (button && timeSinceToggle > 200) {
            if (isOpen) {
                wobblePinch.setPosition(wobblePinched);
                isOpen = false;
            } else {
                wobblePinch.setPosition(wobbleOpened);
                isOpen = true;
            }
            lastToggleDone = System.currentTimeMillis();
        }
    }

    public void raiseArm() {
        wobbleArm.setPower(0.3);
        wobbleArm.setTargetPosition(500);
        sleep(1000, "wobble raise");
        wobbleArm.setPower(0);
    }
    public void lowerArm() {
        wobbleArm.setPower(0.1);
        wobbleArm.setTargetPosition(200);
        sleep(500, "wobble lower");
        wobbleArm.setPower(0);
    }

    public void controlWobbleArm(boolean buttonY, boolean buttonB) {
        wobbleArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if (buttonY) {
            if (armPosIndex < 2) {
                wobbleArm.setPower(0.5);
                wobbleArm.setTargetPosition(armPositions[armPosIndex]);
                armPosIndex ++;
            }
        }
        if (buttonB) {
            if (armPosIndex > 0) {
                wobbleArm.setPower(0.5);
                wobbleArm.setTargetPosition(armPositions[armPosIndex]);
                armPosIndex --;
            }
        }
    }

    public void setArmPosition(int position) {
        wobbleArm.setPower(0.2);
        wobbleArm.setTargetPosition(position);
        wobbleArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (this.opMode.opModeIsActive() && wobbleArm.isBusy()) {
            sleep(50, "set wobble arm position");
        }
        wobbleArm.setPower(0.5);
    }
}
