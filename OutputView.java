import java.util.List;

public class OutputView {
    final String INPUT_SIZE_MESSAGE = "원의 크기는?";
    final String INPUT_DATE_MESSAGE = "Sun, Earth, Moon\n날짜를 입력하세요.";
    final String INPUT_TIME_SPEED_MESSAGE = "1 이상 9 이하의 재생 배속을 입력해주세요.\n1배속 기준 1초에 60일이 재생됩니다.";
    public void askSize() {
        System.out.println(INPUT_SIZE_MESSAGE);
    }

    public void askDate() {
        System.out.println(INPUT_DATE_MESSAGE);
    }

    public void askTimeSpeed() {
        System.out.println(INPUT_TIME_SPEED_MESSAGE);
    }

    public void printMap(List<String> map) {
        for (int x = 0; x < SolarMap.getXAxisMapSize(); x++) {
            for (int y = 0; y < SolarMap.getYAxisMapSize(); y++) {
                System.out.print(map.get(SolarMap.getYAxisMapSize() * x + y));
            }
            System.out.println();
        }

    }


}
