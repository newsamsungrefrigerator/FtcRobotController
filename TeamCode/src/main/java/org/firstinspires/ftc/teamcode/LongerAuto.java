package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.bot.GyroBot;
import org.firstinspires.ftc.teamcode.bot.ShooterBot;

@Autonomous(name="Shooting Auto", group="Auto")

public class LongerAuto extends LinearOpMode {

    protected ShooterBot robot = new ShooterBot(this);

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
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 500, 0.6);
            for (int i = 0; i < 3; i++) {
                robot.launchRing(true);
            }
            //align with target zone
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 500, 0.6);
            //drop wobble goal
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 200, 1, false);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 200, 0.6, false);
            //park
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 500, 0.6);
            robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 700, 0.6);
        } else if (numberOfRings == 1) {
            //drive to shooting position
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 1200, 0.7, false);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 500, 0.6);
            for (int i = 0; i < 3; i++) {
                robot.launchRing(true);
            }
            //drop wobble goal
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 650, 1, false);
            robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 500, 1);

        } else if (numberOfRings == 4) {
            //drive to shooting position
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 1200, 0.7, false);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 500, 0.6);
            for (int i = 0; i < 3; i++) {
                robot.launchRing(true);
            }
            //align with target zone
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 500, 0.6);
            //drop wobble goal
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 1400, 1, false);
            robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 1100, 1);
        }

    }

}
