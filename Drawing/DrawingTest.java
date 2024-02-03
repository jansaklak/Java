import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

class TestSegment implements Segment {
    private int direction;
    private int length;
    private int color;

    public TestSegment(int direction, int length, int color) {
        this.direction = direction;
        this.length = length;
        this.color = color;
    }

    @Override
    public int getDirection() {
        return this.direction;
    }

    @Override
    public int getLength() {
        return this.length;
    }

    @Override
    public int getColor() {
        return this.color;
    }
}

class TestGeometry implements Geometry {
    private int size;
    private int firstCoordinate;
    private int secondCoordinate;

    public TestGeometry(int size, int firstCoordinate, int secondCoordinate) {
        this.size = size;
        this.firstCoordinate = firstCoordinate;
        this.secondCoordinate = secondCoordinate;
    }


    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int getInitialFirstCoordinate() {
        return this.firstCoordinate;
    }

    @Override
    public int getInitialSecondCoordinate() {
        return this.secondCoordinate;
    }
}


public class DrawingTest {
    private Drawing drawing;


    @Before
    public void setUp() {
        this.drawing = new Drawing();
    }

    @Test
    public void initializesFields() {
        assertNull(this.drawing.getPainting());
    }

    @Test
    public void initializesCanvasWithDefaultColor() {
        int size = 5;
        Geometry geometry = new TestGeometry(size, 3, 3);
        this.drawing.setCanvasGeometry(geometry);

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                assertEquals(0, this.drawing.getPainting()[i][j]);
            }
        }
    }

    @Test
    public void ifNoGeometrySetDrawDoesNotModifyTheCanvas() {
        Segment segment = new TestSegment(1, 1, 1);
        this.drawing.draw(segment);
        assertNull(this.drawing.getPainting());
    }

    @Test
    public void ifGeometrySizeIsZeroDrawDoesNotModifyTheCanvas() {
        Geometry geometry = new TestGeometry(0, 0, 0);
        Segment segment = new TestSegment(1, 1, 1);
        this.drawing.setCanvasGeometry(geometry);
        this.drawing.draw(segment);

        assertNull(this.drawing.getPainting());
    }

    @Test
    public void initializesGeometry() {
        Geometry geometry = new TestGeometry(13, 3, 6);
        this.drawing.setCanvasGeometry(geometry);
        assertNotNull(this.drawing.getPainting());
    }

    @Test
    public void drawsSingleLine() {
        int size = 13;
        Geometry geometry = new TestGeometry(size, 3, 6);
        Segment segment = new TestSegment(1, 3, 1);
        this.drawing.setCanvasGeometry(geometry);
        this.drawing.draw(segment);

        int[][] expectedCanvas = new int[size][size];
        expectedCanvas[3][6] = 1;
        expectedCanvas[4][6] = 1;
        expectedCanvas[5][6] = 1;

        assertArrayEquals(expectedCanvas, this.drawing.getPainting());
    }

    @Test
    public void laterLineOverridesFormerInSameDirection() {
        int size = 13;
        Geometry geometry = new TestGeometry(size, 3, 6);
        this.drawing.setCanvasGeometry(geometry);

        this.drawing.draw(new TestSegment(1, 3, 1));

        int[][] expectedCanvas = new int[size][size];
        expectedCanvas[3][6] = 1;
        expectedCanvas[4][6] = 1;
        expectedCanvas[5][6] = 1;

        assertArrayEquals(expectedCanvas, this.drawing.getPainting());

        this.drawing.draw(new TestSegment(1, 3, 2));
        expectedCanvas[5][6] = 2;
        expectedCanvas[6][6] = 2;
        expectedCanvas[7][6] = 2;

        assertArrayEquals(expectedCanvas, this.drawing.getPainting());
    }

    @Test
    public void laterLineOverridesFormerInCounterDirection() {
        int size = 13;
        Geometry geometry = new TestGeometry(size, 3, 6);
        this.drawing.setCanvasGeometry(geometry);

        this.drawing.draw(new TestSegment(1, 3, 1));

        int[][] expectedCanvas = new int[size][size];
        expectedCanvas[3][6] = 1;
        expectedCanvas[4][6] = 1;
        expectedCanvas[5][6] = 1;

        assertArrayEquals(expectedCanvas, this.drawing.getPainting());

        this.drawing.draw(new TestSegment(-1, 3, 2));
        expectedCanvas[3][6] = 2;
        expectedCanvas[4][6] = 2;
        expectedCanvas[5][6] = 2;

        assertArrayEquals(expectedCanvas, this.drawing.getPainting());
    }

    @Test
    public void paintsAnImage() {
        int size = 13;
        Geometry geometry = new TestGeometry(size, 3, 6);
        this.drawing.setCanvasGeometry(geometry);

        this.drawing.draw(new TestSegment(1, 3, 1));
        this.drawing.draw(new TestSegment(1, 3, 2));
        this.drawing.draw(new TestSegment(2, 4, 3));
        this.drawing.draw(new TestSegment(-1, 5, 4));
        this.drawing.draw(new TestSegment(-2, 2, 5));

        int[][] expectedCanvas = new int[size][size];
        expectedCanvas[3][6] = 1;
        expectedCanvas[4][6] = 1;
        expectedCanvas[5][6] = 2;
        expectedCanvas[6][6] = 2;
        expectedCanvas[7][6] = 3;
        expectedCanvas[7][7] = 3;
        expectedCanvas[7][8] = 3;
        expectedCanvas[7][9] = 4;
        expectedCanvas[6][9] = 4;
        expectedCanvas[5][9] = 4;
        expectedCanvas[4][9] = 4;
        expectedCanvas[3][9] = 5;
        expectedCanvas[3][8] = 5;

        assertArrayEquals(expectedCanvas, this.drawing.getPainting());
    }

    @Test
    public void limitsDrawingToCanvasBounds() {
        int size = 13;
        Geometry geometry = new TestGeometry(size, 3, 6);
        Segment segment = new TestSegment(1, 100, 1);
        this.drawing.setCanvasGeometry(geometry);
        this.drawing.draw(segment);

        int[][] expectedCanvas = new int[size][size];
        expectedCanvas[3][6] = 1;
        expectedCanvas[4][6] = 1;
        expectedCanvas[5][6] = 1;
        expectedCanvas[6][6] = 1;
        expectedCanvas[7][6] = 1;
        expectedCanvas[8][6] = 1;
        expectedCanvas[9][6] = 1;
        expectedCanvas[10][6] = 1;
        expectedCanvas[11][6] = 1;
        expectedCanvas[12][6] = 1;

        assertArrayEquals(expectedCanvas, this.drawing.getPainting());
    }

    @Test
    public void clearsPaintedImage() {
        int size = 13;
        Geometry geometry = new TestGeometry(size, 3, 6);
        this.drawing.setCanvasGeometry(geometry);

        this.drawing.draw(new TestSegment(1, 3, 1));
        this.drawing.draw(new TestSegment(1, 3, 2));
        this.drawing.draw(new TestSegment(2, 4, 3));
        this.drawing.draw(new TestSegment(-1, 5, 4));
        this.drawing.draw(new TestSegment(-2, 2, 5));

        this.drawing.clear();

        int[][] expectedCanvas = new int[size][size];

        assertArrayEquals(expectedCanvas, this.drawing.getPainting());
    }

    @Test
    public void overridesOneAvailablePixelWithMinimumCanvasSize() {
        int size = 1;
        Geometry geometry = new TestGeometry(size, 0, 0);
        this.drawing.setCanvasGeometry(geometry);

        this.drawing.draw(new TestSegment(-2, 999999, 1));
        this.drawing.draw(new TestSegment(-2, 99999, 2));
        this.drawing.draw(new TestSegment(2, 9999, 3));
        this.drawing.draw(new TestSegment(2, 99, 5));
        this.drawing.draw(new TestSegment(1, 9, 6));
        this.drawing.draw(new TestSegment(1, 3, 7));
        this.drawing.draw(new TestSegment(-1, 2, 8));
        this.drawing.draw(new TestSegment(-1, 1, 9));

        int[][] expectedCanvas = new int[size][size];
        expectedCanvas[0][0] = 9;

        assertArrayEquals(expectedCanvas, this.drawing.getPainting());
    }

    @Test
    public void ignoresUnsupportedDirectionValue() {
        int size = 13;
        Geometry geometry = new TestGeometry(size, 3, 6);
        this.drawing.setCanvasGeometry(geometry);

        this.drawing.draw(new TestSegment(1, 3, 1));

        int[][] expectedCanvas = new int[size][size];
        expectedCanvas[3][6] = 1;
        expectedCanvas[4][6] = 1;
        expectedCanvas[5][6] = 1;

        assertArrayEquals(expectedCanvas, this.drawing.getPainting());

        this.drawing.draw(new TestSegment(0, 3, 2));
        assertArrayEquals(expectedCanvas, this.drawing.getPainting());

        this.drawing.draw(new TestSegment(-3, 3, 2));
        assertArrayEquals(expectedCanvas, this.drawing.getPainting());

        this.drawing.draw(new TestSegment(3, 3, 2));
        assertArrayEquals(expectedCanvas, this.drawing.getPainting());

        this.drawing.draw(new TestSegment(10, 3, 2));
        assertArrayEquals(expectedCanvas, this.drawing.getPainting());
    }

    @Test
    public void spiralGoesBrrrrrrrrrrrrrrrrrrrrrrrrrrr() {
        int size = 5;
        Geometry geometry = new TestGeometry(size, 0, 0);
        this.drawing.setCanvasGeometry(geometry);

        this.drawing.draw(new TestSegment(1, 3, 1));

        int[][] expectedCanvas = {
                {1, 5, 4, 4, 4},
                {1, 5, 9, 8, 3},
                {1, 5, 9, 7, 3},
                {1, 6, 6, 7, 3},
                {2, 2, 2, 2, 3},
        };

        this.drawing.draw(new TestSegment(1, 5, 1));
        this.drawing.draw(new TestSegment(2, 5, 2));
        this.drawing.draw(new TestSegment(-1, 5, 3));
        this.drawing.draw(new TestSegment(-2, 4, 4));
        this.drawing.draw(new TestSegment(1, 4, 5));
        this.drawing.draw(new TestSegment(2, 3, 6));
        this.drawing.draw(new TestSegment(-1, 3, 7));
        this.drawing.draw(new TestSegment(-2, 2, 8));
        this.drawing.draw(new TestSegment(1, 2, 9));

        assertArrayEquals(expectedCanvas, this.drawing.getPainting());
    }
}
