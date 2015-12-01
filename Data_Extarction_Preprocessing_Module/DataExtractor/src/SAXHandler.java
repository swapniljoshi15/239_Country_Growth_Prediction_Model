import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler{
	
	
	private boolean parseCountry = false;
	private boolean parseItem = false;
	private boolean parseYear = false;
	private boolean parseValue = false;
	private Record record = null;
	

	public void startElement(String uri, String localName,String qName, 
            Attributes attributes) throws SAXException {
		if("record".equalsIgnoreCase(qName)){
			record = new Record();
			return;
		}
		if("Country or Area".equalsIgnoreCase(attributes.getValue(0))){
			parseCountry = true;
			return;
		}
		if("Item".equalsIgnoreCase(attributes.getValue(0))){
			parseItem = true;
			return;
		}
		if("Year".equalsIgnoreCase(attributes.getValue(0))){
			parseYear = true;
			return;
		}
		if("Value".equalsIgnoreCase(attributes.getValue(0))){
			parseValue = true;
			return;
		}
	}

	public void endElement(String uri, String localName,
			String qName) throws SAXException {
		if("record".equalsIgnoreCase(qName)){
			addToCountryDataMap(record);
			record = null;
		}
	}

	private void addToCountryDataMap(Record record) {
		try{
			if(InputData.year.equalsIgnoreCase(record.getYear())){
				Data data = InputData.countryMap.get(record.getCountry().trim());
				if(data==null){
					return;
				}
				if("Electric power consumption (kWh per capita)".equalsIgnoreCase(record.getItem().trim())){
					if(record==null || record.getValue()==null || record.getValue().trim().isEmpty()){
						data.ele_con="0";
						return;
					}
					data.ele_con=record.getValue();
					return;
				}
				if("Fixed broadband subscriptions (per 100 people)".equalsIgnoreCase(record.getItem().trim())){
					if(record==null || record.getValue()==null || record.getValue().trim().isEmpty()){
						data.broadband_sub="0";
						return;
					}
					data.broadband_sub=record.getValue();
					return;				
				}
				if("Internet users (per 100 people)".equalsIgnoreCase(record.getItem().trim())){
					if(record==null || record.getValue()==null || record.getValue().trim().isEmpty()){
						data.internet_user="0";
						return;
					}
					data.internet_user=record.getValue();
					return;
				}
				if("GDP per capita (current US$)".equalsIgnoreCase(record.getItem().trim())){
					if(record==null || record.getValue()==null || record.getValue().trim().isEmpty()){
						data.gdp_per_capita="0";
						return;
					}
					data.gdp_per_capita=record.getValue();
					return;
				}
				if("Imports of goods and services (% of GDP)".equalsIgnoreCase(record.getItem().trim())){
					if(record==null || record.getValue()==null || record.getValue().trim().isEmpty()){
						data.importVal="0";
						return;
					}
					data.importVal=record.getValue();
					return;
				}
				if("Exports of goods and services (% of GDP)".equalsIgnoreCase(record.getItem().trim())){
					if(record==null || record.getValue()==null || record.getValue().trim().isEmpty()){
						data.exportVal="0";
						return;
					}
					data.exportVal=record.getValue();
					return;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void characters(char ch[], int start, int length) throws SAXException {
		if(parseCountry){
			record.setCountry(new String(ch, start, length));
			parseCountry = false;
		}
		if(parseItem){
			record.setItem(new String(ch, start, length));
			parseItem = false;
		}
		if(parseYear){
			record.setYear(new String(ch, start, length));
			parseYear = false;
		}
		if(parseValue){
			record.setValue(new String(ch, start, length));
			parseValue = false;
		}
	}


}
