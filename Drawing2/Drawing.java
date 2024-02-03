import java.util.Arrays;

public class Drawing implements SimpleDrawing{
    private static int rozmiar = 0;
    private static int pozX;
    private static int pozY;

    public static int[][] plotno;
    public Drawing() {;

    }

    public void setCanvasGeometry(Geometry input) {
        if(input.getSize()<0 || input.getInitialFirstCoordinate() < 0  || input.getInitialSecondCoordinate() < 0){
            return;
        }
        if(input.getSize()==0){
            Drawing.plotno[0][0] = 0;
            return;

        }
        Drawing.rozmiar = input.getSize();
        Drawing.pozX = input.getInitialFirstCoordinate();
        Drawing.pozY = input.getInitialSecondCoordinate();
        Drawing.plotno = new int[rozmiar][rozmiar];
        clear();
    }

    public void draw(Segment segment) {
        int color = segment.getColor();
        int dir = segment.getDirection();
        int length = segment.getLength();
        int i = Drawing.pozX;
        int j = Drawing.pozY;
        int destX;
        int destY;

        if(length<0 || i<0 || j<0 || Drawing.rozmiar < 1){
            return;
        }

        if(dir==1) {
            destX = Drawing.pozX + length;
            if(destX>rozmiar-1) {
                destX = rozmiar-1;
            }
            while (i != destX) {
                Drawing.plotno[i][Drawing.pozY] = color;
                i++;
            }
            Drawing.pozX = destX;
            return;
        }

        if (dir == 2) {
            destY = Drawing.pozY + length;
            if(destY>rozmiar-1){
                destY = rozmiar-1;
            }
            while (j != destY) {
                Drawing.plotno[Drawing.pozX][j] = color;
                j++;
            }
            Drawing.pozY = destY;
            return;
        }

        if (dir == -1) {
            destX = Drawing.pozX - length;
            if(destX<0){
                destX = 0;
            }
            while (i != destX) {
                Drawing.plotno[i][Drawing.pozY] = color;
                i--;
            }
            Drawing.pozX = destX;
            return;
        }

        if (dir == -2) {
            destY = Drawing.pozY - length;
            if(destY<0){
                destY = 0;
            }
            while (j != destY) {
                Drawing.plotno[Drawing.pozX][j] = color;
                j--;
            }
            Drawing.pozY = destY;
            return;
        }
        else{
            return;
        }


        }


    public int[][] getPainting() {
        wypisz();

        return Drawing.plotno;



    }

    public void wypisz() {
        for (int row = 0; row < Drawing.plotno.length; row++) {
            for (int col = 0; col < Drawing.plotno.length; col++) {
                System.out.printf("%4d", Drawing.plotno[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public void clear() {
        for(int k = 0;k<Drawing.rozmiar;k++){
            for(int l = 0; l<Drawing.rozmiar;l++){
                Drawing.plotno[k][l] = 0;
            }
        }

    }
}
