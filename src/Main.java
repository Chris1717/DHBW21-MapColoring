package coloring;

class Main { 
    
 /*   0----1----2
      |  / |
      | /  |
      3 ---4----5 */

    static int[][] graph = {
        { 0, 1, 0, 1, 0, 0},
        { 1, 0, 1, 1, 1, 0},
        { 0, 1, 0, 0, 0, 0},
        { 1, 1, 0, 0, 1, 0},
        { 0, 1, 0, 1, 0, 1},
        { 0, 0, 0, 0, 1, 0}
    };
    static int[] colors;

    public static void main(String[] args) {

       

        int coloring = coloring(graph, 3);
        if(coloring == 0) {
            System.out.println("There is no cololring solution.");
        } else {
            System.out.println("You need " + coloring + " colors to color your map.");
            for (int i = 0; i < colors.length; i++) {
                System.out.println("Node " + i + " gets color " + colors[i] + ".");
            }
        }
    }

    public static int coloring(int[][] graph, int numberOfColors) {
        //init colors array with -1 (no color)
        colors = new int[graph.length]; 
        for (int i = 0; i < graph.length; i++) 
            colors[i] = -1; 

        if (!graphColoring(0, graph, colors, numberOfColors)) {
            return 0;
        }
        int numColors = 0;
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] > numColors) {
                numColors = colors[i];
            }
        }
        return numColors+1;


        
    }

    private static boolean graphColoring(int vertex, int[][] graph, int[] colors, int numberOfColors) {
        if (colors[colors.length-1] > -1) {
            //all vertices have been colored
            return true;
        }

        for(int c = 0; c < numberOfColors; c++) {
            if(checkColorOk(vertex, graph, colors, c)) {
                //System.out.println("vertex " + vertex + ": " + c);
                colors[vertex] = c;
                
                if (graphColoring(vertex+1, graph, colors, numberOfColors)) {
                    return true; 
                } else {
                    colors[vertex] = -1;
                }
            }
        }
        return false;
    }

    private static boolean checkColorOk(int vertex, int[][] graph, int[] colors, int color) {
        for (int i = 0; i < graph.length; i++) {
            if (graph[vertex][i] == 1 && color == colors[i]) {
                return false; 
            }
        }
        return true; 
    }
}