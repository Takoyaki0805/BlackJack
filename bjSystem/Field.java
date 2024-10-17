package bjSystem;

public class Field {
	//両者の手札を設定する
	public Hands player = new Hands(false);
	public Hands enemy = new Hands(true);
	public boolean isturn=true;
	public void Draw(Card c) {
		if(isturn) {
			player.Hand.add(c);
//			isturn=false;
		}else {
			enemy.Hand.add(c);
//			isturn=true;
		}
	}	
	
	//勝ちを判定する
	public int win() {
		if(player.sum()>21) {
			if(enemy.sum()>21) {
				return 2;
			}else {
				return 0;
			}
		}
		if(enemy.sum()>21) {
			return 1;
		}
		if(player.sum()>enemy.sum()) {
			return 1;
		}else if(player.sum()==enemy.sum()){
			return 2;
		}else {
			return 0;
		}
	}
	
	@Override
	public String toString() {
		String str="player:";
		str+=player;
		str+="\r\nenemy:";
		str+=enemy;
		return str;
	}
}
