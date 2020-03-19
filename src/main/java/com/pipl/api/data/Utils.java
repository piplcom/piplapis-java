package com.pipl.api.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


/**
 * The <code>Utils</code> class is an utility class which has static utility
 * methods.
 */

public class Utils {
	public static Map<String, String> COUNTRIES = new HashMap<String, String>();
	public static Map<String, Map<String, String>> STATES = new HashMap<String, Map<String, String>>();

	private static String countryString = "{BD:Bangladesh,WF:\"Wallis And Futuna Islands\",BF:\"Burkina Faso\",PY:Paraguay,BA:\"Bosnia And Herzegovina\",BB:Barbados,BE:Belgium,BM:Bermuda,BN:\"Brunei Darussalam\",BO:Bolivia,BH:Bahrain,BI:Burundi,BJ:Benin,BT:Bhutan,JM:Jamaica,BV:\"Bouvet Island\",BW:Botswana,WS:Samoa,BR:Brazil,BS:Bahamas,JE:Jersey,BY:Belarus,BZ:Belize,RU:\"Russian Federation\",RW:Rwanda,LT:Lithuania,RE:Reunion,TM:Turkmenistan,TJ:Tajikistan,RO:Romania,LS:Lesotho,GW:Guinea-bissau,GU:Guam,GT:Guatemala,GS:\"South Georgia And South Sandwich Islands\",GR:Greece,GQ:\"Equatorial Guinea\",GP:Guadeloupe,JP:Japan,GY:Guyana,GG:Guernsey,GF:\"French Guiana\",GE:Georgia,GD:Grenada,GB:\"Great Britain\",GA:Gabon,GN:Guinea,GM:Gambia,GL:Greenland,GI:Gibraltar,GH:Ghana,OM:Oman,TN:Tunisia,JO:Jordan,HR:Croatia,HT:Haiti,SV:\"El Salvador\",HK:\"Hong Kong\",HN:Honduras,HM:\"Heard And Mcdonald Islands\",AD:Andorra,PR:\"Puerto Rico\",PS:Palestine,PW:Palau,PT:Portugal,SJ:\"Svalbard And Jan Mayen Islands\",VG:\"Virgin Islands, British\",AI:Anguilla,KP:\"North Korea\",PF:\"French Polynesia\",PG:\"Papua New Guinea\",PE:Peru,PK:Pakistan,PH:Philippines,PN:Pitcairn,PL:Poland,PM:\"Saint Pierre And Miquelon\",ZM:Zambia,EH:\"Western Sahara\",EE:Estonia,EG:Egypt,ZA:\"South Africa\",EC:Ecuador,IT:Italy,AO:Angola,KZ:Kazakhstan,ET:Ethiopia,ZW:Zimbabwe,SA:\"Saudi Arabia\",ES:Spain,ER:Eritrea,ME:Montenegro,MD:Moldova,MG:Madagascar,MA:Morocco,MC:Monaco,UZ:Uzbekistan,MM:Myanmar,ML:Mali,MO:Macau,MN:Mongolia,MH:\"Marshall Islands\",US:\"United States\",UM:\"United States Minor Outlying Islands\",MT:Malta,MW:Malawi,MV:Maldives,MQ:Martinique,MP:\"Northern Mariana Islands\",MS:Montserrat,NA:Namibia,IM:\"Isle Of Man\",UG:Uganda,MY:Malaysia,MX:Mexico,IL:Israel,BG:Bulgaria,FR:France,AW:Aruba,AX:\"�land Islands\",FI:Finland,FJ:Fiji,FK:\"Falkland Islands\",FM:Micronesia,FO:\"Faroe Islands\",NI:Nicaragua,NL:Netherlands,NO:Norway,SO:Somalia,NC:\"New Caledonia\",NE:Niger,NF:\"Norfolk Island\",NG:Nigeria,NZ:\"New Zealand\",NP:Nepal,NR:Nauru,NU:Niue,MR:Mauritania,CK:\"Cook Islands\",CI:\"C�te D'Ivoire\",CH:Switzerland,CO:Colombia,CN:China,CM:Cameroon,CL:Chile,CC:\"Cocos (keeling) Islands\",CA:Canada,CG:\"Congo (brazzaville)\",CF:\"Central African Republic\",CD:\"Congo (kinshasa)\",CZ:\"Czech Republic\",CY:Cyprus,CX:\"Christmas Island\",CS:Serbia,CR:\"Costa Rica\",HU:Hungary,CV:\"Cape Verde\",CU:Cuba,SZ:Swaziland,SY:Syria,KG:Kyrgyzstan,KE:Kenya,SR:Suriname,KI:Kiribati,KH:Cambodia,KN:\"Saint Kitts And Nevis\",KM:Comoros,ST:\"Sao Tome And Principe\",SK:Slovakia,KR:\"South Korea\",SI:Slovenia,SH:\"Saint Helena\",KW:Kuwait,SN:Senegal,SM:\"San Marino\",SL:\"Sierra Leone\",SC:Seychelles,SB:\"Solomon Islands\",KY:\"Cayman Islands\",SG:Singapore,SE:Sweden,SD:Sudan,DO:\"Dominican Republic\",DM:Dominica,DJ:Djibouti,DK:Denmark,DE:Germany,YE:Yemen,AT:Austria,DZ:Algeria,MK:Macedonia,UY:Uruguay,YT:Mayotte,MU:Mauritius,TZ:Tanzania,LC:\"Saint Lucia\",LA:Laos,TV:Tuvalu,TW:Taiwan,TT:\"Trinidad And Tobago\",TR:Turkey,LK:\"Sri Lanka\",LI:Liechtenstein,LV:Latvia,TO:Tonga,TL:Timor-leste,LU:Luxembourg,LR:Liberia,TK:Tokelau,TH:Thailand,TF:\"French Southern Lands\",TG:Togo,TD:Chad,TC:\"Turks And Caicos Islands\",LY:Libya,VA:\"Vatican City\",AC:\"Ascension Island\",VC:\"Saint Vincent And The Grenadines\",AE:\"United Arab Emirates\",VE:Venezuela,AG:\"Antigua And Barbuda\",AF:Afghanistan,IQ:Iraq,VI:\"Virgin Islands, U.s.\",IS:Iceland,IR:Iran,AM:Armenia,AL:Albania,VN:Vietnam,AN:\"Netherlands Antilles\",AQ:Antarctica,AS:\"American Samoa\",AR:Argentina,AU:Australia,VU:Vanuatu,IO:\"British Indian Ocean Territory\",IN:India,LB:Lebanon,AZ:Azerbaijan,IE:Ireland,ID:Indonesia,PA:Panama,UA:Ukraine,QA:Qatar,MZ:Mozambique, BL:\"Saint Barthélemy\",BQ:\"Caribbean Netherlands\",MF:\"Saint Martin\",SS:\"South Sudan\",SX:\"Sint Maarten\",XK:Kosovo,CW:Curaçao,RS:Serbia}";

