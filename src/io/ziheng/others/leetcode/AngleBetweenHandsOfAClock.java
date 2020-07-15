package io.ziheng.others.leetcode;
/**
 * LeetCode 1344. Angle Between Hands of a Clock
 * https://leetcode.com/problems/angle-between-hands-of-a-clock/
 */
public class AngleBetweenHandsOfAClock {
    public static void main(String[] args) {
        AngleBetweenHandsOfAClock obj = new AngleBetweenHandsOfAClock();
        System.out.println(obj.angleClock(12, 30));
    }
    /**
     * LeetCode 1344. Angle Between Hands of a Clock
     *
     * @param hour
     * @param minutes
     * @return double
     */
    public double angleClock(int hour, int minutes) {
        double anglePerHour = 30.0;
        double anglePerMinute = 6.0;
        double hoursAngle = ((hour % 12) * anglePerHour)
            + ((double)minutes / 60 * anglePerHour);
        double minutesAngle = minutes * anglePerMinute;
        double clockAngle = Math.abs(minutesAngle - hoursAngle);
        return clockAngle > 180.0 ? 360.0 - clockAngle : clockAngle;
    }
}
/* EOF */