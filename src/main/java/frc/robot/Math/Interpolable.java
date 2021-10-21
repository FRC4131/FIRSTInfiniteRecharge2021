package frc.robot.Math;

public interface Interpolable<T> {
    T interpolate(T other, double t);
}
