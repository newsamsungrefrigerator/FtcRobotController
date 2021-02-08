package org.firstinspires.ftc.teamcode.bot;
import java.lang.Math;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Radius Determiner", group="Radius")
public class RadiusDeterminer extends LinearOpMode {
    //Define Variables
    public DcMotor OdometryLeft, OdometryRight, OdometryCenter;
    double OdometryCircumferenceLeft;
    double OdometryCircumferenceRight;
    double OdometryCircumferenceCenter;
    double AverageOdometryCircumference;
    double radius;

    private FourWheelDriveBot robot = new FourWheelDriveBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();

        //Rotate Robot 360 degrees
        robot.driveStraightByDistance(robot.DIRECTION_RQUARTER, 100, 100);

        //Get Positions
        OdometryCircumferenceLeft = OdometryLeft.getCurrentPosition();
        OdometryCircumferenceRight = OdometryRight.getCurrentPosition();
        OdometryCircumferenceCenter = OdometryCenter.getCurrentPosition();

        //Calculate Radius
        AverageOdometryCircumference = (OdometryCircumferenceLeft + OdometryCircumferenceRight) / 2;
        radius = AverageOdometryCircumference / (2 * Math.PI);
    }
}