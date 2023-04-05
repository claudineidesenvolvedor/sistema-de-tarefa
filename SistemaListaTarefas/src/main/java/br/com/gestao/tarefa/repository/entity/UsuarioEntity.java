package br.com.gestao.tarefa.repository.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.gestao.tarefa.util.enuns.StatusPessoa;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
@SequenceGenerator(name = "Usuario_Seq", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
@NamedQueries({ @NamedQuery(name = "UsuarioEntity.findAll", query = "SELECT p FROM UsuarioEntity p"),
		@NamedQuery(name = "UsuarioEntity.findGeral", query = "SELECT p FROM UsuarioEntity p WHERE( p.nome like :nome or p.status = :status or p.id = :id ) order by p.nome ") })
public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@PrimaryKeyJoinColumn(name = "usuarioPK")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Usuario_Seq")
	private Integer id;

	@Column(nullable = false, length = 80, name = "nome")
	private String nome;

	@Column(nullable = false, length = 40, name = "email")
	private String email;

	@Column(nullable = false, length = 40, name = "cpf")
	private String cpf;

	@Column(nullable = false, name = "senha")
	private String senha;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10, name = "status")
	private StatusPessoa status = StatusPessoa.ATIVO;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	// @JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name =
	// "usuario_id"), inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	private List<GrupoEntity> grupos = new ArrayList<>();

}