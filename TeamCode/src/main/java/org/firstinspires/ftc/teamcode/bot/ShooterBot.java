package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import android.content.Context;

public class ShooterBot extends GyroBot {
    public DcMotor shooter = null;
    public Servo pusher = null;

    long currentTime = 0;
    long lastTime = 0;
    long timeDifference = 0;

    double currentPosition = 0;
    double lastPosition = 0;
    double positionDifference = 0;

    double currentShooterSpeed = 1;

    //change these values to control what speed the shooter spins around
    final double highShooterSpeedThreshold = 1.022;
    final double lowShooterSpeedThreshold = 1.019;

    //the two speeds the shooter switches between to control itself
    final double highShooterSpeed = -0.5;
    final double lowShooterSpeed = -0.285;

    //two positions of the pusher servo
    final double pusherRetracted = 0.35;
    final double pusherPushing = 0.53;

    boolean shooterIsOn = false;

    OutputStreamWriter writer;

    public ShooterBot(LinearOpMode opMode) {
        super(opMode);
        try {
            writer = new FileWriter("/sdcard/FIRST/shooterlog" + java.text.DateFormat.getDateTimeInstance().format(new Date()) + ".csv", true);
        } catch (IOException e) {
            opMode.telemetry.addData("Exception", "shooter file writer open failed: " + e.toString());
        }
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        shooter = hwMap.get(DcMotor.class, "Shooter");
        shooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pusher = hwMap.get(Servo.class, "Pusher");
        pusher.setPosition(pusherPushing);
    }

    public void toggleShooter(boolean input) {
        if (input) {
            shooterIsOn = true;
        } else {
            shooterIsOn = false;
        }
    }

    public void spinShooter() {
        if (shooterIsOn) {
            //calculate difference in time between last and current cycle
            currentTime = System.currentTimeMillis();
            timeDifference = currentTime - lastTime;
            //calculate difference in position between last and current cycle
            currentPosition = shooter.getCurrentPosition();
            positionDifference = Math.abs(currentPosition - lastPosition);
            //calculate current shooter speed
            currentShooterSpeed = positionDifference / (double)timeDifference;
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
            opMode.telemetry.addData("Shooter speed", currentShooterSpeed);
            // opMode.telemetry.addData("Position Difference", positionDifference);
            // opMode.telemetry.addData("Time Difference", (double)timeDifference);
            //opMode.telemetry.addData("Current Position", currentPosition);
            opMode.telemetry.update();
            try {
                writer.write(String.format("%d, %f\n", currentTime, currentShooterSpeed));
            } catch (IOException e) {
                    opMode.telemetry.addData("Exception", "shooter speed file write failed: " + e.toString());
                }
        } else {
            shooter.setPower(0);
        }
    }

    public void launchRing(boolean rightBumper) {
        if (rightBumper) {
            pusher.setPosition(pusherRetracted);
            for (int i = 0; i < 2; i++) {
                onLoop(350, "launch ring 1");
            }
            pusher.setPosition(pusherPushing);
            for (int i = 0; i < 10; i++) {
                onLoop(200, "launch ring 2");
            }
        }
    }

    protected void onTick(){
        spinShooter();
        super.onTick();
    }

}
