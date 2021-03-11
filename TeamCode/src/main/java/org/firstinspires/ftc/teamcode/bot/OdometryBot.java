package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.RobotLog;

public class OdometryBot extends FourWheelDriveBot {

    public DcMotor horizontal = null;
    public DcMotor verticalLeft = null;
    public DcMotor verticalRight = null;

    String verticalLeftEncoderName = "v1", verticalRightEncoderName = "v2", horizontalEncoderName = "h";

    public double xBlue = 0, yBlue = 0, xBlueChange = 0, yBlueChange = 0, thetaDEG = 0;
    double xRed = 0, yRed = 0, xRedChange = 0, yRedChange = 0;
    double hError = 0;

    final int vLDirection = 1;
    final int vRDirection = -1;
    final int hDirection = 1;
    final double diameter = 18971; // actually diameter
    final double hDiameter = 11936; //radius of horizontal encoder

    public double previousVL = 0, previousVR = 0, previousH = 0;
    double angleChange = 0;


    public OdometryBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        initDriveHardwareMap(ahwMap);
        opMode.telemetry.addData("Status", "Init Complete");
        opMode.telemetry.update();
    }

    private void initDriveHardwareMap(HardwareMap ahwMap){

        horizontal = ahwMap.dcMotor.get(horizontalEncoderName);
        horizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        horizontal.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        verticalLeft = ahwMap.dcMotor.get(verticalLeftEncoderName);
//        verticalLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        verticalLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        verticalRight = ahwMap.dcMotor.get(verticalRightEncoderName);
//        verticalRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        verticalRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        opMode.telemetry.addData("Status", "Hardware Map Init Complete");
        opMode.telemetry.update();
    }

    protected void onTick(){
        RobotLog.d(String.format("Position, heading: %.2f, %.2f, %.2f", xBlue, yBlue, thetaDEG));
        RobotLog.d(String.format("v1: %d v2: %d h: %d", leftFront.getCurrentPosition(), rightFront.getCurrentPosition(), horizontal.getCurrentPosition()));
        opMode.telemetry.addData("X:", xBlue);
        opMode.telemetry.addData("Y:", yBlue);
        opMode.telemetry.addData("Theta:", thetaDEG);
        opMode.telemetry.addData("v1", leftFront.getCurrentPosition());
        opMode.telemetry.addData("v2", rightFront.getCurrentPosition());
        opMode.telemetry.addData("h", horizontal.getCurrentPosition());
        //opMode.telemetry.update();
        super.onTick();
        calculateCaseThree(leftFront.getCurrentPosition(), rightFront.getCurrentPosition(), horizontal.getCurrentPosition(), thetaDEG);
    }

    public void outputEncoders() {
        opMode.telemetry.addData("v1",  leftFront.getCurrentPosition());
        opMode.telemetry.addData("v2", rightFront.getCurrentPosition());
        opMode.telemetry.addData("h", horizontal.getCurrentPosition());
        opMode.telemetry.addData("percent: ", ((double)leftFront.getCurrentPosition() / rightFront.getCurrentPosition() - 1) * 100);
        opMode.telemetry.update();
    }

    public double[] calculateCaseThree(double vL, double vR, double h, double angleDEG) {
        vL = vL * vLDirection;
        vR = vR * vRDirection;
        h = h * hDirection;

        double lC = vL - previousVL;
        double rC = vR - previousVR;

        angleChange = ((lC - rC) / (Math.PI * diameter * 2) * 360);

        angleDEG = angleDEG + angleChange;
        thetaDEG = angleDEG;

        hError = (angleChange / 360) * (Math.PI * hDiameter);

        double hC = h - previousH;

        xRedChange = hC + hError;
        yRedChange = (lC + rC)/2;

        xBlueChange = Math.cos(Math.toRadians(angleDEG - 90)) * xRedChange + Math.cos(Math.toRadians(angleDEG)) * yRedChange;
        yBlueChange = Math.sin(Math.toRadians(angleDEG)) * yRedChange + Math.sin(Math.toRadians(angleDEG - 90)) * xRedChange;

        xBlue = xBlue + yBlueChange;
        yBlue = yBlue + xBlueChange;

        previousVL = vL;
        previousVR = vR;
        previousH = h;

        double[] position = {xBlue, yBlue};

        return position;
    }
}
