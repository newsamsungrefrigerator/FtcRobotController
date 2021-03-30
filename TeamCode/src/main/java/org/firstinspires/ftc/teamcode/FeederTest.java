package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.IntakeBot;
import org.firstinspires.ftc.teamcode.bot.NewCameraBot;

@Autonomous(name="Feeder Test", group="Tests")

public class FeederTest extends LinearOpMode {

    protected NewCameraBot robot = new NewCameraBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.toggleFeeder(true);
        robot.toggleShooter(true);
        robot.sleep(5000);
        robot.waitForThreshold();
        robot.launchRing(true);
        robot.launchRing(true);
        robot.launchRing(true);
        sleep(10000);
    }

}
