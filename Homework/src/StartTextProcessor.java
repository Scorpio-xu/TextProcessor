import java.util.Scanner;
/**
 * 执行入口
 * @author GTC
 *
 */
public class StartTextProcessor {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 输入待处理文本
		String text = sc.nextLine();
		// 输入排版宽度
		int width = sc.nextInt();
		sc.close();
		TextProcessor tp = new TextProcessor();
		System.out.println(tp.process(text, width));
	}

}