import entity.TicTacToeBoard;
import usecase.MakeMoveUseCase;
import adapter.TicTacToePresenter;
import adapter.TicTacToeController;
import view.TicTacToeView;
import view.TicTacToeCLIView;

import java.util.Scanner;

public class TextTTTGame {
    public static void main(String[] args) {
        TicTacToeBoard board = new TicTacToeBoard();
        TicTacToeView view = new TicTacToeCLIView();
        TicTacToePresenter presenter = new TicTacToePresenter(view);
        MakeMoveUseCase useCase = new MakeMoveUseCase(board, presenter);
        TicTacToeController controller = new TicTacToeController(useCase);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            view.displayBoard(board.getBoard());
            System.out.println("Player " + board.getCurrentPlayer() + ", enter your move by typing the row then a space then column number. Your options are 0, 1, or 2. To exit, enter 9 and then 9");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            if (row==9 && col==9) {
                break;
            }
            controller.makeMove(row, col);
            if (board.checkWin() != '-' || board.isFull()) {
                break;
            }
        }

        view.displayBoard(board.getBoard());
        char winner = board.checkWin();
        if (winner != '-') {
            view.displayWinner(winner);
        } else {
            view.displayWinner('D'); // Draw
        }
    }
}
