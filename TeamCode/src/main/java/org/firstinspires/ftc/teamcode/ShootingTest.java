package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.IntakeBot;
import org.firstinspires.ftc.teamcode.bot.NewCameraBot;

@Autonomous(name="Shooting Test", group="Tests")

public class ShootingTest extends LinearOpMode {

    protected NewCameraBot robot = new NewCameraBot(this); //replace IntakeBot with whichever Bot is required

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.setArmPositionNoWait(-390);
        robot.lowerIntake();
        robot.sleep(100);
        robot.highShooterSpeedThreshold = 1.345;
        robot.lowShooterSpeedThreshold = 1.343;
        robot.highShooterSpeed = -0.65;
        robot.lowShooterSpeed = -0.1;
        robot.toggleFeeder(true);
        robot.toggleShooter(true);
        robot.raiseIntake();
        robot.sleep(400);

        //driving

        robot.waitForThreshold(1.341, 1.347);
        robot.launchRing(true);
        robot.launchRing(true);
        //robot.sleep(300);
        //robot.waitForThreshold(1.587, 1.592);
        robot.launchRing(true);
        robot.sleep(100);
        robot.toggleFeeder(true);
    }

}
