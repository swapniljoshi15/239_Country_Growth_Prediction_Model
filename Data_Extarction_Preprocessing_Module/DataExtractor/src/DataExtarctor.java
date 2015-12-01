import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;


public class DataExtarctor {

	static double rangeScale = 100.00;
	
	static String xmlFile1 = "C:\\Users\\Swapnil\\Desktop\\sjsu\\239\\project\\data\\9_Topic_en_xml_v2\\9_Topic_en_xml_v2.xml";
	static String xmlFile2 = "C:\\Users\\Swapnil\\Desktop\\sjsu\\239\\project\\data\\gdp\\ny.gdp.pcap.cd_Indicator_en_xml_v2\\ny.gdp.pcap.cd_Indicator_en_xml_v2.xml";
	static String xmlFile3 = "C:\\Users\\Swapnil\\Desktop\\sjsu\\239\\project\\data\\newone\\ne.exp.gnfs.zs_Indicator_en_xml_v2.xml";
	static String xmlFile4 = "C:\\Users\\Swapnil\\Desktop\\sjsu\\239\\project\\data\\newone\\ne.imp.gnfs.zs_Indicator_en_xml_v2.xml";
	static String xmlFile5 = "C:\\Users\\Swapnil\\Desktop\\sjsu\\239\\project\\data\\newone\\ny.gdp.mktp.cd_Indicator_en_xml_v2.xml";
	
	static HashMap<String, double[]> minmaxMap = new HashMap<String, double[]>();
	
	public static void main(String[] args) {
		
		int startYear = 1980;
		int endYear = 2012;
		
		System.out.println("country,elec_con,boradband_sub,internet_usr,import,export,gdp_per_capita");
		for(int i=startYear; i<=endYear; i++){
			InputData.year=Integer.toString(i);
			XMLDataExtractor dataExtractor = new XMLDataExtractor(new String[]{xmlFile1, xmlFile2, xmlFile3, xmlFile4});
			dataExtractor.parseXMLForCountriesBasedOnMap();
			//printData();
			printScaledData();
		}
		//randomizePrintData();
	}
	
	
	private static void initializeMinMaxMap() {
		
		minmaxMap.put("broadband_sub", new double[]{Double.MAX_VALUE, Double.MIN_VALUE});
		
		minmaxMap.put("internet_user", new double[]{Double.MAX_VALUE, Double.MIN_VALUE});
		
		minmaxMap.put("ele_con", new double[]{Double.MAX_VALUE, Double.MIN_VALUE});
		
		minmaxMap.put("importVal", new double[]{Double.MAX_VALUE, Double.MIN_VALUE});
		
		minmaxMap.put("exportVal", new double[]{Double.MAX_VALUE, Double.MIN_VALUE});
		
		minmaxMap.put("gdp_per_capita", new double[]{Double.MAX_VALUE, Double.MIN_VALUE});
	}


	private static void calculateMinMaxMap() {
		for(Entry<String, Data> country : InputData.countryMap.entrySet()){
			Data data = InputData.countryMap.get(country.getKey());
			if(data.broadband_sub.equalsIgnoreCase("0") || data.ele_con.equalsIgnoreCase("0") ||
					data.gdp_per_capita.equalsIgnoreCase("0") || data.internet_user.equalsIgnoreCase("0") 
					|| data.exportVal.equalsIgnoreCase("0") || data.importVal.equalsIgnoreCase("0")){
				continue;
			}
			updateMinMax("broadband_sub", data.broadband_sub);
			updateMinMax("ele_con", data.ele_con);
			updateMinMax("gdp_per_capita", data.gdp_per_capita);
			updateMinMax("internet_user", data.internet_user);
			updateMinMax("exportVal", data.exportVal);
			updateMinMax("importVal", data.importVal);
		}
	}


	private static void updateMinMax(String factorName, String value) {
		double dValue = Double.parseDouble(value);
		double[] values = minmaxMap.get(factorName);
		if(dValue>values[1]){
			values[1]=dValue;
		}
		if(dValue<values[0]){
			values[0]=dValue;
		}
	}


