package org.example;

public class UpitStruct {
    private static final int mxn = (1 << 18);
    private Node[] data;
    int n;

    public UpitStruct(int n) {
        data = new Node[2 * mxn];
        this.n = n;
        build(1, 0, mxn);
    }

    private Node merge(Node a, Node b) {
        Node result = new Node();

        result.start = a.start;
        result.end = b.end;
        result.len = a.len + b.len;
        result.left = a.left;

        if (a.len == a.left && a.end != b.start) {
            result.left += b.left;
        }

        result.right = b.right;
        if (b.len == b.right && a.end != b.start) {
            result.right += a.right;
        }

        result.mx = Math.max(Math.max(a.mx, b.mx), Math.max(result.left, result.right));
        if (a.end != b.start) {
            result.mx = Math.max(result.mx, a.right + b.left);
        }

        return result;
    }

    private void build(int i, int lo, int hi) {
        if (hi - lo == 1) {
            Node curr = new Node();
            if (lo >= n) {
                curr.mx = 0;
                curr.left = 0;
                curr.right = 0;
            } else {
                curr.mx = 1;
                curr.left = 1;
                curr.right = 1;
            }
            curr.start = 'L';
            curr.end = 'L';
            curr.len = 1;
            data[i] = curr;
            return;
        }

        build(2 * i, lo, (lo + hi) / 2);
        build(2 * i + 1, (lo + hi) / 2, hi);
        data[i] = merge(data[2 * i], data[2 * i + 1]);
    }

    public void update(int i) {
        i += mxn;
        if (data[i].start == 'L') {
            data[i].start = 'R';
        } else {
            data[i].start = 'L';
        }
        data[i].end = data[i].start;

        for (i /= 2; i > 0; i /= 2) {
            data[i] = merge(data[2 * i], data[2 * i + 1]);
        }
    }

    public int length() {
        return data[1].mx;
    }

    public static class Node {
        int mx;
        int left, right;
        char start, end;
        int len;
    }
}
