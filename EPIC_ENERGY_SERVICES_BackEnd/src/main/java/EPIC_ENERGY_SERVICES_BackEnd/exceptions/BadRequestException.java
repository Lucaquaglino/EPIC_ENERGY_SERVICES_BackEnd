package EPIC_ENERGY_SERVICES_BackEnd.exceptions;

@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException {

	public BadRequestException(String message) {
		super(message);
	}

}
