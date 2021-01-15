package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.WobbleGoalBot;


@Autonomous(name="Double Wobble", group="Auto")

public class DoubleWobble extends LinearOpMode {

    protected WobbleGoalBot robot = new WobbleGoalBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.toggleShooter(true);
        int numberOfRings = robot.detectRings();
        telemetry.addData("Number of rings:", numberOfRings);
        telemetry.update();
        robot.setArmPosition(-979);
        robot.driveStraightByDistance(robot.DIRECTION_LEFT, 230, 0.8);
        robot.closePinch();
        robot.sleep(300, "close pinch");
        robot.setArmPosition(-390);
        robot.driveStraightByDistance(robot.DIRECTION_LEFT, 110, 0.8);
        robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 900, 0.6, false);
        robot.goToAngle(-1.8, 0.14);
        //robot.sleep(1000, "before shooting");
        robot.waitForThreshold();
//        for (int i = 0; i < 3; i++) {
//            robot.launchRing(true);
//            robot.goToAngle(angles[i]);
//            robot.sleep(600, "wait between shots");
//        }
        robot.launchRing(true);
        robot.goToAngle(5.50, 0.14);
//        robot.sleep(1500, "wait between shots");
        robot.waitForThreshold();
        robot.launchRing(true);
        robot.goToAngle(10.7, 0.14);
//        robot.sleep(1500, "wait between shots");
        robot.waitForThreshold();
        robot.launchRing(true);
        robot.sleep(300, "after last shot");
        robot.toggleShooter(false);
        if (numberOfRings == 0) {
            robot.goToAnglePID(-65);
            //align with target zone
            robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 450, 0.6);
            //drop wobble goal
            robot.setArmPosition(-750);
            robot.openPinch();
            robot.sleep(150, "after drop");
            robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 100, 0.8);
//            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 400, 1);
//            robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 400, 1);
        } else if (numberOfRings == 1) {
            robot.goToAnglePID(-25);
            //align with target zone
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 200, 0.7, true);
            //drop wobble goal
            robot.setArmPosition(-750);
            robot.openPinch();
            robot.sleep(150, "after drop");
            //park on line
            robot.goToAnglePID(-160);
            robot.setArmPosition(-979);
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 900, 0.8, true);
            robot.driveStraightByGyro(robot.DIRECTION_LEFT, 400, 0.6, true);
            robot.closePinch();
            robot.sleep(300, "close pinch");
            robot.setArmPosition(-750);
            robot.goToAnglePID(30);
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 900, 0.8, true);
        } else if (numberOfRings == 4) {
            //align with target zone
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 800, 0.6);
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 450, 0.9, false);
            //drop wobble goal
            robot.setArmPosition(-750);
            robot.openPinch();
            robot.sleep(150, "after drop");
            //park on line
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 350, 1, false);
        }
        robot.close();

    }

}
