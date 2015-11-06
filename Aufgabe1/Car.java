public class Car {
    public final int startSegmentNum;
    public final int endSegmentNum;
    public boolean started = false;
    public int totalTime = 0;
    public int currentPosition = 0;

    public Car(int startSegmentNum, int endSegmentNum) {
        this.startSegmentNum = startSegmentNum;
        this.endSegmentNum = endSegmentNum;
    }
}