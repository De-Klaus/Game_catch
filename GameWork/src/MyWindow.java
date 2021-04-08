import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyPanel panel;
	public int miss=1; //скорость игры и колличество пропущенных ингридиентов
	
	public MyWindow() {
		
	}
	
	public MyWindow(int s) throws IOException {
		
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Игра. Версия 1.0");
		
		panel = new MyPanel(s);
		getContentPane().add(panel);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				//System.out.println(keyCode);
				if(keyCode == 39) {//нажали клавишу вправо
					if(panel.x > 590) {
						panel.x = 0;
					}
					else {
						panel.x += 30;
					}
				}
				else if(keyCode == 37) {//нажали клавишу влево
					if(panel.x < 12) {
						panel.x = 600;
					}
					
					else {
						panel.x -= 30;
					}
				}
				else if(keyCode == 32) {//нажали клавишу пробела для запуска игры
						panel.start = true;
				}
				else if(keyCode == 27) {//нажали esc для завершения игры
					sent(panel.count, panel.miss);
				}
			}
		});
		setVisible(true);
		//String info = "<html><font color=#ffffdd>Hello</font> world!";
		String info = "Основные параметры игры:\n\n* для того, чтобы запустить игру нажмите клавишу \"пробел\"\n"
				+ "* для завершения игры нажмите клавишу ESC\n"
				+ "* вам даётся возможность собрать в корзину 25 продуктов после чего сеанс завершается\"\n\n"
				+ "                                                            Приятной игры!\n\n";
		JOptionPane.showMessageDialog(null, info);
	}
	
	public void sent(int counts, int miss) {
		setVisible(false);
		String info = "Вы собрали в вашу корзину\n"
				+"                      " + counts
				+ "\n          из возможных \n"
				+ "                      " + (miss)
				+ "\n            продуктов.";
		ImageIcon icon = new ImageIcon("basketFull.png");
		JOptionPane.showMessageDialog(null, info, "Ваш резултат", JOptionPane.DEFAULT_OPTION, icon);
		System.exit(0);
	}
}
