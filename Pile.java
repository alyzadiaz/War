/*
 * Alyza Diaz Rodriguez
 * Chelsea Dillon
 * 
 */
public class Pile {
	private Card topCard;
	private Card bottomCard;
	
	private Node first;
	private Node last;
	
	private int nCards;
	
	public Pile() {
		topCard = null;
		bottomCard = null;
		
		first = null;
		last = null;
		
		nCards = 0;
	}
	
	public void add(Card c) {
		Node newNode = new Node(c);
		
		if(first==null) {
			first = newNode;
			last = first;
			topCard = newNode.getCard();
			bottomCard = newNode.getCard();
		}else {
			first.setPrevious(newNode);
			newNode.setNext(first);
			first = newNode;
			topCard = newNode.getCard();
		}
		nCards++;
	}
	
	public void addToBottom(Card c) {
		Node newNode = new Node(c);
		
		Node temp = last;
		last.setNext(newNode);
		last = newNode;
		last.setPrevious(temp);
		bottomCard = c;
		
		nCards++;
	}
	
	public void remove(Card c) {
		if(isEmpty()) {
			throw new NullPointerException();
		}else if(searching(c)==-1) {
			throw new IndexOutOfBoundsException();
		}
		
		int place = searching(c);
		
		if(place==0) {
			if(nCards==1) {
				first = null;
				last = null;
				topCard = null;
				bottomCard = null;
				nCards = 0;
			}else {
				first = first.getNext();
				first.setPrevious(null);
				topCard = first.getCard();
				nCards--;
			}
		}else if(place==(nCards-1)) {
			last = last.getPrevious();
			last.setNext(null);
			bottomCard = last.getCard();
			nCards--;
		}else {
			int i=0;
			Node nextNode = first;
			
			for(Node currNode=first;nextNode!=null;currNode=nextNode) {
				nextNode = currNode.getNext();
				
				if(i==place) {
					currNode.getPrevious().setNext(nextNode);
					currNode.getNext().setPrevious(currNode.getPrevious());
					nCards--;
					break;
				}
				i++;
			}
		}
	}
	
	public int searching(Card c) {
		Card[] cards = toArray();
		int index = -1;
		
		for(int i=0;i<cards.length;i++) {
			if(cards[i].equals(c)) {
				index = i;
			}
		}
		return index;
	}
	
	public void shuffle() {
		Card[] cards = toArray();
		
		for(int i=0;i<cards.length;i++) {
			int rng = (int)(Math.random()*cards.length);
			
			Card temp = cards[i];
			cards[i] = cards[rng];
			cards[rng] = temp;
		}
		
		clear();
		for(int i=0;i<cards.length;i++) {
			add(cards[i]);
		}
	}
	
	public void sort() {
		/*
		 * sort by rank and suit
		 * lowest suit: clubs
		 * highest suit: spades
		 * 
		 * lowest rank: one
		 * highest rank: ace
		 * 
		 */
		
		int lowest = 1, count = 0;
		Suit[] suits = {Suit.CLUBS, Suit.HEARTS, Suit.DIAMONDS, Suit.SPADES};
		Card[] cards = toArray(), array = new Card[nCards];
		
		for(int a=0;a<suits.length;a++) {
			for(int b=lowest;b<=14;b++) {
				for(int c=0;c<cards.length;c++) {
					if(cards[c].getSuit().equals(suits[a]) && cards[c].rankOrder()==b) {
						array[count] = cards[c];
						count++;
					}
				}
			}
		}
		
		clear();
		for(int i=array.length-1;i>=0;i--) {
			add(array[i]);
		}
		
	}
	
	public Card getTop() {
		return topCard;
	}
	
	public Card getBottom() {
		return bottomCard;
	}
	
	public boolean isEmpty() {
		return nCards==0;
	}
	
	public void clear() {
		if(isEmpty()) {
			throw new NullPointerException();
		}
		
		while(!isEmpty()) {
			remove(topCard);
		}
		
	}
	
	public int getNumCards() {
		return nCards;
	}
	
	public Card[] toArray() {
		Card[] cards = new Card[getNumCards()];
		
		int i=0;
		Node n = first;
		
		while(n!=null && i<getNumCards()) {
			Card c = n.getCard();
			n = n.getNext();
			cards[i] = c;
			i++;
		}
		
		return cards;
	}
	
	private class Node{
		private Card c;
		private Node next;
		private Node previous;
		
		
		@SuppressWarnings("unused")
		public Node() {
			c = null;
			next = null;
			previous = null;
		}
		
		public Node(Card c) {
			this.c = c;
		}
		
		@SuppressWarnings("unused")
		public void setCard(Card c) {
			this.c = c;
		}
		
		public void setNext(Node next) {
			this.next = next;
		}
		
		public void setPrevious(Node previous) {
			this.previous = previous;
		}
		
		public Card getCard() {
			return c;
		}
		
		public Node getNext() {
			return next;
		}
		
		public Node getPrevious() {
			return previous;
		}
	}
}