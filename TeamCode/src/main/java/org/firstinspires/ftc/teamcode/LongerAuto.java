package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name="Longer Test", group="Auto")

public class LongerAuto extends LinearOpMode {

    protected GyroBot robot = new GyroBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        int numberOfRings = robot.detectRings();
        telemetry.addData("Number of rings:", numberOfRings);
        telemetry.update();
        robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 300, 0.3);
        if (numberOfRings == 0) {
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 1350, 1, false);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 200, 1, false);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 500, 0.6);
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 1400, 1, false);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 700, 0.6, false);
        } else if (numberOfRings == 1) {
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 1200, 0.7, false);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 500, 0.6);
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 650, 1, false);
            robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 200, 1);
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 500, 0.6);
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 700, 0.6, false);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 500, 0.6);
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 300, 1, false);
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 500, 0.6);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 700, 1, false);
        } else if (numberOfRings == 4) {
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 2600, 1, false);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 200, 1, false);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 500, 0.6);
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 800, 1, false);
            robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 1200, 1);
        }

    }

}
