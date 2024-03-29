/** Project: IST242Lab3
 * Purpose Details: TO create CRUD operations for different databases
 * Course: IST242
 * Author: Jacobo Medina
 * Date Developed:2-18-2024
 * Last Date Changed: 2-21-2024
 * Rev: latest update 2-21-2024

 */


import com.google.gson.Gson;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Jacobo Medina
 */
class Block {

    /**
     * This is the Block class. It sets up index, timestamp, hashes, and data strings
     */

    private int index;
    private long timestamp;
    private String previousHash;
    private String hash;
    private String data;

    /**
     * Jacobo Medina
     */

    public Block(int index, String previousHash, String data) {

        /**
         * This is the Block constructor. It sets up index, timestamp, hashes, and data strings
         */

        this.index = index;
        this.timestamp = new Date().getTime();
        this.data = data;
        this.hash = calculateHash();
    }

    /**
     * Jacobo Medina
     */

    public String calculateHash() {

        /**
         * This is the Hash calculator . It's used to get a new hash for each file
         */


            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                String input = index + timestamp + previousHash + data;
                byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));
                StringBuilder hexString = new StringBuilder();

                for (byte b : hashBytes) {
                    String hex = Integer.toHexString(0xff & b);
                    if (hex.length() == 1) hexString.append('0');
                    hexString.append(hex);
                }
                return hexString.toString();
            } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

        public int getIndex () {
            return index;
        }
        public long getTimestamp () {
            return timestamp;
        }
        public String getPreviousHash() {
            return previousHash;
        }
        public String getHash () {
            return hash;
        }
        public String getData () {
            return data;
        }
    }

/**
 * Jacobo Medina
 */

class Blockchain {

    /**
     * This is the Blockchain class . It's used to create the blockchain and gather the data in order ot be displayed
     */


        private List<Block> chain;

        public Blockchain() {
            chain = new ArrayList<Block>();

            chain.add(new Block(0, "0", "Genesis Block"));
        }

        public void addBlock(String data) {
            Block previousBlock = chain.get(chain.size() - 1);
            Block newBlock = new Block(previousBlock.getIndex() + 1, previousBlock.getHash(), data);
            chain.add(newBlock);
        }


            public void printBlockchain() {
                for (Block block : chain) {
                    System.out.println("Block #" + block.getIndex());
                    System.out.println("Timestamp: " + block.getTimestamp());
                    System.out.println("Previous Hash: " + block.getPreviousHash());
                    System.out.println("Hash: " + block.getHash());
                    System.out.println("Data: " + block.getData());
                    System.out.println();
                }
            }

        }

/**
 * Jacobo Medina
 */

public class SimpleBlockchain {

    /**
     * This is the SimpleBlockchain class . It's used to insert the
     */



            public static void main() {

                Gson gson = new Gson();
                Customer customer1 = new Customer("101", "John Doe", "john@example", "123 Street rd");
                Customer customer2 = new Customer("102", "Jacobo Doe", "jacobo@example", "124 Street rd");
                Customer customer3 = new Customer("103", "Jordan Doe", "jordan@example", "125 Street rd");
                String jcustomer = gson.toJson(customer1);
                String jcustomer2 = gson.toJson(customer2);
                String jcustomer3 = gson.toJson(customer3);


                Blockchain blockchain = new Blockchain();

                blockchain.addBlock(jcustomer);
                blockchain.addBlock(jcustomer2);
                blockchain.addBlock(jcustomer3);

                blockchain.printBlockchain();
            }
}


