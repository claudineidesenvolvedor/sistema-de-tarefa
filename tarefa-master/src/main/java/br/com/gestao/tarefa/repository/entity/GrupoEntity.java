package br.com.gestao.tarefa.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import br.com.gestao.tarefa.util.enuns.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited
@Table(name = "grupo")
@SequenceGenerator(name = "Grupo_Seq", sequenceName = "seq_grupo", allocationSize = 1, initialValue = 1)
public class GrupoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@PrimaryKeyJoinColumn(name = "grupoPK")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Grupo_Seq")
	private Integer id;

	@Column(nullable = false, length = 80)
	@Enumerated(EnumType.STRING)
	private Role descricao;

	@ManyToOne
	@JoinColumn(name = "id_perfil", nullable = false)
	private UsuarioEntity usuario;

}