package Exercise1;

import java.util.Arrays;
import java.util.Comparator;

public class PQueue {

    private class PQCell implements Comparable<PQCell> {
        //Data == String
        private String data;
        //priority int between 1 and 20
        private int priority;

        //PQCell
        private PQCell(String data, int priority) {
            this.data = data;
            this.priority = priority;
        }

        //Comparator for priorities of each element
        public int compareTo(PQCell o) {
            return Integer.compare(this.priority, o.priority);
        }
    }

    //PQCell Initialisation as array
    private PQCell[] array;

    //frontpos and backpos pointers
    private int frontPos, backPos;

    //PQueue Initialisation
    public PQueue() {
        array = new PQCell[10];
        frontPos = 0;
        backPos = -1;

    }

    //isEmpty boolean
    public boolean isEmpty() {
        return frontPos == (backPos + 1) % array.length;
    }

    //front method returns the data of the element at the front of the queue
    public String front() throws QueueException {
        if (frontPos == (backPos + 1) % array.length) {
            throw new QueueException("'front'");
        }
        return array[frontPos].data;
    }

    //frontpri returns the priority of the front element
    public int frontpri() throws QueueException {
        if (frontPos == (backPos + 1) % array.length) {
            throw new QueueException("'frontpri'");
        }
        return array[frontPos].priority;
    }

    //removefront removes the element at the front of the array
    public PQueue removefront() throws QueueException {
        if (frontPos == (backPos + 1) % array.length) {
            throw new QueueException("'removefront'");
        }

        frontPos++;
        return this;
    }

    //addtopq method allows adddition to the PriorityQueue using a string as data and a int as priority
    public PQCell addtopq(String data, int p) throws QueueException {
        if (p > 20 || p < 1) {
            throw new QueueException("Priority must be between 1 and 20");
        }

        System.out.println("Adding " + '"' + data + '"' + " with priority " + p);

        if (this.isEmpty()) {
            backPos = (backPos + 1) % array.length;
            array[backPos] = new PQCell(data, p);
        } else {
            backPos = (backPos + 1) % array.length;
            array[backPos] = new PQCell(data, p);
            Arrays.sort(array, Comparator.nullsLast(PQCell::compareTo));
        }

        if ((backPos + 1) % array.length == frontPos) {
            PQCell[] newarr = new PQCell[array.length * 2];
            int i;
            for (i = 0; i < array.length; i++)
                newarr[i] = array[(frontPos + i) % array.length];
            array = newarr;
            frontPos = 0;
            backPos = i - 1;
        }

        return array[frontPos];
    }

    //toString method returns built string using StringBuilder as specified outcome
    public String toString() {
        if (frontPos == (backPos + 1) % array.length) {
            return "<>";
        }

        StringBuilder strb = new StringBuilder();
        strb.append('<');

        //temporary position assignment
        int pos = frontPos;

        while (pos != backPos) {
            strb.append("\"").append(array[pos].data).append("\"");
            strb.append(":").append(array[pos].priority).append(",");
            pos = (pos + 1) % array.length;
        }

        strb.append("\"").append(array[backPos].data).append("\":")
                .append(array[backPos].priority);

        strb.append('>');

        String built = strb.toString();

        return (built);
    }
}