import java.util.*;


public class Hora{

  public String get_hora(){
		Date date = new Date();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		int hours = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		
		return Integer.toString(hours)+ ":" + Integer.toString(min) +":" + Integer.toString(sec);
	}

}