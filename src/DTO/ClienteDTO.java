package DTO;

import java.util.Date;

/**
 * Classe onde fica os dados dos clientes 
 */
public class ClienteDTO {
	private int idCliente, idFunc;
	private String nome, cpf, telefone;
	private Date dataNasc, dataCompra;	
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdFunc() {
		return idFunc;
	}
	public void setIdFunc(int idFunc) {
		this.idFunc = idFunc;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}
	
	@Override
	public String toString() {
		return "ClienteDTO [idCliente=" + idCliente + ", idFunc=" + idFunc + ", nome=" + nome + ", cpf=" + cpf
				+ ", telefone=" + telefone + ", dataNasc=" + dataNasc + ", dataCompra=" + dataCompra + "]";
	}
	
}
