package com.pipl.api.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.pipl.api.data.fields.Address;
import com.pipl.api.data.fields.DOB;
import com.pipl.api.data.fields.DisplayField;
import com.pipl.api.data.fields.Name;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * The <code>Utils</code> class is an utility class which has static utility
 * methods.
 * <p/>
 */

public class Utils {
	public static Map<String, String> COUNTRIES = new HashMap<String, String>();
	public static Map<String, Map<String, String>> STATES = new HashMap<String, Map<String, String>>();

	private static String countryString = "{BD:Bangladesh,WF:\"Wallis And Futuna Islands\",BF:\"Burkina Faso\",PY:Paraguay,BA:\"Bosnia And Herzegovina\",BB:Barbados,BE:Belgium,BM:Bermuda,BN:\"Brunei Darussalam\",BO:Bolivia,BH:Bahrain,BI:Burundi,BJ:Benin,BT:Bhutan,JM:Jamaica,BV:\"Bouvet Island\",BW:Botswana,WS:Samoa,BR:Brazil,BS:Bahamas,JE:Jersey,BY:Belarus,BZ:Belize,RU:\"Russian Federation\",RW:Rwanda,LT:Lithuania,RE:Reunion,TM:Turkmenistan,TJ:Tajikistan,RO:Romania,LS:Lesotho,GW:Guinea-bissau,GU:Guam,GT:Guatemala,GS:\"South Georgia And South Sandwich Islands\",GR:Greece,GQ:\"Equatorial Guinea\",GP:Guadeloupe,JP:Japan,GY:Guyana,GG:Guernsey,GF:\"French Guiana\",GE:Georgia,GD:Grenada,GB:\"Great Britain\",GA:Gabon,GN:Guinea,GM:Gambia,GL:Greenland,GI:Gibraltar,GH:Ghana,OM:Oman,TN:Tunisia,JO:Jordan,HR:Croatia,HT:Haiti,SV:\"El Salvador\",HK:\"Hong Kong\",HN:Honduras,HM:\"Heard And Mcdonald Islands\",AD:Andorra,PR:\"Puerto Rico\",PS:Palestine,PW:Palau,PT:Portugal,SJ:\"Svalbard And Jan Mayen Islands\",VG:\"Virgin Islands, British\",AI:Anguilla,KP:\"North Korea\",PF:\"French Polynesia\",PG:\"Papua New Guinea\",PE:Peru,PK:Pakistan,PH:Philippines,PN:Pitcairn,PL:Poland,PM:\"Saint Pierre And Miquelon\",ZM:Zambia,EH:\"Western Sahara\",EE:Estonia,EG:Egypt,ZA:\"South Africa\",EC:Ecuador,IT:Italy,AO:Angola,KZ:Kazakhstan,ET:Ethiopia,ZW:Zimbabwe,SA:\"Saudi Arabia\",ES:Spain,ER:Eritrea,ME:Montenegro,MD:Moldova,MG:Madagascar,MA:Morocco,MC:Monaco,UZ:Uzbekistan,MM:Myanmar,ML:Mali,MO:Macau,MN:Mongolia,MH:\"Marshall Islands\",US:\"United States\",UM:\"United States Minor Outlying Islands\",MT:Malta,MW:Malawi,MV:Maldives,MQ:Martinique,MP:\"Northern Mariana Islands\",MS:Montserrat,NA:Namibia,IM:\"Isle Of Man\",UG:Uganda,MY:Malaysia,MX:Mexico,IL:Israel,BG:Bulgaria,FR:France,AW:Aruba,AX:\"Åland Islands\",FI:Finland,FJ:Fiji,FK:\"Falkland Islands\",FM:Micronesia,FO:\"Faroe Islands\",NI:Nicaragua,NL:Netherlands,NO:Norway,SO:Somalia,NC:\"New Caledonia\",NE:Niger,NF:\"Norfolk Island\",NG:Nigeria,NZ:\"New Zealand\",NP:Nepal,NR:Nauru,NU:Niue,MR:Mauritania,CK:\"Cook Islands\",CI:\"Côte D'Ivoire\",CH:Switzerland,CO:Colombia,CN:China,CM:Cameroon,CL:Chile,CC:\"Cocos (keeling) Islands\",CA:Canada,CG:\"Congo (brazzaville)\",CF:\"Central African Republic\",CD:\"Congo (kinshasa)\",CZ:\"Czech Republic\",CY:Cyprus,CX:\"Christmas Island\",CS:Serbia,CR:\"Costa Rica\",HU:Hungary,CV:\"Cape Verde\",CU:Cuba,SZ:Swaziland,SY:Syria,KG:Kyrgyzstan,KE:Kenya,SR:Suriname,KI:Kiribati,KH:Cambodia,KN:\"Saint Kitts And Nevis\",KM:Comoros,ST:\"Sao Tome And Principe\",SK:Slovakia,KR:\"South Korea\",SI:Slovenia,SH:\"Saint Helena\",KW:Kuwait,SN:Senegal,SM:\"San Marino\",SL:\"Sierra Leone\",SC:Seychelles,SB:\"Solomon Islands\",KY:\"Cayman Islands\",SG:Singapore,SE:Sweden,SD:Sudan,DO:\"Dominican Republic\",DM:Dominica,DJ:Djibouti,DK:Denmark,DE:Germany,YE:Yemen,AT:Austria,DZ:Algeria,MK:Macedonia,UY:Uruguay,YT:Mayotte,MU:Mauritius,TZ:Tanzania,LC:\"Saint Lucia\",LA:Laos,TV:Tuvalu,TW:Taiwan,TT:\"Trinidad And Tobago\",TR:Turkey,LK:\"Sri Lanka\",LI:Liechtenstein,LV:Latvia,TO:Tonga,TL:Timor-leste,LU:Luxembourg,LR:Liberia,TK:Tokelau,TH:Thailand,TF:\"French Southern Lands\",TG:Togo,TD:Chad,TC:\"Turks And Caicos Islands\",LY:Libya,VA:\"Vatican City\",AC:\"Ascension Island\",VC:\"Saint Vincent And The Grenadines\",AE:\"United Arab Emirates\",VE:Venezuela,AG:\"Antigua And Barbuda\",AF:Afghanistan,IQ:Iraq,VI:\"Virgin Islands, U.s.\",IS:Iceland,IR:Iran,AM:Armenia,AL:Albania,VN:Vietnam,AN:\"Netherlands Antilles\",AQ:Antarctica,AS:\"American Samoa\",AR:Argentina,AU:Australia,VU:Vanuatu,IO:\"British Indian Ocean Territory\",IN:India,LB:Lebanon,AZ:Azerbaijan,IE:Ireland,ID:Indonesia,PA:Panama,UA:Ukraine,QA:Qatar,MZ:Mozambique}";

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

