package bjSystem;

import java.util.*;

public class Decks {
	int cardSize = 13*4;
	int nowtop = 0;
	public Card[] Deck = new Card[cardSize];
	String[] suitName = {"Heart","Clover","Spade","Diamond"};
////	ArrayList<Card> card = new ArrayList<Card>();
//	public Deck() {
//		
//	}
	//初期化
	public void Decksetup() {
		 for(int i = 0;i<Deck.length;i++) {
			 Deck[i] = new Card();
			 Deck[i].setSuit(suitName[i/13],i%13+1);		 
		 }
		 nowtop=0;
	}
	//シャッフル
	public void DeckShuffle() {
		 Card temp;
		 Random ran = new Random();
		 for(int i = 0;i<Deck.length;i++) {
			 temp = Deck[i];
			 int num = ran.nextInt(Deck.length);
			 Deck[i] = Deck[num];
			 Deck[num] = temp;
		 }
	}//一枚引く
	public Card Deckdraw() {
		nowtop++;
		return Deck[nowtop];
	}
	@Override
	public String toString(){
		String write="";
		 for(int i = 0;i<Deck.length;i++) {
			 write+="\""+Deck[i]+"\",";
			 if((i+1)%13==0) {
				 write+="\r\n";
			 }
		 }
		return write;
	}
}
