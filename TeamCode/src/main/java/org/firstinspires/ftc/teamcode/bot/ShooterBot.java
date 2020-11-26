package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ShooterBot extends FourWheelDriveBot {
    public DcMotor shooter = null;
    public Servo pusher = null;

    long currentTime = 0;
    long lastTime = 0;
    long timeDifference = 0;

    double currentPosition = 0;
    double lastPosition = 0;
    double positionDifference = 0;

    double currentShooterSpeed = 0;

    //change these values to control what speed the shooter spins around
    final double highShooterSpeedThreshold = 1;
    final double lowShooterSpeedThreshold = 0.5;

    //the two speeds the shooter switches between to control itself
    final double highShooterSpeed = -0.5;
    final double lowShooterSpeed = -0.4;

    //two positions of the pusher servo
    final double pusherRetracted = 0.35;
    final double pusherPushing = 0.6;

    public ShooterBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        shooter = hwMap.get(DcMotor.class, "Shooter");
        shooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pusher = hwMap.get(Servo.class, "Pusher");
        pusher.setPosition(pusherRetracted);
    }

    public void spinShooter() {
        //calculate difference in time between last and current cycle
        currentTime = System.currentTimeMillis();
        timeDifference = currentTime - lastTime;
        //calculate difference in position between last and current cycle
        currentPosition = shooter.getCurrentPosition();
        positionDifference = Math.abs(currentPosition - lastPosition);
        //calculate current shooter speed
        currentShooterSpeed = positionDifference / timeDifference;
        //check if current speed is less than or high than the two thresholds
        if (currentShooterSpeed < lowShooterSpeedThreshold) {
            //increase shooter power to compensate
            shooter.setPower(highShooterSpeed);
        }
        if (currentShooterSpeed > highShooterSpeedThreshold) {
            //decrease shooter power to compensate
            shooter.setPower(lowShooterSpeed);
        }
        //save current time and position for next cycle
        lastTime = currentTime;
        lastPosition = currentPosition;
    }

    public void launchRing(boolean rightBumper) {
        if (rightBumper) {
            pusher.setPosition(pusherPushing);
            for (int i = 0; i < 10; i++) {
                onLoop(50);
            }
            pusher.setPosition(pusherRetracted);
        }
    }

    protected void onTick(){
        spinShooter();
        opMode.telemetry.addData("Shooter speed", currentShooterSpeed);
        opMode.telemetry.update();
        super.onTick();
    }

}
