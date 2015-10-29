package angularbeans.javaone.helloWorld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;

import angularBeans.api.AngularBean;
import angularBeans.api.CORS;
import angularBeans.api.NGModel;
import angularBeans.api.NGReturn;
import angularBeans.api.NGSubmit;
import angularBeans.log.NGLogger;
import angularBeans.log.NGLogger.Level;
import angularBeans.realtime.RealTimeClient;
import angularBeans.util.ModelQuery;

// i added few comments to finish ower counter case :) 

//1) we make again ower @AngularBean requestScoped (and the counter??)

@AngularBean
public class HelloAngularBeans {

	private int counter = 0;

	@Inject
	NGLogger logger;

	private List<User> users;

	@Inject
	ModelQuery models;

	@Inject
	RealTimeClient client;

	@PostConstruct
	public void init() {

		users = new ArrayList<User>(Arrays.asList(new User("user1", 1),
				new User("user2", 2)

		)

		);

	}

	@NGReturn(model = "message", updates = "counter")
	//2) we add NG submit that will tell the client 
	//proxy to send his counter value and update the counter here (we need to add a setter also)
	@NGSubmit(backEndModels="counter")
	public String sayHello(String name) {

		counter++;
		if (counter == 10) {
			logger.log(Level.DEBUG, "you called sayHello %d times", counter);

		}
		return "hello " + name + " from AngularBeans !";

	}

	@NGModel
	public int getCounter() {
		return counter;
	}
	
	
	//3) added for NGSubmit..
	public void setCounter(int counter) {
		this.counter = counter;
	}

	@GET
	public void getSomeUsers() {

		models.setProperty("users", users);

	}

	
	public void remove(User user) {

		client.broadcast(models.removeFrom("users", user), false);

	}

}
