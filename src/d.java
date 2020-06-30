public class d {
    public static void main(String[] args) {
        int sh  = 0;
        int sw  = 0;



        for (int y = sh + 120, my = 480 - y - 1; y < 360; y++, my--) {
            int k = 0;
            for (int x = sw + 150, L = 640 - x - 1; x < 480 && x >= sw + 150; k++, L--,x++) {
                System.out.println("x = "+ x);

            }
            System.out.println("y = "+ y);

        }}
            }