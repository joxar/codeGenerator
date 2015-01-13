package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.IOUtility;

public class JQueryCodeGeneratorMain {

	/* settings */
	final static String INPUT_FILE = "/private/var/root/Documents/workspace/sandbox/input/test.csv";
	final static String REP_TARGET_NAME = "XXXXX";
	final static String REP_TARGET_SIZE = "YYYYY";
	final static String SEED_VAL = "A";
	final static String CODE_TEMPLATE = "$(\":text[name='XXXXX']\").val(\"YYYYY\")";
	final static String TAB = "	";

	public static void main(String args[]) {
		IOUtility iou = new IOUtility(INPUT_FILE);
		List<String> contentsList = new ArrayList<String>();

		try {
			/* input csv */
			contentsList = iou.getFileContents();

			/* generate codes(formatData(contentsList): format input data) */
			String[] codeList = codeGenerate(formatData(contentsList));

			/* output codes */
			for (int i=0; i<codeList.length; i++) {
				System.out.println(codeList[i]);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static List<String[]> formatData(List<String> inputList) {
		List<String[]> formattedDataList = new ArrayList<String[]>();
		String[] tmpStrArr = new String[2];

		for (int i=0; i<inputList.size(); i++) {
			tmpStrArr = inputList.get(i).split(",");
			formattedDataList.add(tmpStrArr);
		}

		return formattedDataList;
	}

	private static String[] codeGenerate(List<String[]> inputList) {
		String[] codeList = new String[inputList.size()];
		String resultCode = "";
		String tmpName = "";
		String tmpValue = "";
		int tmpSize = 0;

		for (int i=0; i<inputList.size(); i++) {
			tmpValue = "";
			resultCode = "";
			int num = i+1;

			tmpName = inputList.get(i)[0];
			tmpSize = Integer.parseInt(inputList.get(i)[1]);
			for (int j=0; j<tmpSize; j++) {
				tmpValue += SEED_VAL;
			}

			resultCode = num+TAB;
			resultCode += CODE_TEMPLATE.replace(REP_TARGET_NAME, tmpName);
			resultCode = resultCode.replace(REP_TARGET_SIZE, tmpValue);
			codeList[i] = resultCode;
		}

		return codeList;
	}
}
