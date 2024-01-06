import java.util.Arrays;

public class UnionFind {
    /**
     * DO NOT DELETE OR MODIFY THIS, OTHERWISE THE TESTS WILL NOT PASS.
     * You can assume that we are only working with non-negative integers as the items
     * in our disjoint sets.
     */
    private int[] data;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        this.data = new int[N];
        Arrays.fill(this.data, -1);
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        return data[v];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        return -1;
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        return false;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        if (v < 0 || v >= data.length) {
            throw new IllegalArgumentException("Index " + v + " is not between 0 and " + (data.length-1));
        }
        if (data[v] < 0 ) {
            return v; // v is root
        } else {
            int root = find(data[v]);
            data[v] = root; // path compression: update v's parent to the set's root
            return root;
        }
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie-break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE

        // Special case: union(2, 2)--union with itself
        if (v1 == v2) {
            return;
        }
        // Identify root
        int root1 = find(v1);
        int root2 = find(v2);

        // Special case: elements already in same set
        if (root1 == root2) {
            return; // do nothing
        }

        // Compare size by look at index of root and execute union
//        if (data[rootV1] >= data[rootV2]) { // if v1 is smaller than v2, but needs >= between negative index
        if (Math.abs(data[root1]) <= Math.abs(data[root2])) {
            data[root1] = root2; // root2 is the new root
            data[root2] = data[root2] + data[root1]; // update the size of the unioned set
        } else {
            data[root2] = root1;
            data[root1] = data[root1] + data[root2]; // update the size of unioned set
        }
    }

    /**
     * DO NOT DELETE OR MODIFY THIS, OTHERWISE THE TESTS WILL NOT PASS.
     */
    public int[] returnData() {
        return data;
    }
}
