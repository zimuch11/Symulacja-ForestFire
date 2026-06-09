package com.example.symulacja_fire;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private final int width;
    private final int height;
    private final double forestation;
    private int resources;
    private int numForest = 0;
    private final double cityPart = 0.1;

    private Cell[][] cells;
    private Random random = new Random();

    public Board(int width, int height, double forestation,int resources) {
        this.width = width;
        this.height = height;
        this.forestation = forestation;
        this.resources = resources;
        cells = new Cell[width][height];
        createBoard();
    }

    private void createBoard() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x >= width - (width * cityPart)) {
                        cells[x][y] = new CityCell();
                    }
                 else {
                    if (random.nextDouble() < forestation) {
                        cells[x][y] = new ForestCell(resources);
                        numForest++;
                    } else {
                        cells[x][y] = new EmptyCell();
                    }
                }
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public List<Cell> getNeighbours(int x, int y ) {

        List<Cell> neighbours = new ArrayList<>();

        int[][] directions = {
                {-1, -1}, {0, -1}, {1, -1},
                {-1, 0}, {1, 0},
                {-1, 1}, {0, 1}, {1, 1}
        };

        for (int[] d : directions) {
            int nx = x + d[0];
            int ny = y + d[1];

            if (nx >= 0 && nx < width &&
                    ny >= 0 && ny < height) {

                neighbours.add(cells[nx][ny]);
            }
        }

        return neighbours;
    }


    public List<int[]> getClosestfire(int pos_x, int pos_y, int type, int fuel, int startx, int starty){
        String typeh = "";
        if(type == -1){
            typeh = "Firefighter";
        } else{
            typeh = "Helicopter";
        }
        List<int[]> closestFirelist = new ArrayList<>();
        double pyt_final=1000000;
        int x_final=0;
        int y_final=0;
        if(fuel<=0){
            closestFirelist.add(new int[]{startx, starty, (int)pyt_final});
//            System.out.printf("Coming back: Distance: "+"%.2f"+", Fire |x:"+"%d"+", y:"+"%d"+"| <__> %s|x: "+"%d"+", y:"+"%d"+",fuel: %d|\n",pyt_final, startx, starty, typeh, pos_x, pos_y, fuel);
            return closestFirelist;
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                Cell cell = cells[x][y];

                if (cell.isOnFire()) {
                    double dx = pos_x - x;
                    dx = Math.abs(dx);
                    double dy = pos_y - y;
                    dy = Math.abs(dy);
                    double pyt = Math.sqrt((dx*dx)+(dy*dy));
                    if(pyt_final > pyt){
                        pyt_final = pyt;
                        x_final = x;
                        y_final = y;
                    }
                }
            }
        }
        if(pyt_final==1000000){
            x_final = pos_x;
            y_final = pos_y;
//            System.out.println("No fire");
        } else {
//            System.out.printf("Closest fire: Distance: "+"%.2f"+", Fire |x:"+"%d"+", y:"+"%d"+"| <__> %s|x: "+"%d"+", y:"+"%d"+",fuel: %d|\n",pyt_final, x_final, y_final, typeh, pos_x, pos_y, fuel);
        }
        if(pyt_final > 1 && pyt_final < 2){
            pyt_final = 2;
        }
        closestFirelist.add(new int[]{x_final, y_final, (int)pyt_final});
        return closestFirelist;
    }

    public int[] moveFirefigh(List<int[]>closestFirelist, int[] i){
        Random r = new Random();
        int nxt = r.nextInt(2);
        int x_fire = closestFirelist.get(0)[0];
        int y_fire = closestFirelist.get(0)[1];
        if(i[1] > x_fire){
            i[1] -= 1 + nxt;
        } else if (i[1] < x_fire){
            i[1] += 1 + nxt;
        }
        if(i[2] > y_fire){
            i[2] -= 1 + nxt;
        } else if (i[2] < y_fire){
            i[2] += 1 + nxt;
        }
        return i;
    }

    public int extinguishfire(int[] i, List<int[]> pyt){
        int fuelloss = 0;
        String type;
        if(i[4]==-1){
            type = "Firefighter";
        } else {
            type = "Helicopter";
        }
        int x = i[1];
        int y = i[2];
        Cell cell;
        String direction = "around";
//        System.out.println(Arrays.toString(i));
//        System.out.print(pyt.get(0)[0]+", ");
//        System.out.print(pyt.get(0)[1]+", ");
//        System.out.println(pyt.get(0)[2]);
        if(pyt.get(0)[2] <= 1){
            int[][] celllist = new int[0][];
            if(x > pyt.get(0)[0] && type.equals("Firefighter")){
                direction = "left";
            }
            if(x < pyt.get(0)[0] && type.equals("Firefighter")){
                direction = "right";
            }
            if(y > pyt.get(0)[1] && type.equals("Firefighter")){
                direction = "up";
            }
            if(y < pyt.get(0)[1] && type.equals("Firefighter")){
                direction = "down";
            }
            if( type.equals("Helicopter")){
                direction = "heli";
           if(pyt.get(0)[2]==0 && type.equals("Firefighter")){
                    direction = "around";
                }
            }

//            System.out.println(direction);
            switch(direction){
                case "left":
                    celllist = new int[][]{
                            {0, 0},
                            {-1, 0}, {-1, 1}, {-1, -1},
                            {-2, 0}, {-2, 1}, {-2, -1}, {-2, 2}, {-2, -2},
                            {-3, 0}, {-3, 1}, {-3, 2}, {-3, 3}, {-3, -1}, {-3, -2}, {-3, -3},
                            {-4, 0}, {-4, 1}, {-4, 2}, {-4, 3}, {-4, 4}, {-4, -1}, {-4, -2}, {-4, -3}, {-4, -4}
                    };
                    break;
                case "right":
                    celllist = new int[][]{
                            {0, 0},
                            {1, 0}, {1, 1}, {1, -1},
                            {2, 0}, {2, 1}, {2, -1}, {2, 2}, {2, -2},
                            {3, 0}, {3, 1}, {3, 2}, {3, 3}, {3, -1}, {3, -2}, {3, -3},
                            {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}, {4, -1}, {4, -2}, {4, -3}, {4, -4}
                    };
                    break;
                case "down":
                    celllist = new int[][]{
                            {0, 0},
                            {0, 1}, {1, 1}, {-1, 1},
                            {0, 2}, {1, 2}, {2, 2}, {-1, 2}, {-2, 2},
                            {0, 3}, {1, 3}, {2, 3}, {3, 3}, {-1, 3}, {-2, 3}, {-3, 3},
                            {0, 4}, {1, 4}, {2, 4}, {3, 4}, {4, 4}, {-1, 4}, {-2, 4}, {-3, 4}, {-4, 4}
                    };
                    break;
                case "up":
                    celllist = new int[][]{
                            {0, 0},
                            {0, -1}, {1, -1}, {-1, -1},
                            {0, -2}, {1, -2}, {2, -2}, {-1, -2}, {-2, -2},
                            {0, -3}, {1, -3}, {2, -3}, {3, -3}, {-1, -3}, {-2, -3}, {-3, -3},
                            {0, -4}, {1, -4}, {2, -4}, {3, -4}, {4, -4}, {-1, -4}, {-2, -4}, {-3, -4}, {-4, -4}
                    };
                    break;
                case "around":
                    celllist = new int[][]{
                            {0, 0}, {1, 0}, {2, 0}, {-1, 0}, {-2, 0},
                            {0, 1}, {1, 1}, {2, 1}, {-1, 1}, {-2, 1},
                            {0, 2}, {1, 2}, {2, 2}, {-1, 2}, {-2, 2},
                            {0, -1}, {1, -1}, {2, -1}, {-1, -1}, {-2, -1},
                            {0, -2}, {1, -2}, {2, -2}, {-1, -2}, {-2, -2}
                    };
                    break;
                case "heli":
                    int size = 30;
                    celllist = new int[size * size][2];
                    int index = 0;

                    for (int w = -size / 2; w < size / 2; w++) {
                        for (int d = -size / 2; d < size / 2; d++) {
                            celllist[index][0] = d;
                            celllist[index][1] = w;
                            index++;
                        }
                    }
                    break;
            }
            for (int[] cellh : celllist){
                int nx = x + cellh[0];
                int ny = y + cellh[1];
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    if (cells[nx][ny] instanceof ForestCell tree) {
                        if (tree.isOnFire()) {
                            cells[nx][ny] = new EmptyCell();
                        }
                    }
                }
            }
            fuelloss = -1;
        };
