package AplicarPatron;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public abstract class CadenaMando {
	private static final String MEDICINES_TAGNAME = "medicines";
	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String PNAME_FIELD_TAGNAME= "name";
	private static final String IMAGE_FIELD_TAGNAME= "image";
	private static final String FIELD_SEPARATOR = ";";
	private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String DOSE_FIELD_TAGNAME = "dose";
	
	protected CadenaMando sig;
	protected String nombreCategoria;
	
	
	public CadenaMando(CadenaMando sig) {
		this.sig=sig;
		
	}
	
	public String parse(String jsonFileName) throws IOException {
		return sig.parse(jsonFileName);
	}
	
	public void setCadenaMando(CadenaMando cm) {
	sig=cm;
	}
	 // else name.equals = null devuelvo error, o para else siguiente aplico de manera recursiva leerCategoria

		public  StringBuffer leerCategoria(JsonReader reader, String name) throws IOException{
		StringBuffer obj = new StringBuffer();
		reader.beginArray();
	
		if(name.equals(nombreCategoria)) {
	
			while(reader.hasNext()) {
				reader.beginObject();
				if(nombreCategoria=="medicines") {
					obj.append(leerDentroMed(reader)).append("\n");
				}else if(nombreCategoria=="physiotherapies") {
					obj.append(leerDentroPhy(reader)).append("\n");
				}else {
					obj.append(leerDentroRMD(reader)).append("\n");
				}
				
				reader.endObject();
			}

			obj.append("\n");
			reader.endArray();
			
			
		}else 
			{ 
			if(sig!=null){
			obj= sig.leerCategoria(reader,name);
			
		}else {
			reader.skipValue();
			System.err.println(" Error Categoria" + name );
			}
		}
	
		return obj;
	}
	//copiar los tres tipos que tengo porque este no se puede poner generico
		public String leerDentroMed(JsonReader reader) throws IOException {
			// reader.require(XmlPullParser.START_TAG, ns, SINGLE_ELEMENT_TAGNAME);
			String medName = null;
			while (reader.hasNext()) {
				String name = reader.nextName();
				if (name.equals(NAME_FIELD_TAGNAME)) {
					medName = reader.nextString();
				} else {
					reader.skipValue();
				}
			}

			return medName;
		}

public String leerDentroPhy(JsonReader reader) throws IOException{
	String PhysioName= null;
	String PhysioImage=null;
	while(reader.hasNext()) {
		String name= reader.nextName();
		if(name.equals(PNAME_FIELD_TAGNAME)) {
			PhysioName=reader.nextString();
		}else if(name.equals(IMAGE_FIELD_TAGNAME)){
			PhysioImage=reader.nextString();
			
		}else {
			reader.skipValue();
		}
	}
	
	return PhysioName + FIELD_SEPARATOR + PhysioImage;
}

public String leerDentroRMD(JsonReader reader) throws IOException {
	String medRef = null;
	String aiRef = null;
	String inhRef = null;
	String dose = null;
	while (reader.hasNext()) {
		String name = reader.nextName();
		if (name.equals(MEDREF_FIELD_TAGNAME)) {
			medRef = reader.nextString();
		} else if (name.equals(ACTINGREF_FIELD_TAGNAME)) {
			aiRef = reader.nextString();
		} else if (name.equals(INHREF_FIELD_TAGNAME)) {
			StringBuffer res = new StringBuffer();
			reader.beginArray();
			while (reader.hasNext()) {
				res.append(reader.nextString()).append(",");
			}
			reader.endArray();
			res.deleteCharAt(res.length() - 1);
			inhRef = new String(res);
		} else if (name.equals(DOSE_FIELD_TAGNAME)) {
			StringBuffer res = new StringBuffer();
			reader.beginArray();
			while (reader.hasNext()) {
				res.append(reader.nextString()).append(",");
			}
			reader.endArray();
			res.deleteCharAt(res.length() - 1);
			dose = new String(res);
		} else {
			reader.skipValue();
		}

	}
	return medRef + FIELD_SEPARATOR + aiRef + FIELD_SEPARATOR + inhRef + FIELD_SEPARATOR + dose;
}

}
