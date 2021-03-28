package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.NewCameraBot;


@Autonomous(name="Auto 1 (Rotate)", group="Auto")

public class Auto1 extends LinearOpMode {

    protected NewCameraBot robot = new NewCameraBot(this);

    double[] angles = new double[]{7, 12, -6};

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.lowerIntake();
        robot.sleep(100);
        robot.toggleFeeder(true);
        robot.toggleShooter(true);
        robot.raiseIntake();
        int numberOfRings = robot.detectRings();
        robot.sleep(500);
        //telemetry.addData("Number of rings:", numberOfRings);
        //telemetry.update();
        robot.setArmPositionNoWait(-390);
        robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 90000, 1, false, true);

        robot.goToAngle(-20 , 0.2);

        robot.goToAngle(1 , 0.14);
        robot.waitForThreshold();
        //robot.sleep(100, "before shooting");
        robot.launchRing(true);

        robot.goToAngle(5.8, 0.14);
        robot.waitForThreshold();
        //robot.sleep(100, "before shooting");
        robot.launchRing(true);

        robot.goToAngle(11.8, 0.14);
        robot.waitForThreshold();
        //robot.sleep(100, "before shooting");
        robot.launchRing(true);

        robot.goBacktoStartAngle();
        robot.toggleFeeder(true);
        robot.toggleShooter(false);
        if (numberOfRings == 0) {
            //arm to drop position
            robot.setArmPositionNoWait(-750);
            //drive to drop zone
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 69000, 0.8, false, true);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 5000, 0.8, false, true);
            //drop wobble goal
            robot.openPinch();
            robot.sleep(150, "after drop");
            //drive to pickup wobble goal
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_LEFT, 50000, 0.8, false, true);
            robot.setArmPositionNoWait(-1000);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 84000, 0.8, false, true);
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
            robot.sleep(150, "after drop");
            //park on line
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_LEFT, 10000, 0.5, false, false);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 10000, 0.5, false, false);
        } else if (numberOfRings == 1) {
            //arm to drop position
            robot.setArmPositionNoWait(-750);
            //drive to drop zone
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 30000, 0.7, false, false);
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 21000, 0.6, false, false);
            //drop wobble goal
            robot.sleep(500, "before drop");
            robot.openPinch();
            robot.sleep(500, "after drop");
            //drive to pickup wobble goal
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_LEFT, 11000, 0.6, false, false);
            robot.setArmPositionNoWait(-950);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 124000, 1, false, true);
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 30000, 0.8, false, true);
            //pickup wobble goal
            robot.closePinch();
            robot.sleep(500, "after pickup");
            robot.setArmPositionNoWait(-750);
            //drive back to drop zone
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_LEFT, 30000, 0.8, false, true);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 117000, 1, false, true);
            //robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 5000, 0.6, false, false);
            //drop wobble goal
            robot.openPinch();
            robot.sleep(150, "after drop");
            //park on line
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 5000, 1, false, false);
        } else if (numberOfRings == 4) {
            //arm to drop position
            robot.setArmPositionNoWait(-750);
            //drive to drop zone
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 66000, 1, false, true);
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 67000, 0.8, false, true);
            //drop wobble goal
            robot.openPinch();
            robot.sleep(500, "after drop");
            //drive to pickup wobble goal
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_LEFT, 62000, 0.8, false, true);
            robot.setArmPositionNoWait(-1000);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 158000, 1, false, true);
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 30000, 0.8, false, true);
            //pickup wobble goal
            robot.closePinch();
            robot.sleep(500, "after pickup");
            robot.setArmPositionNoWait(-750);
            //drive back to drop zone
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 9000, 0.7, false, false);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 160000, 1, false, true);
            //drop wobble goal
            robot.openPinch();
            robot.sleep(150, "after drop");
            //park on line
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 45000, 1, false, true);
        }
        robot.savePosition();
        robot.close();
    }

}
