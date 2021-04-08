import java.io.IOException;

import javax.swing.JOptionPane;

public class MyGame {

	public static void main(String[] args) throws IOException {
		String t = JOptionPane.showInputDialog("Введите сложность игры");
		if(t.matches("[-+]?\\d+")) {
			int s = Integer.parseInt(t);
			if(s >= 1 && s <= 7) {
				new MyWindow(s);
			}
			else {
				JOptionPane.showMessageDialog(null, "Вы ввели некорректно сложность игры!");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Вы ввели не коректное значение");
		}
	}
}
