package br.com.spdm.inventario.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Movimentacao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCadastro;
	private Calendar dataMovimentacao;
	@OneToOne
	private Equipamento equipamentoMovimentado;
	//private Departamento departamentoAnterior;
	//private Departamento departamentoAtual;
	//private Usuario usuarioQueMovimentou;
}
