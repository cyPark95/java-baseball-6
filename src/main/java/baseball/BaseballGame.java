package baseball;

import static baseball.view.InputView.getEndOption;
import static baseball.view.InputView.getNumbers;
import static baseball.view.OutputView.printEnd;
import static baseball.view.OutputView.printHint;

import baseball.domain.BallNumber;
import baseball.domain.BallStatus;
import baseball.domain.Balls;
import baseball.domain.EndOption;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import utils.ValidationUtils;

public class BaseballGame {

    private static final int BOARD_SIZE = 3;
    private static final int BALL_SIZE = 3;

    private int[] board;

    public void start() {
        Balls computer = createComputerBalls();
        do {
            initBoard();
            play(computer);
        } while (!isMatch());
        printEnd();
    }

    public boolean isEnd() {
        int option = getOptionNumber();
        return option == EndOption.END.getValue();
    }

    private Balls createComputerBalls() {
        List<Integer> numbers = createRandomNumbers();
        return new Balls(numbers);
    }

    private void initBoard() {
        board = new int[BOARD_SIZE];
    }

    private boolean isMatch() {
        return board[BallStatus.STRIKE.getValue()] == BALL_SIZE;
    }

    private void play(Balls computer) {
        List<BallNumber> ballNumbers = getBallNumbers();
        for (int i = 0; i < BALL_SIZE; i++) {
            int result = computer.compare(ballNumbers.get(i), i);
            board[result]++;
        }
        printHint(board);
    }

    private List<Integer> createRandomNumbers() {
        List<Integer> numbers = new ArrayList<>();
        while (numbers.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }
        return numbers;
    }

    private List<BallNumber> getBallNumbers() {
        String inputNumbers = getNumbers();
        validateInput(inputNumbers);
        List<Integer> numbers = parseNumbers(inputNumbers);
        return numbers.stream()
            .map(BallNumber::new)
            .collect(Collectors.toList());
    }

    private void validateInput(String inputNumbers) {
        if (!ValidationUtils.isNumber(inputNumbers)) {
            throw new IllegalArgumentException("잘못된 입력입니다. 숫자를 입력해 주세요.");
        }
    }

    private List<Integer> parseNumbers(String inputNumbers) {
        List<Integer> numbers = new ArrayList<>();
        for (char c : inputNumbers.toCharArray()) {
            numbers.add(c - '0');
        }
        return numbers;
    }

    private int getOptionNumber() {
        String option = getEndOption();
        validateInput(option);
        int number = Integer.parseInt(option);
        validateOption(number);
        return number;
    }

    private void validateOption(int number) {
        if (!ValidationUtils.isEndOptionNumber(number)) {
            throw new IllegalArgumentException("잘못된 입력입니다. 1(재시작) 또는 2(종료)를 입력해주세요.");
        }
    }
}

