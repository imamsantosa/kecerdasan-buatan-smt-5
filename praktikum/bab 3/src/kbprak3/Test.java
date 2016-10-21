package kbprak3;

public class Test
{
    public static void main(String[] args)
    {
        Pair startNode = new Pair(2, 1);

        AStarSearch greedy = new AStarSearch(startNode);
        greedy.astar();
    }
}