	static {
		Gson gson = new Gson();
		Type mapType = new TypeToken<Map<String, String>>() {
		}.getType();
		COUNTRIES = gson.fromJson(countryString, mapType);
		COUNTRIES = Collections.unmodifiableMap(COUNTRIES);
	}

	private static String us = "{WA:Washington,VA:Virginia,DE:Delaware,DC:\"District Of Columbia\",WI:Wisconsin,WV:\"West Virginia\",HI:Hawaii,FL:Florida,YT:Yukon,WY:Wyoming,PR:\"Puerto Rico\",NJ:\"New Jersey\",NM:\"New Mexico\",TX:Texas,LA:Louisiana,NC:\"North Carolina\",ND:\"North Dakota\",NE:Nebraska,FM:\"Federated States Of Micronesia\",TN:Tennessee,NY:\"New York\",PA:Pennsylvania,CT:Connecticut,RI:\"Rhode Island\",NV:Nevada,NH:\"New Hampshire\",GU:Guam,CO:Colorado,VI:\"Virgin Islands\",AK:Alaska,AL:Alabama,AS:\"American Samoa\",AR:Arkansas,VT:Vermont,IL:Illinois,GA:Georgia,IN:Indiana,IA:Iowa,MA:Massachusetts,AZ:Arizona,CA:California,ID:Idaho,PW:Palau,ME:Maine,MD:Maryland,OK:Oklahoma,OH:Ohio,UT:Utah,MO:Missouri,MN:Minnesota,MI:Michigan,MH:\"Marshall Islands\",KS:Kansas,MT:Montana,MP:\"Northern Mariana Islands\",MS:Mississippi,SC:\"South Carolina\",KY:Kentucky,OR:Oregon,SD:\"South Dakota\"}";
	private static String ca = "{AB:Alberta,BC:\"British Columbia\",MB:Manitoba,NB:\"New Brunswick\",NT:\"Northwest Territories\",NS:\"Nova Scotia\",NU:Nunavut,ON:Ontario,PE:\"Prince Edward Island\",QC:Quebec,SK:Saskatchewan,YU:Yukon,NL:\"Newfoundland and Labrador\"}";
	private static String au = "{WA:\"State of Western Australia\",SA:\"State of South Australia\",NT:\"Northern Territory\",VIC:\"State of Victoria\",TAS:\"State of Tasmania\",QLD:\"State of Queensland\",NSW:\"State of New South Wales\",ACT:\"Australian Capital Territory\"}";
	private static String gb = "{WLS:Wales,SCT:Scotland,NIR:\"Northern Ireland\",ENG:England'}";
	public static final String CHARSET_NAME = "UTF-8";

