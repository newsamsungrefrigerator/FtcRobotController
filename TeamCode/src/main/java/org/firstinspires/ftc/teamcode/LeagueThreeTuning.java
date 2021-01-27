package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.OdometryBot;

@Autonomous(name="League Three Tuning", group="Tests")

public class LeagueThreeTuning extends LinearOpMode {

    protected OdometryBot robot = new OdometryBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.driveByGyroWithEncoders(robot.DIRECTION_FORWARD, 80000, 1, false);
    }

}
