package usecase;

import entity.TicTacToeBoard;

public class MakeMoveUseCase implements MakeMoveInputBoundary {
    private final TicTacToeBoard board;
    private final MakeMoveOutputBoundary outputBoundary;

    public MakeMoveUseCase(TicTacToeBoard board, MakeMoveOutputBoundary outputBoundary) {
        this.board = board;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void makeMove(int row, int col) {
        if (board.makeMove(row, col)) {
            char winner = board.checkWin();
            if (winner != '-') {
                outputBoundary.presentWinner(winner);
            } else if (board.isFull()) {
                outputBoundary.presentWinner('D'); // Draw
            } else {
                board.switchPlayer();
                //outputBoundary.presentBoard(board.getBoard());
            }
        } else {
            outputBoundary.presentError("Invalid move. Try again.");
        }
    }
}