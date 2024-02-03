
public class TimeHelper {

    public static double getTime() {
        return System.currentTimeMillis() / 1000.0;
    }

    public static void sleep(long msec) {
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
        }
    }
}