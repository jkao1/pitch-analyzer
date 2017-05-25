public class MyHeap {

    private int O_CONST;

    private Double[] ary;
    private int size;

    public MyHeap() {
        ary = new Double[10];
        size = 1;
        O_CONST = 1;
    }

    public MyHeap(boolean isMax) {
        this();
        if (isMax) {
            O_CONST = 1;
        } else {
            O_CONST = -1;
        }
    }

    public void add(Double i)
    {
        if (size == ary.length) {
            resize();
        }
        ary[size] = i;
        pushUp();
        size++;
    }

    private void pushUp()
    {
        int tracker = size;
        while (tracker > 1 && ary[tracker].compareTo( ary[tracker / 2] ) * O_CONST > 0) {
            swap(tracker, tracker / 2);
            tracker = tracker / 2;
        }
    }

    private void resize()
    {
        Double[] biggerAry = new Double[ary.length * 2];
        for (int i = 0; i < ary.length; i++) {
            biggerAry[i] = ary[i];
        }
        ary = biggerAry;
    }

    public Double remove()
    {
        Double output = ary[1];
        ary[1] = ary[size - 1];
        pushDown();
        size--;
        return output;
    }

    private void pushDown()
    {
        int tracker = 1;
        while (2 * tracker + 1 < size) {
            boolean goLeft = ary[2 * tracker].compareTo(ary[2 * tracker + 1]) * O_CONST > 0;
            if (goLeft) {
                if (ary[tracker].compareTo( ary[2 * tracker] ) * O_CONST < 0) {
                    swap(tracker, 2 * tracker);
                    tracker = 2 * tracker;
                } else if (ary[tracker].compareTo( ary[2 * tracker + 1] ) * O_CONST < 0) {
                    swap(tracker, 2 * tracker + 1);
                    tracker = 2 * tracker + 1;
                } else {
                    break;
                }
            } else {
                if (ary[tracker].compareTo( ary[2 * tracker + 1] ) * O_CONST < 0) {
                    swap(tracker, 2 * tracker + 1);
                    tracker = 2 * tracker + 1;
                } else if (ary[tracker].compareTo( ary[2 * tracker] ) * O_CONST < 0) {
                    swap(tracker, 2 * tracker);
                    tracker = 2 * tracker;
                }  else {
                    break;
                }
            }
        }
    }

    private void swap(int a, int b)
    {
        Double temp = ary[a];
        ary[a] = ary[b];
        ary[b] = temp;
    }

    public Double peek()
    {
        return ary[1];
    }

    public int size()
    {
        return size - 1;
    }

    public String toString()
    {
        String output = "[";
        for (int i = 0; i < size; i++) {
            output += ary[i] + ", ";
        }
        if (size > 1) {
            output = output.substring(0, output.length() - 2);
        }
        return output + "] " + O_CONST;
    }

}
