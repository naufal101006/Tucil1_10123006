public class Block {
    Character[][] block;

    public Block(String s) {
        String[] parts = s.split("\\r?\\n");
        int maxLen = -1;

        for (String p : parts) {
            maxLen = Math.max(p.length(), maxLen);
        }

        this.block = new Character[parts.length][maxLen];

        for (int i = 0; i < this.block.length; i++) {
            for (int j = 0; j < this.block[i].length; j++) {
                this.block[i][j] = parts[i].length() > j ? parts[i].charAt(j) : '.';
            }
        }
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