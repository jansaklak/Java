import java.util.*;

public class Lines implements LinesInterface {
    private Map<Point, Set<Point>> Graf;
    private Map<Point, Set<Segment>> pointToSegments;

    public Lines() {
        Graf = new HashMap<>();
        pointToSegments = new HashMap<>();
    }

    public List<Point> sasiedzi(Point pkt) {
        List<Point> listaSasiadow = new LinkedList<>();
        for (Segment odcinki : pointToSegments.get(pkt)) {
            listaSasiadow.add(odcinki.getEndpoint2());
        }
        return listaSasiadow;
    }


    @Override
    public void addPoints(Set<Point> points) {
        for (Point punkt : points) {
            if (!Graf.containsKey(punkt)) {
                Graf.put(punkt, new HashSet<>());
                pointToSegments.put(punkt, new HashSet<>());
            }
        }
    }

    @Override
    public void addSegments(Set<Segment> segments) {
        for (Segment segment : segments) {
            Point p1 = segment.getEndpoint1();
            Point p2 = segment.getEndpoint2();

            if (Graf.containsKey(p1) && Graf.containsKey(p2)) {
                Graf.get(p1).add(p2);
                Graf.get(p2).add(p1);
                pointToSegments.get(p1).add(segment);
                pointToSegments.get(p2).add(segment);
            }
        }
    }
    public List<Point> BFS(Point start, Point end) {
            Queue<Point> queue = new LinkedList<>();
            List<Point> visited = new LinkedList<>();
            visited.add(start);
            queue.add(start);

            while (!queue.isEmpty()) {
                Point current = queue.poll();
                if (current == end) {
                    return visited;
                }
                for (Point neighbor : sasiedzi(current)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
            return new LinkedList<>();
    }

    public List<Segment> pointToPath(List<Point> bfsLista){
        List<Segment> droga = new ArrayList<>();
        for(int i = 0;i<bfsLista.size();i++){
            droga.add(szukajSegmentu(bfsLista.get(i),bfsLista.get(i+1)));
        }
        return droga;
    }

    public Segment szukajSegmentu(Point p1, Point p2){
        Set<Segment> segmenty = pointToSegments.get(p1);
        for(Segment seg : segmenty){
            if(seg.getEndpoint2() == p2) return seg;
        }
    }

    @Override
    public List<Segment> findConnection(Point start, Point end) {
        return pointToPath(BFS(start, end));
    }

    @Override
    public Map<Point, Set<Segment>> getMapEndpointToSegments() {
        return this.pointToSegments;
    }

    @Override
    public Map<Point, Map<Integer, Set<Point>>> getReachableEndpoints() {
        Map<Point, Map<Integer, Set<Point>>> reachable = new HashMap<>();

        for (var point : this.graph.keySet()) {
            reachable.put(point, new HashMap<>());
            for (int depth_target = 1; depth_target <= 4; ++depth_target) {
                reachable.get(point).put(depth_target, this.findPointsOnDepthN(point, depth_target));
            }
        }

        return reachable;
    }

    private List<Segment> pathToListOfSegments(final List<Point> path) {
        List<Segment> segments = new ArrayList<>();
        for (int i = 0; i < path.size() - 1; ++i) {
            var point1 = path.get(i);
            var point2 = path.get(i + 1);
            var segment = this.pointToSegments.get(point1).stream().filter(s -> s.getEndpoint1() == point2 || s.getEndpoint2() == point2).findFirst().orElse(null);
            if (segment != null) {
                segments.add(segment);
            }
        }
        return segments;
    }

}