	public static String VALID_URL_REGEX = "^(?:(?:ht|f)tp(?:s?)\\:\\/\\/|~\\/|\\/)?(?:\\w+:\\w+@)?(?:(?:[-\\w]+\\.)+(?:com|org|net|gov|mil|biz|info|mobi|name|aero|jobs|museum|travel|[a-z]{2}))(?::[\\d]{1,5})?(?:(?:(?:\\/(?:[-\\w~!$+|.,=]|%[a-f\\d]{2})+)+|\\/)+|\\?|#)?(?:(?:\\?(?:[-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?(?:[-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)(?:&(?:[-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?(?:[-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)*)*(?:#(?:[-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)?$";

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
	 * Return True if `url` (str/unicode) is a valid URL, False otherwise.
	 * 
	 * @param url
	 *            url to be validated
	 * @return <code>true</code> if the provided url is valid;
	 *         <code>false</code> otherwise.
	 */
	public static boolean isValidUrl(String url) {

		if (url == null) {
			return false;
		}
		try {
			new URI(url);
		} catch (URISyntaxException e) {
			return false;
		}
		return url.matches(VALID_URL_REGEX);
	}

	/**
	 * Strip all non alphabetic characters from the string.
	 * 
	 * @param s
	 * @return
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
	 * @return
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
				.registerTypeAdapter(Address.class, new DisplayField(null))
				.registerTypeAdapter(DOB.class, new DisplayField(null))
				.registerTypeAdapter(Name.class, new DisplayField(null))
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
	public static Object fromJson(String json, Class<?> cls) throws IOException {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		return gson.fromJson(json, cls);
	}

	/**
	 * Transform a <code>Date</code> object with pattern "yyyy-MM-dd'T'HH:mm:ss"
	 * to a <code>String</code> object.
	 * 
	 * @param date
	 *            <code>Date</code> object
	 * @return converted String.
	 */
	public static String dateTimeToString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(date);
	}

	/**
	 * Transform a <code>String</code> object to a <code>Date</code> object with
	 * pattern "yyyy-MM-dd'T'HH:mm:ss".
	 * 
	 * @param str
	 *            <code>String</code> object
	 * @return <code>Date</code> object
	 * @throws ParseException
	 */
	public static Date stringToDateTime(String str) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
		simpleDateFormat.setLenient(false);
		return simpleDateFormat.parse(str);
	}

	/**
	 * Transform a <code>Date</code> object with pattern "yyyy-MM-dd" to a
	 * <code>String</code> object.
	 * 
	 * @param date
	 *            <code>Date</code> object
	 * @return converted String.
	 */
	public static String dateToString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	/**
	 * Transform a <code>String</code> object to a <code>Date</code> object with
	 * pattern "yyyy-MM-dd".
	 * 
	 * @param str
	 *            <code>String</code> object
	 * @return <code>Date</code> object
	 * @throws ParseException
	 */
	public static Date stringToDate(String str) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",
				Locale.ENGLISH);
		simpleDateFormat.setLenient(false);
		return simpleDateFormat.parse(str);
	}

	public static boolean isNullOrEmpty(String s) {
		return s == null || s.isEmpty();
	}

	public static String encodeToUtf8(String s)
			throws UnsupportedEncodingException {
		return URLEncoder.encode(s, "UTF-8");
	}

	public static String decodeFromUtf8(String s)
			throws UnsupportedEncodingException {
		return URLDecoder.decode(s, "UTF-8");
	}

	public static <T> String join(String delim, Iterable<T> iterable) {
		String currDelim = "";
		StringBuffer sb = new StringBuffer();
		for (T i : iterable) {
			sb.append(currDelim).append(i.toString());
			currDelim = delim;
		}
		return sb.toString();
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
}
