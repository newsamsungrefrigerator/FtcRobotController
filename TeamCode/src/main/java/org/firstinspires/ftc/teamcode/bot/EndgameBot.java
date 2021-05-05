package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class EndgameBot extends LEDBot{

    final double optimalDistance = 145000;

    public EndgameBot(LinearOpMode opMode) {
        super(opMode);
    }

    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
    }

    public void endgame(boolean button) {
        if (button) {
            isAuto = true;
            setShooterSpeed = 1.382;
            highShooterSpeedThreshold = 1.385;
            lowShooterSpeedThreshold = 1.38;
            raiseIntake();
            goToAnglePID(0);
            toggleFeeder(true);
            toggleShooter(true);
            controlWobbleArm(true, false);
            driveByGyroWithEncodersVertical(DIRECTION_BACKWARD, 55000, 1, false, true);
            driveWithEncodersHorizontal(DIRECTION_LEFT, 7000, 0.3, false);

            goToAngle(-20 , 0.2);

            goToAngle(-0.5 , 0.14);
            waitForThreshold();
            launchRing(true);

            goToAngle(5.8, 0.14);
            waitForThreshold();
            launchRing(true);

            goToAngle(11.8, 0.14);
            waitForThreshold();
            launchRing(true);

            leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            toggleFeeder(true);
            leftFront.setPower(-0.9);
            rightFront.setPower(0.9);
            leftRear.setPower(-0.9);
            rightRear.setPower(0.9);
            onLoop(800, "180 degrees");

            toggleShooter(false);

            controlWobbleArm(true, false);

            driveWithEncodersVertical(DIRECTION_FORWARD, 90000, 1, true);

            controlWobbleArm(false, true);
            sleep(400);
            toggleWobble(true);
            driveWithEncodersVertical(DIRECTION_BACKWARD, 106000, 1, true);
        }
    }

    public void goToShoot(){
        toggleFeeder(true);
        double angleToGoal = Math.toDegrees(Math.atan2(towerGoalX - xBlue, towerGoalY - yBlue));
        double xTarget = (Math.cos(angleToGoal) * optimalDistance) + towerGoalX;
        double yTarget = (Math.sin(angleToGoal) * optimalDistance) + towerGoalY;
        driveToCoordinate(xTarget, yTarget, angleToGoal, 1000, 0.8);
    }

}
