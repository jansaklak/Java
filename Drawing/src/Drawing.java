abstract public class Drawing implements SimpleDrawing{
    SimpleDrawing rysuj = new SimpleDrawing() {

        int pozX;
        int pozY;
        int plotno[][];


        int rozmiar;

        public void setCanvasGeometry(Geometry input) {
            rozmiar = input.getSize();
            pozX = input.getInitialFirstCoordinate();
            pozY = input.getInitialSecondCoordinate();
        }


        public void draw(Segment segment) {
            int color = segment.getColor();
            int dir = segment.getDirection();
            int length = segment.getLength();
            int i = pozX;
            int j = pozY;
            if(dir==1){
                while(i<(pozX+length) && i<rozmiar) {
                    plotno[i][pozY] = color;
                    i++;
                }
                pozX = i;
            }
            if(dir==2){
                while(j<(pozY+length) && j<rozmiar) {
                    plotno[pozX][j] = color;
                    j++;
                }
                pozY = j;

            }
            if(dir==-1){
                while(i>(pozX+length) && i>rozmiar) {
                    plotno[i][pozY] = color;
                    i--;
                }
                pozX = i;

            }
            if(dir==-2){
                while(j>(pozX+length) && j>rozmiar) {
                    plotno[pozX][j] = color;
                    j--;
                }
                pozY = j;

            }
        }

        public int[][] getPainting() {
            return plotno;
        }

        public void clear() {
            for(int k = 0;k<rozmiar;k++){
                for(int l = 0; l<rozmiar;l++){
                    plotno[k][l] = 0;
                }
            }

        }
    };
};
