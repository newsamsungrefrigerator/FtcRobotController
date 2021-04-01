package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.NewCameraBot;
import org.firstinspires.ftc.teamcode.bot.ShooterBot;
import org.firstinspires.ftc.teamcode.bot.WobbleGoalBot;

@Autonomous(name="Shooting Only", group="Auto")

public class TempAuto extends LinearOpMode {

    protected NewCameraBot robot = new NewCameraBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.lowerIntake();
        robot.sleep(100);
        robot.toggleFeeder(true);
        robot.toggleShooter(true);
        robot.raiseIntake();
        robot.sleep(3000);
        robot.goToAngle(-20 , 0.2);

        robot.goToAngle(1 , 0.14);
        robot.waitForThreshold();
        //robot.sleep(100, "before shooting");
        robot.launchRing(true);

        robot.goToAngle(5.8, 0.14);
        robot.waitForThreshold();
        //robot.sleep(100, "before shooting");
        robot.launchRing(true);

        robot.goToAngle(11.8, 0.14);
        robot.waitForThreshold();
        //robot.sleep(100, "before shooting");
        robot.launchRing(true);

        robot.goBacktoStartAngle();
        robot.toggleFeeder(true);
        robot.toggleShooter(false);
        robot.close();
    }

}
