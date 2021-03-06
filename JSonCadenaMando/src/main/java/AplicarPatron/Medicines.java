package AplicarPatron;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.stream.JsonReader;

public class Medicines extends CadenaMando{
	
	private static final String MEDICINES_TAGNAME = "medicines";
	private static final String NAME_FIELD_TAGNAME = "name";
	
	
	public Medicines(CadenaMando s) {
		super(s);
		nombreCategoria= new String (MEDICINES_TAGNAME);
	}
	
	
	// Parses the contents of a medicine list.
		public StringBuffer leerCategoria(JsonReader reader) throws IOException {
			StringBuffer medicineData = new StringBuffer();
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				medicineData.append(leerDentroMed(reader)).append("\n");
				reader.endObject();
			}
			medicineData.append("\n");
			reader.endArray();
			return medicineData;
		}

		// Parses the contents of a medicine.
//		public String leerDentro(JsonReader reader) throws IOException {
//			// reader.require(XmlPullParser.START_TAG, ns, SINGLE_ELEMENT_TAGNAME);
//			String medName = null;
//			while (reader.hasNext()) {
//				String name = reader.nextName();
//				if (name.equals(NAME_FIELD_TAGNAME)) {
//					medName = reader.nextString();
//				} else {
//					reader.skipValue();
//				}
//			}
//
//			return medName;
//		}
//		
//		
}
