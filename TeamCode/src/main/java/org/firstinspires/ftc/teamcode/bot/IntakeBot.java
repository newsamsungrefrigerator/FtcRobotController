package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class IntakeBot extends WobbleGoalBot{
    public DcMotor intakeMotor = null;
    public Servo intakeArm = null;

    final double feederDown = 0.16;
    final double feederUp = 0.41;

    public boolean isDown = true;
    public boolean intakeOn = false;

    long lastToggleDone2 = 0;
    long timeSinceToggle2 = 0;
    long lastToggleDone3 = 0;
    long timeSinceToggle3 = 0;

    public IntakeBot(LinearOpMode opMode) {
        super(opMode);
    }

    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        intakeMotor = hwMap.get(DcMotor.class, "intake");
        intakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeArm = hwMap.get(Servo.class, "intakeArm");
        intakeArm.setPosition(feederDown);
    }

    public void startIntake() {
        intakeMotor.setPower(1);
    }

    public void stopIntake() {
        intakeMotor.setPower(0);
    }

    public void toggleFeeder(boolean button) {
        timeSinceToggle2 = System.currentTimeMillis() - lastToggleDone2;
        if (button && timeSinceToggle2 > 400) {
            if (isDown) {
                intakeArm.setPosition(feederUp);
                isDown = false;
                lastToggleDone2 = System.currentTimeMillis();
            } else if (!isDown) {
                intakeArm.setPosition(feederDown);
                isDown = true;
                lastToggleDone2 = System.currentTimeMillis();
            }
        }
//        opMode.telemetry.addData("lastToggle", timeSinceToggle);
//        opMode.telemetry.update();
    }

    public void intakeControl(boolean button) {
        timeSinceToggle3 = System.currentTimeMillis() - lastToggleDone3;
        if (button && timeSinceToggle3 > 400) {
            if (intakeOn) {
                stopIntake();
                intakeOn = false;
                lastToggleDone3 = System.currentTimeMillis();
            } else if (!intakeOn && isDown) {
                startIntake();
                intakeOn = true;
                lastToggleDone3 = System.currentTimeMillis();
            }
        }
    }

}
