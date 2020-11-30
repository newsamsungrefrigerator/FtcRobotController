package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class WobbleGoalBot extends ShooterBot {
    public Servo wobbleArm = null;

    //two positions of the wobble servo
    final double wobbleRetracted = 0.25;
    final double wobbleExtended = 0.53;

    boolean isOpen = false;

    public WobbleGoalBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        wobbleArm = hwMap.get(Servo.class, "WobbleGoal");
        wobbleArm.setPosition(wobbleRetracted);
    }

    //call openArm() to open the arm
    public void openArm() {
        wobbleArm.setPosition(wobbleExtended);
    }

    //call closeArm() to close the arm
    public void closeArm() {
        wobbleArm.setPosition(wobbleRetracted);
    }

    public void toggleWobble(boolean button) {
        if (button) {
            if (isOpen) {
                wobbleArm.setPosition(wobbleRetracted);

            } else if (isOpen = false) {
                wobbleArm.setPosition(wobbleExtended);

            }
        }
    }
}
