import java.io.*;
import java.util.*;

public class Jam {
    static Scanner sr;
    static boolean[] places;
    static Deque<Integer> anweisungen = new LinkedList<>();
    static List<Segment> segments;
    static ArrayList<Car> cars = new ArrayList<>();
    private static int segmentCount;
    private static int carCount;

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("/home/anton/CCCC/level1_3.in"));

            String line;

            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");
                for (String s : values) {
                    //System.out.println(s);
                    anweisungen.push(Integer.parseInt(s));
                }
            }
            br.close();
            segmentCount = anweisungen.pollLast();
            carCount = anweisungen.pollLast();
        } catch (IOException e) {
            e.printStackTrace();
        }

        init();
        drive();

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("/home/anton/CCCC/level1_2.out"));
            StringBuilder sb = new StringBuilder();
            for (Car car : cars) {
                sb.append(car.totalTime);
                sb.append(", ");
            }
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void init() {
        segments = new ArrayList<>();
        cars = new ArrayList<>();

        for (int i = 0; i < carCount; ++i) {
            //System.out.println(anweisungen.pollLast() + anweisungen.pollLast());
            cars.add(new Car(anweisungen.pollLast(), anweisungen.pollLast()));
        }

        for (int i = 0; i < segmentCount; ++i) {
            segments.add(new Segment());
        }

    }

    static void drive() {
        while (isTrue()) {
            for (Car car : cars) {
                Segment segment = segments.get(car.startSegmentNum);
                if (!car.started && !segment.carOnSegment) {
                    segment.carOnSegment = true;
                    car.currentPosition = car.startSegmentNum;
                    car.started = true;
                    car.totalTime++;
                } else if (car.currentPosition != car.endSegmentNum) {
                    Segment nextSegment = segments.get(car.currentPosition + 1);
                    Segment currentSegment = segments.get(car.currentPosition);
                    if (!nextSegment.carOnSegment) {
                        nextSegment.carOnSegment = true;
                        currentSegment.carOnSegment = false;
                        car.currentPosition++;
                    }
                    car.totalTime++;
                } else {
                    Segment currentSegment = segments.get(car.currentPosition);
                    currentSegment.carOnSegment = false;
                }
            }
        }
    }

    public static boolean isTrue() {
        boolean temp = false;
        for (Car car : cars) {
            if (car.currentPosition != car.endSegmentNum) {
                temp = true;
            }
        }
        return temp;
    }
}
