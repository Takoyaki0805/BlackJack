package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.*;

import bjSystem.Decks;
import bjSystem.Field;

//画面描画とゲームシステム
public class Panel extends JPanel implements ActionListener,MouseListener{
	JButton  hit = new JButton  ("hit");
	JButton  stand = new JButton  ("stand");
	JButton  bet = new JButton  ("bet");
	JLabel cardA1 = new JLabel();
	JLabel cardA2 = new JLabel();
	JLabel cardA3 = new JLabel(); 
	JLabel cardA4 = new JLabel(); 
	JLabel cardA5 = new JLabel(); 
	JLabel cardA6 = new JLabel(); 
	JLabel cardB1 = new JLabel(); 
	JLabel cardB2 = new JLabel(); 
	JLabel cardB3 = new JLabel(); 
	JLabel cardB4 = new JLabel(); 
	JLabel cardB5 = new JLabel(); 
	JLabel cardB6 = new JLabel();
	JLabel[] LabelName = {cardA1,cardA2,cardA3,cardA4,cardA5,cardA6,
						  cardB1,cardB2,cardB3,cardB4,cardB5,cardB6};
	JLabel text = new JLabel();
	JLabel pLabel = new JLabel();
	JLabel eLabel = new JLabel();
	JLabel ptotal = new JLabel();
	JLabel etotal = new JLabel();
	JButton upper = new JButton("up");
	JButton lower = new JButton("down");
	JLabel mText = new JLabel();
	Decks Deck = new Decks();
	Field f = new Field();
	boolean reset=true;
	boolean drawable=true;
	boolean isGameover=true;
	int money = 100;
	int betM = 0;
	boolean oneMore = false;
	public Panel() {
		this.setLayout(null);
		hit.setBounds(340, 380, 100, 30);
		stand.setBounds(450, 380, 100, 30);
		bet.setBounds(250, 380, 80, 30);
		text.setBounds(205, 110, 200, 200);
		pLabel.setBounds(230, 230, 200, 200);
		eLabel.setBounds(235, -30, 200, 200);
		ptotal.setBounds(500, 150, 200, 200);
		etotal.setBounds(500, 50, 200, 200);
		upper.setBounds(170, 350, 70, 40);
		lower.setBounds(170, 390, 70, 40);
		mText.setBounds(20, 290, 200, 200);
		text.setFont(new Font("Ariel", Font.PLAIN, 64));
		pLabel.setFont(new Font("Ariel", Font.PLAIN, 32));
		eLabel.setFont(new Font("Ariel", Font.PLAIN, 32));
		ptotal.setFont(new Font("Ariel", Font.PLAIN, 16));
		etotal.setFont(new Font("Ariel", Font.PLAIN, 16));
		mText.setFont(new Font("Ariel", Font.PLAIN, 16));
		if(reset) {
			setup();
			reset=false;
		}
		add(hit);
		add(stand);
		add(bet);
		add(text);
		add(pLabel);
		add(eLabel);
		add(ptotal);
		add(etotal);
		add(upper);
		add(lower);
		add(mText);
		for(int j=0;j<LabelName.length;j++) {
			if(j<6) {
				LabelName[j].setBounds(100+j*70,150,200,200);
			}else {
				LabelName[j].setBounds(100+(j-6)*70,50,200,200);
			}
			add(LabelName[j]);
		}
		hit.addActionListener(this);
		stand.addActionListener(this);
		bet.addActionListener(this);
		upper.addActionListener(this);
		lower.addActionListener(this);
		addMouseListener(this);
	}
	public void paint(Graphics g) {
		super.paint(g);
//		g.drawString(""+f, 100,100 );
		pLabel.setText("PLAYER");
		eLabel.setText("ENEMY");
		ptotal.setText("P:TOTAL\n"+f.player.sum()+"");
		etotal.setText("E:TOTAL\n"+f.enemy.sum()+"");
		mText.setText("所持金:"+money+"/掛け金:"+betM);
		for(int i=0;i<LabelName.length;i++) {
			if(i<6&&i<f.player.Hand.size()) {
//				System.out.println(getClass().getResource("card_back.png"));
//				System.out.println("/card_"+f.player.Hand.get(i).getSuit()+"_"+f.player.Hand.get(i).getNumber()+".png");
				ImageIcon icon = new ImageIcon(getClass().getResource("card_"+f.player.Hand.get(i).getSuit()+"_"+f.player.Hand.get(i).getNumber()+".png"));
//				System.out.println(icon);
				LabelName[i].setIcon(icon);
				
			}
			if(i>=6&&i-6<f.enemy.Hand.size()) {
				if(f.enemy.close&&i-6==0) {
					ImageIcon icon = new ImageIcon(getClass().getResource("card_back.png"));
					//				System.out.println(icon);
					LabelName[i].setIcon(icon);	
				}else {
	//				System.out.println(getClass().getResource("card_back.png"));
	//				System.out.println("/card_"+f.player.Hand.get(i).getSuit()+"_"+f.player.Hand.get(i).getNumber()+".png");
					ImageIcon icon = new ImageIcon(getClass().getResource("card_"+f.enemy.Hand.get(i-6).getSuit()+"_"+f.enemy.Hand.get(i-6).getNumber()+".png"));
	//				System.out.println(icon);
					LabelName[i].setIcon(icon);
				}
			}
		}
		if(!drawable&&f.enemy.sum()>=17) {
			String t;
			if(f.win()==0) {
				t="LOSE";
				text.setForeground(Color.BLUE);
			}else if(f.win()==1) {
				t="WIN!";
				text.setForeground(Color.RED);
			}else {
				t="DRAW";
				text.setForeground(Color.BLACK);

			}
			text.setText(t);
			isGameover=true;
 		}
	}
	public void setup() {
//		Field f = new Field();
//		f.enemy.close=true;
		drawable=true;
		Deck.Decksetup();
		Deck.DeckShuffle();
		for(int k=0;k<LabelName.length;k++) {
			LabelName[k].setIcon(null);
		}
		for(int i=0;i<4;i++) {
			if(i<2) {f.isturn=true;}else{f.isturn=false;}
			f.Draw(Deck.Deckdraw());
		}
		System.out.println(f);
		text.setText("");
		repaint();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
//		System.out.println(e.getSource());
		JButton eve = (JButton)e.getSource();
		if(eve.getText().equals("hit")&&f.player.sum()<=21&&drawable&&!isGameover) {
			f.isturn=true;
			f.Draw(Deck.Deckdraw());
			System.out.println(f);
			repaint();
		}
		
		if(eve.getText().equals("stand")&&!isGameover) {
			f.isturn=false;
			f.enemy.close=false;
//				f.Draw(Deck.Deckdraw());
				System.out.println(f);
			System.out.println(f);
//			System.out.println(f.win());
			drawable=false;
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}

//			System.out.println(Deck);
//			reset=true;
//			f.player.Hand.clear();
//			f.enemy.Hand.clear();
//			f.enemy.close=true;
//			setup();
		}
		
		if(eve.getText().equals("bet")&&isGameover) {
			reset=true;
			f.player.Hand.clear();
			f.enemy.Hand.clear();
			f.enemy.close=true;
			setup();
			isGameover=false;
//			System.out.println("sss");
			oneMore = false;
//			money -= betM;
			repaint();
		}
		if(eve.getText().equals("up")&&money>0) {
			money-=10;
			betM+=10;
			repaint();
//			System.out.println("YO");
		}
		if(eve.getText().equals("down")&&betM>0) {
			money += 10;
			betM -= 10;
			repaint();
//			System.out.println("OY");
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		repaint();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
		// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		if(!drawable&&f.enemy.sum()<17) {
			f.isturn=false;
			f.enemy.close=false;
			f.Draw(Deck.Deckdraw());
		}
		if(!drawable&&f.enemy.sum()>=17&&!oneMore) {
			System.out.println(f.win());
			if(f.win()==0) {
				betM=0;
			}else if(f.win()==1) {
				money+=betM*2;
				betM=0;
			}else {
				money+=betM;
				betM=0;
			}
			oneMore=true;
 		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	

	
	
	
}
