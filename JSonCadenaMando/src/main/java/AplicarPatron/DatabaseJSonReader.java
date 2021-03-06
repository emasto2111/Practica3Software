package AplicarPatron;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.jar.Attributes.Name;

import com.google.gson.stream.JsonReader;

/**
 * Created by jmalvarez on 11/5/16.
 * http://developer.android.com/intl/es/training/basics/network-ops/xml.html
 */
public class DatabaseJSonReader {

	public DatabaseJSonReader() {
	}
	
	CadenaMando cad;
	Medicines med;
	Physiotherapy py;
	Rescuemedpres rmp;

	
	
	
	
	
	public String parse(String jsonFileName) throws IOException {

		med= new Medicines(null);
		py= new Physiotherapy (med);
		rmp= new Rescuemedpres (py);
//		cad= new CadenaMando(rmp);
		InputStream usersIS = new FileInputStream(new File(jsonFileName));
		JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));
		reader.beginObject();
		
		StringBuffer readData = new StringBuffer();
		
		while (reader.hasNext()) {
			String name = reader.nextName();
			//ver si es para mi, si es buscar categoria, si mi objeto apunta a null no hago nada.
			readData.append(cad.leerCategoria(reader, name));
			
		}

		reader.endObject();
		reader.close();
		usersIS.close();

		return new String(readData);
	}

}
