package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import static org.junit.Assert.*;
import org.junit.Test;
import org.firstinspires.ftc.teamcode.bot.OdometryBot;


public class OdometryBotTest {

    public class TestOpMode extends LinearOpMode{
        public void runOpMode(){
        }
    }

    @Test
    public void calculateCaseThreeTest() {
        LinearOpMode opmode = new TestOpMode();
        OdometryBot test = new OdometryBot(opmode);
        double[] expected1 = {1.37, 0.37};
        double[] expected2 = {1.87, 1.23};
        double[] expected3 = {-2.83, 5.10};
        double[] expected4 = {-6.60, -5.43};
        double[] expected5 = {0.95, -0.32};
        double[] expected6 = {5.00, 5.00};
        test.previousVL = 0;
        test.previousVR = 0;
        assertArrayEquals(expected1, test.calculateCaseThree(1, -1, 1, 60), 0.01);
        test.previousVL = 0;
        test.previousVR = 0;
        assertArrayEquals(expected2, test.calculateCaseThree(2, -2, 1, 60), 0.01);
        test.previousVL = 0;
        test.previousVR = 0;
        assertArrayEquals(expected3, test.calculateCaseThree(3, -3, -5, 60), 0.01);
        test.previousVL = 0;
        test.previousVR = 0;
        assertArrayEquals(expected4, test.calculateCaseThree(-8, 8, -3, 60), 0.01);
        test.previousVL = 0;
        test.previousVR = 0;
        assertArrayEquals(expected5, test.calculateCaseThree(1, 1, 1, 60), 0.01);
        test.previousVL = 0;
        test.previousVR = 0;
        assertArrayEquals(expected6, test.calculateCaseThree(5, -5, 5, 90), 0.01);
    }

    @Test
    public void complicatedTest() {
        LinearOpMode opmode = new TestOpMode();
        OdometryBot test = new OdometryBot(opmode);

        double[] expected1 = {10, 0};
        double[] expected2 = {10, 10};

        assertArrayEquals(expected1, test.calculateCaseThree(10, 10, 0, 0), 0.01);
        assertArrayEquals(expected2, test.calculateCaseThree(10, 10, 0, 90), 0.01);
    }
}