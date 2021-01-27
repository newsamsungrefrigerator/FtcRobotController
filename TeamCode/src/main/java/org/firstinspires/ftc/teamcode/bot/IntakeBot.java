package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class IntakeBot extends WobbleGoalBot{
    public DcMotor intake = null;

    //HardwareMap hwMap = null;
    protected LinearOpMode opMode;

    public IntakeBot(LinearOpMode opMode) {
        super(opMode);
    }

    public void init(HardwareMap ahwMap) {
        //hwMap = ahwMap;
        super.init(ahwMap);
        intake = hwMap.get(DcMotor.class, "intake");
        intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void startIntake() {
        intake.setPower(0.8);
    }

    public void stopIntake() {
        intake.setPower(0);
    }

}
