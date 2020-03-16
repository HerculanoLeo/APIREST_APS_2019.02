package br.com.aplicacaoaps.apirest.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Transient
	private BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "email", unique = true, length = 200)
	private String email;
	private String nome;
	private String senha;
	@ManyToMany
	@JoinTable(name = "Usuario_Perfil", 
	joinColumns = @JoinColumn(name = "id"),
	inverseJoinColumns = @JoinColumn(name = "perfil_nome"))
	private List<Perfil> perfil = new ArrayList<Perfil>();

	public Usuario(String nome, String email,String senha, List<Perfil> perfil) {
		this.email = email;
		this.nome = nome;
		this.senha = bcryptEncoder.encode(senha);
		this.perfil = perfil;
	}
	
	public void setSenha(String senha) {
		this.senha = bcryptEncoder.encode(senha);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfil;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
