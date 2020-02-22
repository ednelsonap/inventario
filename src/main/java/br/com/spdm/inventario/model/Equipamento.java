package br.com.spdm.inventario.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.envers.Audited;

@Entity
@Audited
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Equipamento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCadastro = Calendar.getInstance();
	@Column(unique=true, length=6)
	private String codigoPatrimonio;
	@Column(unique=true, length=20)
	private String nome;
	@Column(length=20)
	private String status;
	@Column(length=20)
	private String marca;
	@Column(length=20)
	private String modelo;
	@ManyToOne(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	private Fornecedor fornecedor;
	@Column(length=15)
	private String ip;
	@Column(length=500)
	private String observacao;
	
	@ManyToOne(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Categoria categoria;
	
	@ManyToOne(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	private Departamento departamento;
	
	//@ManyToOne
	//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	//private List<Localizacao> localizacoes = new ArrayList<>();
	
	
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public String getCodigoPatrimonio() {
		return codigoPatrimonio;
	}

	public void setCodigoPatrimonio(String codigoPatrimonio) {
		this.codigoPatrimonio = codigoPatrimonio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	/*public List<Localizacao> getLocalizacoes() {
		return localizacoes;
	}

	public void setLocalizacoes(List<Localizacao> localizacoes) {
		this.localizacoes = localizacoes;
	}*/
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
}
