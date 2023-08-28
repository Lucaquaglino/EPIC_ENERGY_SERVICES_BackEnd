package EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;


@Entity
@Builder
@Data
@Table(name="clienti")

public class Cliente {

	@Id
	@GeneratedValue
	private UUID id_cliente;
	
	//dati cliente (azienda)
	private String ragioneSociale;
	private int partitaIva;
	private String emailCliente;
	private String pec;
	private int telefonoCliente;
	private List<String> indirizzi;
	@Enumerated(EnumType.STRING)
	private TIPO_CLIENTE tipoCliente;
	
	//dati contatto
	private String nomeContatto;
	private String cognomeContatto;
	private String emailContatto;
	private int telefonoContatto;
	
	//attributi inizializzati alla crazione
	private LocalDate dataInserimento;
	private LocalDate ultimoContatto; 
	final private double fatturatoAnnuale = 0;
	
	
}
