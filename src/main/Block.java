package main;

import main.utils.Function3;

public class Block {
    public Character[][] block;
    public int area;

    public Block(String s) {
        String[] parts = s.split("\\r?\\n");
        int maxLen = -1;

        for (String p : parts) {
            maxLen = Math.max(p.length(), maxLen);
        }

        this.block = new Character[parts.length][maxLen];

        for (int i = 0; i < this.block.length; i++) {
            for (int j = 0; j < this.block[i].length; j++) {
                this.block[i][j] = parts[i].length() > j ? (parts[i].charAt(j) != ' ' ? parts[i].charAt(j) : '.') : '.';
                if (Character.isAlphabetic(this.block[i][j])) {
                    this.area++;
                }
            }
        }
    }

    public void iterate(Function3<Character, Integer, Integer> f) {
        for (int i = 0; i < this.block.length; i++) {
            for (int j = 0; j < this.block[i].length; j++) {
                f.apply(this.block[i][j], i, j);
            }
        }
    }

    public int[] size() {
        int[] L = {this.block.length, this.block[0].length};
        return L;
    }

    public Block rotate(int r) {
        StringBuilder sb = new StringBuilder();
        
        switch(r) {
            case 0:
                return this;
            case 1:
                for (int j = this.block[0].length-1; j >= 0; j--) {
                    for (int i = 0; i < this.block.length; i++) {
                        sb.append(this.block[i][j]);
                    }
                    sb.append('\n');
                }
                break;
            case 2:
                for (int i = this.block.length-1; i >= 0; i--) {
                    for (int j = this.block[0].length-1; j >= 0; j--) {
                        sb.append(this.block[i][j]);
                    }
                    sb.append('\n');
                }
                break;
            case 3:
                for (int j = 0; j < this.block[0].length; j++) {
                    for (int i = this.block.length-1; i >= 0; i--) {
                        sb.append(this.block[i][j]);
                    }
                    sb.append('\n');
                }
                break;
        }
        return new Block(sb.toString());
    }

    public Block flip(int f) {
        StringBuilder sb = new StringBuilder();
        
        switch(f) {
            case 0:
                return this;
            case 1:
                for (int i = 0; i < this.block.length; i++) {
                    for (int j = this.block[0].length-1; j >= 0; j--) {
                        sb.append(this.block[i][j]);
                    }
                    sb.append('\n');
                }
                break;
        }
        return new Block(sb.toString());
    }

    public void print() {
        for (int i = 0; i < this.block.length; i++) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < this.block[i].length; j++) {
                sb.append(this.block[i][j]);
            }
            
            System.out.println(sb.toString());
        }
    }
}