package org.firstinspires.ftc.teamcode.bot;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.RobotLog;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;

public class NewCameraBot extends FourWheelDriveBot {

    class Area extends Object{
        public int x;
        public int y;
        public int width;
        public int height;
    }

    final int startX = 10;
    final int startY = 140;
    final int boxWidth = 90;
    final int boxHeight = 50;
    final int numberOfColumns = 14;
    final int numberOfRows = 7;

    protected Area[][] boxes = new Area[numberOfColumns][numberOfRows];

    public NewCameraBot(LinearOpMode opMode) {
        super(opMode);
    }

    private static final String VUFORIA_KEY =
            "AW3DaKr/////AAABmbYMj0zPp0oqll2pQvI8zaoN8ktPz319ETtFtBMP7b609q4wWm6yRX9OVwWnf+mXPgSC/fSdDI2uUp/69KTNAJ6Kz+sTx+9DG+mymW00Xm3LP7Xe526NP/lM1CIBsOZ2DJlQ2mqmObbDs5WR5HXyfopN12irAile/dEYkr3uIFnJ95P19NMdbiSlNQS6SNzooW0Nc8cBKWz91P020YDqC4dHSpbQvYeFgVp2VWZJC/uyvmE15nePzZ30Uq/n8pIeYWKh4+XR74RoRyabXMXFB6PZz7lgKdRMhhhBvQ5Eh21VxjE5h8ZhGw27K56XDPk63eczGTYP/FfeLvTuK4iKSNyqRLS/37kuxKn3t/dlkwv1";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {

        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Camera bot

        // setup frame queue
        vuforia.enableConvertFrameToBitmap();
        vuforia.setFrameQueueCapacity(5);

    }


    @Override
    public void init(HardwareMap ahwMap) {

        for (int i = 0; i < numberOfColumns; i++) {
            for (int j = 0; j < numberOfRows; j++) {
                boxes[i][j] = new Area();
                boxes[i][j].x = startX + boxWidth * i;
                boxes[i][j].y = startY + boxHeight * j;
                boxes[i][j].width = boxWidth;
                boxes[i][j].height = boxHeight;
            }
        }

        super.init(ahwMap);
        initVuforia();

    }

    @Override
    public void print(String message) {

    }

    final int NORINGS = 0;
    final int ONERING = 1;
    final int FOURRINGS = 4;

