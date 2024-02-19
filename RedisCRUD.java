import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import com.google.gson.Gson;

//Redis are localhost and port 6379

public class RedisCRUD {
    public static void main(String[] args) {
        try {
            Gson gson = new Gson();
            Customer customer = new Customer("101", "John Doe", "john@example", "123 Street rd", "20", "123456", "male");
            String jcustomer = gson.toJson(customer);

            Jedis jedis = new Jedis("localhost", 6379);
            //Create (Set a key-value pair)
            jedis.set(customer.getId(), jcustomer);

            //Read (Get the value of a key)
            String value = jedis.get(customer.getId());
            System.out.println(value);

            //Update (Update the value of a key)
            customer.setName("Joe Doe");
            jcustomer = gson.toJson(customer);
            jedis.set(customer.getName(), jcustomer);

            //Delete (Delete a key-value pair)
            jedis.del(customer.getId());

            jedis.close();
        } catch (JedisConnectionException e) {
            System.out.println("Could not connect to Redis: " + e.getMessage());
        }
    }
}
