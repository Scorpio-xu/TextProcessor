/**
 * 文本处理
 * @author GTC
 *
 */
public class TextProcessor {

	public String process(String text, int width) {
		String result = "";
		boolean flag = true;

		if(text.length() == 0) {
			flag = false;
			result = "ERROR: Empty string";
		}
		
		// 判断是否有异常字符
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (!(c == ' ' || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
				result = "ERROR: Invalid character detected!";
				flag = false;
				break;
			}
		}

		// 判断输入的宽度是否合适
		if (width < 10 || width > 80) {
			flag = false;
			result = "ERROR: Width out of range!";
		}

		if (flag) {

			// 计算节数
			String[] strArr = text.split("\\s+");
			int segmentNum = 0;
			if (text.charAt(0) == ' ') {
				if (text.charAt(text.length() - 1) == ' ') {
					segmentNum = 2 * (strArr.length - 1) + 1;
				} else {
					segmentNum = 2 * (strArr.length - 1);
				}
			} else {
				if (text.charAt(text.length() - 1) == ' ') {
					segmentNum = 2 * strArr.length;
				} else {
					segmentNum = 2 * strArr.length - 1;
				}
			}

			if (segmentNum == -1)
				segmentNum = 1;
			
			// 将每个节存入数组
			String[] segmentArray = new String[segmentNum];
			int[] segmentLastCharNo = new int[segmentNum];
			for (int i = 0; i < segmentArray.length; i++) {
				segmentArray[i] = "";
			}
			int segmentNo = 0;
			for (int i = 0; i < text.length() - 1; i++) {
				char c1 = text.charAt(i);
				char c2 = text.charAt(i + 1);

				// 当前字符为空白字符且下一个字符为文本字符或
				// 当前字符为文本字符且下一个字符为空白字符时
				// 将此节存入数组，并记录末尾字符位置，节数+1
				if ((c1 == ' ' && c2 != ' ') || (c1 != ' ' && c2 == ' ')) {
					segmentArray[segmentNo] += c1;
					segmentLastCharNo[segmentNo] = i;
					segmentNo++;
				} else {
					// 若当前字符和下一个字符同为空白字符或文本字符时，
					// 拼接此字符，形成节
					segmentArray[segmentNo] += c1;
				}

				if (i == text.length() - 2) {
					segmentArray[segmentNo] += c2;
					segmentLastCharNo[segmentNo] = i + 1;
				}

			}

			// 遍历存放节的数组
			for (int i = 0; i < segmentArray.length; i++) {
				// 得到首字符所在原文本中的位置
				int segmentFirstCharNo = segmentLastCharNo[i] - segmentArray[i].length() + 1;
				// 判断当前节所占行数
				int n = segmentLastCharNo[i] / width - segmentFirstCharNo / width + 1;
				// 当前节所有行号的数组
				int[] segmentlines = new int[n];
				// 当前节首字符所在行号为数组中第一个值
				segmentlines[0] = segmentFirstCharNo / width + 1;
				String lineStr = "(" + segmentlines[0];
				// 若该节占多行(>=2),则将所有行号依次拼接成字符串
				for (int j = 1; j < n; j++) {
					segmentlines[j] = segmentlines[j - 1] + 1;
					lineStr += "," + segmentlines[j];
				}
				lineStr += ");";
				// 按要求拼接字符串
				result += (segmentArray[i] + lineStr);
			}
		}

		return result;
	}
}
