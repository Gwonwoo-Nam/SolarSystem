import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public enum Planets {
    SUN(25, 25, 5, "O"),
    MERCURY(1 / 30.0, 5, 2, "M", SUN),
    VENUS(1 / 100.0, 7, 2, "V", SUN),
    EARTH(1 / 365.25, 10, 3, "*", SUN),
    MOON(1 / 27.3, 2, 1, "-", EARTH),
    JUPITER(1 / 100.0, 13, 4, "J", SUN),
    SATURN(1 / 120.0, 15, 2, "S", SUN),
    NEPTUNE(1 / 140.0, 17, 2, "N", SUN),
    PLUTO(1 / 130.0, 20, 2, "P", SUN);


    private final double CLOSE_DISTANCE = 0.5;
    private String circleMark;
    private final String EMPTY_MARK = " ";
    private double xLocation;
    private double yLocation;
    private int size;
    private double rotationalSpeed;
    private double revolutionRadius;
    private Planets orbitalCircle;

    Planets(double xLocation, double yLocation, int size, String circleMark) {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.size = size;
        this.circleMark = circleMark;
    }

    Planets(double rotationalSpeed, double revolutionRadius, int size, String circleMark,
            Planets orbitalCircle) {
        this.rotationalSpeed = rotationalSpeed;
        this.revolutionRadius = revolutionRadius;
        this.size = size;
        this.circleMark = circleMark;
        this.orbitalCircle = orbitalCircle;
    }


    public void draw() {

        double radius = (size - 1) / 2.0;

        for (int xLocation = 0; xLocation < SolarMap.getXAxisMapSize(); xLocation++) {
            for (int yLocation = 0; yLocation < SolarMap.getYAxisMapSize(); yLocation++) {
                double distance = calculateDistance(xLocation, yLocation, radius);
                int index = xLocation * SolarMap.getYAxisMapSize() + yLocation;
                addMark(distance, index);
            }
        }
    }

    private double calculateDistance(int x, int y, double radius) {
        double distance = Math.abs(
                radius - Math.sqrt(
                        (Math.pow(x - (int) xLocation, 2) + Math.pow(y - (int) yLocation, 2))));

        return distance;
    }

    private void addMark(double distance, int index) {
        if (distance <= CLOSE_DISTANCE) {
            drawCircleMark(index);
        }
        drawEmptyMark(index);
    }

    /**
     * Empty Mark가 이미 있는 경우 Circle Mark로 교체한다. 없는 경우 Circle Mark를 추가한다.
     *
     * @param index
     */
    private void drawCircleMark(int index) {
        try {
            if (SolarMap.getSolarMap().get(index) == EMPTY_MARK) {
                SolarMap.setMarker(index, circleMark);
            }
        } catch (IndexOutOfBoundsException exception) {
            SolarMap.addMarker(circleMark);
        }
    }

    /**
     * Mark가 이미 있는 경우 Skip한다. 없는 경우 Empty Mark를 추가한다.
     *
     * @param index
     */
    private void drawEmptyMark(int index) {
        try {
            SolarMap.getSolarMap().get(index); // 값이 이미 있으면 넘어간다.
        } catch (IndexOutOfBoundsException exception) {
            SolarMap.addMarker(EMPTY_MARK);
        }
    }

    public void rotate(LocalDate currentDate) {
        LocalDate firstDate = LocalDate.of(1, 01, 01);
        long rotationDays = ChronoUnit.DAYS.between(firstDate, currentDate);

        double rotationAngle = 2 * Math.PI * rotationalSpeed * rotationDays;

        if (orbitalCircle != null) {
            xLocation = orbitalCircle.xLocation + (revolutionRadius * Math.sin(rotationAngle));
            yLocation = orbitalCircle.yLocation + (revolutionRadius * Math.cos(rotationAngle));
        }
    }
}