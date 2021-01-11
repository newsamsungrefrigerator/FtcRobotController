package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;

public class WobbleBot extends FourWheelDriveBot {
    static final double MAX_POS     =  1.0;     // Maximum rotational position
    static final double MIN_POS     =  0.0;     // Minimum rotational position
    static final double PINCH_PINCH = 0.9;
    static final double PINCH_RELEASE = 0.5;


    //public DcMotorEx motorArm = null;
    public Servo servoPinch = null;
    public DcMotorEx leftFront = null;
    double  position = (MAX_POS - MIN_POS) / 2; // Start at halfway position
    protected LinearOpMode opMode;

    public WobbleBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        leftFront = (DcMotorEx) hwMap.get(DcMotor.class, "leftFront");
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFront.setDirection(DcMotorEx.Direction.REVERSE);
        leftFront.setPower(0);
        leftFront.setTargetPosition(0);
        servoPinch = hwMap.get(Servo.class, "servoPinch");
    }
    public void pickupWobble() {
        servoPinch.setPosition(PINCH_PINCH);
        return;
    }
    public void armUp() {
        leftFront.setPower(0.6);
        return;
    }
    public void holdArm() {
        leftFront.setPower(0.2);
    }
    public void stopArm(){
        leftFront.setPower(0);
    }

    public void dropWobble(){
        servoPinch.setPosition(PINCH_RELEASE);
        return;
    }

    public void resetServo(){
        servoPinch.setPosition(PINCH_RELEASE);

        return;
    }
    public void resetArm(){
        leftFront.setPower(0.4);
        return;
    }

}