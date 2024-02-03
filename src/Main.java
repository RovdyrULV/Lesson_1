class CustomException extends Exception {
    CustomException(String message) {
        super(message);
    }
}

class MyArrayDataException extends CustomException {
    MyArrayDataException(int row, int col) {
        super(String.format("Неверные данные находятся в ячейках [%d, %d]", row, col));
    }
}

class MyArraySizeException extends CustomException {
    MyArraySizeException() {
        super("Размерность массива должна быть [4 x 4]");
    }
}

class Converter {
    static int strConverter(String[][] strArray) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        if (4 != strArray.length) throw new MyArraySizeException();
        for (int i = 0; i < strArray.length; i++) {
            if (4 != strArray[i].length) throw new MyArraySizeException();
            for (int j = 0; j < strArray[i].length; j++) {
                try {
                    sum += Integer.parseInt(strArray[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sum;
    }
}

public class Main {
    public static void main(String[] args) {
        String[][] correctMatrix = {
                {"3", "7", "3", "1"},
                {"1", "6", "0", "4"},
                {"0", "5", "7", "2"},
                {"0", "2", "7", "8"}
        };
        String[][] wrongSizeMatrix = {
                {"1", "2", "0", "3"},
                {"6", "4", "1", "5"},
                {"9", "2", "7", "4"},
                {"0", "2"}
        };
        String[][] wrongCharMatrix = {
                {"1", "9", "2", "3"},
                {"6", "1", "5", "7"},
                {"2", "Z", "7", "6"},
                {"1", "9", "4", "5"}
        };
        try {
            System.out.println("Вариант 1:");
            System.out.println("Сумма всех элементов массива равна " + Converter.strConverter(correctMatrix));
        } catch (CustomException e) {
            e.getMessage();
        }
        try {
            System.out.println("Вариант 2:");
            System.out.println("Сумма всех элементов массива равна " + Converter.strConverter(wrongSizeMatrix));
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("Вариант 3:");
            System.out.println("Сумма всех элементов массива равна " + Converter.strConverter(wrongCharMatrix));
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
    }
}