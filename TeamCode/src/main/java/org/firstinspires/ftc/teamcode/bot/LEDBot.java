package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;

public class LEDBot extends WobbleGoalBot{
    public Servo LEDControl = null;

    public double shootingDistance = 0;

    final double defaultPattern = 0.2525; //rainbow, glitter
    final int towerGoalX = -36000;
    public final int towerGoalY = -229000; //124000 108331

    final double shootingDistanceFar = 155000;
    final double shootingDistanceClose = 135000;

    public LEDBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);

        LEDControl = hwMap.servo.get("LED");

        LEDControl.setPosition(defaultPattern);
    }

    public void switchPattern(double input) {
        LEDControl.setPosition(input);
    }

    public void calculateShootingDistance() {
        shootingDistance = Math.sqrt(Math.pow(xBlue - towerGoalX, 2) + Math.pow(yBlue - towerGoalY, 2));
    }

    public boolean checkShootingDistance() {
        if (shootingDistance < shootingDistanceFar && shootingDistance > shootingDistanceClose) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkShooterSpeed() {
        if (currentShooterSpeed > lowShooterSpeedThreshold) {
            return true;
        } else {
            return false;
        }
    }

    public void updateLED() {
        if (checkShooterSpeed() && checkShootingDistance()) {
            switchPattern(0.7145); //green
        } else if (checkShootingDistance()) {
            switchPattern(0.6995); //lawn green
        } else {
            switchPattern(0.6695); //red
        }
    }

    protected void onTick(){
        calculateShootingDistance();
        updateLED();
        RobotLog.d(String.format("Distance to tower: %.2f", shootingDistance));
        //opMode.telemetry.addData("Shooting Distance", shootingDistance);
        //opMode.telemetry.update();
        super.onTick();
    }
}
