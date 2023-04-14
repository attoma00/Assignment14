import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setCenter(new TicTacToe());

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public class TicTacToe extends Pane {
        private char[][] board;
        private boolean xTurn;

        public TicTacToe() {
            board = new char[5][5];
            xTurn = true;

            setPrefSize(500, 500);

            drawBoard();
        }

        private void drawBoard() {
            for (int i = 0; i < 5; i++) {
                final int row = i; // Declare i as effectively final
                for (int j = 0; j < 5; j++) {
                    final int column = j; // Declare j as effectively final
                    Rectangle r = new Rectangle(100 * j, 100 * i, 100, 100);
                    r.setFill(null);
                    r.setStroke(Color.BLACK);
                    r.setOnMouseClicked(e -> handleClick(column, row));
                    getChildren().add(r);
                }
            }
        }

        private void handleClick(int x, int y) {
            if (board[y][x] == '\u0000') {
                char mark = xTurn ? 'X' : 'O';
                board[y][x] = mark;

                Text text = new Text(mark + "");
                text.setFont(Font.font(72));
                text.setX(x * 100 + 25);
                text.setY(y * 100 + 72);

                getChildren().add(text);

                if (checkWin()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Game Over");
                    alert.setHeaderText(null);
                    alert.setContentText(mark + " wins!");
                    alert.showAndWait();

                    reset();
                } else if (checkTie()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Game Over");
                    alert.setHeaderText(null);
                    alert.setContentText("It's a tie!");
                    alert.showAndWait();

                    reset();
                } else {
                    xTurn = !xTurn;
                }
            }
        }

        private boolean checkWin() {
            for (int i = 0; i < 5; i++) {
                if (board[i][0] != '\u0000' && board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][2] == board[i][3] && board[i][3] == board[i][4]) {
                    return true;
                }
            }

            for (int i = 0; i < 5; i++) {
                if (board[0][i] != '\u0000' && board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[2][i] == board[3][i] && board[3][i] == board[4][i]) {
                    return true;
                }
            }

            if (board[0][0] != '\u0000' && board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[2][2] == board[3][3] && board[3][3] == board[4][4]) {
                return true;
                }
                if (board[0][4] != '\u0000' && board[0][4] == board[1][3] && board[1][3] == board[2][2] && board[2][2] == board[3][1] && board[3][1] == board[4][0]) {
                    return true;
                }
        
                return false;
            }
        
            private boolean checkTie() {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (board[i][j] == '\u0000') {
                            return false;
                        }
                    }
                }
        
                return true;
            }
        
            private void reset() {
                board = new char[5][5];
                xTurn = true;
                getChildren().clear();
                drawBoard();
            }
        }

        public static void main(String[] args) {
            launch(args);
        }
    }        
