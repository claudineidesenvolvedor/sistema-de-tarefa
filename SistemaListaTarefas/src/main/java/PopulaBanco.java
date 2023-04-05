import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.gestao.tarefa.repository.entity.GrupoEntity;
import br.com.gestao.tarefa.repository.entity.UsuarioEntity;
import br.com.gestao.tarefa.util.enuns.Role;

public class PopulaBanco {

	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("TarefaPU");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();

		adcionarUsuario(manager);
		manager.getTransaction().commit();

	}

	private static void adcionarUsuario(EntityManager manager) {
		UsuarioEntity user = new UsuarioEntity();

		user.setNome("admin");
		user.setEmail("craucrau@teste.com");
		user.setCpf("003.033.001-75");
		user.setSenha("12345");
		user.setGrupos(adcionargrupo(user));
		manager.persist(user);
	}

	private static List<GrupoEntity> adcionargrupo(UsuarioEntity user) {
		List<GrupoEntity> lista = new ArrayList<GrupoEntity>();
		GrupoEntity grupo = new GrupoEntity();
		grupo.setDescricao(Role.ADMIN);
		grupo.setUsuario(user);
		GrupoEntity grupo1 = new GrupoEntity();
		grupo1.setDescricao(Role.ADMIN_COMMON);
		grupo1.setUsuario(user);
		GrupoEntity grupo2 = new GrupoEntity();
		grupo2.setDescricao(Role.COMMON);
		grupo2.setUsuario(user);
		lista.add(grupo);
		lista.add(grupo1);
		lista.add(grupo2);
		return lista;

	}

}
