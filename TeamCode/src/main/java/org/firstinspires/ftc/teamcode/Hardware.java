package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Hardware {

    // DEVICES
    DcMotor frontRight;
    DcMotor frontLeft;
    DcMotor backRight;
    DcMotor backLeft;

    private BNO055IMU imu;

    HardwareMap hwMap;
    private ElapsedTime period = new ElapsedTime();


        public Hardware(){
            // Empty because we have nothing to initialise when creating a new object.
        }


        public void init(HardwareMap ahwMap) {
            hwMap = ahwMap;
            initDevices();
            initMotorSettings();
            initDefaultPosition();
        }


        private void initDevices() {
            // Initialise all parts connected to the expansion hubs
            imu = hwMap.get(BNO055IMU.class, "gyro");
            BNO055IMU.Parameters param = new BNO055IMU.Parameters();
            param.calibrationDataFile = "BNO055IMUCalibration.json";
            param.loggingEnabled = true;
            param.loggingTag = "IMU";
            param.mode = BNO055IMU.SensorMode.IMU;
            imu.initialize(param);

            frontRight = hwMap.dcMotor.get("frontRight");
            frontLeft = hwMap.dcMotor.get("frontLeft");
            backRight = hwMap.dcMotor.get("backRight");
            backLeft = hwMap.dcMotor.get("backLeft");
        }

        private void initMotorSettings() {
            // Set motor Mode and Direction
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            frontRight.setDirection(DcMotor.Direction.FORWARD);
            frontLeft.setDirection(DcMotor.Direction.REVERSE);
            backRight.setDirection(DcMotor.Direction.FORWARD);
            backLeft.setDirection(DcMotor.Direction.REVERSE);
        }

        private void initDefaultPosition() {
            // Set default positions for motors.
            frontRight.setPower(0);
            frontLeft.setPower(0);
            backRight.setPower(0);
            backLeft.setPower(0);
        }

        public void waitForTick(long periodMs) throws InterruptedException {
            long remaining = periodMs - (long) period.milliseconds();

            if (remaining > 0)
                Thread.sleep(remaining);

            period.reset();
        }
}