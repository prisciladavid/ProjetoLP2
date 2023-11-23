package DTO;

import java.util.Date;

/**
 * Classe onde fica os dados dos funcionarios
 */
public class FuncionarioDTO {
		private int idFunc;
		private String nome, cpf, senha;
		private Date dataNasc, dataEntrada;	
		public static int idFuncionarioLogado;
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
		public String getSenha() {
			return senha;
		}
		public void setSenha(String senha) {
			this.senha = senha;
		}
		public Date getDataNasc() {
			return dataNasc;
		}
		public void setDataNasc(Date dataNasc) {
			this.dataNasc = dataNasc;
		}
		public Date getDataEntrada() {
			return dataEntrada;
		}
		public void setDataEntrada(Date dataEntrada) {
			this.dataEntrada = dataEntrada;
		}
		public static int getIdFuncionarioLogado() {
			return idFuncionarioLogado;
		}
		public static void setIdFuncionarioLogado(int idFuncionarioLogado) {
			FuncionarioDTO.idFuncionarioLogado = idFuncionarioLogado;
		}
		
		@Override
		public String toString() {
			return "FuncionarioDTO [idFunc=" + idFunc + ", nome=" + nome + ", cpf=" + cpf + ", senha=" + senha
					+ ", dataNasc=" + dataNasc + ", dataEntrada=" + dataEntrada + "]";
		}
		
		
}
