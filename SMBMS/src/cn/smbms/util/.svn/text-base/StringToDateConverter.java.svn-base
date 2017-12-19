package cn.smbms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;


//编写自定义转换器
public class StringToDateConverter implements Converter<String,Date>{

	private String datePattern;
	public StringToDateConverter(String datePattern){
		System.out.println("StringToDateConverter convert:"+datePattern);
		this.datePattern = datePattern;
	 }
	
	public Date convert(String s) {
		Date date = null;
		try {
			date = new SimpleDateFormat(datePattern).parse(s);
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
