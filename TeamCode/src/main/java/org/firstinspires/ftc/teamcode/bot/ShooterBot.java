package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

public class ShooterBot extends IntakeBot {
    public DcMotor shooter = null;
    public Servo pusher = null;

    long currentTime = 0;
    long lastTime = 0;
    long timeDifference = 0;

    double currentPosition = 0;
    double lastPosition = 0;
    double positionDifference = 0;

    public double currentShooterSpeed = 1;

    //change these values to control what speed the shooter spins around
    double highShooterSpeedThreshold = 1.4;
    double lowShooterSpeedThreshold = 1.395;

    //the two speeds the shooter switches between to control itself
    final double highShooterSpeed = -0.7;
    final double lowShooterSpeed = -0.285;

    //two positions of the pusher servo
    final double pusherRetracted = 0.51;
    final double pusherPushing = 0.605;

    boolean shooterIsOn = false;
    public boolean isAuto = true;

    OutputStreamWriter shooterWriter;

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
        try {
            shooterWriter = new FileWriter("/sdcard/FIRST/shooterlog_" + java.text.DateFormat.getDateTimeInstance().format(new Date()) + ".csv", true);
        } catch (IOException e) {
            throw new RuntimeException("shooter log file writer open failed: " + e.toString());
        }
    }

    public void toggleShooter(boolean input) {
        if (!isDown) {
            shooterIsOn = true;
        } else {
            shooterIsOn = false;
        }
    }

    public void spinShooter() {
        if (!isAuto) {
            highShooterSpeedThreshold = 1.6;
            lowShooterSpeedThreshold = 1.595;
        }
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
                RobotLog.d("shooterWriter.write");
                shooterWriter.write(String.format("%d, %f\n", currentTime, currentShooterSpeed));
            } catch (IOException e) {
                throw new RuntimeException("shooter log file writer write failed: " + e.toString());
            }
        } else {
            shooter.setPower(0);
        }
    }

    public void waitForThreshold() {
        while (currentShooterSpeed > lowShooterSpeedThreshold) {
            onLoop(50, "wait between shots");
        }
    }

    public void launchRing(boolean rightBumper) {
        if (rightBumper) {
            pusher.setPosition(pusherPushing);
            sleep(700, "launch ring 1");
            pusher.setPosition(pusherRetracted);
            sleep(700, "launch ring 2");
        }
    }

    protected void onTick(){
        spinShooter();
        super.onTick();
    }

    public void close(){
        try {
            RobotLog.d("shooter log Writer.close");
            shooterWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("shooter log file writer close failed: " + e.toString());
        }
        super.close();
    }

}
