package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.IntakeBot;

@Autonomous(name="DEBUG Odometry", group="Tests")

public class OdometryDebug extends LinearOpMode {

    protected IntakeBot robot = new IntakeBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.raiseIntake();
        robot.sleep(500);
        for(int i = 0; i < 500; i++) {
            robot.driveByHand(-0.5, 0.7, 0.3, false);
        }
        for(int i = 0; i < 500; i++) {
            robot.driveByHand(0.8, 0.2, 0, false);
        }
        for(int i = 0; i < 500; i++) {
            robot.driveByHand(-1, 1, 0.5, false);
        }
        for(int i = 0; i < 500; i++) {
            robot.driveByHand(0.4, -0.6, -0.4, false);
        }
        for(int i = 0; i < 500; i++) {
            robot.driveByHand(-0.3, -0.5, -0.7, false);
        }
        robot.sleep(2000);
        robot.driveToCoordinate(0, 0, 0, 1000, 0.8);
    }

}
