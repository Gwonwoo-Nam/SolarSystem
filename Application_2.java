import java.time.LocalDate;
import java.util.List;


public class Application_2 {

    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        outputView.askDate();
        LocalDate inputDate;
        inputDate = inputView.readDate();

        int mapSizeX = 50;
        int mapSizeY = 50;
        SolarMap.setXAxisMapSize(mapSizeX);
        SolarMap.setYAxisMapSize(mapSizeY);

        Planets.EARTH.rotate(inputDate);
        Planets.MOON.rotate(inputDate);

        Planets.SUN.draw();
        Planets.EARTH.draw();
        Planets.MOON.draw();

        List<String> solarMap = SolarMap.getSolarMap();
        outputView.printMap(solarMap);
    }
}
