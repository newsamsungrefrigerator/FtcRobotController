package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class EndgameBot extends LEDBot{

    long startTime = 0;
    long timeElapsed = 0;

    public EndgameBot(LinearOpMode opMode) {
        super(opMode);
    }

    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        startTime = System.currentTimeMillis();
    }

    public void endgame(boolean button) {
        timeElapsed = System.currentTimeMillis() - startTime;
        if (button && timeElapsed > 88 * 1000) {
            toggleShooter(true);
            controlWobbleArm(true, false);
            driveByGyroWithEncodersVertical(DIRECTION_BACKWARD, 30000, 0.8, false, true);
            toggleFeeder(true);
            driveByGyroWithEncodersVertical(DIRECTION_FORWARD, 95000, 1, false, true);

            goToAngle(-20 , 0.2);

            goToAngle(1 , 0.14);
            waitForThreshold();
            sleep(300, "before shooting");
            launchRing(true);

            goToAngle(5.8, 0.14);
            waitForThreshold();
            sleep(300, "before shooting");
            launchRing(true);

            goToAngle(11.8, 0.14);
            waitForThreshold();
            sleep(300, "before shooting");
            launchRing(true);

            goBacktoStartAngle();
            toggleShooter(false);

            controlWobbleArm(true, false);

            driveByGyroWithEncodersVertical(DIRECTION_BACKWARD, 95000, 1, false, true);

            controlWobbleArm(false, true);
            sleep(200);
            toggleWobble(true);
            driveByGyroWithEncodersVertical(DIRECTION_FORWARD, 95000, 1, false, true);
        }
    }

}
