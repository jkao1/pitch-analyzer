public class RunningMedian {

    public MyHeap left, right;

    public RunningMedian() {
        left = new MyHeap(true);
        right = new MyHeap(false);
    }

    public void add(double v)
    {
        if (left.size() + right.size() == 0) {
            left.add(v);
        } else if (v * 1.0 > getMedian()) {
            right.add(v);
        } else {
            left.add(v);
        }

        if (right.size() >= left.size() + 2) {
            left.add(right.remove());
        } else if (left.size() >= right.size() + 2) {
            right.add(left.remove());
        }
    }

    public double getMedian()
    {
        if (left.size() == right.size()) {
            return (left.peek() + right.peek()) / 2.0;
        } else if (left.size() > right.size()) {
            return left.peek();
        }
        return right.peek();
    }

    public String toString()
    {
        return left + "\n" + right;
    }

}
