import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class KnightTravelGame {

    static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dy = {2, 1, -1, -2, -2, -1, 1, 2};

    static boolean[][] vis;
    static int[][] dis;

    public static boolean isvalid(int x, int y, int n, int m) {
        return x >= 1 && y >= 1 && x <= n && y <= m && !vis[x][y];
    }

    public static boolean isvalid1(int x, int y, int n, int m) {
        return x >= 1 && y >= 1 && x <= n && y <= m;
    }

    static int n, m;
    static int startx, starty, endx, endy;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("\n\n\n\t\t\t\t\t\t WELCOME TO KNIGHT TRAVEL\n\n\n");
        System.out.println("\t\t\t\t For correct answer in efficient steps you will be awarded 20 points\n\n\n\t\t\t\t For each extra step (-2) points and for exceeding the max limit 0 points\n\n");
        System.out.println("\t\t\t\t\t\t Choose your level of game :\n\n\n");
        System.out.println("\n\t\t\t\t\t\t 1: EASY (4 X 4 GRID)\n\n");
        System.out.println("\n\t\t\t\t\t\t 2: MEDIUM (8 X 8 GRID)\n\n");
        System.out.println("\n\t\t\t\t\t\t 3: HARD (12 X 12 GRID)\n\n");
        System.out.println("\n\t\t\t\t\t\t 4: QUIT\n\n");
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        choice = scanner.nextInt();

        switch (choice) {
            case 1: {
                System.out.println("\n\n\n");
                solve(4, 4, scanner);
                break;
            }
            case 2: {
                System.out.println("\n\n\n");
                solve(8, 8, scanner);
                break;
            }
            case 3: {
                System.out.println("\n\n\n");
                solve(12, 12, scanner);
                break;
            }
            case 4: {
                System.out.println("\n\n\n");
                System.out.println("\t\t\t\t\t\tThank you for playing");
                break;
            }
            default: {
                System.out.println("\n\n\n");
                System.out.println("\n \t\t\t\t\t\tWrong choice entered");
                break;
            }
        }
    }

    public static void solve(int arg1, int arg2, Scanner scanner) {
        vis = new boolean[105][105];
        dis = new int[105][105];

        n = arg1;
        m = arg2;

        Random random = new Random();
        startx = random.nextInt(n) + 1;
        starty = random.nextInt(m) + 1;
        endx = random.nextInt(n) + 1;
        endy = random.nextInt(m) + 1;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startx, starty});
        vis[startx][starty] = true;
        dis[startx][starty] = 0;

        int step = 0;
        int maxstep = dis[endx][endy] + 4;
        int[][] t;
        int flag = 0;
        int ans = 1;
        System.out.println("\t\t\t\t\t\t\t\t\t\t\tMAXIMUM MOVES :" + (dis[endx][endy] + 4));
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\tS: START\n\t\t\t\t\t\t\t\t\t\t\tF: FINISH");

        for (int p = 1; p <= n; p++) {
            System.out.print("\t\t\t\t\t\t");
            for (int j = 1; j <= m; j++) {
                if (startx == p && starty == j)
                    System.out.print("[S] ");
                else if (endx == p && endy == j) {
                    System.out.print("[F] ");
                } else if (dis[p][j] == 1)
                    System.out.print("[1] ");
                else
                    System.out.print("[x] ");
            }
            System.out.println();
            System.out.print("\t\t\t\t\t\t");
            for (int x = 1; x <= n; x++)
                System.out.print("----");
            System.out.println();
        }

        int xx, yy;
        int in = 0;
        int sx = startx, sy = starty;
        System.out.println();

        for (step = 1; step <= maxstep; step++) {
            t = new int[n + 1][m + 1];
            int[] xy = new int[]{0, 0};
            System.out.print("\t\t\tEnter X-Coordinates :");
            xy[0] = scanner.nextInt();
            System.out.print("\t\t\tEnter Y-Coordinates :");
            xy[1] = scanner.nextInt();

            for (int i = 0; i < 8; i++) {
                int nx = xy[0] + dx[i];
                int ny = xy[1] + dy[i];
                if (isvalid1(nx, ny, n, m)) {
                    t[nx][ny] = step + 1;
                }
            }

            for (int p = 1; p <= n; p++) {
                System.out.print("\t\t\t\t\t\t");
                for (int j = 1; j <= m; j++) {
                    if (startx == p && starty == j)
                        System.out.print("[S] ");
                    else if (endx == p && endy == j) {
                        System.out.print("[F] ");
                    } else if (t[p][j] != -1)
                        System.out.print("[" + t[p][j] + "] ");
                    else if (p == xy[0] && j == xy[1])
                        System.out.print("[" + step + "] ");
                    else
                        System.out.print("[x] ");
                }
                System.out.println();
                System.out.print("\t\t\t\t\t\t");
                for (int x = 1; x <= n; x++)
                    System.out.print("----");
                System.out.println();
            }
            System.out.println();

            int flag1 = 0;
            xx = xy[0];
            yy = xy[1];

            for (int i = 0; i < 8; i++) {
                if (xx == sx + dx[i] && yy == sy + dy[i]) {
                    flag1 = 1;
                    break;
                }
            }

            if (flag1 == 0) {
                step--;
                System.out.println("\t\t\t\tInvalid move");
                in = 1;
                break;
            } else {
                if (xx == endx && yy == endy) {
                    System.out.println("\t\t\t\tFound in steps : " + step);
                    ans = 0;
                    break;
                }
                sx = xx;
                sy = yy;
            }
        }

        if (ans == 0 && in == 0) {
            if (step == dis[endx][endy]) {
                System.out.println("\t\t\t\tCONGRATULATIONS !!! YOU HAVE WON \n \t\t\t\tCompleted the game in " + step + " steps");
                System.out.println("\t\t\t\tYour score: 20");
            } else {
                System.out.println("\t\t\t\tHARD LUCK BUT THERE IS A MORE EFFICIENT WAY TO REACH HERE WITH STEPS : " + dis[endx][endy]);
                System.out.println("\t\t\t\tYour score: " + (20 - (step - dis[endx][endy]) * 2));
            }
        } else if (in == 0) {
            System.out.println("\t\t\t\tYOU LOSE !!! YOU EXCEEDED THE MAXIMUM MOVES");
            System.out.println("\t\t\t\tYour score: 0");
        }
    }
}
