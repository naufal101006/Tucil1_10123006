package main;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import main.Board.*;

public class Main {
    private static int iters = 0;

    private static Board solveRec(ArrayList<Board> boards, Block[] blocks, int depth) {
        Board b =  boards.get(boards.size()-1);
        if (depth >= blocks.length) {
            return b;
        }

        for (int i = 0; i < b.board.length; i++) {
            for (int j = 0; j < b.board[0].length; j++) {
                if (!b.isOccupied(i, j)) {
                    for (int f = 0; f < 2; f++) {
                        for (int r = 0; r < 4; r++) {
                            Board clone = b.clone();
                            boolean canPlace = clone.place(blocks[depth].flip(f).rotate(r), i, j);

                            if (canPlace) {
                                iters++;
                                boards.add(clone);

                                Board next = solveRec(boards, blocks, depth+1);
    
                                if (next != null) {
                                    return next;
                                }
                            }
                        }
                    }
                }
            }
        }

        boards.remove(boards.size()-1);
        return null;
    }

    private static Board solve(Board board, Block[] blocks) {
        ArrayList<Board> list = new ArrayList<Board>();
        list.add(board);

        Board result = solveRec(list, blocks, 0);
        return result;
    }

    public static void main(String[] args) {
        Board board = new RectBoard(0, 0);
        ArrayList<Block> blocks = new ArrayList<Block>();

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            int lineIdx = 0;

            int x = -1;
            int y = -1;
            int blockLen = -1;
            int blockArea = 0;
            char currentChar = '\u0000';
            String boardType = "";

            while (line != null) {
                // Size
                if (lineIdx == 0) {
                    String[] parts = line.split(" ");
                    if (parts.length != 3) {
                        throw new IOException("Wrong arguments, check first line.");
                    }

                    y = Integer.parseInt(parts[0]);
                    x = Integer.parseInt(parts[1]);
                    blockLen = Integer.parseInt(parts[2]);
                // Constructor
                } else if (lineIdx == 1) {
                    switch (line) {
                        case "DEFAULT":
                            boardType = "Default";
                            board = new RectBoard(y, x);
                            // board.print();
                            break;
                        case "CUSTOM":
                            boardType = "Custom";
                            break;
                        default:
                            throw new IOException("Wrong board type, check second line.");
                    }
                // Block reading
                } else {
                    if (boardType == "Custom" && lineIdx < 2+y) {
                        sb.append(line);
                        sb.append('\n');
                    } else {
                        if (boardType == "Custom" && lineIdx == 2+y) {
                            board = new CustomBoard(y, x, sb.toString());
                            sb.setLength(0);
                        }

                        if (sb.length() == 0 && blocks.size() == 0) {
                            currentChar = line.trim().charAt(0);
                        }
    
                        if (currentChar != line.trim().charAt(0)) {
                            Block newBlock = new Block(sb.toString());
                            blocks.add(newBlock);
                            blockArea += newBlock.area;
    
                            sb.setLength(0);
                            currentChar = line.trim().charAt(0);
                        }
    
                        sb.append(line);
                        sb.append('\n');
                    }
                }

                line = br.readLine();
                lineIdx++;
            }
            Block newBlock = new Block(sb.toString());
            blocks.add(newBlock);
            blockArea += newBlock.area;
            sb = null;

            if (blocks.size() != blockLen) {
                throw new IOException("Wrong block length, check first line.");
            }

            if (board.area != blockArea) {
                throw new IOException("Block area and board area mismatch.");
            }

            Block[] blockArr = new Block[blockLen];
            blockArr = blocks.toArray(blockArr);

            long start = System.currentTimeMillis();
            Board solution = solve(board, blockArr);
            long end = System.currentTimeMillis();

            if (solution != null) {
                solution.print();
                System.out.println("Took " + (end-start) + "ms");
                System.out.println("Iterations: " + iters);
    
                solution.toFile(args[0].replaceAll("(\\w+)\\.txt$", "$1-result.txt"));
                solution.toImage(args[0].replaceAll("(\\w+)\\.txt$", "$1-result.png"));
            } else {
                System.out.println("No solution");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.err.println(e);
        } catch (IOException e) {
            System.out.println("Failed to read!");
            System.err.println(e);
        }
    }
}