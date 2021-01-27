package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.IntakeBot;
import org.firstinspires.ftc.teamcode.bot.NewCameraBot;


@Autonomous(name="Auto Test", group="Auto")

public class AutoTest extends LinearOpMode {

    protected NewCameraBot robot = new NewCameraBot(this);

    double[] angles = new double[]{7, 12, -6};

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.toggleShooter(true);
        int numberOfRings = robot.detectRings();
        telemetry.addData("Number of rings:", numberOfRings);
        telemetry.update();
        robot.setArmPositionNoWait(-390);
        robot.toggleFeeder(true);
        robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 95000, 1, false, true);

        robot.goToAngle(-1.8 , 0.14);
        robot.waitForThreshold();
        robot.sleep(300, "before shooting");
        robot.launchRing(true);

        robot.goToAngle(5.5, 0.14);
        robot.waitForThreshold();
        robot.sleep(300, "before shooting");
        robot.launchRing(true);

        robot.goToAngle(10.7, 0.14);
        robot.waitForThreshold();
        robot.sleep(300, "before shooting");
        robot.launchRing(true);

        robot.goBacktoStartAngle();
        robot.toggleShooter(false);
        if (numberOfRings == 0) {
            //align with target zone
            robot.setArmPositionNoWait(-750);
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 69000, 0.8, false, true);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 5000, 0.8, false, true);
            //drop wobble goal
            robot.openPinch();
            robot.sleep(150, "after drop");
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_LEFT, 50000, 0.8, false, true);
            robot.setArmPositionNoWait(-1000);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 90000, 0.8, false, true);
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 21000, 0.6, false, false);
            robot.closePinch();
            robot.sleep(500, "after drop");
            robot.setArmPositionNoWait(-750);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 90000, 0.8, false, true);
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 5000, 0.4, false, false);
            robot.openPinch();
            robot.sleep(150, "after drop");
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_LEFT, 10000, 0.5, false, false);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 10000, 0.5, false, false);
        } else if (numberOfRings == 1) {
            //align with target zone
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 20000, 1, false, true);
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 20000, 0.8, false, true);
            //drop wobble goal
            robot.setArmPosition(-750);
            robot.openPinch();
            robot.sleep(150, "after drop");
            //park on line
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 15000, 0.7, false, true);
        } else if (numberOfRings == 4) {
            //align with target zone
            robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_RIGHT, 80000, 0.8, false, true);
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 52000, 1, false, true);
            //drop wobble goal
            robot.setArmPosition(-750);
            robot.openPinch();
            robot.sleep(150, "after drop");
            //park on line
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 35000, 1, false, true);
        }
        robot.close();

    }

}
