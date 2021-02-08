package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.GyroBot;
import org.firstinspires.ftc.teamcode.bot.OdometryBot;

@Autonomous(name="Odometry Test", group="Tests")

public class RadiusCalculator extends LinearOpMode {

    protected GyroBot robot = new GyroBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.fullRotate(0.2);
        while (opModeIsActive()) {
            robot.onLoop(50, "calculate");
        }

    }

}
