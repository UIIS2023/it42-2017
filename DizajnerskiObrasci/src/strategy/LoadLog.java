package strategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class LoadLog implements Load {

	@Override
	public List<Object> load(String path) {
		try {
			Stream<String> logs=Files.lines(Paths.get(path));
			return logs.collect(Collectors.toList());
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
