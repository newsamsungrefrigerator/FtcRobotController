package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.GyroBot;
import org.firstinspires.ftc.teamcode.bot.WobbleBot;


@Autonomous(name="Wobble Test", group="Auto")

public class WobbleTest extends LinearOpMode {

    protected WobbleBot robot = new WobbleBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        sleep(500);
        robot.resetServo();
        sleep(2000);
        robot.pickupWobble();
        sleep(1000);
        robot.armUp();
        sleep(1000);
        robot.holdArm();
        sleep(10000);
        robot.stopArm();
        sleep(2000);
        robot.resetServo();
        sleep(500);
        robot.resetArm();
        sleep(2000);
    }

}
