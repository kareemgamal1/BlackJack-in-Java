package blackjack;

import java.util.Scanner;

public class BlackJack {
    static Game game = new Game();
    public static void main(String[] args) {
        GUI gui = new GUI();
        game.GenerateDeck(); // Generate the whole card deck
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            System.out.println("Enter the name of player number: " + (i + 1));
            game.players[i] = new Player();
            game.players[i].name = scanner.next();
        }
        game.GenerateStart(); // Give each player two cards
        game.ShowPlayers();
        gui.runGUI(game.Card, game.players[0].getCardObjects(),
                game.players[1].getCardObjects(),
                game.players[2].getCardObjects(), game.players[3].getCardObjects()); //for the GUI
        game.playerTurn(gui);
        game.UpdateScore();
        game.dealerTurn(gui);
    }
}
