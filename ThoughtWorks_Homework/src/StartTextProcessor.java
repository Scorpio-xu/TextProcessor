import java.util.Scanner;
/**
 * ִ�����
 * @author GTC
 *
 */
public class StartTextProcessor {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// ����������ı�
		String text = sc.nextLine();
		// �����Ű���
		int width = sc.nextInt();
		sc.close();
		TextProcessor tp = new TextProcessor();
		System.out.println(tp.process(text, width));
	}

}