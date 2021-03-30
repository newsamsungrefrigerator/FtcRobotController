package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.IntakeBot;

@Autonomous(name="TemplateAuto", group="Template")

public class TemplateAuto extends LinearOpMode {

    protected IntakeBot robot = new IntakeBot(this); //replace IntakeBot with whichever Bot is required

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        //call methods here
    }

}
