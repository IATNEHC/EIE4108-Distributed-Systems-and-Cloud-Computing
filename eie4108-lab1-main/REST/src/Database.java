import java.util.Hashtable;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.sun.jersey.spi.resource.Singleton;

@Singleton
@Path("/mark")
public class Database {
	private Hashtable<String,Integer> table;
	
	public Database() {
		table = new Hashtable<String,Integer>();
	}
	
	@POST
	@Path("add/{student}+{mark}")
	public String addRecord(@PathParam("student") String student, @PathParam("mark") String mark) {
	// Add a record using id as key and mark as value
		if(table.containsKey(student)) {
			return String.format("Record of %s exists", student);
		}
		table.put(student, Integer.valueOf(mark));
		System.out.println("Student: " + student);
		System.out.println("Mark: " + mark);
		return String.format("Record of %s added", student);
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getMark(@QueryParam("student") String student) {
	// Retrieve a record using id as key
		if(!table.containsKey(student)) {
			System.out.println("Student not exists");
			return String.format("Record of %s not exists", student);
		}
		System.out.println(String.format("%s: %s", student, table.get(student)));
		return String.format("Mark of %s: %s", student, Integer.toString(table.get(student)));
	}
	
	@POST
	@Path("update/{student}+{mark}")
	public String updateRecord(@PathParam("student") String student, @PathParam("mark") String mark) {
	// Update a record using id as key and mark as value
		if(!table.containsKey(student)) {
			System.out.println("Student not exists");
			return String.format("Record of %s not exists", student);
		}
		table.replace(student, Integer.valueOf(mark));
		System.out.println("Student: " + student);
		System.out.println("Mark: " + mark);
		return String.format("Record of %s updated", student);
	}
}
