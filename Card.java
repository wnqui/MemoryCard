import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Card {

	private int x;
	private int y;
	private int number;
	private int size;
	private boolean isOpen;
	private boolean isLife =true;

	public Card(int number) {
		this.number = number;

	}
	//依牌序給坐標
	public void cardPos(int cardPos) {
		
		int width = MemoryCard.WIDTH;
		int height = MemoryCard.HEIGHT;
		
		switch(cardPos) {
		case 1:
			this.x = 50+width/20;
			this.y = 100 +height/25;
			this.size = 70;
			
			break;
		case 2:
			this.x = 50 + (width-100) /4 +width/20;
			this.y = 100 +height/25;
			this.size = 70;
			
			break;
		case 3:
			this.x = 50 +  (width-100) *2/4 +width/20;
			this.y = 100 +height/25;
			this.size = 75;
			
			break;
		case 4:
			this.x = 50 +  (width-100) *3/4 +width/20;
			this.y = 100 +height/25;
			this.size = 70;
			
			break;
		case 5:
			this.x = 50 +width/20;
			this.y = 100 +(height-300) /4 +height/25;
			this.size = 70;
			
			break;
		case 6:
			this.x = 50 +  (width-100) /4 +width/20;
			this.y = 100 +(height-300) /4 +height/25;
			this.size = 70;
			
			break;
		case 7:
			this.x = 50 +  (width-100) *2/4 +width/20;
			this.y = 100 +(height-300) /4 +height/25;
			this.size = 70;
			
			break;
		case 8:
			this.x = 50 +  (width-100) *3/4 +width/20;
			this.y = 100 +(height-300) /4 +height/25;
			this.size = 70;
			
			break;
		case 9:
			this.x = 50 +width/20;
			this.y = 100 +(height-300) *2/4 +height/25;
			this.size = 70;
			
			break;
		case 10:
			this.x = 50 +  (width-100) /4 +width/20;
			this.y = 100 +(height-300) *2/4 +height/25;
			this.size = 70;
			
			break;
		case 11:
			this.x = 50 +  (width-100) *2/4 +width/20;
			this.y = 100 +(height-300) *2/4 +height/25;
			this.size = 70;
			
			break;
		case 12:
			this.x = 50 +  (width-100) *3/4 +width/20;
			this.y = 100 +(height-300) *2/4 +height/25;
			this.size = 70;
			
			break;
		case 13:
			this.x = 50 +width/20 ;
			this.y = 100 +(height-300) *3/4 +height/25;
			this.size = 70;
			
			break;
		case 14:
			this.x = 50 +  (width-100) /4 +width/20;
			this.y = 100 +(height-300) *3/4 +height/25;
			this.size = 70;
			
			break;
		case 15:
			this.x = 50 +  (width-100) *2/4 +width/20;
			this.y = 100 +(height-300) *3/4 +height/25;
			this.size = 70;
			
			break;
		case 16:
			this.x = 50 +  (width-100) *3/4 +width/20;
			this.y = 100 +(height-300) *3/4 +height/25;
			this.size = 70;
			
			break;
			
			
		}
	}

	//畫卡 正反兩面
	public void CardBackground(Graphics g) {
		
		int width = MemoryCard.WIDTH;
		int height = MemoryCard.HEIGHT;
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		
		
		if(this.isOpen == false) {
			g2.setColor(Color.white);
			g2.fillRoundRect(x-width/20, y-height/25, ((width-100)/4), ((height-300)/4),20,20);
			g2.setColor(Color.red);
			g2.fillRect(x+10-width/20, y+10-height/25, ((width-100)/4-20), ((height-300)/4)-20);
			g2.setColor(Color.black);
			g2.drawRoundRect(x-width/20, y-height/25, ((width-100)/4), ((height-300)/4),20,20);
		}
		
		
	}
	//畫卡的圖案
	public void drawIcon(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));

		switch (this.number) {
		case 1:
			// 愛心
			int x1 = x;
			int y1 = y + size / 4;
			// int x2 = x + width /2;
			// int y2 = y + height /4;
			int x3 = x + size;
			int y3 = y + size / 4;
			int x4 = x + 11 * size / 12;
			int y4 = y + size / 2;
			int x5 = x + 3 * size / 4;
			int y5 = y + 3 * size / 4;
			int x6 = x + size / 2;
			int y6 = y + size;
			int x7 = x + size / 4;
			int y7 = y + 3 * size / 4;
			int x8 = x + size / 12;
			int y8 = y + size / 2;
			int[] xPoints_heart = { x3, x4, x5, x6, x7, x8, x1 };
			int[] yPoints_heart = { y3, y4, y5, y6, y7, y8, y1 };

			g2.setColor(Color.red);
			g2.fillArc(x, y, size / 2, size / 2, 0, 180);
			g2.fillArc(x + size / 2, y, size / 2, size / 2, 0, 180);
			g2.fillPolygon(xPoints_heart, yPoints_heart, 7);

			g2.setColor(Color.black);
			g2.drawArc(x, y, size / 2, size / 2, 0, 180);
			g2.drawArc(x + size / 2, y, size / 2, size / 2, 0, 180);
			g2.drawLine(x3, y3, x4, y4);
			g2.drawLine(x4, y4, x5, y5);
			g2.drawLine(x5, y5, x6, y6);
			g2.drawLine(x6, y6, x7, y7);
			g2.drawLine(x7, y7, x8, y8);
			g2.drawLine(x8, y8, x1, y1);

			break;
		case 2:
			// 圓橙
			g2.setColor(Color.orange);
			g2.fillOval(x, y, size, size);
			g2.setColor(Color.yellow);
			g2.fillOval(x + 5, y + 5, size - 10, size - 10);
			g2.setColor(Color.black);
			g2.drawOval(x, y, size, size);
			g2.drawOval(x + 5, y + 5, size - 10, size - 10);
			g2.drawLine(x + 5, (y + size / 2), x + size - 5, y + (size / 2));
			g2.drawLine(x + (size / 2), y + 5, x + (size / 2), y + size - 5);
			g2.drawLine(x + (size / 4), y + (size / 4), x + (size * 3 / 4),
					y + (size * 3 / 4));
			g2.drawLine(x + (size / 4), y + (size * 3 / 4), x + (size * 3 / 4),
					y + (size / 4));
			break;
		case 3:
			// 太極
			
			g2.setColor(Color.white);
			g2.fillArc(x, y, size, size, 270, -180);
			g2.setColor(Color.black);
			g2.fillArc(x, y, size, size, 90, -180);
			g2.setColor(Color.white);
			//g2.fillArc(x+size/4, y, size/2, size/2, 90, -180);
			g2.fillOval(x+size/4, y,  size/2,  size/2);
			g2.setColor(Color.black);
			//g2.fillArc(x+size/4, y+size/2, size/2, size/2, 270, -180);
			g2.fillOval(x+size/4, y+size/2, size/2, size/2);
			g2.setColor(Color.white);
			g2.fillOval(x+size/2-size/12, y+size*4/6, size/6, size/6);
			g2.setColor(Color.black);
			g2.fillOval(x+size/2-size/12, y+size/6, size/6, size/6);
			g2.drawOval(x, y, size, size);
			
			break;
		case 4:
			// 笑臉
			g2.setColor(Color.yellow);
			g2.fillOval(x, y, size, size);
						
			g2.setColor(Color.black);
			g2.drawOval(x, y, size, size);
			
			g2.fillOval(x+size/4, y+size/4, size/5, size/3);
			g2.fillOval(x+size*3/4-size/5, y+size/4, size/5, size/3);
			g2.setColor(Color.red);
			g2.fillArc(x+size/5, y+size*2/5, size*3/5, size/2, 180, 180);
			g2.setColor(Color.black);
			g2.drawLine(x+size/5, y+size*2/5+size/4, x+size*4/5, y+size*2/5+size/4);
			g2.drawArc(x+size/5, y+size*2/5, size*3/5, size/2, 180, 180);
			
			break;
		case 5:
			// 魔方
			int rx1 = x;
			int ry1 = y;
			int rx2 = x+size/3;
			int ry2 = y+size/3;
			int rx3 = x+size;
			int ry3 = y+size;
			int rx4 = x+ 2* size /3;
			int ry4 = y+ 2* size /3;
			
			g2.setColor(Color.blue);
			g2.fillRect(rx1, ry1, size, size);
			g2.setColor(Color.white);
			g2.fillRect(rx1, ry2, size/3, size/3);
			g2.setColor(Color.green);
			g2.fillRect(rx4, ry4, size/3, size/3);
			g2.setColor(Color.red);
			g2.fillRect(rx4, ry2, size/3, size/3);
			g2.setColor(Color.yellow);
			g2.fillRect(rx2, ry4, size/3, size/3);
			
			g2.setColor(Color.black);
			g2.drawRect(rx1, ry1, size, size);
			g2.drawLine(rx2, ry1, rx2, ry3);
			g2.drawLine(rx4, ry1, rx4, ry3);
			g2.drawLine(rx1, ry2, rx3, ry2);
			g2.drawLine(rx1, ry4, rx3, ry4);
			
			break;
		case 6:
			// 鑽石
			int dx1 = x + size / 4;
			int dy1 = y;
			int dx2 = x + 3 * size / 4;
			int dy2 = y;
			int dx3 = x + size;
			int dy3 = y + size / 4;
			int dx4 = x + size / 2;
			int dy4 = y + size;
			int dx5 = x;
			int dy5 = y + size / 4;
			int dx6 = x + size / 2;
			int dy6 = y;
			int dx7 = x + 5 * size / 16;
			int dy7 = y + size / 4;
			int dx8 = x + 11 * size / 16;
			int dy8 = y + size / 4;

			int[] xPoints_diamond = { dx1, dx2, dx3, dx4, dx5 };
			int[] yPoints_diamond = { dy1, dy2, dy3, dy4, dy5 };
			g2.setColor(Color.cyan);
			g2.fillPolygon(xPoints_diamond, yPoints_diamond, xPoints_diamond.length);
			g2.setColor(Color.black);
			g2.drawPolygon(xPoints_diamond, yPoints_diamond, xPoints_diamond.length);
			g2.drawLine(dx5, dy5, dx3, dy3);
			g2.drawLine(dx1, dy1, dx4, dy4);
			g2.drawLine(dx2, dy2, dx4, dy4);
			g2.drawLine(dx6, dy6, dx7, dy7);
			g2.drawLine(dx6, dy6, dx8, dy8);

			break;
		case 7:
			// 星星
			int[] xPoints_star = new int[10];
			int[] yPoints_star = new int[10];
			float PI = 3.14f;
			float theta = PI / 2;
			float step = (2 * PI) / 10;
			boolean out = true;
			float radiusOut = (size) / 5;
			float radiusIn = (size) / 2;
			float radius = 0;
			for (int i = 0; i < 10; i++) {
				if (out) {
					radius = radiusOut;
				} else {
					radius = radiusIn;
				}
				xPoints_star[i] = x + size / 2 + (int) (Math.cos(theta) * radius);
				yPoints_star[i] = y + size / 2 + (int) (Math.sin(theta) * radius);
				theta += step;
				out = !out;
			}

			g2.setColor(Color.magenta);
			g2.fillPolygon(xPoints_star, yPoints_star, 10);
			g2.setColor(Color.black);
			g2.drawPolygon(xPoints_star, yPoints_star, 10);

			break;
		case 8:
			// 方手機
			g2.setColor(Color.white);
			g2.fillRect(x+size/8, y, 6*size/8, size);
			g2.setColor(Color.gray);
			g2.fillRect(x+size/8+(size/10), y+(size/10), 6*size/8-(size/5), size-(size*2/6));
			
			g2.setColor(Color.black);
			g2.drawRect(x+size/8, y, 6*size/8, size);
			g2.drawRect(x+size/8+(size/10), y+(size/10), 6*size/8-(size/5), size-(size*2/6));
			g2.drawOval(x+size/2-size/10, y+size*7/8, size/5, size/10);
			break;
		}

	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	public Card setPos(int x, int y ,int size) {
		this.x = x;
		this.y = y;
		this.size = size;
		return this;
	}

	public int getNumber() {
		return this.number;
	}
	public boolean getOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
		
	}
	public boolean getLife() {
		return isLife;
	}
	public void setLife(boolean isLife) {
		this.isLife = isLife;
	}

}
