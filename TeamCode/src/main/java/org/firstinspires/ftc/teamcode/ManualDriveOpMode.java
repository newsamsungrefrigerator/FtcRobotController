
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.bot.OdometryBot;
import org.firstinspires.ftc.teamcode.bot.ShooterBot;
import org.firstinspires.ftc.teamcode.bot.WobbleGoalBot;

/**
 * Mecanum teleop (with an optional arcade mode)
 * * Left stick controls x/y translation.
 * * Right stick controls rotation about the z axis
 * * When arcade mode is enabled (press "a"), translation direction
 * becomes relative to the field as opposed to the robot. You can
 * reset the forward heading by pressing "x".
 */
@TeleOp(name = "Manual Drive")
public class ManualDriveOpMode extends LinearOpMode {
    private WobbleGoalBot robot = new WobbleGoalBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            robot.driveByHand(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            robot.launchRing(gamepad1.right_bumper);
            robot.toggleShooter(gamepad1.left_bumper);
            robot.toggleWobble(gamepad1.x);
            robot.onLoop();
        }
    }
}