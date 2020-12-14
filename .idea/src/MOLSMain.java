import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MOLSMain {
    public int[][] instArray( int[] perm, ArrayList<ArrayList<Integer>> col ) {
        // Make our array
        int[][] list = new int[12][12];
        // For each column in the array, add the permuted column from the column arrays
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 12; j++) {
                list[i][j] = col.get(perm[j]).get(i);
            }
        }
        return list;
    }

    public boolean determinePairs(int[][] Li, int[][] Lj) {
        // Create a set to commit pairs to
        Set<Pair> set = new HashSet<Pair>();
        // For each pair, if the set contains our pair already, then they are not mutually orthogonal
        // Otherwise, add the pair to the set
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
        // Initialize our matrices
        int[][] L1 = new int[12][12];
        int[][] L2 = new int[12][12];
        int[][] L3 = new int[12][12];
        int[][] L4 = new int[12][12];
        int[][] L5 = new int[12][12];
        // Initialize the permutations for each L_i
        int[] permL1 = {0,1,2,3,4,5,6,7,8,9,10,11};
        int[] permL2 = {0,6,8,2,7,1,9,11,4,10,5,3};
        int[] permL3 = {0,3,6,1,9,11,2,8,5,4,7,10};
        int[] permL4 = {0,8,1,11,5,9,3,10,2,7,6,4};
        int[] permL5 = {0,4,11,10,2,7,8,6,9,1,3,5};
        // Columns of the input array
        ArrayList<ArrayList<Integer>> col = new ArrayList<ArrayList<Integer>>();
        // String array for input
        String[] s;
        // Add 12 new column arrays
        for(int i = 0; i < 12; i++) {
            col.add(new ArrayList<Integer>());
        }
        try {
            // Open file, hardcoded to my machine
            Scanner sc = new Scanner(new File("F:\\MA4209\\HW6\\MOLS\\.idea\\src\\Input.txt"));
            // While the file has more input, enter the input into our column arrays
            do {
                s = sc.nextLine().split(" ");
                for( int i = 0; i < s.length; i++ ) {
                    col.get(i).add(Integer.parseInt(s[i]));
                }
            } while( sc.hasNextLine() );
            // Instantiate our L_i
            L1 = mols.instArray(permL1, col);
            L2 = mols.instArray(permL2, col);
            L3 = mols.instArray(permL3, col);
            L4 = mols.instArray(permL4, col);
            L5 = mols.instArray(permL5, col);
            // Output our L_i to ensure they were permuted correctly
            System.out.println(Arrays.toString(L1[0]));
            System.out.println(Arrays.toString(L2[0]));
            System.out.println(Arrays.toString(L3[0]));
            System.out.println(Arrays.toString(L4[0]));
            System.out.println(Arrays.toString(L5[0]) + "\n");
            // If any of the determinePairs return false, the set is not mutually orthogonal
            if( mols.determinePairs(L1, L2) &&
                    mols.determinePairs(L1, L3) &&
                    mols.determinePairs(L1, L4) &&
                    mols.determinePairs(L1, L5) &&
                    mols.determinePairs(L2, L3) &&
                    mols.determinePairs(L2, L4) &&
                    mols.determinePairs(L2, L5) &&
                    mols.determinePairs(L3, L4) &&
                    mols.determinePairs(L3, L5) &&
                    mols.determinePairs(L4, L5)
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
