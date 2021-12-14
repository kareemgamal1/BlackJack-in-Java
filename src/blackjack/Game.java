package blackjack;

import java.util.Random;
import java.util.Objects;
import java.util.Scanner;

public class Game {
	Player[] players = new Player[4];
	Card[] Card = new Card[52]; // deck
	int[] highscore = new int[4];
	GUI gui = new GUI();

	public void GenerateDeck() { // generate
		// Creates the 52 cards, each card has it's respectable suit,rank and value.
		// The value of each card is equaivilant to each rank, and 10 for each shape.
		for (int index = 0; index < 52; index++) {
			int rank = index % 13;
			int value;
			if (rank <= 9) {
				value = 1 + rank;

			} else {
				value = 10;
			}
			int suit = index / 13;
			Card[index] = new Card(suit, rank, value);
		}
	}

	public Card GenerateRandomCard() {// setInfo
		// Generates a card from the created deck, then make it null as it can only be
		// taken once.
		Random rand = new Random();
		while (true) {
			int randomIndex = rand.nextInt(52);
			Card randomCard = Card[randomIndex];
			if (!Objects.isNull(randomCard)) {
				Card[randomIndex] = null;
				return randomCard;
			}
		}
	}

	public void Hit(int i) {
		players[i].addCard(GenerateRandomCard());
	}

	public void GenerateStart() {
		// The game starts with each player having two initial drawn cards.
		for (int i = 0; i < 3; i++) {
			players[i] = new Player();
			for (int j = 0; j < 2; j++) {
				Hit(i);
			}

		}
		players[3] = new Player();
		for (int j = 0; j < 2; j++) {
			Hit(3);
		}
	}

	

	public int GenerateMaxNumber() {
		int max = -1;
		// 20 7 6 17
		for (int i = 0; i < 3; i++) {
			if ((players[i].score > max) && (players[i].score <= 21)) {// yes
				max = players[i].score;// max = 20
			}
		}
		return max;// 20
	}

	public int GenerateMaxIndex(int maxPlayer) {
		int maxIndex = -1;
		for (int i = 0; i < 3; i++) {
			if (maxPlayer == players[i].score) {
				maxIndex = i;
			}
		}
		return maxIndex + 1;
	}

	public void If_Dealer_Bigger_Then_Exit(int maxPlayer) {
		if ((players[3].score > maxPlayer) && (players[3].score <= 21)) {
			System.out.println("The dealer has won with a highscore of: " + players[3].score);
			System.exit(0);
			// if the dealer is bigger than them and <=21, then he won and end the 
		}
	}

	public void IsDealerBigger(int maxPlayer) {
		if ((players[3].score > maxPlayer) && (players[3].score <= 21)) {
			System.out.println("The dealer has won with a highscore of: " + players[3].score);
			// if the dealer is bigger than them and <=21, then he won
		}
	}

	public void IsDealerBust() {
		// if he busts find the maximum of the 3 players
		int maxPlayer = GenerateMaxNumber();
		if (players[3].score > 21) {
			if (CheckPush(maxPlayer) > 1) {
				System.out.println("it's a PUSH");
				// if the maximum is repeated among the 3 players then its push
			} else if (CheckPush(maxPlayer) == 1) {
				// if it's not repeated then that player has won
				System.out
						.println("Player number " + GenerateMaxIndex(maxPlayer) + " has won with a highscore of: " + maxPlayer);
			}
		}
	}

	public int CheckPush(int maxPlayer) {
		// returns 1 if there's no repeated maximum element
		int cnt = 0;
		for (int i = 0; i < 3; i++) {
			if (maxPlayer == players[i].score) {
				cnt++;
			}
		}
		return cnt;
	}

	public Player GenerateMaxPlayer() {
		Player max = players[0];
		int maxI = players[0].score;

		for (int i = 0; i < 4; i++) {
			if (players[i].score > maxI) {
				max = players[i];
				maxI = players[i].score;
			}
		}
		return max;
	}

	public void UpdateScore() {
		for (int i = 0; i < 4; i++) {
			highscore[i] = players[i].score;
		}
	}

	public void GenerateCard() {
		for (int i = 0; i < 4; i++) {
			for (int j = 2; j < 4; j++) {
				players[i].cardObjects[j] = GenerateRandomCard();
				players[i].score = +players[i].cardObjects[j].getValue();
				if (players[i].score > 21) {
					players[i].isBusted = true;
					break;
				}
				if (players[i].score == 21) {
					players[i].isBlackjack = true;
					break;
				}
			}
		}
	}
	
	public  void dealerTurn(GUI gui) {
		int maxPlayer = GenerateMaxNumber();// Check the maximum score of the players
		If_Dealer_Bigger_Then_Exit(maxPlayer);// if the dealer is bigger than them and <=21, then he won
		while ((players[3].score <= maxPlayer) && (players[3].score <= 21)) {
			addCardToDealer(gui);// keep drawing until he either become bigger or busts
		}
		IsDealerBigger(maxPlayer);// if the dealer is bigger than them and <=21, then he won
		IsDealerBust();// if he busts then we check for maximum among the players
	}

	public  void addCardToDealer(GUI gui) {
		Card card = GenerateRandomCard();
		players[3].addCard(card);
		gui.updateDealerHand(card, Card); // for the GUI
	}

	public  void playerTurn(GUI gui) {// Each player can either hit until he's either blackjack or he busts, or
											// stand.
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < 3; i++) {
			String s = "";
			while ((!s.toLowerCase().equals("stand") && !s.toLowerCase().equals("s")) && !players[i].isBusted
					&& !players[i].isBlackjack) {
				System.out.println("Player number: " + (i + 1) + ", Would you like to stand/hit?");
				s = input.next();
				if (s.toLowerCase().equals("hit") || s.toLowerCase().equals("h")) {
					addCardToPlayer(i, gui);
					ShowPlayers();
					CheckForAll();
				}

			}
			ShowPlayers();
		}
	}

	public  void addCardToPlayer(int i, GUI gui) {
		Card card = GenerateRandomCard();
		players[i].addCard(card);
		gui.updatePlayerHand(card, i);
	}

	public  void printBooleans() {
		for (int i = 0; i < 4; i++) {
			System.out
					.println(players[i].isBusted + "" + players[i].isBlackjack + "" + players[i].isMax
							+ "\n" + "---");
		}
	}

	public  void CheckForAll() {
		for (int i = 0; i < 4; i++) {
			if ((players[i].score) > 21) {
				players[i].isBusted = true;
				players[i].isMax = false;
			}
		}
		for (int i = 0; i < 4; i++) {
			if ((players[i].score) == 21) {
				players[i].isBlackjack = true;
			}
		}
		for (int i = 0; i < 4; i++) {
			int max = GenerateMaxNumber();
			if (players[i].score == max && !players[i].isBusted)
				players[i].isMax = true;
			else
				players[i].isMax = false;
		}
	}

	public  void ShowPlayers() {
		System.out.println("\n");
		for (int i = 0; i < 4; i++) {
			System.out.println("Player " + (i + 1) + "'s score Is: " + players[i].score);
		}
		System.out.println("\n");
	}
}