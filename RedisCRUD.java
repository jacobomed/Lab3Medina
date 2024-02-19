import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

//Redis are localhost and port 6379

public class RedisCRUD {
    public static void main(String[] args) {
        try {
            Jedis jedis = new Jedis("localhost");

            jedis.set("key", "value");

            String value = jedis.get("key");
            System.out.println(value);

            jedis.set("key", "newValue");

            jedis.del("key");

            jedis.close();
        } catch (JedisConnectionException e) {
            System.out.println("Could not connect to Redis: " + e.getMessage());
        }
    }
}
