
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class TestaConexao {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("TarefaPU");
		EntityManager manager = factory.createEntityManager();
		
	}
}
