package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.bot.LEDBot;

@Autonomous(name="LED Test", group="Tests")

public class LEDTest extends LinearOpMode {

    protected LEDBot robot = new LEDBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.switchPattern(0.7445);
        sleep(5000);
        robot.switchPattern(0.3795);
        sleep(5000);
        robot.switchPattern(0.4795);
        sleep(5000);
        robot.switchPattern(0.6145);
        sleep(5000);

    }

}
