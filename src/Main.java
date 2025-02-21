import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Board.*;

public class Main {
    public static void main(String[] args) {
        Board board;
        ArrayList<Block> blocks = new ArrayList<Block>();

        try(BufferedReader br = new BufferedReader(new FileReader("../test/test1.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            int lineIdx = 0;

            int x = -1;
            int y = -1;
            int blockLen = -1;
            char currentChar = '\u0000';

            while (line != null) {
                // Size
                if (lineIdx == 0) {
                    String[] parts = line.split(" ");
                    if (parts.length != 3) {
                        throw new IOException("Wrong arguments, check first line.");
                    }

                    x = Integer.parseInt(parts[0]);
                    y = Integer.parseInt(parts[1]);
                    blockLen = Integer.parseInt(parts[2]);
                // Constructor
                } else if (lineIdx == 1) {
                    switch (line) {
                        case "DEFAULT":
                            board = new RectBoard(y, x);
                            board.print();
                            break;
                        default:
                            throw new IOException("Wrong arguments, check first line.");
                    }
                // Block reading
                } else {
                    if (sb.length() == 0 && blocks.size() == 0) {
                        currentChar = line.charAt(0);
                    }

                    if (currentChar != line.charAt(0)) {
                        Block newBlock = new Block(sb.toString());
                        blocks.add(newBlock);

                        sb.setLength(0);
                        currentChar = line.charAt(0);
                    }

                    sb.append(line);
                    sb.append('\n');
                }

                line = br.readLine();
                lineIdx++;
            }
            Block newBlock = new Block(sb.toString());
            blocks.add(newBlock);
            sb = null;

            blocks.forEach((b) -> b.print());
            if (blocks.size() != blockLen) {
                throw new IOException("Wrong arguments, check first line.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("Failed to read!");
            System.out.println(e);
        }
    }
}