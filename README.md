# BlackJack in Java (without bets)

The game can be summarized with the following steps:
1. Deck creation &amp; initial card draw.  
a. Create deck.  
b. Do the initial card draw for the player and the dealer (a total of 4 cards).  
c. Remove drawn cards from the deck.  

2. Player’s turn (repeated 3 times for the 3 players)  
a. Hit or Stand  
b. Update the player’s score.  

4. Dealer’s turn  
a. Hit until he either scores 21 (BLACKJACK), a score more than the maximum score of the 3 players or bust.  

5. Make decisions   
  a. someone WINS.  
  b. it's a PUSH.
 ## Basic running scenario.
<img src="https://github.com/kareemgamal1/BlackJack-in-Java/blob/main/Initial%20draw.PNG" />  
<img src="https://github.com/kareemgamal1/BlackJack-in-Java/blob/main/Basic%20run%20scenario.PNG" />
(The dealer kept drawing cards until he busted)

### In order for the GUI to work properly  
1-Put the .java files in the same directory as your Java file at [...\NetBeans
(or eclipse or visual studio code) \BlackJack\src\blackjack]  
2-Put the images (4 images) in the same directory as your project. [...\NetBeans (or eclipse
or visual studio code) \BlackJack]
