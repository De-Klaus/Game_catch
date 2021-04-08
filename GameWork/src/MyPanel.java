import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MyPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MyWindow wind;
	public int x = 400;//координата корзины по оси X
	public int yE = -1300;//координата egg по оси Y
	public int yC = -200;//координата cheese по оси Y
	public int yB = -800;//координата bread по оси Y
	private Image basket, egg, cheese, bread, fon;//элементы участвующие в анимации
	private Timer timerDraw;
	public int s=0, count = 0, miss=0; //s - скорость падения предмата, count - колличество пойманых предметов, miss - колличесво всего упавших предметов
	public boolean operation, counter1, counter2, counter3, start; //логические выражения которые отслеживают пересечения координат с ловящим объектом
	
	public MyPanel(int s) throws IOException {
		this.s=s;
		Font font = new Font("Arial", Font.BOLD, 20);
		JLabel text = new JLabel();
		text.setForeground(Color.GREEN);
		text.setFont(font);
		add(text);
		basket = ImageIO.read(new File("basket.png"));
		egg = ImageIO.read(new File("egg.png"));
		cheese = ImageIO.read(new File("cheese.png"));
		bread = ImageIO.read(new File("bread.png"));
		fon = ImageIO.read(new File("fon.jpg"));
		timerDraw = new Timer((75-(10*s)), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text.setText("Ваш счёт: "+count);
				if(start) {
					if(yE<600||yC<600||yB<600) {
						yE+=10; yC+=10; yB+=10;//увелечение координаты Y для анимации падения
						try {
							toCatch(yE, yC, yB, x);//передача координат для расчёта пойманого предмета
						} catch (Exception e1) {							
							e1.printStackTrace();
						}
					}
					else {
						reStart();//присвоение рандомного значения координат
					}
				}
				repaint();
			}
		});		
		timerDraw.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(fon,0,0,null);
		g.drawImage(egg,0,yE,null);
		g.drawImage(cheese,290,yC,null);
		g.drawImage(bread,600,yB,null);
		g.drawImage(basket,x,460, null);
	}
	
	public void toCatch(int eggY, int cheeseY, int breadY, int basketX) throws Exception {	//метод который прибавляет очки за пойманый предмет
		if(eggY<502 && (eggY+156)>502 && (basketX+100)>0 && (basketX+100)<154||cheeseY<502 && (cheeseY+128)>502 && (basketX+100)>290 && (basketX+100)<440||breadY<502 && (breadY+128)>502 && (basketX+100)>600 && (basketX+100)<750) {
			calculate();
			reStart();
		}
		else if(yE==200||yC==200||yB==200) {
			miss++;
		}
	}
	
	public void calculate() { //счётчик пойманого
		count++;
		System.out.println("Ваш счёт равен = " + count+" "+miss);
		if(miss>25) {
	    	   wind = new MyWindow();
	    	   wind.sent(count,miss);
	       }
	}
	
	public void reStart() {
		int[] array = {-1300, -200, -800};
		int y1=0, y2=0, y3=0;
        while(y1==y2||y2==y3||y1==y3){ //рандомное присвоение стартовой координаты
        	y1 = array[ new Random().nextInt(array.length)];
        	y2 = array[ new Random().nextInt(array.length)];
        	y3 = array[ new Random().nextInt(array.length)];
        }
        yE=y1; yC=y2; yB=y3;
	}
}
