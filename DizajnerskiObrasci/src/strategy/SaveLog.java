package strategy;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class SaveLog implements Save {

	@Override
	public void save(List<Object> objects, String path) {
		PrintWriter printWriter;
		try {
			printWriter=new PrintWriter(path);
			objects.stream().forEach(command ->printWriter.write(command.toString() + System.lineSeparator()));
			printWriter.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
