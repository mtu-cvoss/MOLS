import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MOLSMain {
    public int[][] instArray( int[] perm, ArrayList<ArrayList<Integer>> col ) {
        int[][] list = new int[12][12];
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 12; j++) {
                list[i][perm[j]] = col.get(j).get(i);
            }
        }
        return list;
    }

    public boolean determinePairs(Set<Pair> set, int[][] Li, int[][] Lj) {
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 12; j++) {
                if(set.contains(new Pair(Li[i][j],Lj[i][j]))) {
                    return false;
                } else {
                    set.add(new Pair(Li[i][j],Lj[i][j]));
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MOLSMain mols = new MOLSMain();
        int[][] L1 = new int[12][12];
        int[][] L2 = new int[12][12];
        int[][] L3 = new int[12][12];
        int[][] L4 = new int[12][12];
        int[][] L5 = new int[12][12];
        int[] permL1 = {0,1,2,3,4,5,6,7,8,9,10,11};
        int[] permL2 = {0,6,8,2,7,1,9,11,4,10,5,3};
        int[] permL3 = {0,3,6,1,9,11,2,8,5,4,7,10};
        int[] permL4 = {0,8,1,11,5,9,3,10,2,7,6,4};
        int[] permL5 = {0,4,11,10,2,7,8,6,9,1,3,5};
        ArrayList<ArrayList<Integer>> col = new ArrayList<ArrayList<Integer>>();
        String[] s;
        Set<Pair> set1 = new HashSet<Pair>();
        Set<Pair> set2 = new HashSet<Pair>();
        Set<Pair> set3 = new HashSet<Pair>();
        Set<Pair> set4 = new HashSet<Pair>();
        Set<Pair> set5 = new HashSet<Pair>();
        Set<Pair> set6 = new HashSet<Pair>();
        Set<Pair> set7 = new HashSet<Pair>();
        Set<Pair> set8 = new HashSet<Pair>();
        Set<Pair> set9 = new HashSet<Pair>();
        Set<Pair> set10 = new HashSet<Pair>();
        for(int i = 0; i < 12; i++) {
            col.add(new ArrayList<Integer>());
        }
        try {
            Scanner sc = new Scanner(new File("F:\\MA4209\\HW6\\MOLS\\.idea\\src\\Input.txt"));
            do {
                s = sc.nextLine().split(" ");
                for( int i = 0; i < s.length; i++ ) {
                    col.get(i).add(Integer.parseInt(s[i]));
                }
            } while( sc.hasNextLine() );
            L1 = mols.instArray(permL1, col);
            System.out.println(L1);
            L2 = mols.instArray(permL2, col);
            L3 = mols.instArray(permL3, col);
            L4 = mols.instArray(permL4, col);
            L5 = mols.instArray(permL5, col);
            if( mols.determinePairs(set1, L1, L2) &&
                    mols.determinePairs(set2, L1, L3) &&
                    mols.determinePairs(set3, L1, L4) &&
                    mols.determinePairs(set4, L1, L5) &&
                    mols.determinePairs(set5, L2, L3) &&
                    mols.determinePairs(set6, L2, L4) &&
                    mols.determinePairs(set7, L2, L5) &&
                    mols.determinePairs(set8, L3, L4) &&
                    mols.determinePairs(set9, L3, L5) &&
                    mols.determinePairs(set10, L4, L5)
            ) {
                System.out.println("They are all mutually orthogonal");
            } else {
                System.out.println("They are not mutually orthogonal");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