    protected void printAndSave(Bitmap bmp, int average, String label){
        RobotLog.d("Image %s with %d x %d and average RGB #%02X #%02X #%02X", label, bmp.getWidth(), bmp.getHeight(),
                Color.red(average), Color.green(average), Color.blue(average));
        try (FileOutputStream out = new FileOutputStream(String.format("/sdcard/FIRST/ftc_%s.png", label))) {
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int detectRings() {
        BlockingQueue<VuforiaLocalizer.CloseableFrame> queue = vuforia.getFrameQueue();
        RobotLog.d("Detecting Started ...");
        try {
            VuforiaLocalizer.CloseableFrame frame = queue.take();
            RobotLog.d("Took Frames from Vuforia");
            Image image = frame.getImage(0);
            Bitmap bmp = vuforia.convertFrameToBitmap(frame);
            RobotLog.d(String.format("Converted Vuforia frame to BMP: %s", bmp.getConfig().toString()));
            // DEBUG : uncomment the following line to save the whole picture captured
            //printAndSave(bmp, "camera");
            RobotLog.d("Saved camera BMP");
            frame.close();
            RobotLog.d("Closed frame");
            Bitmap[][] b = new Bitmap[numberOfColumns][numberOfRows];
            int[][] c = new int[numberOfColumns][numberOfRows];
            for (int i = 0; i < numberOfColumns; i++) {
                for (int j = 0; j < numberOfRows; j++) {
                    //b[i][j] = Bitmap.createBitmap(bmp, boxes[i][j].x, boxes[i][j].y, boxes[i][j].width, boxes[i][j].height);
                    c[i][j] = getAverageRGB(bmp, boxes[i][j].x, boxes[i][j].y);

                    RobotLog.d(String.format("Box %d,%d has coordinates: %d, %d", i, j, boxes[i][j].x, boxes[i][j].y));
                    //printAndSave(bmp, "camera");
                    //printAndSave(b[i][j], c[i][j], String.format("box_%d_%d", i, j));
                }
            }
//            Bitmap smallBmp = Bitmap.createBitmap(bmp, 0, 0, 10, 10);
//            RobotLog.d(String.format("small BMP: %s", smallBmp.getConfig().toString()));
//            int test = getAverageRGB(bmp, 0, 0);
            RobotLog.d("Created 9 sub-bitmaps");
            RobotLog.d("Calculate AVG for 9 sub-bitmaps");
            RobotLog.d("Saved 9 sub-bitmaps");
            int numberOfRings = chooseRings(c);
            RobotLog.d("Determine # of rings through 9 sub-bitmaps");
            return numberOfRings;
        } catch (InterruptedException e) {
            print("Photo taken has been interrupted !");
            return NORINGS;
        }

    }

    public int chooseRings (int[][] c){

        int correctBoxes = 0;
        for (int i = 0; i < numberOfColumns; i++) {
            for (int j = 0; j < numberOfRows; j++) {
                if (colourIsCorrect(c[i][j], i, j)) {
                    correctBoxes++;
                }
            }
        }
        if (correctBoxes <= 2) {
            return NORINGS;
        } else if (correctBoxes <= 5) {
            return ONERING;
        } else {
            return FOURRINGS;
        }
    }

    public boolean colourIsCorrect (int c, int i, int j) {
        int red = Color.red(c);
        int green = Color.green(c);
        int blue = Color.blue(c);
        int average = (red + green + blue) / 3;
        int redGreenDifference = Math.abs(red - green);
        int greenBlueDifference = Math.abs(green - blue);
//((120 < red && 200 > red && 50 < green && 110 > green) || (120 < red && 200 > red && 100 > blue))
//(red - green + 10 > 0 && red - blue + 10 > 0)
//(120 < red && 225 > red && 50 < green && 160 > green && 100 > blue)
        if (red >= average && green > blue && red > green && green > red/2 && greenBlueDifference > 20 && redGreenDifference > 10
            && ((70 < red && 220 > red && 50 < green && 150 > green) || (70 < red && 220 > red && 100 > blue))) {
            RobotLog.d(String.format("Box %d,%d is TRUE", i, j));
            return true;
        } else {
            RobotLog.d(String.format("Box %d,%d is false", i, j));
            return false;
        }
    }

    protected int getAverageRGB (Bitmap bmp, int offsetX, int offsetY){

        int totalRed = 0;
        int totalGreen = 0;
        int totalBlue = 0;

//        int bytes = bmp.getWidth() * bmp.getHeight() * 4;
//
//        RobotLog.d(String.format("bytes: %d width: %d height: %d", bytes, bmp.getWidth(), bmp.getHeight()));
//        ByteBuffer buffer = ByteBuffer.allocate(bytes);
//        bmp.copyPixelsToBuffer(buffer);
//
//        byte[] byteArray = buffer.array();

        for (int x=offsetX; x < offsetX+boxWidth; x += 4) {
            for (int y=offsetY; y < offsetY+boxHeight; y += 4) {
                int pixel = bmp.getPixel(x, y);

                int red = Color.red(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);

                totalRed += red;
//                totalRed = Math.max(red, totalRed);
                totalGreen += green;
                totalBlue += blue;
            }
        }

//        for (int i = 0; i<bytes; i++) {
//            int pixel = byteArray[i];
//            int red = Color.red(pixel);
//            int green = Color.green(pixel);
//            int blue = Color.blue(pixel);
//            RobotLog.d(String.format("Average RGB of %d: %d %d %d, %d", i, red, green, blue, pixel));
//        }

        int averageRed = totalRed / (boxWidth / 4 * boxHeight / 4);
        int averageGreen = totalGreen / (boxWidth / 4 * boxHeight / 4);
        int averageBlue = totalBlue / (boxWidth / 4 * boxHeight / 4);

        RobotLog.d(String.format("Average RGB: %d %d %d", averageRed, averageGreen, averageBlue));

        return Color.rgb(averageRed, averageGreen, averageBlue);
    }

}
