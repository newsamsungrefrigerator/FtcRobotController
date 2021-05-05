package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.IntakeBot;
import org.firstinspires.ftc.teamcode.bot.LEDBot;
import org.firstinspires.ftc.teamcode.bot.OdometryBot;

@Autonomous(name="Coodinate Drive Test", group="Tests")

public class CoordinateDriveTest extends LinearOpMode {

    protected LEDBot robot = new LEDBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.driveToCoordinate(80000, 120000, 90, 1000, 0.8);
        robot.switchPattern(0.7145);
        sleep(10000);
    }

}
