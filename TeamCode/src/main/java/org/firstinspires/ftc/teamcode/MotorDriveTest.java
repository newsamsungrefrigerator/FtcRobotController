package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.WobbleGoalBot;

@Autonomous(name="Drive Test", group="Tests")

public class MotorDriveTest extends LinearOpMode {

    protected WobbleGoalBot robot = new WobbleGoalBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 500, 0.3);
        robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 500, 0.3);
        robot.driveStraightByDistance(robot.DIRECTION_LEFT, 500, 0.3);
        robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 500, 0.3);
    }

}
