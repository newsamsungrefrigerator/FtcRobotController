package org.firstinspires.ftc.teamcode;

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

    double xCurrentBlue = 0, yCurrentBlue = 0, thetaCurrentBlue = 60;
    double xRed = 0, yRed = 0;

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
        RobotLog.d(String.format("Position, heading: %2d, %2d, %2d", xCurrentBlue, yCurrentBlue, thetaCurrentBlue));
        opMode.telemetry.addData("X:", xCurrentBlue);
        opMode.telemetry.addData("Y:", yCurrentBlue);
        opMode.telemetry.addData("Theta:", thetaCurrentBlue);
        opMode.telemetry.update();
        super.onTick();
        calculateCaseThree();
    }

    private void calculateCaseThree() {
        xRed = horizontal.getCurrentPosition();
        yRed = (verticalLeft.getCurrentPosition() + verticalRight.getCurrentPosition())/2;

        xCurrentBlue = Math.cos(Math.toRadians(thetaCurrentBlue - 90))*xRed + Math.cos(Math.toRadians(thetaCurrentBlue))*yRed;
        yCurrentBlue = Math.sin(Math.toRadians(thetaCurrentBlue))*yRed + Math.sin(Math.toRadians(thetaCurrentBlue - 90))*xRed;
    }
}
