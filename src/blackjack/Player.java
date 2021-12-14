package blackjack;

public class Player {
    public String name;
    private int numberOfCards = 0;
    public int score = 0;
    Card[] cardObjects = new Card[11]; 
    boolean isBlackjack = false;
    boolean isBusted = false;
    boolean isMax = false;

    public void addCard(Card card) {
        if (numberOfCards < 11)
            cardObjects[numberOfCards] = card;
        numberOfCards++;
        score += card.getValue();
    }

    public boolean getIsBlackjack() {
        return isBlackjack;
    }

    public void setIsBlackjack(boolean isBlackjack) {
        this.isBlackjack = isBlackjack;
    }

    public boolean getIsLost() {
        return isBusted;
    }

    public void setIsLost(boolean isLost) {
        this.isBusted = isLost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setCardObjects(Card[] cardObjects) {
        this.cardObjects = cardObjects;
    }

    public Card[] getCardObjects() {
        return this.cardObjects;
    }
}
