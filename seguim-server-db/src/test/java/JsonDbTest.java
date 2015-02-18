import com.seguim.domain.User;
import com.seguim.util.FileSystemJsonDB;
import com.seguim.util.JsonDB;
import com.seguim.util.MemoryJsonDB;
import com.seguim.util.PerformanceManager;

import java.util.List;

/**
 * Date: 29/01/15.
 * Time: 17:47
 */
public class JsonDbTest {
	public static void main(String args[]) throws Exception {
		System.out.println("Filesystem-----------------------------");
		testFileSystem();
		System.out.println("Memory-----------------------------");
		testMemory();
		System.out.flush();
		System.out.close();
	}

	private static void testMemory() throws Exception {
		JsonDB jsonDB = new MemoryJsonDB();

		User user = new User();
		user.setName("agusti");
		user.setSurname("febrer");
		user.setEmail("agustifp@gmail.com");
		jsonDB.saveOrUpdate(user, User.class);

		PerformanceManager.start("STORE_JSON");
		for(int i = 0; i < 200; i++) {
			user = new User();
			user.setName("Test_"+i);
			user.setSurname("Test_"+i);
			user.setEmail("test_"+i+"@gmail.com");
			jsonDB.saveOrUpdate(user, User.class);
		}
		PerformanceManager.stop("STORE_JSON");

		PerformanceManager.start("LIST");
		List list = jsonDB.list("name", "agusti", User.class);
		PerformanceManager.stop("LIST");
		System.out.println(list);

		PerformanceManager.start("LIST");
		list = jsonDB.list("name", "Test_", User.class);
		PerformanceManager.stop("LIST");
		System.out.println(list.size());

//		PerformanceManager.start("LIST");
//		user = jsonDB.get(3, User.class);
//		System.out.println(PerformanceManager.stop("LIST"));
//		user.setId(3);
//		jsonDB.saveOrUpdate(user, User.class);
//		System.out.println("user = " + user);
	}

	private static void testFileSystem() throws Exception {
		JsonDB jsonDB = new FileSystemJsonDB();

//		PerformanceManager.start("STORE_JSON");
//		for(int i = 0; i < 100; i++) {
//			User user = new User();
//			user.setName("Test_"+i);
//			user.setSurname("Test_"+i);
//			user.setEmail("test_"+i+"@gmail.com");
//			jsonDB.saveOrUpdate(user, User.class);
//		}
//		PerformanceManager.stop("STORE_JSON");

		PerformanceManager.start("LIST");
		List list = jsonDB.list("name", "agusti", User.class);
		PerformanceManager.stop("LIST");
		System.out.println(list);

		PerformanceManager.start("LIST");
		list = jsonDB.list("name", "Test_", User.class);
		PerformanceManager.stop("LIST");
		System.out.println(list.size());

		PerformanceManager.start("LIST");
		User user = jsonDB.get(3, User.class);
		PerformanceManager.stop("LIST");
//		user.setId(3);
//		jsonDB.saveOrUpdate(user, User.class);
		System.out.println("user = " + user);
	}
}