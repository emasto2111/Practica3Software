package AplicarPatron;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Physiotherapy extends CadenaMando {
	

	public Physiotherapy(CadenaMando s) {
		super(s);
	}
	//physiotherapies
		public StringBuffer leerCategoria(JsonReader reader) throws IOException{
			StringBuffer physioData= new StringBuffer();
			reader.beginArray();
			while(reader.hasNext()) {
				reader.beginObject();
				physioData.append(leerDentroPhy(reader)).append("\n");
				reader.endObject();
			}
			
			physioData.append("\n");
			reader.endArray();
			return physioData;
		}
//		public String leerDentro(JsonReader reader) throws IOException{
//			String PhysioName= null;
//			String PhysioImage=null;
//			while(reader.hasNext()) {
//				String name= reader.nextName();
//				if(name.equals(PNAME_FIELD_TAGNAME)) {
//					PhysioName=reader.nextString();
//				}else if(name.equals(IMAGE_FIELD_TAGNAME)){
//					PhysioImage=reader.nextString();
//					
//				}else {
//					reader.skipValue();
//				}
//			}
//			
//			return PhysioName + FIELD_SEPARATOR + PhysioImage;
//		}

}
