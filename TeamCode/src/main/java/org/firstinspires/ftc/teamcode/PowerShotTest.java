package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.ShooterBot;
import org.firstinspires.ftc.teamcode.bot.WobbleGoalBot;

@Autonomous(name="Power Shot Test", group="Tests")

public class PowerShotTest extends LinearOpMode {

    protected WobbleGoalBot robot = new WobbleGoalBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.lowerIntake();
        robot.setArmPositionNoWait(-390);
        robot.sleep(100);
        robot.toggleFeeder(true);
        robot.toggleShooter(true);
        robot.raiseIntake();
        robot.sleep(3000);
        robot.launchRing(true);
        robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_LEFT, 10000, 0.4, false, false);
        robot.launchRing(true);
        robot.driveByGyroWithEncodersHorizontal(robot.DIRECTION_LEFT, 10000, 0.4, false, false);
        robot.sleep(200);
        robot.launchRing(true);
        //robot.shootPowerShots();
    }

}
