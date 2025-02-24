package main.Board;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import main.Block;

public abstract class Board {
    public Character[][] board;
    private static final Map<Character, String> consoleColorMap = new HashMap<>();
    private static final Map<Character, Integer> imageColorMap = new HashMap<>();
    static {
        consoleColorMap.put('A', "\u001B[31m");
        consoleColorMap.put('B', "\u001B[32m");
        consoleColorMap.put('C', "\u001B[33m");
        consoleColorMap.put('D', "\u001B[34m");
        consoleColorMap.put('E', "\u001B[35m");
        consoleColorMap.put('F', "\u001B[36m");
        consoleColorMap.put('G', "\u001B[38;2;140;184;165m");
        consoleColorMap.put('H', "\u001B[91m");
        consoleColorMap.put('I', "\u001B[92m");
        consoleColorMap.put('J', "\u001B[93m");
        consoleColorMap.put('K', "\u001B[94m");
        consoleColorMap.put('L', "\u001B[95m"); 
        consoleColorMap.put('M', "\u001B[96m");
        consoleColorMap.put('N', "\u001B[38;2;191;105;29m"); 
        consoleColorMap.put('O', "\u001B[38;2;38;171;164m");
        consoleColorMap.put('P', "\u001B[38;2;156;201;66m");
        consoleColorMap.put('Q', "\u001B[38;2;128;43;126m");
        consoleColorMap.put('R', "\u001B[38;2;194;164;89m");
        consoleColorMap.put('S', "\u001B[38;2;186;123;145m");
        consoleColorMap.put('T', "\u001B[38;2;115;55;37m");
        consoleColorMap.put('U', "\u001B[38;2;118;194;172m");
        consoleColorMap.put('V', "\u001B[38;2;142;21;194m");
        consoleColorMap.put('W', "\u001B[38;2;76;81;181m");
        consoleColorMap.put('X', "\u001B[38;2;77;153;61m");
        consoleColorMap.put('Y', "\u001B[38;2;94;35;73m");
        consoleColorMap.put('Z', "\u001B[38;2;39;161;81m");

        imageColorMap.put('A', 0xff800000);
        imageColorMap.put('B', 0xff008000);
        imageColorMap.put('C', 0xff808000);
        imageColorMap.put('D', 0xff000080);
        imageColorMap.put('E', 0xff800080);
        imageColorMap.put('F', 0xff008080);
        imageColorMap.put('G', 0xff8cb8a5);
        imageColorMap.put('H', 0xffff0000);
        imageColorMap.put('I', 0xff00ff00);
        imageColorMap.put('J', 0xffffff00);
        imageColorMap.put('K', 0xff0000ff);
        imageColorMap.put('L', 0xffff00ff); 
        imageColorMap.put('M', 0xff00ffff);
        imageColorMap.put('N', 0xffbf691d); 
        imageColorMap.put('O', 0xff26aba4);
        imageColorMap.put('P', 0xff9cc942);
        imageColorMap.put('Q', 0xff802b7e);
        imageColorMap.put('R', 0xffc2a459);
        imageColorMap.put('S', 0xffba7b91);
        imageColorMap.put('T', 0xff733725);
        imageColorMap.put('U', 0xff76c2ac);
        imageColorMap.put('V', 0xff8e15c2);
        imageColorMap.put('W', 0xff4c51b5);
        imageColorMap.put('X', 0xff4d993d);
        imageColorMap.put('Y', 0xff5e2349);
        imageColorMap.put('Z', 0xff27a151);
    }

    public abstract Board clone();
    public abstract boolean place(Block b, int x, int y);
    public boolean isEmpty(int y, int x) {
        if (y < this.board.length && x < this.board[0].length) {
            return this.board[y][x] == '.';
        }
        return false;
    }

    public boolean isOccupied(int y, int x) {
        if (y < this.board.length && x < this.board[0].length) {
            return Character.isLetterOrDigit(this.board[y][x]);
        }
        return false;
    }

    public String toString(Boolean color) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.board.length; i++) {

            for (int j = 0; j < this.board[i].length; j++) {
                char c = this.board[i][j];
                if (color) {
                    sb.append(consoleColorMap.containsKey(c) ? consoleColorMap.get(c) : "\u001B[0m");
                    sb.append(c);
                    sb.append("\u001B[0m");
                } else {
                    sb.append(c);
                }
            }
            
            sb.append("\n");
        }
        return sb.toString();
    }

    public void print() {
        System.out.println(toString(true));
    }

    public void toFile(String filepath) {
        try (FileWriter f = new FileWriter(filepath)) {
            f.write(toString(false));
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void toImage(String filepath) {
        int PIXELS_PER_CHAR = 50;
        try {
            BufferedImage bi = new BufferedImage(board[0].length * PIXELS_PER_CHAR, board.length * PIXELS_PER_CHAR, BufferedImage.TYPE_INT_ARGB);

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    for (int px = 0; px < PIXELS_PER_CHAR; px++) {
                        for (int py = 0; py < PIXELS_PER_CHAR; py++) {
                            char c = this.board[i][j];
                            bi.setRGB(j*PIXELS_PER_CHAR+px, i*PIXELS_PER_CHAR+py, imageColorMap.containsKey(c) ? imageColorMap.get(c) : 0x00000000);
                        }
                    }
                }
            }

            File output = new File(filepath);
            ImageIO.write(bi, "png", output);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}