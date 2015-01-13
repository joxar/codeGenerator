package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IOUtility {

	private String fileName = "";

	public IOUtility(String fileName) {
		this.fileName = fileName;
	}

	public List<String> getFileContents() throws IOException {
		File file = null;
		FileReader fr = null;
		BufferedReader br = null;
		List<String> contentsList = new ArrayList<String>();
		String line = null;

		try {
			file = new File(this.fileName);
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			while ( (line=br.readLine()) != null ) {
				contentsList.add(line);
			}

		} catch (IOException e) {
			throw e;
		} finally {
			br.close();
			fr.close();
		}
		
		return contentsList;
	}
}
