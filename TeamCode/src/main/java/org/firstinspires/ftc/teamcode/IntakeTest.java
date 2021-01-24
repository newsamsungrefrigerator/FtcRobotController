package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.IntakeBot;
import org.firstinspires.ftc.teamcode.bot.OdometryBot;

@Autonomous(name="Intake Test", group="Tests")

public class IntakeTest extends LinearOpMode {

    protected IntakeBot robot = new IntakeBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.startIntake();
        while (opModeIsActive()) {
            robot.onLoop(50, "intake running");
        }
        robot.stopIntake();
    }

}
