package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.bot.NewCameraBot;

@Autonomous(name="Camera Test", group="Tests")

public class CameraTest extends LinearOpMode {

    protected NewCameraBot robot = new NewCameraBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        int numberOfRings = robot.detectRings();
        //telemetry.addData("Number of rings:", numberOfRings);
        //telemetry.update();
        robot.sleep(10000);
        robot.close();

    }

}
