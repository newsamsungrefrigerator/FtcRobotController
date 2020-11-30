package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.bot.GyroBot;
import org.firstinspires.ftc.teamcode.bot.ShooterBot;
import org.firstinspires.ftc.teamcode.bot.WobbleGoalBot;

@Autonomous(name="Shooting Auto", group="Auto")

public class LongerAuto extends LinearOpMode {

    protected WobbleGoalBot robot = new WobbleGoalBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        int numberOfRings = robot.detectRings();
        telemetry.addData("Number of rings:", numberOfRings);
        telemetry.update();
        robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 300, 0.3);
        robot.toggleShooter(true);
        if (numberOfRings == 0) {
            //drive to shooting position
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 1200, 0.7, false);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 850, 0.6);
            for (int i = 0; i < 3; i++) {
                robot.launchRing(true);
                robot.onLoop(1300, "wait between shots");
            }
            //align with target zone
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 850, 0.6);
            //drop wobble goal
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 30, 1, false);
            robot.openArm();
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 50, 0.6, false);
            //park
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 500, 0.6);
            robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 500, 0.6);
        } else if (numberOfRings == 1) {
            //drive to shooting position
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 1200, 0.7, false);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 850, 0.6);
            for (int i = 0; i < 3; i++) {
                robot.launchRing(true);
                robot.onLoop(1300, "wait between shots");
            }
            //drop wobble goal
            robot.driveStraightByGyro(robot.DIRECTION_RIGHT, 100, 1, false);
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 400, 1, false);
            robot.openArm();
            robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 400, .8);

        } else if (numberOfRings == 4) {
            //drive to shooting position
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 1200, 0.7, false);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 850, 0.6);
            for (int i = 0; i < 3; i++) {
                robot.launchRing(true);
                robot.onLoop(1300, "wait between shots");
            }
            //align with target zone
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 850, 0.6);
            //drop wobble goal
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 800, 1, false);
            robot.openArm();
            robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 900, 1);
        }

    }

}
