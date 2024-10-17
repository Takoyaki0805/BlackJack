package bjSystem;

public class Card {
	String suit;
	int number;
	boolean used;
	Card(){
		number=0;
		suit="none";
	}
	//設定
	public void setSuit(String s,int n) {
		suit = s;
		number = n;
		used=false;
	}
	//取得
	public int getNumber() {
		return number;
	}
	public String getSuit() {
		return suit;
	}
	
	@Override
	public String toString() {
		String str;
		switch(getNumber()) {
		case 1:
			str="A";
			break;
		case 11:
			str="J";
			break;
		case 12:
			str="Q";
			break;
		case 13:
			str="K";
			break;
		default:
			str=""+getNumber();
		}
		return getSuit()+","+str;
	}
}
