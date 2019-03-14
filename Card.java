import java.util.Arrays;
import java.util.List;

/*
 * Alyza Diaz Rodriguez
 * Chelsea Dillon
 * 
 */

public class Card {
	private Suit s;
	private Rank r;
	
	public Card() {
		s = null;
		r = null;
	}
	
	public Card(String s, String r) {
		this.s = convertSuit(s);
		this.r = convertRank(r);
	}
	
	public Suit getSuit() {
		return s;
	}
	
	public Rank getRank() {
		return r;
	}
	
	public void setCard(Card c) {
		s = c.getSuit();
		r = c.getRank();
	}
	
	private Rank convertRank(String r) {
		List<Rank> a = Arrays.asList(Rank.values());
		for(Rank x: a) {
			if(x.toString().equals(r)) {
				return x;
			}
		}
		return null;
	}
	
	private Suit convertSuit(String s) {
		List<Suit> a = Arrays.asList(Suit.values());
		for(Suit x: a) {
			if(x.toString().equals(s)) {
				return x;
			}
		}
		return null;
	}
	
	public String toString() {
		return getRank()+" OF "+getSuit();
	}
	
	public int suitOrder() {
		switch (s) {
		case CLUBS:
			return 4;
		case DIAMONDS:
			return 3;
		case HEARTS:
			return 2;
		case SPADES:
			return 1;
		default:
			return 0;
		}
	}
	
	public int rankOrder() {
		switch (r) {
		case TWO:
			return 2;
		case THREE:
			return 3;
		case FOUR:
			return 4;
		case FIVE:
			return 5;
		case SIX:
			return 6;
		case SEVEN:
			return 7;
		case EIGHT:
			return 8;
		case NINE:
			return 9;
		case TEN:
			return 10;
		case JACK:
			return 11;
		case QUEEN:
			return 12;
		case KING:
			return 13;
		case ACE:
			return 14;		
		default:
			return 0;
		}
	}
}

enum Suit{
	HEARTS, SPADES, DIAMONDS, CLUBS
}

enum Rank{
	ACE, TWO, THREE, FOUR, FIVE, SIX,
	SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
}



