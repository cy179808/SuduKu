import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudoKuFactory {

    private static int[][] seed = {{5, 4, 6, 1, 8, 7, 2, 3, 9},
            {2, 3, 9, 5, 4, 6, 1, 7, 8},
            {8, 1, 7, 3, 9, 2, 4, 6, 5},
            {1, 7, 5, 8, 2, 3, 9, 4, 6},
            {4, 8, 3, 9, 6, 5, 7, 2, 1},
            {6, 9, 2, 4, 7, 1, 8, 5, 3},
            {7, 6, 1, 2, 3, 9, 5, 8, 4},
            {3, 5, 4, 7, 1, 8, 6, 9, 2},
            {9, 2, 8, 6, 5, 4, 3, 1, 7}};

    private int[][] SudoKu = new int[9][9];
    private Random random = new Random();

    public int[][] generateSudoKu() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SudoKu[i][j] = seed[i][j];
            }
        }
        List<Integer> randomList = buildRandomList();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    if (SudoKu[i][j] == randomList.get(k)) {
                        SudoKu[i][j] = randomList.get((k + 1) % 9);
                        break;
                    }
                }
            }
        }
        return SudoKu;
    }

    private List<Integer> buildRandomList() {
        List<Integer> randomList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Collections.shuffle(randomList);
        return randomList;
    }

    public int[][] generateSudoKuII() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SudoKu[i][j] = 0;
            }
        }
        solve(0,0);
        return SudoKu;
    }

    private boolean solve(int i, int j) {
        if (i > 8) {
            return true;
        }
        if (SudoKu[i][j] != 0) {
            if (j == 8) {
                return solve(i + 1, 0);
            } else {
                return solve(i, j + 1);
            }
        } else {
            int[] randOrder = new int[9];

            for (int k = 0; k < 9; k++) {
                randOrder[k] = k + 1;
            }

            for (int k = 0; k < 9; k++) {
                int rand = random.nextInt(9);
                int temp = randOrder[rand];
                randOrder[rand] = randOrder[k];
                randOrder[k] = temp;
            }

            for (int k = 0; k < 9; k++) {
                if (isOk(i, j, randOrder[k])) {
                    SudoKu[i][j] = randOrder[k];
                    if (j == 8) {
                        if (solve(i + 1, 0)) {
                            return true;
                        }
                    } else {
                        if (solve(i, j + 1)) {
                            return true;
                        }
                    }
                }
            }
        }

        SudoKu[i][j] = 0;
        return false;
    }


    private boolean isOk(int i, int j, int value) {
        if (!isColOk(i, j, value)) {
            return false;
        }
        if (!isRowOk(i, j, value)) {
            return false;
        }
        if (!isSubOk(i, j, value)) {
            return false;
        }

        return true;
    }

    private boolean isRowOk(int i, int j, int value) {
        for (int k = 0; k < 9; k++) {
            if (value == SudoKu[i][k]) {
                return false;
            }
        }
        return true;
    }

    private boolean isColOk(int i, int j, int value) {
        for (int k = 0; k < 9; k++) {
            if (value == SudoKu[k][j]) {
                return false;
            }
        }
        return true;
    }

    private boolean isSubOk(int i, int j, int value) {
        for (int k = 0; k < 9; k++) {
            int ii = (i / 3) * 3 + k / 3;
            int jj = (j / 3) * 3 + k % 3;
            if (value == SudoKu[ii][jj]) {
                return false;
            }
        }
        return true;
    }

}
