package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.bot.LEDBot;
import org.firstinspires.ftc.teamcode.bot.OdometryBot;

@Autonomous(name="Odometry Test", group="Tests")

public class OdometryTest extends LinearOpMode {

    protected OdometryBot robot = new OdometryBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            robot.outputEncoders();
        }

    }

}
