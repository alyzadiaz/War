/*
 * Alyza Diaz Rodriguez
 * Chelsea Dillon
 * 
 */
public class Deck extends Pile{
	private final int LIMIT = 52;
		
	public Deck() {
		
	}
	
	public void deal(Hand h) {
		int count = 0;	//keep track of cards dealt
		
		while(count<h.getLimit()) {
			h.add(draw());
			count++;
		}
	}
	
	public Card draw() {
		int rng = (int)(Math.random()*getNumCards());
		Card drawn = getCard(rng);
		
		remove(drawn);
		
		return drawn;
	}
	
	private Card getCard(int x) {
		Card[] cards = toArray();
		return cards[x];
	}
	
	public void shuffle() {
		Card[] cards = toArray();
		int times = 3;	//shuffle the cards 3 times for increased randomness
		
		while(times>0) {
			for(int i=0;i<cards.length;i++) {
				int rng = (int)(Math.random()*cards.length);
				
				Card temp = cards[i];
				cards[i] = cards[rng];
				cards[rng] = temp;
			}
			times--;
		}
		
		clear();
		for(int i=0;i<cards.length;i++) {
			add(cards[i]);
		}
	}
	
	public int getLimit() {
		return LIMIT;
	}
}
