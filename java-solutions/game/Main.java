package game;

import game.settings.Settings;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final Settings settings = new Settings();
        final Game game = new Game(
        false,
            settings.getPlayer1(),
            settings.getPlayer2()
        );

        String ans;
        do {
            game.play(settings.getBoard());
            System.out.println("Start new game? y/n");
            while (true) {
                ans = scanner.next();
                if (Objects.equals(ans, "y") || Objects.equals(ans, "n")) {
                    break;
                } else {
                    System.out.println("I don't understand. Repeat enter: y/n");
                }
            }
            settings.resetBoard();
        } while (ans.equals("y"));
    }
}
