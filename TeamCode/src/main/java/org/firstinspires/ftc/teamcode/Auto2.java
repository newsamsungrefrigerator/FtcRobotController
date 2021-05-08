package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.bot.NewCameraBot;


@Autonomous(name="Auto 2 (HG, 2 WG)", group="Auto")

public class Auto2 extends LinearOpMode {

    protected NewCameraBot robot = new NewCameraBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.setArmPositionNoWait(-390);
        robot.lowerIntake();
        robot.sleep(100);
        robot.setShooterSpeed = 1.591;
        robot.highShooterSpeedThreshold = 1.6;
        robot.lowShooterSpeedThreshold = 1.595;
        robot.highShooterSpeed = -0.9;
        robot.lowShooterSpeed = -0.285;
        robot.toggleFeeder(true);
        robot.toggleShooter(true);
        robot.raiseIntake();
        int numberOfRings = robot.detectRings();
        robot.sleep(500);
        //telemetry.addData("Number of rings:", numberOfRings);
        //telemetry.update();
        robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 40000, 1, false, true);
        robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 16000, 0.8, false, false, 100);
        robot.goBacktoStartAngle();

        robot.waitForThreshold(1.586, 1.596);
        robot.launchRing(true);
        robot.launchRing(true);
        //robot.sleep(300);
        //robot.waitForThreshold(1.587, 1.592);
        robot.launchRing(true);
        robot.sleep(100);
        robot.toggleFeeder(true);

        if (numberOfRings == 0) {
            robot.toggleShooter(false);
            //arm to drop position
            robot.setArmPositionNoWait(-470);
            //drive to drop zone
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 45000, 1, false, true);
            robot.setArmPositionNoWait(-750);
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 45000, 0.8, false, false);
            //drop wobble goal
            robot.sleep(200, "before drop");
            robot.openPinch();
            robot.sleep(500, "after drop");
            //drive to pickup wobble goal
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_LEFT, 50000, 0.8, false, false);
            robot.setArmPositionNoWait(-900);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 92000, 0.8, false, true);
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 21000, 0.6, false, false);
            //pickup wobble goal
            robot.closePinch();
            robot.sleep(500, "after pickup");
            robot.setArmPositionNoWait(-750);
            //drive back to drop zone
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 90000, 0.8, false, true);
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 7000, 0.7, false, false);
            //drop wobble goal
            robot.openPinch();
            robot.sleep(300, "after drop");
            //park on line
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_LEFT, 10000, 0.5, false, false);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 15000, 0.5, false, false);
        } else if (numberOfRings == 1) {
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 8500, 0.4, false, false);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 1000, 0.3, false, false);
            robot.lowerIntake();
            robot.startIntake();
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 12000, 0.3, false, false);
            robot.raiseIntake();
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_LEFT, 10700, 0.5, false, false);

            robot.sleep(400);
            robot.stopIntake();
            robot.toggleFeeder(true);

            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 10000, 0.6, false, false);
            robot.goBacktoStartAngle();
            robot.waitForThreshold(1.586, 1.598);
            robot.launchRing(true);
            robot.sleep(100);
            robot.toggleFeeder(true);
            //arm to drop position
            robot.setArmPositionNoWait(-750);
            //drive to drop zone
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 78000, 0.9, false, true);
            robot.toggleShooter(false);
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 4800, 0.6, false, false);
            robot.goBacktoStartAngle();
            //drop wobble goal
            robot.sleep(500, "before drop");
            robot.openPinch();
            robot.sleep(500, "after drop");
            //drive to pickup wobble goal
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_LEFT, 2000, 0.5, false, false);
            robot.setArmPositionNoWait(-900);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 118000, 1, false, true);
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 12000, 0.6, false, false);
            //pickup wobble goal
            robot.closePinch();
            robot.sleep(500, "after pickup");
            robot.setArmPositionNoWait(-750);
            //drive back to drop zone
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_LEFT, 20500, 0.8, false, false);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 117000, 1, false, true);
            //robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 5000, 0.6, false, false);
            //drop wobble goal
            robot.openPinch();
            robot.sleep(150, "after drop");
            //park on line
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 5000, 1, false, false);
        } else if (numberOfRings == 4) {
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 8000, 0.5, false, false, 250);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 6000, 1, false, false, 100);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 1000, 1, false, false, 0);
            robot.lowerIntake();
            robot.startIntake();
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 7000, 0.4, false, false, 200);
            robot.sleep(100);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 5000, 0.3, false, false, 250);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 15000, 1, false, false, 250);
            robot.sleep(800);
            robot.stopIntake();
            robot.toggleFeeder(true);
            robot.sleep(300);
            robot.toggleFeeder(true);
            robot.sleep(300);
            robot.toggleFeeder(true);
            robot.sleep(300);
            robot.raiseIntake();
            robot.goToAngle(6, 0.2);
            robot.waitForThreshold(1.586, 1.598);
            robot.launchRing(true);
            robot.launchRing(true);
            robot.toggleFeeder(true);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 15000, 1, false, false, 0);
            robot.lowerIntake();
            robot.startIntake();
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 11000, 0.3, false, false, 0);
            robot.sleep(500);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 7000, 0.4, false, false, 800);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 35000, 1, false, false, 0);
            robot.stopIntake();
            robot.sleep(100);
            robot.toggleFeeder(true);
            robot.sleep(200);
            robot.raiseIntake();
            robot.goToAngle(6, 0.2);
            robot.waitForThreshold(1.586, 1.598);
            robot.launchRing(true);
            robot.launchRing(true);
            robot.launchRing(true);
            robot.toggleFeeder(true);
            //arm to drop position
            robot.setArmPositionNoWait(-750);
            //drive to drop zone
            //robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_LEFT, 15000, 1, false, false);
            robot.toggleShooter(false);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 115000, 1, false, true, 250);
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 33000, 1, false, false, 50);
            //drop wobble goal
            robot.openPinch();
            //robot.sleep(100, "after drop");
            //drive to pickup wobble goal
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_LEFT, 40000, 1, false, false, 0);
            robot.setArmPositionNoWait(-900);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 163500, 1, false, true, 0);
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 15000, 1, false, false, 0);
            //pickup wobble goal
            robot.closePinch();
            robot.sleep(300, "after pickup");
            robot.setArmPositionNoWait(-750);
            //drive back to drop zone
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 7000, 0.9, false, false, 0);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 162000, 1, false, true, 0);
            //drop wobble goal
            robot.openPinch();
            robot.sleep(100, "after drop");
            //park on line
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 45000, 1, false, true, 0);
        }
        robot.savePosition();
        robot.close();
    }

}