	public static void printScaledData(){
		initializeMinMaxMap();
		calculateMinMaxMap();
		for(Entry<String, Data> country : InputData.countryMap.entrySet()){
			Data data = InputData.countryMap.get(country.getKey());
			if(data.broadband_sub.equalsIgnoreCase("0") || data.ele_con.equalsIgnoreCase("0") ||
					data.gdp_per_capita.equalsIgnoreCase("0") || data.internet_user.equalsIgnoreCase("0") 
					|| data.exportVal.equalsIgnoreCase("0") || data.importVal.equalsIgnoreCase("0")){
				continue;
			}
			System.out.println(country.getKey().trim().replaceAll(",", "")+","+
					scaledData("ele_con",data.ele_con)+","+
					scaledData("broadband_sub",data.broadband_sub)+","+
					scaledData("internet_user",data.internet_user)+","+
					scaledData("importVal",data.importVal)+","+
					scaledData("exportVal",data.exportVal)+","+
					scaledData("gdp_per_capita",data.gdp_per_capita));
		}
	} 
	
	private static double scaledData(String factorName, String factorVal) {
		double[] minmaxvals = minmaxMap.get(factorName);
		double minmaxRange = minmaxvals[1]-minmaxvals[0];
		double eleValue = Double.parseDouble(factorVal)-minmaxvals[0];
		return (rangeScale*eleValue)/minmaxRange;
	}


	public static void printData(){
		for(Entry<String, Data> country : InputData.countryMap.entrySet()){
			Data data = InputData.countryMap.get(country.getKey());
			if(data.broadband_sub.equalsIgnoreCase("0") || data.ele_con.equalsIgnoreCase("0") ||
					data.gdp_per_capita.equalsIgnoreCase("0") || data.internet_user.equalsIgnoreCase("0") 
					|| data.exportVal.equalsIgnoreCase("0") || data.importVal.equalsIgnoreCase("0")){
				continue;
			}
			System.out.println(country.getKey().trim().replaceAll(",", "")+","+data.ele_con+","+data.broadband_sub+","+data.internet_user+","+
			data.importVal+","+data.exportVal+","+data.gdp_per_capita);
		}
	}

	private static BigDecimal exportDataConversion(String exportData, String gdpData) {
		BigDecimal exportValue= perccentValue(exportData, gdpData);
		//return new BigDecimal(gdpData).subtract(exportValue);
		return exportValue;
	}

	private static BigDecimal importDataConversion(String importData, String gdpData) {
		BigDecimal importValue= perccentValue(importData, gdpData);
		//return new BigDecimal(gdpData).add(importValue);
		return importValue;
	}

	private static BigDecimal perccentValue(String data, String gdpData) {
		BigDecimal dataVal = new BigDecimal(data);
		BigDecimal gdpDataVal = new BigDecimal(gdpData);
		return dataVal.multiply(gdpDataVal).divide(new BigDecimal(100));
	}

	/*private static void randomizePrintData() {
		int min = 0;
		int max = InputData.countryMap.size();
		String[] printStack = new String[max];
		
		for(Entry<String, Data> country : InputData.countryMap.entrySet()){
			Data data = InputData.countryMap.get(country.getKey());
			if(data.broadband_sub.equalsIgnoreCase("0") && data.internet_user.equalsIgnoreCase("0") &&
					data.ele_con.equalsIgnoreCase("0") && data.gdp_per_capita.equalsIgnoreCase("0")){
				continue;
			}
			String csvData = country.getKey().trim().replaceAll(",", "")+","+data.ele_con+","+data.broadband_sub+","+data.internet_user+","+data.gdp_per_capita;
			int randomNo = ThreadLocalRandom.current().nextInt(min, max);
			while(printStack[randomNo]!=null){
				randomNo = ThreadLocalRandom.current().nextInt(min, max);
			}
			printStack[randomNo]=csvData;
		}
		
		int dataCutPercent = 75; //75%
		int dataCutNo = (int)((dataCutPercent*max)/100);
		System.out.println("Training Data");
		System.out.println("country,elec_con,boradband_sub,internet_usr,gdp_per_capita");
		for(int i=0; i<dataCutNo; i++){
			if(printStack[i]==null){
				continue;
			}
			System.out.println(printStack[i]);
		}
		System.out.println("\n\n");
		System.out.println("Testing Data");
		System.out.println("country,elec_con,boradband_sub,internet_usr,gdp_per_capita");
		for(int i=dataCutNo; i<max; i++){
			if(printStack[i]==null){
				continue;
			}
			System.out.println(printStack[i]);
		}
	}*/
	
}
