import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    private char[][] board;
    private char[][] mineLocations;
    private int rows;
    private int cols;
    private int mines;
    private boolean gameOver;
    private Scanner scanner;

    public MineSweeper() {
        scanner = new Scanner(System.in);
        gameOver = false;
    }

    public void startGame() {
        System.out.println("Mayın Tarlası Oyununa Hoş Geldiniz!");
        System.out.println("Oyun Alanı Boyutları En Az 2x2 Olmalıdır.");

        while (true) {
            System.out.print("Satır Sayısı Giriniz: ");
            rows = scanner.nextInt();
            System.out.print("Sütun Sayısı Giriniz: ");
            cols = scanner.nextInt();
            if (rows >= 2 && cols >= 2) {
                break;
            } else {
                System.out.println("En az 2x2 boyutunda giriniz!");
            }
        }

        board = new char[rows][cols];
        mineLocations = new char[rows][cols];
        int totalCells = rows * cols;
        mines = totalCells / 4;
        if (mines < 1) {
            mines = 1;
        } else if (mines > totalCells - 1) {
            mines = totalCells - 1;
        }
        initializeBoard();
        printBoard();
        revealMineLocations(); // Mayınları başlangıçta göster
        while (!gameOver) {
            playTurn();
            printBoard();
            if (checkWin()) {
                System.out.println("Tebrikler! Oyunu kazandınız!");
                gameOver = true;
            }
        }
        if (!checkWin()) {
            System.out.println("Game Over!! Mayınlı bölgeyi görmek ister misiniz?");
            revealBoard();
        }
        scanner.close();
    }

    // Oyun tahtasını ve mayınların yerleştirildiği matrisi oluşturur
    private void initializeBoard() {
        // Board'u "-" ile doldur
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = '-';
                mineLocations[i][j] = '-';
            }
        }
        // Mayınları yerleştir
        Random random = new Random();
        for (int i = 0; i < mines; i++) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);
            while (mineLocations[row][col] == '*') {
                row = random.nextInt(rows);
                col = random.nextInt(cols);
            }
            mineLocations[row][col] = '*';
        }
    }

    // Oyun tahtasını ekrana basar
    private void printBoard() {
        System.out.println("   " + "0123456789".substring(0, cols)); // Örneğin, eğer oyun tahtasında 5 sütun varsa, bu satır şu şekilde yazdırılacaktır: " 01234".
        for (int i = 0; i < rows; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println("|");
        }
    }

    // Bir hamle yapılmasını sağlar ve oyunun durumunu kontrol eder
    private void playTurn() {
        System.out.println("Bir hamle yapın:");
        System.out.print("Satır seçin: ");
        int row = scanner.nextInt();
        System.out.print("Sütun seçin: ");
        int col = scanner.nextInt();

        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            System.out.println("Geçersiz koordinatlar, lütfen tekrar deneyin!");
            return;
        }

        if (board[row][col] != '-') {
            System.out.println("Bu koordinat daha önce seçildi, başka bir koordinat girin!");
            return;
        }
        if (mineLocations[row][col] == '*') {
            gameOver = true;
            revealBoard();
            System.out.println("Mayına bastınız! Oyunu kaybettiniz.");
        } else {
            int count = countAdjacentMines(row, col);
            board[row][col] = (char) (count + '0');
            if (count == 0) {
                checkAdjacentCells(row, col);
            }
        }
    }

    // Belirtilen hücrenin etrafındaki mayın sayısını hesaplar
    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = Math.max(0, row - 1); i <= Math.min(row + 1, rows - 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(col + 1, cols - 1); j++) {
                if (mineLocations[i][j] == '*') {
                    count++;
                }
            }
        }
        return count;
    }

    // Belirtilen hücrenin etrafındaki boş hücreleri kontrol eder
    private void checkAdjacentCells(int row, int col) {
        for (int i = Math.max(0, row - 1); i <= Math.min(row + 1, rows - 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(col + 1, cols - 1); j++) {
                if (board[i][j] == '-') {
                    int count = countAdjacentMines(i, j);
                    board[i][j] = (char) (count + '0');
                    if (count == 0) {
                        checkAdjacentCells(i, j);
                    }
                }
            }
        }
    }

    // Oyun bittiğinde mayınları gösterir
    private void revealBoard() {
        System.out.println("Mayınlar:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mineLocations[i][j] == '*') {
                    System.out.print("* ");
                } else {
                    System.out.print(countAdjacentMines(i, j) + " ");
                }
            }
            System.out.println();
        }
    }

    // Oyun bittiğinde mayınları ve boş hücreleri gösterir
    private void revealMineLocations() {
        System.out.println("Mayınlar:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(mineLocations[i][j] == '*' ? "* " : "- ");
            }
            System.out.println();
        }
    }

    // Oyunun kazanılıp kazanılmadığını kontrol eder
    private boolean checkWin() {
        // Tüm mayın olmayan hücreler açıldı mı?
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mineLocations[i][j] != '*' && board[i][j] == '-') {
                    return false; // Mayın olmayan hücreler açılmamışsa, oyun kazanılmamıştır.
                }
            }
        }
        return true; // Yukarıdaki kontrollerden geçildiyse, oyun kazanılmıştır.
    }

    public static void main(String[] args) {
        MineSweeper game = new MineSweeper();
        game.startGame();
    }
}