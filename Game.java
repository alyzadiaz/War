import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Alyza Diaz Rodriguez
 * Chelsea Dillon
 * 
 */
public class Game {
	public static void main(String[] args) {
		
		System.out.println("Welcome to the game of War!");
		System.out.println("Would you like to hear the rules before playing? (Y/N) ");
		Scanner response = new Scanner(System.in);
		String yn = response.next();
		
		while(!(yn.equalsIgnoreCase("Y")) && !(yn.equalsIgnoreCase("N"))) {
			yn = response.next();
		}
		
		if(yn.equalsIgnoreCase("Y")) {
			rules();
		}
		
		Player player1 = new Player(), player2 = new Player();
		
		
		System.out.println("What is Player 1's name? ");
		String p1Name = response.next();
		player1.setName(p1Name);
			
		System.out.println("What is Player 2's name? ");
		String p2Name = response.next();
		player2.setName(p2Name);
		System.out.println();
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Deck deck = new Deck();
		Card[] allCards = new Card[52];
		File listOfCards = new File("C:\\Users\\diazrodrigueza\\workspaceFall2018\\Project 4\\src\\List of 52 Cards");
		
		try {
			Scanner in = new Scanner(listOfCards);
			
			while(in.hasNextLine()) {
				for(int i=0;i<allCards.length;i++) {
					allCards[i] = new Card(in.next(), in.next());
				}
			}
			
			for(int i=allCards.length-1;i>=0;i--) {
				deck.add(allCards[i]);
			}
			
			deck.shuffle();
			
			in.close();
		}catch(FileNotFoundException e) {
			System.out.println("File not found.");
		}
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		boolean play = true;
		
		while(play) {
			deck.deal(player1.getHand());
			deck.deal(player2.getHand());
			
			player1.getHand().shuffle();
			player2.getHand().shuffle();
			
			boolean inProgress = true;
			
			System.out.println("Press 'Q' at any time to quit.");
			
			do {
				System.out.println("It's "+player1.getName()+"'s turn. 'S': shuffle hand 'D': draw. ");
				String key = response.next();
				while(!key.equalsIgnoreCase("d") && !key.equalsIgnoreCase("Q") && !key.equalsIgnoreCase("S")) {
					key = response.next();
				}
				
				if(key.equalsIgnoreCase("Q")) {
					System.out.println("Thank you for playing!");
					System.exit(0);
				}else if(key.equalsIgnoreCase("S")) {
					player1.getHand().shuffle();
				}
				
				Card drawn1 = player1.draw();
				
				
				System.out.println("It's "+player2.getName()+"'s turn. 'S': shuffle hand 'D': draw. ");
				key = response.next();
				while(!key.equalsIgnoreCase("d") && !key.equalsIgnoreCase("Q") && !key.equalsIgnoreCase("S")) {
					key = response.next();
				}
				
				if(key.equalsIgnoreCase("Q")) {
					System.out.println("Thank you for playing!");
					System.exit(0);
				}else if(key.equalsIgnoreCase("S")) {
					player2.getHand().shuffle();
				}
				
				Card drawn2 = player2.draw();
				
				compareCards(drawn1, drawn2, player1, player2, 1);
				
				System.out.println(player1.getName()+"'s points: "+player1.getScore());
				System.out.println(player2.getName()+"'s points: "+player2.getScore()+"\n");
				
				if(player1.getHand().isEmpty() || player2.getHand().isEmpty()) {
					if(player1.getScore()>player2.getScore()) {
						System.out.println(player1.getName()+" is the winner!");
					}else {
						System.out.println(player2.getName()+" is the winner!");
					}
					
					if(!playAgain()) {
						play = false;
						System.out.println("Thank you for playing!");
						response.close();
						System.exit(0);
					}
				}
				
			}while(inProgress);
		
		}
		
	}
	
	public static void rules() {
		System.out.println("The game of War is a two player game, each receiving 26 cards in their hand. \n"
				+ "Each player draws a card from their hand and the player with the higher card gains a point \n"
				+ "and both cards are discarded. \n");
		System.out.println("If the cards are the same rank, it is War, and each player draws four cards. \n"
				+ "The last of the four cards is compared and the player with the higher card gains a point for \n"
				+ "every card in both piles. If the last cards are the same rank again, the process repeats until \n"
				+ "there is a higher card. \n");
		System.out.println("The game of War ends when one player has no cards left. The player with the most points wins. \n");
		System.out.println("Have fun!\n");
	}
	
	public static void compareCards(Card a, Card b, Player p1, Player p2, int w) {
		System.out.println(p1.getName()+"'s "+a.toString()+" vs. "+p2.getName()+"'s "+b.toString());
		
		int warScore = w;
		
		if(a.rankOrder()>b.rankOrder()) {
			for(int i=0;i<warScore;i++) {
				p1.givePoint();
			}
			System.out.println(p1.getName()+" wins this round. \n");
		}else if(b.rankOrder()>a.rankOrder()) {
			for(int i=0;i<warScore;i++) {
				p2.givePoint();
			}
			System.out.println(p2.getName()+" wins this round. \n");
		}else if(a.rankOrder()==b.rankOrder()) {
			int i=0;
			Card c = new Card();
			while(!p1.getHand().isEmpty() && i<=3) {
				c.setCard(p1.draw());;
				i++;
			}
			int j=0;
			Card d = new Card();
			while(!p2.getHand().isEmpty() && j<=3) {
				d.setCard(p2.draw());
				j++;
			}
			warScore++;
			compareCards(c, d, p1, p2, warScore);
		}
	}
	
	public static boolean playAgain() {
		Scanner in = new Scanner(System.in);
		System.out.println("Would you like to play again? (Y/N)");
		String again = in.next();
		
		while(!(again.equalsIgnoreCase("Y")) && !(again.equalsIgnoreCase("N"))) {
			again = in.next();
		}
		
		in.close();
		
		if(again.equalsIgnoreCase("Y")) {
			return true;
		}else {
			return false;
		}
	}
}
