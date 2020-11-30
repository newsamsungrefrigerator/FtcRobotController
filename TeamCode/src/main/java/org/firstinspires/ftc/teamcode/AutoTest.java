package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.bot.GyroBot;
import org.firstinspires.ftc.teamcode.bot.WobbleGoalBot;


@Autonomous(name="Auto Test", group="Auto")

public class AutoTest extends LinearOpMode {

    protected WobbleGoalBot robot = new WobbleGoalBot(this);

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
            robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 700, 0.6);
        } else if (numberOfRings == 1) {
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 1200, 0.7, false);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 500, 0.6);
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 650, 1, false);
            robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 500, 1);
        } else if (numberOfRings == 4) {
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 2600, 1, false);
            robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 1100, 1);
        }
        robot.close();

    }

}
