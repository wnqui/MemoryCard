import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MemoryCard implements ActionListener, MouseListener, MouseMotionListener, KeyListener {

	// 常數
	public static final int WIDTH = 600;
	public static final int HEIGHT = 800;
	// 物件
	public static MemoryCard MC;
	JFrame JF;
	myPanel MP;
	public static ArrayList<Card> card = new ArrayList<Card>();
	public static ArrayList<Card> shuffleCard = new ArrayList<Card>();
	public static ArrayList<Card> play = new ArrayList<Card>();
	public static Card[] choose = new Card[2];
	// 控制
	int mouseX;
	int mouseY;
	int mouseClick;
	int ticks;
	int time;
	int takeTime;
	int chooseTimes;
	int ms = 100;
	boolean shuffle = true;
	boolean show = true;
	boolean isStart; // 遊戲開始才能選牌
	boolean isOver;// 遊遊戲結束，結算。
	int showCount = 0;
	int choose1 = 0;
	int choose2 = 0;
	int closeTime = 10;
	String m;
	String s;

	public MemoryCard() {

		JF = new JFrame("Memory card Game");
		JF.setBounds(100, 100, WIDTH, HEIGHT);
		JF.setVisible(true);
		JF.setResizable(false);
		JF.setAlwaysOnTop(true);
		JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MP = new myPanel();
		JF.add(MP);

		JF.addMouseListener(this);
		JF.addMouseMotionListener(this);
		JF.addKeyListener(this);

		Timer T = new Timer(ms, this);
		T.start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ticks++;
		time++;

		// 洗牌
		if (shuffle) {
			// 重置 play牌堆
			if (play.size() > 0) {
				play.clear();
			}
			System.out.println("paly牌堆" + play.size() + "張");

			shuffleCard();
			shuffle = false;
			show = true;
		}
		// 展示一段時間蓋上
		if (show) {
			for (int i = 0; i < play.size(); i++) {
				play.get(i).setOpen(true);
			}
			show = false;
			showCount = 0;
		}
		if (ticks % 2 == 0) {
			showCount++;
			if (showCount == closeTime) {
				for (int i = 0; i < play.size(); i++) {
					play.get(i).setOpen(false);
				}
				isStart = true;
			}
		}

		// 判斷區
		if (choose2 != 0 && ticks > 3) {
			// ticks 翻牌延遲
			if (play.get(choose1 - 1).getNumber() == play.get(choose2 - 1).getNumber()) {
				System.out.println("相同");
				play.get(choose1 - 1).setLife(false);
				play.get(choose2 - 1).setLife(false);
				choose1 = 0;
				choose2 = 0;
			} else {
				System.out.println("不同");
				play.get(choose1 - 1).setOpen(false);
				play.get(choose2 - 1).setOpen(false);
				choose1 = 0;
				choose2 = 0;
			}

		}

		// 剩餘牌組檢查
		if (isStart) {
			isOver = true;
			for (int i = 0; i < play.size(); i++) {
				if (play.get(i).getLife()) {
					isOver = false;
				}
			}

			if (isOver) {
				isStart = false;

				takeTime = time * ms / 1000;

			}
		}

		MP.repaint();
	}

	public void repaint(Graphics g) {
		// 白底
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g.setColor(Color.white);
		g2.fillRect(0, 0, WIDTH, HEIGHT);
		// 遊戲名稱
		g2.setFont(new Font("", 1, 60));
		g2.setColor(Color.gray);
		g2.drawString("Memory Card", 103, 68);
		g2.setColor(Color.black);
		g2.drawString("Memory Card", 100, 65);

		if (choose1 != 0) {
			chooseRect(play.get(choose1 - 1).getX() - WIDTH / 20, play.get(choose1 - 1).getY() - HEIGHT / 25, g);
			if (choose2 != 0) {
				chooseRect(play.get(choose2 - 1).getX() - WIDTH / 20, play.get(choose2 - 1).getY() - HEIGHT / 25, g);
			}
		}
		// 牌堆區域框線,結束畫win
		if (isOver) {
			Win(g);

		} else {
			Grid(g);
		}

		// 將遊戲牌堆畫出來
		for (int i = 0; i < play.size(); i++) {
			if (play.get(i).getLife()) {
				play.get(i).drawIcon(g);
				play.get(i).CardBackground(g);
				// System.out.println(play.get(i).getNumber());
			}
		}

	}

	// 畫選擇框
	public void chooseRect(int x, int y, Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.yellow);
		g2.fillRect(x, y, ((WIDTH - 100) / 4), ((HEIGHT - 300) / 4));

	}

	// 畫格子
	public void Grid(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.black);
		g2.drawRect(50, 100, WIDTH - 100, HEIGHT - 300);
		g2.drawLine(50 + (WIDTH - 100) / 4, 100, 50 + (WIDTH - 100) / 4, 100 + HEIGHT - 300);
		g2.drawLine(50 + (WIDTH - 100) * 2 / 4, 100, 50 + (WIDTH - 100) * 2 / 4, 100 + HEIGHT - 300);
		g2.drawLine(50 + (WIDTH - 100) * 3 / 4, 100, 50 + (WIDTH - 100) * 3 / 4, 100 + HEIGHT - 300);
		g2.drawLine(50, 100 + (HEIGHT - 300) / 4, 50 + WIDTH - 100, 100 + (HEIGHT - 300) / 4);
		g2.drawLine(50, 100 + (HEIGHT - 300) * 2 / 4, 50 + WIDTH - 100, 100 + (HEIGHT - 300) * 2 / 4);
		g2.drawLine(50, 100 + (HEIGHT - 300) * 3 / 4, 50 + WIDTH - 100, 100 + (HEIGHT - 300) * 3 / 4);
	}

	// 畫出結果
	public void Win(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.setFont(new Font("", 1, 100));
		g2.setColor(Color.gray);
		g2.drawString("W I N", WIDTH / 2 - 132, HEIGHT / 2 - 97);
		if (ticks % 3 == 0) {
			g2.setColor(Color.red);
		} else {
			g2.setColor(Color.yellow);
		}
		g2.drawString("W I N", WIDTH / 2 - 135, HEIGHT / 2 - 100);

		g2.setColor(Color.black);
		if (takeTime >= 60) {
			m = takeTime / 60 + " m";
			s = takeTime % 60 + " s";
		} else {
			m = "";
			s = takeTime + " s";
		}
		g2.setFont(new Font("", 1, 30));
		g2.drawString("TIME: " + m + s, WIDTH / 2 - 135, HEIGHT / 2);
		g2.drawString("FLOP: " + chooseTimes + " times", WIDTH / 2 - 135, HEIGHT / 2 + 50);

		g2.drawString("- Tpye R to new game -", WIDTH / 5, HEIGHT / 2 + 200);
	}

	public static void main(String[] args) {
		// 創建主牌堆
		for (int i = 0; i < 2; i++) {
			for (int j = 1; j < 9; j++) {
				card.add(new Card(j));
			}
		}
		MC = new MemoryCard();
	}

	public void shuffleCard() {
		// 主牌堆 複製到 洗牌堆 (重置洗牌堆)
		for (int i = 0; i < card.size(); i++) {
			shuffleCard.add(card.get(i));
		}
		// 洗牌後 移到 遊戲牌堆
		while (shuffleCard.size() > 0) {
			// 洗牌堆 隨機取一張加到 遊戲牌堆，直到洗牌堆清空
			int r = (int) (Math.random() * shuffleCard.size());
			play.add(shuffleCard.get(r));
			shuffleCard.remove(r);
		}
		// 遊戲牌 依順序 加座標;
		for (int i = 0; i < play.size(); i++) {
			play.get(i).cardPos(i + 1);
			play.get(i).setLife(true);
			System.out.print(play.get(i).getNumber() + " ");
		}
		System.out.println("");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseX = e.getX() - 3;
		mouseY = e.getY() - 32;
		System.out.println("(" + mouseX + "," + mouseY + ")");
		// 點擊時 判斷點擊區域
		if (mouseX > 50 && mouseX < 50 + (WIDTH - 100) / 4) {
			if (mouseY > 100 && mouseY < 100 + (HEIGHT - 300) / 4) {
				mouseClick = 1;
			} else if (mouseY > 100 + (HEIGHT - 300) / 4 && mouseY < 100 + (HEIGHT - 300) * 2 / 4) {
				mouseClick = 5;
			} else if (mouseY > 100 + (HEIGHT - 300) * 2 / 4 && mouseY < 100 + (HEIGHT - 300) * 3 / 4) {
				mouseClick = 9;
			} else if (mouseY > 100 + (HEIGHT - 300) * 3 / 4 && mouseY < 100 + (HEIGHT - 300)) {
				mouseClick = 13;
			} else {
				mouseClick = 0;
			}

		} else if (mouseX > 50 + (WIDTH - 100) / 4 && mouseX < 50 + (WIDTH - 100) * 2 / 4) {
			if (mouseY > 100 && mouseY < 100 + (HEIGHT - 300) / 4) {
				mouseClick = 2;
			} else if (mouseY > 100 + (HEIGHT - 300) / 4 && mouseY < 100 + (HEIGHT - 300) * 2 / 4) {
				mouseClick = 6;
			} else if (mouseY > 100 + (HEIGHT - 300) * 2 / 4 && mouseY < 100 + (HEIGHT - 300) * 3 / 4) {
				mouseClick = 10;
			} else if (mouseY > 100 + (HEIGHT - 300) * 3 / 4 && mouseY < 100 + (HEIGHT - 300)) {
				mouseClick = 14;
			} else {
				mouseClick = 0;
			}

		} else if (mouseX > 50 + (WIDTH - 100) * 2 / 4 && mouseX < 50 + (WIDTH - 100) * 3 / 4) {
			if (mouseY > 100 && mouseY < 100 + (HEIGHT - 300) / 4) {
				mouseClick = 3;
			} else if (mouseY > 100 + (HEIGHT - 300) / 4 && mouseY < 100 + (HEIGHT - 300) * 2 / 4) {
				mouseClick = 7;
			} else if (mouseY > 100 + (HEIGHT - 300) * 2 / 4 && mouseY < 100 + (HEIGHT - 300) * 3 / 4) {
				mouseClick = 11;
			} else if (mouseY > 100 + (HEIGHT - 300) * 3 / 4 && mouseY < 100 + (HEIGHT - 300)) {
				mouseClick = 15;
			} else {
				mouseClick = 0;
			}

		} else if (mouseX > 50 + (WIDTH - 100) * 3 / 4 && mouseX < 50 + (WIDTH - 100)) {
			if (mouseY > 100 && mouseY < 100 + (HEIGHT - 300) / 4) {
				mouseClick = 4;
			} else if (mouseY > 100 + (HEIGHT - 300) / 4 && mouseY < 100 + (HEIGHT - 300) * 2 / 4) {
				mouseClick = 8;
			} else if (mouseY > 100 + (HEIGHT - 300) * 2 / 4 && mouseY < 100 + (HEIGHT - 300) * 3 / 4) {
				mouseClick = 12;
			} else if (mouseY > 100 + (HEIGHT - 300) * 3 / 4 && mouseY < 100 + (HEIGHT - 300)) {
				mouseClick = 16;
			} else {
				mouseClick = 0;
			}
		} else {
			mouseClick = 0;
		}

		System.out.println("點擊" + mouseClick + "區");
		if (isStart) {
			if (choose1 == 0 && mouseClick != 0 && play.get(mouseClick - 1).getLife()) {
				choose1 = mouseClick;
				play.get(choose1 - 1).setOpen(true);
				System.out.println("選擇一");
			} else if (choose1 != 0 && choose2 == 0 && mouseClick != 0 && play.get(mouseClick - 1).getLife()) {
				choose2 = mouseClick;
				play.get(choose2 - 1).setOpen(true);
				System.out.println("選擇二");
				// 翻牌計數
				chooseTimes++;
				// 翻牌延連歸零
				ticks = 0;
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent arg0) {}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_R) {
			shuffle = true;
			show = true;
			isOver = false;
			time = 0;
			chooseTimes = 0;
			choose1 = 0;
			choose2 = 0;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

//end
}

class myPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		MemoryCard.MC.repaint(g);
	}

}
