package AplicarPatron;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Rescuemedpres extends CadenaMando {

	
	public Rescuemedpres (CadenaMando s) {
		super(s);
	}
	

	
	// Parses the contents of a medicine list.
		public StringBuffer leerCategoria(JsonReader reader) throws IOException {
			StringBuffer rescueMedicinePresentationData = new StringBuffer();
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				rescueMedicinePresentationData.append(leerDentroRMD(reader)).append("\n");
				reader.endObject();
			}
			rescueMedicinePresentationData.append("\n");
			reader.endArray();
			return rescueMedicinePresentationData;
		}

		// Parses the contents of a rescue medicine presentation entry
//		public String leerDentro(JsonReader reader) throws IOException {
//			String medRef = null;
//			String aiRef = null;
//			String inhRef = null;
//			String dose = null;
//			while (reader.hasNext()) {
//				String name = reader.nextName();
//				if (name.equals(MEDREF_FIELD_TAGNAME)) {
//					medRef = reader.nextString();
//				} else if (name.equals(ACTINGREF_FIELD_TAGNAME)) {
//					aiRef = reader.nextString();
//				} else if (name.equals(INHREF_FIELD_TAGNAME)) {
//					StringBuffer res = new StringBuffer();
//					reader.beginArray();
//					while (reader.hasNext()) {
//						res.append(reader.nextString()).append(",");
//					}
//					reader.endArray();
//					res.deleteCharAt(res.length() - 1);
//					inhRef = new String(res);
//				} else if (name.equals(DOSE_FIELD_TAGNAME)) {
//					StringBuffer res = new StringBuffer();
//					reader.beginArray();
//					while (reader.hasNext()) {
//						res.append(reader.nextString()).append(",");
//					}
//					reader.endArray();
//					res.deleteCharAt(res.length() - 1);
//					dose = new String(res);
//				} else {
//					reader.skipValue();
//				}
//
//			}
//			return medRef + FIELD_SEPARATOR + aiRef + FIELD_SEPARATOR + inhRef + FIELD_SEPARATOR + dose;
//		}
}
