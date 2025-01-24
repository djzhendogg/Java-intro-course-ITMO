package game.settings;

import game.board.Board;
import game.board.MnkRectangleBoard;
import game.board.MnkRhombusBoard;
import game.players.HumanPlayer;
import game.players.Player;
import game.players.RandomPlayer;
import game.players.SequentialPlayer;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Settings {
    private final PrintStream out;
    private final Scanner in;
    private final BoardMode boardMode;
    private final PlayerMode playerMode;
    private final TwoPlayers twoPlayers;
    private final MnkParameters mnkParameters;
    private Board board;

    public Settings(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
        this.boardMode = setBoardMode();
        this.mnkParameters = setGameParameters();
        this.playerMode = setGameMode();
        this.twoPlayers = setGamePlayers();
        resetBoard();
    }

    public Settings() {
        this(System.out, new Scanner(System.in));
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return twoPlayers.getPlayer1();
    }

    public Player getPlayer2() {
        return twoPlayers.getPlayer2();
    }

    private BoardMode setBoardMode() {
        out.println("Choose A Board:\n1. classic.\n2. rhombus.");
        BoardMode result;
        while (true) {
            try {
                int val = in.nextInt();
                if (val == 1) {
                    result = BoardMode.CLASSIC;
                } else if (val == 2) {
                    result = BoardMode.RHOMBUS;
                } else {
                    out.println("I don't understand. Repeat enter:\n1. classic.\n2. rhombus.");
                    in.nextLine();
                    continue;
                }
                break;
            } catch (InputMismatchException ex) {
                out.println("Wrong value. Value must be integer. Repeat enter:");
                in.nextLine();
            }
        }
        return result;
    }

    private PlayerMode setGameMode() {
        out.println("Game Settings:\n1. single play.\n2. coop play.\n3. robot play.");
        PlayerMode result;
        while (true) {
            try {
                int val = in.nextInt();
                if (val == 1) {
                    result = PlayerMode.SINGLE;
                } else if (val == 2) {
                    result = PlayerMode.COOP;
                } else if (val == 3) {
                    result = PlayerMode.ROBOT;
                } else {
                    out.println("I don't understand. Repeat enter:\n1. classic.\n2. rhombus.");
                    in.nextLine();
                    continue;
                }
                break;
            } catch (InputMismatchException ex) {
                out.println("Wrong value. Value must be integer. Repeat enter:");
                in.nextLine();
            }
        }
        return result;
    }

    private TwoPlayers setGamePlayers() {
        Player player1;
        Player player2;
        if (playerMode == PlayerMode.SINGLE) {
            player1 = new HumanPlayer();
            player2 = setPlayer();
        } else if (playerMode == PlayerMode.COOP) {
            player1 = new HumanPlayer();
            player2 = new HumanPlayer();
        } else {
            player1 = setPlayer();
            player2 = setPlayer();
        }
        return new TwoPlayers(player1, player2);
    }

    private MnkParameters setGameParameters() {
        int mValue = setM();
        if (boardMode == BoardMode.CLASSIC) {
            int nValue = setN();
            return new MnkParameters(mValue, nValue, setK(Math.max(mValue, nValue)));
        } else {
            mValue = mValue * 2 - 1;
            return new MnkParameters(mValue, mValue, setK(mValue));
        }
    }

    public void resetBoard() {
        if (boardMode == BoardMode.CLASSIC) {
            board = new MnkRectangleBoard(mnkParameters);
        } else {
            board = new MnkRhombusBoard(mnkParameters);
        }
    }

    private Player setPlayer() {
        out.println("Choose Opponent:\n1. random player.\n2. sequential player.");
        Player result;
        while (true) {
            try {
                int val = in.nextInt();
                if (val == 1) {
                    result = new RandomPlayer(mnkParameters.getM(), mnkParameters.getN());
                } else if (val == 2) {
                    result = new SequentialPlayer(mnkParameters.getM(), mnkParameters.getN());
                } else {
                    out.println("I don't understand. Repeat enter:\n1. classic.\n2. rhombus.");
                    in.nextLine();
                    continue;
                }
                break;
            } catch (InputMismatchException ex) {
                out.println("Wrong value. Value must be integer. Repeat enter:");
                in.nextLine();
            }
        }
        return result;
    }

    private int setM() {
         return setParam("m", 0);
    }

    private int setN() {
        return setParam("n", 0);
    }

    private int setK(int maxVal) {
        return setParam("k", maxVal);
    }

    private int setParam(String param, int maxNumber) {
        out.printf("Enter %s value:%n", param);
        int res;
        while (true) {
            try {
                res = in.nextInt();
                if (res <= 1) {
                    out.println("Wrong value. Value must be > 1. Repeat enter:");
                    in.nextLine();
                    continue;
                }
                if (Objects.equals(param, "k") && res > maxNumber) {
                    out.printf("Wrong value. Value must be <= %d. Repeat enter:%n", maxNumber);
                    in.nextLine();
                    continue;
                }
                break;
            } catch (InputMismatchException ex) {
                out.println("Wrong value. Value must be a number. Repeat enter:");
                in.nextLine();
            }
        }
        return res;
    }
}
