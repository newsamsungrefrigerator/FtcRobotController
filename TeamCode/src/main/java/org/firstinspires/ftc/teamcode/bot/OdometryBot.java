package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.RobotLog;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class OdometryBot extends GyroBot {

    public DcMotor horizontal = null;
    public DcMotor verticalLeft = null;
    public DcMotor verticalRight = null;

    String verticalLeftEncoderName = "v1", verticalRightEncoderName = "v2", horizontalEncoderName = "h";

    double xBlue = 0, yBlue = 0, thetaDEG = 0;
    double xRed = 0, yRed = 0;

    final int vLDirection = 1;
    final int vRDirection = -1;
    final int hDirection = 1;
    final double radius = 10;

    public double previousVL = 0, previousVR = 0;
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
        verticalLeft = ahwMap.dcMotor.get(verticalLeftEncoderName);
        verticalLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        verticalLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        verticalRight = ahwMap.dcMotor.get(verticalRightEncoderName);
        verticalRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        verticalRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        opMode.telemetry.addData("Status", "Hardware Map Init Complete");
        opMode.telemetry.update();
    }

    protected void onTick(){
        RobotLog.d(String.format("Position, heading: %2d, %2d, %2d", xBlue, yBlue, thetaDEG));
        opMode.telemetry.addData("X:", xBlue);
        opMode.telemetry.addData("Y:", yBlue);
        opMode.telemetry.addData("Theta:", thetaDEG);
        opMode.telemetry.update();
        super.onTick();
        calculateCaseThree(verticalLeft.getCurrentPosition(), verticalRight.getCurrentPosition(), horizontal.getCurrentPosition(), thetaDEG);
    }

    public void outputEncoders() {
        opMode.telemetry.addData("v1", verticalLeft.getCurrentPosition());
        opMode.telemetry.addData("v2", verticalRight.getCurrentPosition());
        opMode.telemetry.addData("h", horizontal.getCurrentPosition());
        opMode.telemetry.update();
    }

    public double[] calculateCaseThree(double vL, double vR, double h, double angleDEG) {
        vL = vL * vLDirection;
        vR = vR * vRDirection;
        h = h * hDirection;

        double lC = vL - previousVL;
        double rC = vR - previousVR;

        angleChange = ((lC - rC) / (Math.PI * radius * 2) * 360);

        angleDEG = angleDEG + angleChange;
        thetaDEG = angleDEG;

//        System.out.println(String.format("%f, %f",angleChange, angleDEG));

        xRed = h;
        yRed = (vL + vR)/2;

        xBlue = Math.cos(Math.toRadians(angleDEG - 90))*xRed + Math.cos(Math.toRadians(angleDEG))*yRed;
        yBlue = Math.sin(Math.toRadians(angleDEG))*yRed + Math.sin(Math.toRadians(angleDEG - 90))*xRed;

        previousVL = vL;
        previousVR = vR;

//        System.out.println(String.format("%f, %f", xBlue, yBlue));

        double[] position = {xBlue, yBlue};

        return position;
    }
}
