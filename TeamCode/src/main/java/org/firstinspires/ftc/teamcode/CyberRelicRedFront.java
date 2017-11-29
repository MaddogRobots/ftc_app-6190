package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

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

/**
 * Created by Steve on 11/11/2017.
 */

@Autonomous(name = "RelicRedFront", group = "RiderModes")
public class CyberRelicRedFront extends CyberRelicAbstract {

    OpenGLMatrix lastLocation = null;


    VuforiaLocalizer vuforia;

    @Override
    public void init() {

        super.init();

        lightSensor.enableLed(true);

    }

    @Override
    public void loop()
    {

        super.loop();

        switch (seqRobot) {

            case 1: {
                motorLeftA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorRightA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorLeftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorRightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorRightA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorLeftA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorRightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorLeftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorLeftA.setTargetPosition(0);
                motorLeftB.setTargetPosition(0);
                motorRightA.setTargetPosition(0);
                motorRightB.setTargetPosition(0);
                seqRobot++;
                break;
            }


            case 2: {
                servoGem.setPosition(90);
                seqRobot++;
                break;
            }

            case 3: {
                lightSensor.getLightDetected();

                if (blue) {
                    if(gyroSensor.getHeading() > 180 && gyroSensor.getHeading() < 350){
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

                if (red) {
                    if (gyroSensor.getHeading() < 10) {
                        motorLeftA.setPower(.1);
                        motorLeftB.setPower(.1);
                        motorRightA.setPower(-.1);
                        motorRightB.setPower(-.1);
                    }
                }

                seqRobot++;
                break;
            }

            case 4: {
                servoGem.setPosition(0);

                if (gyroSensor.getHeading() < 1 && gyroSensor.getHeading() > 359) {
                    motorLeftA.setPower(0);
                    motorLeftB.setPower(0);
                    motorRightA.setPower(0);
                    motorRightB.setPower(0);
                }
                else if (gyroSensor.getHeading() > 0 && gyroSensor.getHeading() <179 ){
                    motorLeftA.setPower(-.1);
                    motorLeftB.setPower(-.1);
                    motorRightA.setPower(.1);
                    motorRightB.setPower(.1);
                }
                else if(gyroSensor.getHeading() < 360 && gyroSensor.getHeading() > 181)
                {
                    motorLeftA.setPower(.1);
                    motorLeftB.setPower(.1);
                    motorRightA.setPower(-.1);
                    motorRightB.setPower(-.1);
                }
                seqRobot++;
                break;
            }
            case 24:
            case 22:
            case 19:
            case 17:
            case 15:
            case 13:
            case 11:
            case 9:
            case 5: {
                motorLeftA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorRightA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorLeftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorRightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorRightA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorLeftA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorRightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorLeftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorLeftA.setTargetPosition(0);
                motorLeftB.setTargetPosition(0);
                motorRightA.setTargetPosition(0);
                motorRightB.setTargetPosition(0);
                seqRobot++;
                break;
            }
            case 6: {
                if(gyroSensor.getHeading() > 180 && gyroSensor.getHeading() <= 340){
                    motorLeftA.setPower(0);
                    motorLeftB.setPower(0);
                    motorRightA.setPower(0);
                    motorRightB.setPower(0);
                }else{
                    motorLeftA.setPower(-.1);
                    motorLeftB.setPower(-.1);
                    motorRightA.setPower(.1);
                    motorRightB.setPower(.1);
                }
                seqRobot++;
                break;
            }

            case 7: {

                int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
                VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

                parameters.vuforiaLicenseKey = "AdlfowT/////AAAAGUpRoaEvyUfNtuTDeKMo6qEf2Y8oPuvPan17xUGgdDWoYKTx+JNrzPv2tPPmKMQcyOw9MNnOeGDXCPFCDOOKjsUTjil2cGK9odRVmSWL0xsxdsxtbz9Y3ZW2q1fi9IJsBofvfxfTa/6t9JDldr1+6lcBi9izU2k0ZC9Md6S8DHkcvQ7Q7P9NRepmQZXU+ztVWxB9gNHJ1128u3zADXS+pIkW9qIUHfc6UybysSNpmeh65VxdFRu2Tnlwh3fqAB+NjG9eMmgP49FyW3C3wnkwMMCVqT4JBdhRPviRHyp7lXXtzVqr/BB30ww0hk0W7gyiANIbVvQq/04i18SFFuS5H9zX2v0g5J3ViTvfUybve/AA";

                parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
                this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

                VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
                VuforiaTrackable relicTemplate = relicTrackables.get(0);
                relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

                relicTrackables.activate();

                RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
                if (vuMark != RelicRecoveryVuMark.UNKNOWN) {


                    telemetry.addData("VuMark", "%s visible", vuMark);

                    if (vuMark == RelicRecoveryVuMark.LEFT) {
                        leftCol = true;
                        centerCol = false;
                        rightCol = false;
                    } else if (vuMark == RelicRecoveryVuMark.CENTER) {
                        leftCol = false;
                        centerCol = true;
                        rightCol = false;
                    } else if (vuMark == RelicRecoveryVuMark.RIGHT) {
                        leftCol = false;
                        centerCol = false;
                        rightCol = true;
                    }


                    OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose();

                    if (pose != null) {
                        VectorF trans = pose.getTranslation();
                        Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                        // Extract the X, Y, and Z components of the offset of the target relative to the robot
                        double tX = trans.get(0);
                        double tY = trans.get(1);
                        double tZ = trans.get(2);

                        // Extract the rotational components of the target relative to the robot
                        double rX = rot.firstAngle;
                        double rY = rot.secondAngle;
                        double rZ = rot.thirdAngle;
                    }
                } else {
                    telemetry.addData("VuMark", "not visible");
                }


                telemetry.update();
                seqRobot++;
                break;
            }

            case 8: {
                if (gyroSensor.getHeading() < 181 || gyroSensor.getHeading() > 179) {
                    motorLeftA.setPower(0);
                    motorLeftB.setPower(0);
                    motorRightA.setPower(0);
                    motorRightB.setPower(0);
                }
                else if (gyroSensor.getHeading() > 181 && gyroSensor.getHeading() < 360 ){
                    motorLeftA.setPower(-.1);
                    motorLeftB.setPower(-.1);
                    motorRightA.setPower(.1);
                    motorRightB.setPower(.1);
                }
                else if(gyroSensor.getHeading() < 18179 && gyroSensor.getHeading() > 0)
                {
                    motorLeftA.setPower(.1);
                    motorLeftB.setPower(.1);
                    motorRightA.setPower(-.1);
                    motorRightB.setPower(-.1);
                }
                seqRobot++;
                break;
            }

            case 10: {

                if (leftCol) {
                    if (rangeSensorB.cmUltrasonic() < 104) {
                        motorLeftA.setPower(.25);
                        motorLeftB.setPower(.25);
                        motorRightA.setPower(.25);
                        motorRightB.setPower(.25);
                    }
                    if (rangeSensorB.cmUltrasonic() > 104) {
                        motorLeftA.setPower(-.05);
                        motorLeftB.setPower(-.05);
                        motorRightA.setPower(-.05);
                        motorRightB.setPower(-.05);
                    }

                }

                if (centerCol) {
                    if (rangeSensorB.cmUltrasonic() < 123) {
                        motorLeftA.setPower(.25);
                        motorLeftB.setPower(.25);
                        motorRightA.setPower(.25);
                        motorRightB.setPower(.25);
                    }
                    if (rangeSensorB.cmUltrasonic() >= 123) {
                        motorLeftA.setPower(-.05);
                        motorLeftB.setPower(-.05);
                        motorRightA.setPower(-.05);
                        motorRightB.setPower(-.05);
                    }

                }

                if (rightCol) {
                    if (rangeSensorB.cmUltrasonic() < 142) {
                        motorLeftA.setPower(.25);
                        motorLeftB.setPower(.25);
                        motorRightA.setPower(.25);
                        motorRightB.setPower(.25);
                    } else if (rangeSensorB.cmUltrasonic() > 142) {
                        motorLeftA.setPower(-.05);
                        motorLeftB.setPower(-.05);
                        motorRightA.setPower(-.05);
                        motorRightB.setPower(-.05);
                    }

                }
                seqRobot++;
                break;
            }

            case 12: {
                    if (gyroSensor.getHeading() > 180 && gyroSensor.getHeading() < 360 ){
                        motorLeftA.setPower(-.1);
                        motorLeftB.setPower(-.1);
                        motorRightA.setPower(.1);
                        motorRightB.setPower(.1);
                    } else if(gyroSensor.getHeading() < 180 && gyroSensor.getHeading() > 0) {
                        motorLeftA.setPower(.1);
                        motorLeftB.setPower(.1);
                        motorRightA.setPower(-.1);
                        motorRightB.setPower(-.1);
                    }
                seqRobot++;
                break;
            }

            case 14: {

                if (leftCol) {

                    if (rangeSensorB.cmUltrasonic() < 104) {
                        motorLeftA.setPower(.1);
                        motorLeftB.setPower(.1);
                        motorRightA.setPower(.1);
                        motorRightB.setPower(.1);
                    }
                }


                if (centerCol) {
                    if (rangeSensorB.cmUltrasonic() < 123) {
                        motorLeftA.setPower(.1);
                        motorLeftB.setPower(.1);
                        motorRightA.setPower(.1);
                        motorRightB.setPower(.1);
                    }
                }

                if (rightCol) {
                    if (rangeSensorB.cmUltrasonic() < 142) {
                        motorLeftA.setPower(.1);
                        motorLeftB.setPower(.1);
                        motorRightA.setPower(.1);
                        motorRightB.setPower(.1);
                    }
                }
                seqRobot++;
                break;
            }


            case 16:
            {
                if (gyroSensor.getHeading() < 270) {
                    motorLeftA.setPower(-.1);
                    motorLeftB.setPower(-.1);
                    motorRightA.setPower(.1);
                    motorRightB.setPower(.1);
                }
                if (gyroSensor.getHeading() > 270) {
                    motorLeftA.setPower(.1);
                    motorLeftB.setPower(.1);
                    motorRightA.setPower(-.1);
                    motorRightB.setPower(-.1);
                }
                seqRobot++;
                break;
            }

            case 18:
            {
                if (rangeSensorF.cmUltrasonic() > 9.8) {
                    motorLeftA.setPower(.1);
                    motorLeftB.setPower(.1);
                    motorRightA.setPower(.1);
                    motorRightB.setPower(.1);
                }
                seqRobot++;
                break;
            }


            case 20:
            {
                servoGlyph1.setPosition(tbd);
                servoGlyph2.setPosition(tbd);
                seqRobot++;
                break;
            }

            case 21:
            {
                targetDrRotateDeg = 0f;
                targetDrDistInch = -5f; // Set target distance
                targetPower = 0.1d;  // Set power

                targetPosLeftA = cmdMoveR(targetDrDistInch, (float)ENCODER_CNT_PER_IN_DRIVE, targetPower, motorLeftA);
                targetPosLeftB = cmdMoveR(targetDrDistInch, (float)ENCODER_CNT_PER_IN_DRIVE, targetPower, motorLeftB);
                targetPosRightA = cmdMoveR(targetDrDistInch, (float)ENCODER_CNT_PER_IN_DRIVE, targetPower, motorRightA);
                targetPosRightB = cmdMoveR(targetDrDistInch, (float)ENCODER_CNT_PER_IN_DRIVE, targetPower, motorRightB);


                seqRobot++;
                break;
            }

            case 23:
            {
                if (gyroSensor.getHeading() < 90.25 || gyroSensor.getHeading() > 89.75) {
                    motorLeftA.setPower(0);
                    motorLeftB.setPower(0);
                    motorRightA.setPower(0);
                    motorRightB.setPower(0);
                }else{
                    motorLeftA.setPower(.1);
                    motorLeftB.setPower(.1);
                    motorRightA.setPower(-.1);
                    motorRightB.setPower(-.1);
                }
                seqRobot++;
                break;
            }

            case 99:  // Done
            {
                break;
            }

            default:
            {
                break;
            }



        }












    }






}