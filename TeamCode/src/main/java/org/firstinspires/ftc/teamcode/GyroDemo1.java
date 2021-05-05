package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.LEDBot;

@Autonomous(name="Gyro Demo 1", group="Demos")

public class GyroDemo1 extends LinearOpMode {

    protected LEDBot robot = new LEDBot(this); //replace IntakeBot with whichever Bot is required

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.raiseIntake();
        waitForStart();
        robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 200000, 0.4, false, true);
    }

}
