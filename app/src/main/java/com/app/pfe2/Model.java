package com.app.pfe2;

public class Model {
    public double acc_x;
    public double acc_y;
    public double acc_z;

    public double gyro_x;
    public double gyro_y;
    public double gyro_z;

    public String y;

    public Model(double acc_x, double acc_y, double acc_z,
                 double gyro_x, double gyro_y, double gyro_z,
                 String y) {
        this.acc_x = acc_x;
        this.acc_y = acc_y;
        this.acc_z = acc_z;
        this.gyro_x = gyro_x;
        this.gyro_y = gyro_y;
        this.gyro_z = gyro_z;
        this.y = y;
    }
}
