package expression.generic;

import expression.exceptions.expresion_exceptions.CommandLineArgumentsException;
import expression.exceptions.expresion_exceptions.UnsupportedModeException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new CommandLineArgumentsException("-[mode] [expression]");
        }
        if (!args[0].startsWith("-")) {
            throw new UnsupportedModeException("Calculations type for performance must starts with '-'.");
        }
        GenericTabulator tabulator = new GenericTabulator();
        Object[][][] table = tabulator.tabulate(args[0].substring(1), args[1], -2, 2, -2, 2, -2, 2);
        printTable(table);
    }

    private static void printTable(Object[][][] table) {
        System.out.print("[");
        for (Object[][] forX : table) {
            System.out.print("[");
            for (Object[] forY : forX) {
                System.out.print("[");
                for (Object forZ : forY) {
                    System.out.print(forZ + ", ");
                }
                System.out.print("],");
                System.out.println();
            }
            System.out.print("]");
        }
        System.out.print("]");
    }
}
