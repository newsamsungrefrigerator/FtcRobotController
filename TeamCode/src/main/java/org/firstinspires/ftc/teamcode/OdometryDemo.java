package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.IntakeBot;
import org.firstinspires.ftc.teamcode.bot.LEDBot;

@Autonomous(name="Odometry Demo", group="Demos")

public class OdometryDemo extends LinearOpMode {

    protected IntakeBot robot = new IntakeBot(this); //replace IntakeBot with whichever Bot is required

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.raiseIntake();
        waitForStart();
        robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 120000, 0.5, false, false);
        robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 121500, 0.5, false, false);
    }

}
