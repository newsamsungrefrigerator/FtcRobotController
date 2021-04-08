package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.IntakeBot;
import org.firstinspires.ftc.teamcode.bot.OdometryBot;

@Autonomous(name="Coodinate Drive Test", group="Tests")

public class CoordinateDriveTest extends LinearOpMode {

    protected IntakeBot robot = new IntakeBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.driveToCoordinate(1000, 2000, 0, 500, 0.5);
    }

}
