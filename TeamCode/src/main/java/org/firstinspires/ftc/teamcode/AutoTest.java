package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.bot.GyroBot;
import org.firstinspires.ftc.teamcode.bot.WobbleGoalBot;


@Autonomous(name="Auto Test", group="Auto")

public class AutoTest extends LinearOpMode {

    protected WobbleGoalBot robot = new WobbleGoalBot(this);

    double[] angles = new double[]{7, 12, -6};

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.toggleShooter(true);
        robot.setArmPosition(-969);
        int numberOfRings = robot.detectRings();
        telemetry.addData("Number of rings:", numberOfRings);
        telemetry.update();
        robot.driveStraightByDistance(robot.DIRECTION_LEFT, 230, 0.5);
        robot.closePinch();
        robot.sleep(300, "close pinch");
        robot.setArmPosition(-390);
        robot.driveStraightByDistance(robot.DIRECTION_LEFT, 140, 0.5);
        robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 900, 0.7, false);
        robot.goToAngle(-1.8);
        //robot.sleep(1000, "before shooting");
        robot.waitForThreshold();
//        for (int i = 0; i < 3; i++) {
//            robot.launchRing(true);
//            robot.goToAngle(angles[i]);
//            robot.sleep(600, "wait between shots");
//        }
        robot.launchRing(true);
        robot.goToAngle(6);
//        robot.sleep(1500, "wait between shots");
        robot.waitForThreshold();
        robot.launchRing(true);
        robot.goToAngle(11);
//        robot.sleep(1500, "wait between shots");
        robot.waitForThreshold();
        robot.launchRing(true);
        robot.goBacktoStartAngle();
        robot.toggleShooter(false);
        if (numberOfRings == 0) {
            //align with target zone
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 850, 0.6);
            //drop wobble goal
            robot.setArmPosition(-750);
            robot.openPinch();
            robot.sleep(150, "after drop");
            robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 200, 0.8);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 400, 1);
            robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 400, 1);
        } else if (numberOfRings == 1) {
            //align with target zone
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 400, 0.5, false);
            robot.driveStraightByGyro(robot.DIRECTION_RIGHT, 200, 0.5, false);
            //drop wobble goal
            robot.setArmPosition(-750);
            robot.openPinch();
            robot.sleep(150, "after drop");
            //park on line
            robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 230, 0.7);
        } else if (numberOfRings == 4) {
            //align with target zone
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 700, 0.6);
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 700, 0.9, false);
            //drop wobble goal
            robot.setArmPosition(-750);
            robot.openPinch();
            robot.sleep(150, "after drop");
            //park on line
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 700, 1, false);
        }
        robot.close();

    }

}
