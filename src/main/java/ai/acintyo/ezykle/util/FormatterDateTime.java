package ai.acintyo.ezykle.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

public class FormatterDateTime {

	public String formatLocalDate() {

		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = currentDate.format(dateFormatter);
		return formattedDate;
	}

	

	public String formatLocalTime() {

		LocalTime currentTime = LocalTime.now();
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String formattedTime = currentTime.format(timeFormatter);
		return formattedTime;
	}

}