	static {
		Gson gson = new Gson();
		Type mapType = new TypeToken<Map<String, String>>() {
		}.getType();

		Map<String, String> temp1 = new HashMap<String, String>();
		temp1 = gson.fromJson(us, mapType);
		STATES.put("US", temp1);

		Map<String, String> temp2 = new HashMap<String, String>();
		temp2 = gson.fromJson(ca, mapType);
		STATES.put("CA", temp2);

		Map<String, String> temp3 = new HashMap<String, String>();
		temp3 = gson.fromJson(au, mapType);
		STATES.put("AU", temp3);

		Map<String, String> temp4 = new HashMap<String, String>();
		temp4 = gson.fromJson(gb, mapType);
		STATES.put("GB", temp4);

		STATES = Collections.unmodifiableMap(STATES);
	}

	/**
	 * Strip all non alphabetic characters from the string.
	 * 
	 * @param s
	 * @return The string without any non alphabetic characters.
	 */
	public static String alphaChars(String s) {
		String result = "";
		for (Character c : s.toCharArray()) {
			if (Character.isLetter(c)) {
				result = result + String.valueOf(c);
			}
		}
		return result;
	}

	/**
	 * Strip all non alphanumeric characters from the string.
	 * 
	 * @param s
	 * @return The string without any non alphanumeric characters.
	 */
	public static String alnumChars(String s) {
		String result = "";
		for (Character c : s.toCharArray()) {
			if (Character.isDigit(c) || Character.isLetter(c)) {
				result = result + String.valueOf(c);
			}
		}
		return result;
	}

	/**
	 * Serialize the object to a JSON string.
	 * 
	 * @param object
	 *            object to be serialized
	 * @return serialized json String
	 * @throws IOException
	 */
	public static String toJson(Object object) throws IOException {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd")
				.excludeFieldsWithModifiers(Modifier.STATIC)
				.excludeFieldsWithoutExposeAnnotation()
				.create();
		return gson.toJson(object);
	}

	/**
	 * Deserialize the object from a JSON string.
	 * 
	 * @param json
	 *            json string
	 * @param cls
	 *            <code>Class</code>
	 * @return Deserialized object
	 * @throws IOException
	 */
	public static Object fromJson(Reader json, Class<?> cls) throws IOException {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		return gson.fromJson(json, cls);
	}

	public static Object fromJson(String json, Class<?> cls) throws IOException {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		return gson.fromJson(json, cls);
	}

	public static boolean isNullOrEmpty(String s) {
		return s == null || s.isEmpty();
	}

	public static <T> String join(String delim, Iterable<T> iterable) {
		String currDelim = "";
		StringBuilder sb = new StringBuilder();
		for (T i : iterable) {
			sb.append(currDelim).append(i.toString());
			currDelim = delim;
		}
		return sb.toString();
	}
	
	public static String titleCase(String s) {
		String[] words = s.split("[ _]");
		for (int i=0 ; i<words.length ; i++) {
			if (words[i].length()>1) {
				words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1, words[i].length()).toLowerCase();
			} else {
				words[i] = words[i].toUpperCase();
			}
		}
		return join(" ", Arrays.asList(words));
	}

	public static <T> ArrayList<T> filter(T value, ArrayList<T> arr) {
		ArrayList<T> newArr = new ArrayList<T>();
		for (T node : arr) {
			if (node != value) {
				newArr.add(node);
			}
		}
		return newArr;
	}

	public static String getNotNullString(String s) {
		if (null == s) {
			return "";
		}
		return s;
	}
	
	public static String InputStreamToString(InputStream is, String encoding) throws IOException {
		final char[] buffer = new char[1024];
		final StringBuilder out = new StringBuilder();
		Reader in = new InputStreamReader(is, encoding);
		for (; ; ) {
		    int rsz = in.read(buffer, 0, buffer.length);
		    if (rsz < 0)
		        break;
		    out.append(buffer, 0, rsz);
		}
		return out.toString();
	}
}
