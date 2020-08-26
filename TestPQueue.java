package Exercise1;

public class TestPQueue {

    //Testing with Main
    public static void main(String[] args) {
        //New PriorityQueue Initialisation
        PQueue newpq = new PQueue();

        //Testing Queue isEmpty method
        System.out.println("Is the New Queue Empty: " + newpq.isEmpty());
        //Should print <> as the queue is empty
        System.out.println("Queue Contents: " + newpq.toString());
        System.out.println();

        //Adding to pq test
        try {
            newpq.addtopq("hello", 5);
        } catch (QueueException e) {
            e.printStackTrace();
            System.out.println(e.toString());
            throw new QueueException("Error");
        }

        //Testing addtopq method
        try {
            newpq.addtopq("how are you", 2);
            newpq.addtopq("All Very Good", 1);
            newpq.addtopq("Awesome", 4);
            newpq.addtopq("day today", 20);
            newpq.addtopq("one ok", 3);
            System.out.println();

        } catch (QueueException el) {
            el.printStackTrace();
            System.out.println(el.toString());
            throw new QueueException("Error While Adding");
        }

        //Testing Queue Contents
        try {
            System.out.println("Queue Contents: " + newpq.toString());

        } catch (QueueException ex) {
            ex.printStackTrace();
            throw new QueueException("Unable to provide Queue contents, Queue may be empty");
        }

        //Testing frontpri method
        try {
            System.out.println("Calling frontpri: " + newpq.frontpri() + " returned");
        } catch (QueueException el) {
            el.printStackTrace();
        }

        //Testing front method
        try {
            System.out.println("Calling front: " + '"' + newpq.front() + '"' + " returned");
        } catch (QueueException ex) {
            ex.printStackTrace();
            System.out.println("Queue is empty or front does not exist");
        }

        //Testing Queue Contents
        try {
            System.out.println("Queue Contents: " + newpq.toString());
            System.out.println();

        } catch (QueueException ex) {
            ex.printStackTrace();
            throw new QueueException("Unable to provide Queue contents, Queue may be empty");
        }

        //Testing removefront method
        try {
            System.out.println("Calling removefront: " + (newpq.removefront()));
        } catch (QueueException el) {
            el.printStackTrace();
            System.out.println("Error unable to remove front, front is null or Queue is empty");
        }

        //Testing Queue Contents
        try {
            System.out.println("Queue Contents: " + newpq.toString());

        } catch (QueueException ex) {
            ex.printStackTrace();
            throw new QueueException("Unable to provide Queue contents, Queue may be empty");
        }
        System.out.println();

        //Testing out of range priority exception (Exception Must Be Thrown if Out of Range Priority!)
        try {
            System.out.println("Adding out of range priority " + "'prioritytest' with priority '21'");
            newpq.addtopq("prioritytest", 21);
        } catch (QueueException el) {
            System.out.println(new QueueException("out of range Priority to queue, priority must be between '1' and '20'"));
            System.out.println();

        } finally {

            //new empty priority queue initialised
            PQueue emptyPQueue = new PQueue();
            System.out.println("new empty queue created, testing 'removefront', 'front', and 'frontpri'");

            //Testing removefront method on empty queue
            try {
                emptyPQueue.removefront();
            } catch (QueueException exe) {
                System.out.println(new QueueException("'removefront' to empty queue"));

            } finally {

                //Testing front method on empty queue
                try {
                    emptyPQueue.front();
                } catch (QueueException ele) {
                    System.out.println(new QueueException("'front' to empty queue"));

                } finally {

                    //Testing frontpri method on empty queue
                    try {
                        emptyPQueue.frontpri();
                    } catch (QueueException exc) {
                        System.out.println(new QueueException("'frontpri' to empty queue"));
                    }
                }
            }
        }
    }
}
