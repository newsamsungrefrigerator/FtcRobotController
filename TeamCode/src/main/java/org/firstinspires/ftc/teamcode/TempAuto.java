package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.ShooterBot;

@Autonomous(name="Shooting Only", group="Auto")

public class TempAuto extends LinearOpMode {

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

        //drive to shooting position
        robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 1200, 0.7, false);
        robot.driveStraightByDistance(robot.DIRECTION_LEFT, 850, 0.6);
        for (int i = 0; i < 3; i++) {
            robot.launchRing(true);
        }
        robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 400, 0.6);

    }

}
