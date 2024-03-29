package strategy;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class SaveShapes implements Save {

	@Override
	public void save(List<Object> shapes, String path) {
		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(path))) {
			oos.writeObject(shapes);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
