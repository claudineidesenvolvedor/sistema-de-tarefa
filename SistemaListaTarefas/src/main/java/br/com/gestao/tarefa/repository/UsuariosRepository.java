package br.com.gestao.tarefa.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.gestao.tarefa.repository.entity.UsuarioEntity;

public class UsuariosRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;

	public UsuarioEntity porId(Integer id) {
		return this.entityManager.find(UsuarioEntity.class, id);
	}

	public List<UsuarioEntity> vendedores() {
		// TODO filtrar apenas vendedores (por um grupo específico)
		return this.entityManager.createQuery("from UsuarioEntity", UsuarioEntity.class).getResultList();
	}

	public UsuarioEntity porEmail(String email) {
		UsuarioEntity usuario = null;

		try {
			usuario = this.entityManager
					.createQuery("from UsuarioEntity where lower(email) = :email", UsuarioEntity.class)
					.setParameter("email", email.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			// nenhum usuário encontrado com o e-mail informado
		}

		return usuario;
	}

	public UsuarioEntity porCpf(String cpf) {
		UsuarioEntity usuario = null;

		try {
			usuario = this.entityManager.createQuery("from UsuarioEntity where cpf = :cpf", UsuarioEntity.class)
					.setParameter("cpf", cpf).getSingleResult();
		} catch (NoResultException e) {
			// nenhum usuário encontrado com o e-mail informado
		}

		return usuario;
	}

	@SuppressWarnings("unchecked")
	public List<UsuarioEntity> filtrados(UsuarioEntity filtro) {
		List<UsuarioEntity> lista = new ArrayList<UsuarioEntity>();

		try {
			lista = this.entityManager.createNamedQuery(
					"UsuarioEntity.findGeral")
					.setParameter("nome",  filtro.getNome())
					.setParameter("status", filtro.getStatus())
					.setParameter("id", filtro.getId())
					.getResultList();
		} catch (NoResultException e) {
			// nenhum usuário encontrado
		}

		return lista;
	}

	/**
	 * @SuppressWarnings({ "unchecked" }) public List<UsuarioEntity>
	 * filtrados(UsuarioEntity filtro) { Session session =
	 * entityManager.unwrap(Session.class); // Session session = (Session)
	 * entityManager.getDelegate(); Criteria criteria =
	 * session.createCriteria(UsuarioEntity.class);
	 * 
	 * if ((!(null == filtro.getId()))) { criteria.add(Restrictions.eq("id",
	 * filtro.getId())); } if (StringUtils.isNotBlank(filtro.getNome())) {
	 * criteria.add(Restrictions.ilike("nome", filtro.getNome(),
	 * MatchMode.ANYWHERE)); } if (filtro.getStatus() != null) {
	 * criteria.add(Restrictions.eq("status", filtro.getStatus())); } return
	 * criteria.addOrder(Order.asc("nome")).list(); }
	 */
	public UsuarioEntity guardar(UsuarioEntity entity) {
		return entityManager.merge(entity);
	}

}