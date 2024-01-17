public class Stopwatch {
    private final long start = System.nanoTime();
    public double elapsedTime()
    {
        long now = System.nanoTime();
        return (now - start)/1000000 ;
    }

    public double elapsedTimeSearch()
    {
        long now = System.nanoTime();
        return (now - start)/1000 ;
    }
}
