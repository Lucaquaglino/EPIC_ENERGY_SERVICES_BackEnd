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
	
	//dati cliente
	private String ragione_sociale;
	private int partita_iva;
	private String email_cliente;
	private String pec;
	private int telefono_cliente;
	private List<String> indirizzi;
	@Enumerated(EnumType.STRING)
	private TIPO_CLIENTE tipo_cliente;
	
	//dati contatto
	private String nome_contatto;
	private String cognome_contatto;
	private String email_contatto;
	private int telefono_contatto;
	
	//attributi inizializzati alla crazione
	private LocalDate data_inserimento;
	private double fatturato_annuale;
	private LocalDate ultimo_contatto; 
	
}
