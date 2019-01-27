/**
 * �ı�����
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
		
		// �ж��Ƿ����쳣�ַ�
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (!(c == ' ' || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
				result = "ERROR: Invalid character detected!";
				flag = false;
				break;
			}
		}

		// �ж�����Ŀ���Ƿ����
		if (width < 10 || width > 80) {
			flag = false;
			result = "ERROR: Width out of range!";
		}

		if (flag) {

			// �������
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
			
			// ��ÿ���ڴ�������
			String[] segmentArray = new String[segmentNum];
			int[] segmentLastCharNo = new int[segmentNum];
			for (int i = 0; i < segmentArray.length; i++) {
				segmentArray[i] = "";
			}
			int segmentNo = 0;
			for (int i = 0; i < text.length() - 1; i++) {
				char c1 = text.charAt(i);
				char c2 = text.charAt(i + 1);

				// ��ǰ�ַ�Ϊ�հ��ַ�����һ���ַ�Ϊ�ı��ַ���
				// ��ǰ�ַ�Ϊ�ı��ַ�����һ���ַ�Ϊ�հ��ַ�ʱ
				// ���˽ڴ������飬����¼ĩβ�ַ�λ�ã�����+1
				if ((c1 == ' ' && c2 != ' ') || (c1 != ' ' && c2 == ' ')) {
					segmentArray[segmentNo] += c1;
					segmentLastCharNo[segmentNo] = i;
					segmentNo++;
				} else {
					// ����ǰ�ַ�����һ���ַ�ͬΪ�հ��ַ����ı��ַ�ʱ��
					// ƴ�Ӵ��ַ����γɽ�
					segmentArray[segmentNo] += c1;
				}

				if (i == text.length() - 2) {
					segmentArray[segmentNo] += c2;
					segmentLastCharNo[segmentNo] = i + 1;
				}

			}

			// ������Žڵ�����
			for (int i = 0; i < segmentArray.length; i++) {
				// �õ����ַ�����ԭ�ı��е�λ��
				int segmentFirstCharNo = segmentLastCharNo[i] - segmentArray[i].length() + 1;
				// �жϵ�ǰ����ռ����
				int n = segmentLastCharNo[i] / width - segmentFirstCharNo / width + 1;
				// ��ǰ�������кŵ�����
				int[] segmentlines = new int[n];
				// ��ǰ�����ַ������к�Ϊ�����е�һ��ֵ
				segmentlines[0] = segmentFirstCharNo / width + 1;
				String lineStr = "(" + segmentlines[0];
				// ���ý�ռ����(>=2),�������к�����ƴ�ӳ��ַ���
				for (int j = 1; j < n; j++) {
					segmentlines[j] = segmentlines[j - 1] + 1;
					lineStr += "," + segmentlines[j];
				}
				lineStr += ");";
				// ��Ҫ��ƴ���ַ���
				result += (segmentArray[i] + lineStr);
			}
		}

		return result;
	}
}
