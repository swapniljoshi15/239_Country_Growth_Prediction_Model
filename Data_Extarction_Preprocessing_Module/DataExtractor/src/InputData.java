import java.util.HashMap;
import java.util.LinkedHashMap;


public class InputData {

	static LinkedHashMap<String, Data> countryMap = new LinkedHashMap<String, Data>();
	static public String year = "2007";
	
	
	static{
		
		//high income NON OECD
		countryMap.put("Aruba", new Data());
		countryMap.put("Andorra", new Data());
		countryMap.put("United Arab Emirates", new Data());
		countryMap.put("Argentina", new Data());
		countryMap.put("Antigua and Barbuda", new Data());
		countryMap.put("Bahrain", new Data());
		countryMap.put("Bahamas, The", new Data());
		countryMap.put("Bermuda", new Data());
		countryMap.put("Barbados", new Data());
		countryMap.put("Brunei Darussalam", new Data());
		countryMap.put("Channel Islands", new Data());
		countryMap.put("Curacao", new Data());
		countryMap.put("Cayman Islands", new Data());
		countryMap.put("Cyprus", new Data());
		countryMap.put("Faeroe Islands", new Data());
		countryMap.put("Equatorial Guinea", new Data());
		countryMap.put("Greenland", new Data());
		countryMap.put("Guam", new Data());
		countryMap.put("Hong Kong SAR, China", new Data());
		countryMap.put("Croatia", new Data());
		countryMap.put("Isle of Man", new Data());
		countryMap.put("St. Kitts and Nevis", new Data());
		countryMap.put("Kuwait", new Data());
		countryMap.put("Liechtenstein", new Data());
		countryMap.put("Lithuania", new Data());
		countryMap.put("Latvia", new Data());
		countryMap.put("Macao SAR, China", new Data());
		countryMap.put("St. Martin (French part)", new Data());
		countryMap.put("Monaco", new Data());
		countryMap.put("Malta", new Data());
		countryMap.put("Northern Mariana Islands", new Data());
		countryMap.put("New Caledonia", new Data());
		countryMap.put("Oman", new Data());
		countryMap.put("Puerto Rico", new Data());
		countryMap.put("French Polynesia", new Data());
		countryMap.put("Qatar", new Data());
		countryMap.put("Russian Federation", new Data());
		countryMap.put("Saudi Arabia", new Data());
		countryMap.put("Singapore", new Data());
		countryMap.put("San Marino", new Data());
		countryMap.put("Sint Maarten (Dutch part)", new Data());
		countryMap.put("Seychelles", new Data());
		countryMap.put("Turks and Caicos Islands", new Data());
		countryMap.put("Trinidad and Tobago", new Data());
		countryMap.put("Uruguay", new Data());
		countryMap.put("Venezuela, RB", new Data());
		countryMap.put("Virgin Islands (U.S.)", new Data());
		
		
		//high income OECD
		countryMap.put("Australia", new Data());
		countryMap.put("Austria", new Data());
		countryMap.put("Belgium", new Data());
		countryMap.put("Canada", new Data());
		countryMap.put("Switzerland", new Data());
		countryMap.put("Chile", new Data());
		countryMap.put("Czech Republic", new Data());
		countryMap.put("Germany", new Data());
		countryMap.put("Denmark", new Data());
		countryMap.put("Spain", new Data());
		countryMap.put("Estonia", new Data());
		countryMap.put("Finland", new Data());
		countryMap.put("France", new Data());
		countryMap.put("United Kingdom", new Data());
		countryMap.put("Greece", new Data());
		countryMap.put("Hungary", new Data());
		countryMap.put("Ireland", new Data());
		countryMap.put("Iceland", new Data());
		countryMap.put("Israel", new Data());
		countryMap.put("Italy", new Data());
		countryMap.put("Japan", new Data());
		countryMap.put("Korea, Rep.", new Data());
		countryMap.put("Luxembourg", new Data());
		countryMap.put("Netherlands", new Data());
		countryMap.put("Norway", new Data());
		countryMap.put("New Zealand", new Data());
		countryMap.put("Poland", new Data());
		countryMap.put("Portugal", new Data());
		countryMap.put("Slovak Republic", new Data());
		countryMap.put("Slovenia", new Data());
		countryMap.put("Sweden", new Data());
		countryMap.put("United States", new Data());
		
		//low income
		countryMap.put("Afghanistan", new Data());
		countryMap.put("Burundi", new Data());
		countryMap.put("Benin", new Data());
		countryMap.put("Burkina Faso", new Data());
		countryMap.put("Central African Republic", new Data());
		countryMap.put("Congo, Dem. Rep.", new Data());
		countryMap.put("Comoros", new Data());
		countryMap.put("Eritrea", new Data());
		countryMap.put("Ethiopia", new Data());
		countryMap.put("Guinea", new Data());
		countryMap.put("Gambia, The", new Data());
		countryMap.put("Guinea-Bissau", new Data());
		countryMap.put("Haiti", new Data());
		countryMap.put("Cambodia", new Data());
		countryMap.put("Liberia", new Data());
		countryMap.put("Madagascar", new Data());
		countryMap.put("Mali", new Data());
		countryMap.put("Mozambique", new Data());
		countryMap.put("Malawi", new Data());
		countryMap.put("Niger", new Data());
		countryMap.put("Nepal", new Data());
		countryMap.put("Korea, Dem. Rep.", new Data());
		countryMap.put("Rwanda", new Data());
		countryMap.put("Sierra Leone", new Data());
		countryMap.put("Somalia", new Data());
		countryMap.put("South Sudan", new Data());
		countryMap.put("Chad", new Data());
		countryMap.put("Togo", new Data());
		countryMap.put("Tanzania", new Data());
		countryMap.put("Uganda", new Data());
		countryMap.put("Zimbabwe", new Data());
		
		//lower middle income
		countryMap.put("Armenia", new Data());
		countryMap.put("Bangladesh", new Data());
		countryMap.put("Bolivia", new Data());
		countryMap.put("Bhutan", new Data());
		countryMap.put("Cote d'Ivoire", new Data());
		countryMap.put("Cameroon", new Data());
		countryMap.put("Congo, Rep.", new Data());
		countryMap.put("Cabo Verde", new Data());
		countryMap.put("Djibouti", new Data());
		countryMap.put("Egypt, Arab Rep.", new Data());
		countryMap.put("Micronesia, Fed. Sts.", new Data());
		countryMap.put("Georgia", new Data());
		countryMap.put("Ghana", new Data());
		countryMap.put("Guatemala", new Data());
		countryMap.put("Guyana", new Data());
		countryMap.put("Honduras", new Data());
		countryMap.put("Indonesia", new Data());
		countryMap.put("India", new Data());
		countryMap.put("Kenya", new Data());
		countryMap.put("Kyrgyz Republic", new Data());
		countryMap.put("Kiribati", new Data());
		countryMap.put("Kosovo", new Data());
		countryMap.put("Lao PDR", new Data());
		countryMap.put("Sri Lanka", new Data());
		countryMap.put("Lesotho", new Data());
		countryMap.put("Morocco", new Data());
		countryMap.put("Moldova", new Data());
		countryMap.put("Myanmar", new Data());
		countryMap.put("Mauritania", new Data());
		countryMap.put("Nigeria", new Data());
		countryMap.put("Nicaragua", new Data());
		countryMap.put("Pakistan", new Data());
		countryMap.put("Philippines", new Data());
		countryMap.put("Papua New Guinea", new Data());
		countryMap.put("West Bank and Gaza", new Data());
		countryMap.put("Sudan", new Data());
		countryMap.put("Senegal", new Data());
		countryMap.put("Solomon Islands", new Data());
		countryMap.put("El Salvador", new Data());
		countryMap.put("Sao Tome and Principe", new Data());
		countryMap.put("Swaziland", new Data());
		countryMap.put("Syrian Arab Republic", new Data());
		countryMap.put("Tajikistan", new Data());
		countryMap.put("Timor-Leste", new Data());
		countryMap.put("Ukraine", new Data());
		countryMap.put("Uzbekistan", new Data());
		countryMap.put("Vietnam", new Data());
		countryMap.put("Vanuatu", new Data());
		countryMap.put("Samoa", new Data());
		countryMap.put("Yemen, Rep.", new Data());
		countryMap.put("Zambia", new Data());
		
		//upper middle income
		countryMap.put("Angola", new Data());
		countryMap.put("Albania", new Data());
		countryMap.put("American Samoa", new Data());
		countryMap.put("Azerbaijan", new Data());
		countryMap.put("Bulgaria", new Data());
		countryMap.put("Bosnia and Herzegovina", new Data());
		countryMap.put("Belarus", new Data());
		countryMap.put("Belize", new Data());
		countryMap.put("Brazil", new Data());
		countryMap.put("Botswana", new Data());
		countryMap.put("China", new Data());
		countryMap.put("Colombia", new Data());
		countryMap.put("Costa Rica", new Data());
		countryMap.put("Cuba", new Data());
		countryMap.put("Dominica", new Data());
		countryMap.put("Dominican Republic", new Data());
		countryMap.put("Algeria", new Data());
		countryMap.put("Ecuador", new Data());
		countryMap.put("Fiji", new Data());
		countryMap.put("Gabon", new Data());
		countryMap.put("Grenada", new Data());
		countryMap.put("Iran, Islamic Rep.", new Data());
		countryMap.put("Iraq", new Data());
		countryMap.put("Jamaica", new Data());
		countryMap.put("Jordan", new Data());
		countryMap.put("Kazakhstan", new Data());
		countryMap.put("Lebanon", new Data());
		countryMap.put("Libya", new Data());
		countryMap.put("St. Lucia", new Data());
		countryMap.put("Maldives", new Data());
		countryMap.put("Mexico", new Data());
		countryMap.put("Marshall Islands", new Data());
		countryMap.put("Macedonia, FYR", new Data());
		countryMap.put("Montenegro", new Data());
		countryMap.put("Mongolia", new Data());
		countryMap.put("Mauritius", new Data());
		countryMap.put("Malaysia", new Data());
		countryMap.put("Namibia", new Data());
		countryMap.put("Panama", new Data());
		countryMap.put("Peru", new Data());
		countryMap.put("Palau", new Data());
		countryMap.put("Paraguay", new Data());
		countryMap.put("Romania", new Data());
		countryMap.put("Serbia", new Data());
		countryMap.put("Suriname", new Data());
		countryMap.put("Thailand", new Data());
		countryMap.put("Turkmenistan", new Data());
		countryMap.put("Tonga", new Data());
		countryMap.put("Tunisia", new Data());
		countryMap.put("Turkey", new Data());
		countryMap.put("Tuvalu", new Data());
		countryMap.put("St. Vincent and the Grenadines", new Data());
		countryMap.put("South Africa", new Data());
		
	}
}