//        System.out.println(fuelloss);
        return fuelloss;
    }

    public void fireFighlogic(List<int[]> firefighs) {
        for (int[] i : firefighs){
            int fuelloss=0;
            List <int[]> pyt=getClosestfire(i[1],i[2],i[4],i[3],i[5],i[6]);
            if (i[1] == i[5] && i[2] == i[6]) {
                i[3] = 5;
            }
            fuelloss = extinguishfire(i,pyt);
            int[] nextPosition = moveFirefigh(pyt,i);
            i[1] = nextPosition[1];
            i[2] = nextPosition[2];
            i[3] += fuelloss;
        }
    }

    public void helislogic(List<int[]> helis) {
        for (int[] h : helis){
            int fuelloss=0;
            int offset=6;
            List <int[]> pyt=getClosestfire(h[1],h[2],h[6],h[5],h[3],h[4]);
            ;
            if (h[1] == h[3] && h[2] == h[4]) {
                h[1] -= offset;
                h[5] = 1;
            }
            fuelloss = extinguishfire(h,pyt);
            if(pyt.get(0)[0]>=h[3]-200){
                int[] nextPosition = moveFirefigh(pyt,h);
                h[0] = nextPosition[0];
                h[1] = nextPosition[1];
                h[5]+=fuelloss;
            }
        }
    }

    public boolean cityOnFire() {

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                Cell cell = cells[x][y];

                if (cell instanceof CityCell &&
                        cell.isOnFire()) {

                    return true;
                }
            }
        }

        return false;
    }

    public double getBurningPercentage() {

        int burning = 0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                Cell cell = cells[x][y];

                if (cell instanceof Cell forest &&
                        forest.isOnFire()) {

                    burning++;
                }
            }
        }

        return 100.0 * burning / numForest;
    }
    public double getBurnedPercentage() {

        int burned = 0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                if (cells[x][y] instanceof BurntCell) {
                    burned++;
                }
            }
        }

        return 100.0 * burned / numForest;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setCell(int x, int y, Cell cell) {
        cells[x][y] = cell;
    }

    public int getNumForest(){
        return numForest;
    }

}

