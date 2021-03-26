
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.bot.EndgameBot;
import org.firstinspires.ftc.teamcode.bot.IntakeBot;
import org.firstinspires.ftc.teamcode.bot.LEDBot;
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
    private EndgameBot robot = new EndgameBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.readPosition();
        robot.startAngle = robot.savedStartAngle;
        robot.isAuto = false;
        robot.lowerIntake();
        waitForStart();
        while (opModeIsActive()) {

            robot.driveByHand(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_button);
            if (!robot.isDown) {
                robot.launchRing(gamepad1.right_bumper);
            }
            robot.toggleShooter(gamepad1.left_bumper);
            robot.toggleWobble(gamepad1.x);
            robot.toggleFeeder(gamepad1.a);
            robot.controlWobbleArm(gamepad1.y, gamepad1.b);
            robot.intakeControl(gamepad1.dpad_down);
            robot.directionToForward(gamepad1.dpad_left);
            robot.directionToReverse(gamepad1.dpad_right);
            robot.lineUpShot(gamepad1.dpad_up, robot.towerGoalY, robot.yBlue, robot.shootingDistance);
            robot.endgame(gamepad2.a);
            robot.resetOdometry(gamepad2.b);
            if (gamepad2.x) {
                robot.resetAngle();
            }
            if (robot.isForward) {
                //telemetry.addData("Intake Direction: Forward", true);
            } else {
                //telemetry.addData("Intake Direction: Backward", true);
            }

            robot.onLoop(50, "manual drive");
            robot.getAngle();
        }
        robot.close();
    }
}