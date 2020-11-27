package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class WobbleGoalBot extends ShooterBot {
    public Servo wobbleArm = null;

    //two positions of the pusher servo
    final double wobbleRetracted = 0.35;
    final double wobbleExtended = 0.53;

    public WobbleGoalBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        wobbleArm = hwMap.get(Servo.class, "WobbleGoal");
        wobbleArm.setPosition(wobbleRetracted);
    }

    public void openArm() {
        wobbleArm.setPosition(wobbleExtended);
    }

    public void closeArm() {
        wobbleArm.setPosition(wobbleRetracted);
    }
}
