package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.RotateServoBot;

@Autonomous(name="Servo Test", group="Exercises")

public class ServoBot extends LinearOpMode {
    protected RotateServoBot robot = new RotateServoBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            robot.startExtension(1);
            sleep(20000);
        }
    }
}