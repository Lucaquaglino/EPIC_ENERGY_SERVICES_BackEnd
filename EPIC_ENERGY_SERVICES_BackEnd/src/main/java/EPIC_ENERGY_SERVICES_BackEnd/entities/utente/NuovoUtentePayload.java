package EPIC_ENERGY_SERVICES_BackEnd.entities.utente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NuovoUtentePayload {

	private String username;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private Ruolo ruolo;

}
