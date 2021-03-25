package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Servo Test", group="Exercises")

public class ServoBot extends LinearOpMode {
    private RotateServo robot = new RotateServo(this);

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