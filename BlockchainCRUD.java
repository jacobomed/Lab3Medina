import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Block {
    private int index;
    private long timestamp;
    private String previousHash;
    private String hash;
    private String data;

    public Block(int index, String previousHash, String data) {
        this.index = index;
        this.timestamp = new Date().getTime();
        this.data = data;
        this.hash = calculateHash();
    }

        public String calculateHash() {
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

    class Blockchain {
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
                System.out.println("Data: " + block.getData());

            }
        }
    }

    public class SimpleBlockchain {
        public static void main(String[] args) {}
        Blockchain blockchain = new Blockchain();

        blockchain.addBlock("Transaction 1");
        blockchain.addBlock("Transaction 2");
        blockchain.addBlock("Transaction 3");

        blockchain.printBlockchain();
    }
}
