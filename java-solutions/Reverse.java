import java.io.IOException;
import java.util.Arrays;

public class Reverse {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            try {
                int[][] listLine = new int[10][];
                int pointerLine = 0;
                while (in.hasNext()) {
                    String line = in.nextLine();
                    int[] listInt = new int[10];
                    int pointerInt = 0;
                    Scanner stringScanner = new Scanner(line);
                    while (stringScanner.hasNextInt()) {
                        if (pointerInt >= listInt.length) {
                            listInt = Arrays.copyOf(listInt, listInt.length * 2);
                        }
                        listInt[pointerInt++] = stringScanner.nextInt();
                    }
                    listInt = Arrays.copyOf(listInt, pointerInt);
                    if (pointerLine >= listLine.length) {
                        listLine = Arrays.copyOf(listLine, listLine.length * 2);
                    }
                    listLine[pointerLine++] = listInt;
                }
                listLine = Arrays.copyOf(listLine, pointerLine);
                for (int i = listLine.length - 1; i >= 0; i--) {
                    if (listLine[i].length > 0) {
                        for (int j = listLine[i].length - 1; j >= 0; j--) {
                            System.out.print(listLine[i][j] + " ");
                        }
                    }
                    System.out.println();
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            System.err.println("Error I/O operation: " + Arrays.toString(e.getStackTrace()));
        }
    }
}
