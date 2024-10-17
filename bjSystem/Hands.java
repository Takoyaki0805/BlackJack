package bjSystem;

import java.util.ArrayList;

public class Hands {
	public boolean close;
	Hands(boolean c){close=c;}
	public ArrayList<Card> Hand = new ArrayList<Card>();
	//合計
	public int sum() {
		int result=0;
		int one=0;
		for(int i=0;i<Hand.size();i++) {
			if(close&&i==0) {
				continue;
			}
			if(Hand.get(i).getNumber()==1) {
				one++;
			}else {
				result+=Hand.get(i).getNumber();
			}
		}
		for(int j=0;j<one;j++) {
			if(result<11) {
				result+=11;
			}else {
				result++;
			}
		}
		return result;
	}
	@Override
	public String toString() {
		String str="";
		for(int k=0;k<Hand.size();k++) {
			if(close&&k==0) {
				str+="\"*\",";
			}else {
				str+="\""+Hand.get(k)+"\",";
			}
		}
		str+="合計:"+sum();
		return str;
	}
	
	
}
