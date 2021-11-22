package com.geekbrains;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class CrossZero {

    public static int sizeMap;
    public static int checkLen;
    public static char[][] mapPad;
    public final static char dotEmpty = '.';
    public final static char dotX = 'X';
    public final static char dotO = 'O';
    public static Scanner scanner = new Scanner(System.in);
    public static Random randomInt = new Random();

    public static void main(String[] args) {
        do {
            System.out.println("Введите размерность поля. Допустимые значения - 3, 5.");
            sizeMap = scanner.nextInt();
            System.out.println("Введите длину контрольной линии...");
            checkLen = scanner.nextInt();
        } while (!((sizeMap == 3 || sizeMap == 5) && checkLen <= sizeMap));
        initMap();
        printMap();
        do {
            humanStep();
            if (checkWin(dotX)) {
                System.out.println("Победил человек!!!");
                break;
            }
            if (mapIsFull()) {
                System.out.println("Ничья!");
                break;
            }
            AiStep();
            if (checkWin(dotO)) {
                System.out.println("Победил компьютер!!!");
                break;
            }
            if (mapIsFull()) {
                System.out.println("Ничья!");
                break;
            }
        } while (true);
        System.out.println("Игра закончена.");
    }

    private static void AiStep() {
        int x;
        int y;
        do {
            x = randomInt.nextInt(sizeMap);
            y = randomInt.nextInt(sizeMap);
        } while (!isCellEmpty(x, y));
        mapPad[y][x] = dotO;
        System.out.println("Компьютер сходил в точку Х=" + (x + 1) + "  У=" + (y + 1));
        printMap();
    }

    private static boolean checkWin(char charToCheck) {
        int maxLen = 0;
        int currLen = 0;
        // проверяем прямые диагонали
        for (int i = 0; i <= (sizeMap - checkLen); i++) {
            for (int j = 0; j <= (sizeMap - checkLen); j++) {
                currLen=0;
                for (int k = 0; k < checkLen; k++) {
                    if (mapPad[k + i][k + j] == charToCheck) {
                        currLen++;
                    }
                }
                if (currLen > maxLen) {
                    maxLen = currLen;
                }
            }
        }
        // проверяем обратные диагонали
        for (int i = 0; i <= (sizeMap - checkLen); i++) {
            for (int j = 0; j <= (sizeMap - checkLen); j++) {
                currLen = 0;
                for (int k = 0; k < checkLen; k++) {
                    if (mapPad[k + i][j + checkLen - 1 - k] == charToCheck) {
                        currLen++;
                    }
                }
                if (currLen > maxLen) {
                    maxLen = currLen;
                }
            }
        }
        // проверяем горизонтали
        for (int i = 0; i < sizeMap; i++) {
            for (int j = 0; j <= (sizeMap - checkLen); j++) {
                currLen = 0;
                for (int k = 0; k < checkLen; k++) {
                    if (mapPad[i][k + j] == charToCheck) {
                        currLen++;
                    }
                }
                if (currLen > maxLen) {
                    maxLen = currLen;
                }
            }
        }
        // проверяем вертикали
        for (int j = 0; j < sizeMap; j++) {
            for (int i = 0; i <= (sizeMap - checkLen); i++) {
                currLen = 0;
                for (int k = 0; k < checkLen; k++) {
                    if (mapPad[k + i][j] == charToCheck) {
                        currLen++;
                    }
                }
                if (currLen > maxLen) {
                    maxLen = currLen;
                }
            }
        }
        // если есть хот одна полная строка, то ...
        if (maxLen == checkLen) {
            return true;
        }
        return false;
    }

    private static boolean mapIsFull() {
        for (int i = 0; i < sizeMap; i++) {
            for (int j = 0; j < sizeMap; j++) {
                if (mapPad[i][j] == dotEmpty) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void humanStep() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты Х и У:");
            x = scanner.nextInt();
            y = scanner.nextInt();
        } while (!isCellEmpty(x - 1, y - 1));
        mapPad[y - 1][x - 1] = dotX;
        printMap();
    }

    private static boolean isCellEmpty(int x, int y) {
        if (!((x >= 0 && x < sizeMap) && (y >= 0 && y < sizeMap))) {
            return false;
        }
        if (mapPad[y][x] != dotEmpty) {
            return false;
        }
        return true;
    }

    private static void initMap() {
        mapPad = new char[sizeMap][sizeMap];
        for (int i = 0; i < sizeMap; i++) {
            Arrays.fill(mapPad[i], dotEmpty);
        }
    }

    private static void printMap() {
        // print the map
        System.out.println("Map pad...");
        for (int i = 0; i < sizeMap + 1; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < sizeMap; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < sizeMap; j++) {
                System.out.print(mapPad[i][j] + " ");
            }
            System.out.println();
        }
    }

}
