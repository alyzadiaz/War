/*
 * Alyza Diaz Rodriguez
 * Chelsea Dillon
 * 
 */
public class Hand extends Pile{
	private final int LIMIT = 26;
	
	public Hand() {
		
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
	
	public int getLimit() {
		return LIMIT;
	}
}