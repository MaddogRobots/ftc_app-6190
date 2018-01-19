package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static java.lang.Thread.sleep;

@Autonomous(name = "RelicBlueFront1", group = "RiderModes")
public class CyberRelicBlueFront1 extends CyberRelicAbstract{

    private ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);  //Added so opMode does not sleep

    //OpenGLMatrix lastLocation = null;


    //VuforiaLocalizer vuforia;
    //BNO055IMU imu;
    @Override
    public void init() {

        super.init();

        colorSensor.enableLed(true);

    }

    //Added start to reset encoders when Play is pressed
    @Override
    public void start() {
        motorRightA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeftA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        timer.reset();
    }

    @Override
    public void loop() {

        switch (seqRobot) {

            case 1:
                telemetry.addData("1", true);
                telemetry.update();
                motorRightA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorLeftA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorRightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorLeftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorLeftA.setTargetPosition(0);
                motorLeftB.setTargetPosition(0);
                motorRightA.setTargetPosition(0);
                motorRightB.setTargetPosition(0);
                telemetry.addData("Time", System.currentTimeMillis());
                telemetry.update();
                if(chkMove(motorRightA, 0, 20)) {
                    seqRobot++;
                    timer.reset();
                }
                break;


            case 2:
                telemetry.addData("2", true);
                telemetry.addData("Time", System.currentTimeMillis());
                telemetry.update();
                servoGlyph1.setPosition(GLYPH_1_GRAB);
                servoGlyph2.setPosition(GLYPH_2_GRAB);
                servoGem.setPosition(0);
                //slp(1000);  Setpoints are not written until the end of the loop. Don't use sleep methods in iterative opModes
                if(timer.milliseconds() > 1000) {
                    seqRobot++;
                    timer.reset();
                }
                break;


            case 3:
                telemetry.addData("3", true);
                telemetry.addData("Time", System.currentTimeMillis());
                telemetry.update();
                colorSensor.getClass();

                if (colorSensor.red() >= colorSensor.blue()) {
                    if(gyro() > 180 && gyro() < 350){
                        motorLeftA.setPower(0);
                        motorLeftB.setPower(0);
                        motorRightA.setPower(0);
                        motorRightB.setPower(0);
                    } else{
                        motorLeftA.setPower(-.1);
                        motorLeftB.setPower(-.1);
                        motorRightA.setPower(.1);
                        motorRightB.setPower(.1);
                    }
                }

                if (colorSensor.blue() >= colorSensor.red()) {
                    if (gyro() < 10) {
                        motorLeftA.setPower(.1);
                        motorLeftB.setPower(.1);
                        motorRightA.setPower(-.1);
                        motorRightB.setPower(-.1);
                    }
                }
                //slp(750);
                if(timer.milliseconds() > 750) {
                    seqRobot++;
                    timer.reset();
                }
                break;

            case 4:
                telemetry.addData("4", true);
                telemetry.addData("Time", System.currentTimeMillis());
                telemetry.update();
                servoGem.setPosition(0.66);

                if (gyro() < 1 && gyro() > 359) {
                    motorLeftA.setPower(0);
                    motorLeftB.setPower(0);
                    motorRightA.setPower(0);
                    motorRightB.setPower(0);
                }
                else if (gyro() > 0 && gyro() <179 ){
                    motorLeftA.setPower(-.1);
                    motorLeftB.setPower(-.1);
                    motorRightA.setPower(.1);
                    motorRightB.setPower(.1);
                }
                else if(gyro() < 360 && gyro() > 181)
                {
                    motorLeftA.setPower(.1);
                    motorLeftB.setPower(.1);
                    motorRightA.setPower(-.1);
                    motorRightB.setPower(-.1);
                }
                //slp(750);
                if (gyro() < 1 && gyro() > 359) {
                    motorLeftA.setPower(0);
                    motorLeftB.setPower(0);
                    motorRightA.setPower(0);
                    motorRightB.setPower(0);
                    seqRobot++;
                }
                break;



            default:

                break;

        }
    }

    @Override
    public void stop() {
        motorLeftA.setPower(0);
        motorLeftB.setPower(0);
        motorRightA.setPower(0);
        motorRightB.setPower(0);
    }

    private void slp(int slptime) {
        try {
            sleep(slptime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}