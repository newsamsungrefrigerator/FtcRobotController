
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.bot.EndgameBot;
import org.firstinspires.ftc.teamcode.bot.FourWheelDriveBot;

/**
 * Mecanum teleop (with an optional arcade mode)
 * * Left stick controls x/y translation.
 * * Right stick controls rotation about the z axis
 * * When arcade mode is enabled (press "a"), translation direction
 * becomes relative to the field as opposed to the robot. You can
 * reset the forward heading by pressing "x".
 */
@TeleOp(name = "Read Controller Values")
public class ReadControllerValues extends LinearOpMode {
    private FourWheelDriveBot robot = new FourWheelDriveBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            robot.readControllerValues(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
        }
        robot.close();
    }
}