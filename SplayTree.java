

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author nidadinc
 * 20160808047
 * 
 * How to: I scanned input.txt with BufferedReader &
 * For reaching methods that written input.txt file, i have used Java Reflection
 * Reflection can be found as ExecuteGivenCommand methods.
 * Then, every method invoking with reflection and running.
 * 
 */

public class SplayTree {

    public static String scanned;
    public static String outputfile;
    private static Node root;
    static String string = "";


    public static void main(String[] args) {


        try {
            if (args.length != 2) {
                System.out.println("Lütfen dosya yolunu doğru veriniz");
                if (args.length == 0) {
                    System.out.println("Dosya yolu girilmedi");
                } else
                    System.out.println("Birden fazla parametre girildi");
                return;
            }
            String textfile = args[0];
            outputfile = args[1];

            File file = new File(textfile);
            BufferedReader buffread = new BufferedReader(new FileReader(file));
            String readline = "";

            while ((readline = buffread.readLine()) != null) {
                scanned = readline;
                String[] splitarray = readline.toLowerCase().split(" ");
                String parameter = splitarray[1];
                int param = Integer.parseInt(parameter);
                if (splitarray.length == 1) {
                    System.out.println("Dosya içeriği yanlış. Lütfen kontrol edin! ");
                } else {
                    ExecuteGivenCommand(splitarray[0], param);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(SplayTree.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public static void ExecuteGivenCommand(String command, int param) throws NoSuchMethodException {
        Method method = SplayTree.class.getDeclaredMethod(command, int.class);

        try {
            method.invoke(null, param);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SplayTree.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(SplayTree.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(SplayTree.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public static class Node {
        public int data;
        public Node left, right;

        public Node(int element) {
            this.data = element;
            left = null;
            right = null;
        }
    }

    public SplayTree() {
        root = null;
    }

    private static Node makeRightChildParent(Node c) {
        Node newTemp = c.left;
        c.left = newTemp.right;
        newTemp.right = c;
        return newTemp;
    }

    private static Node makeLeftChildParent(Node c) {
        Node newTemp = c.right;
        c.right = newTemp.left;
        newTemp.left = c;
        return newTemp;
    }

    /* I have tried with Node node parameters
       but it is not possible bacause i'm invoking methods with using Java.reflection
     private static void Splaytest(Node node) {
        boolean flag = true;
        Node nd = root;
        Node parent = null;
        Node grandParent = null;
        Node grandGrandParent = null;

        while (true) {
            if (nd == null || node.data == nd.data)
                break;
            else if (nd.left != null && node.data < nd.data) {
                if (node.data == nd.left.data) {
                    nd = makeRightChildParent(nd);
                }
                // zig-zig
                else if (nd.left.left != null && node.data == nd.left.left.data) {
                    grandParent = nd;
                    parent = nd.left;
                    nd = makeRightChildParent(grandParent);
                    nd = makeRightChildParent(parent);
                    flag = true;
                }
                // zig-zag
                else if (nd.left.right != null && node.data == nd.left.right.data) {
                    grandParent = nd;
                    parent = nd.left;
                    grandParent.left = makeLeftChildParent(parent);
                    nd = makeRightChildParent(grandParent);
                    flag = true;
                } else if (node.data < nd.data) {
                    grandGrandParent = nd;
                    nd = nd.left;
                }
            } else if (nd.right != null && node.data > nd.data) {
                if (node.data == nd.right.data) {
                    nd = makeLeftChildParent(nd);
                }
                // zig-zig
                else if (nd.right.right != null && node.data == nd.right.right.data) {
                    grandParent = nd;
                    parent = nd.right;
                    nd = makeLeftChildParent(grandParent);
                    nd = makeLeftChildParent(parent);
                    flag = true;
                }
                // zig-zag
                else if (nd.right.left != null && node.data == nd.right.left.data) {
                    grandParent = nd;
                    parent = nd.right;
                    grandParent.right = makeRightChildParent(parent);
                    nd = makeLeftChildParent(grandParent);
                    flag = true;
                } else if (node.data > nd.data) {
                    grandGrandParent = nd;
                    nd = nd.right;
                }
            } else if ((nd.left == null && node.data < nd.data)
                    || (nd.right == null && node.data > nd.data)) {
                node.data = nd.data;
                nd = root;
                grandGrandParent = null;
            }


            if (flag && grandGrandParent != null) {
                if (nd.data < grandGrandParent.data)
                    grandGrandParent.left = nd;
                else if (nd.data > grandGrandParent.data)
                    grandGrandParent.right = nd;
                nd = root;
                grandGrandParent = null;
                flag = false;
            }
        }
        root = nd;
    }
*/

    // Splay function for zig-zag & zig-zig
    private static void Splay(int data) {

        Node node = root;
        Node parent = null;
        Node grandParent = null;
        Node grandGrandParent = null;
        boolean flag = true;

        while (true) {
            if (node == null || data == node.data)
                break;
            else if (node.left != null && data < node.data) {
                if (data == node.left.data) {
                    node = makeRightChildParent(node);
                }
                // zig-zig
                else if (node.left.left != null && data == node.left.left.data) {
                    grandParent = node;
                    parent = node.left;
                    node = makeRightChildParent(grandParent);
                    node = makeRightChildParent(parent);
                    flag = true;
                }
                // zig-zag
                else if (node.left.right != null && data == node.left.right.data) {
                    grandParent = node;
                    parent = node.left;
                    grandParent.left = makeLeftChildParent(parent);
                    node = makeRightChildParent(grandParent);
                    flag = true;
                } else if (data < node.data) {
                    grandGrandParent = node;
                    node = node.left;
                }
            } else if (node.right != null && data > node.data) {
                if (data == node.right.data) {
                    node = makeLeftChildParent(node);
                }
                // zig-zig
                else if (node.right.right != null && data == node.right.right.data) {
                    grandParent = node;
                    parent = node.right;
                    node = makeLeftChildParent(grandParent);
                    node = makeLeftChildParent(parent);
                    flag = true;
                }
                // zig-zag
                else if (node.right.left != null && data == node.right.left.data) {
                    grandParent = node;
                    parent = node.right;
                    grandParent.right = makeRightChildParent(parent);
                    node = makeLeftChildParent(grandParent);
                    flag = true;
                } else if (data > node.data) {
                    grandGrandParent = node;
                    node = node.right;
                }
            } else if ((node.left == null && data < node.data)
                    || (node.right == null && data > node.data)) {
                data = node.data;
                node = root;
                grandGrandParent = null;
            }


            if (flag && grandGrandParent != null) {
                if (node.data < grandGrandParent.data)
                    grandGrandParent.left = node;
                else if (node.data > grandGrandParent.data)
                    grandGrandParent.right = node;
                node = root;
                grandGrandParent = null;
                flag = false;
            }
        }
        root = node;
    }

    // Checks the root is null
    public static boolean isNull() {
        return root == null;
    }

    public static void insert(int ele) {
        root = insert(ele, root);
        Splay(ele);
        printLevelOrder();
        String str = "\n";
        SplayTree.WriteFile.fileWriter(str);

    }

    private static Node insert(int ele, Node node) {
        if (node == null)
            return new Node(ele);
        else {
            if (ele < node.data)
                node.left = insert(ele, node.left);
            else if (ele > node.data)
                node.right = insert(ele, node.right);
            return node;
        }
    }


    /* prints level order of tree */
    static void printLevelOrder() {
        int h = height(root);
        int i;
        for (i = 1; i <= h; i++)
            printGivenLevel(root, i);
    }

    /* Calculates the height of tree */
    static int height(Node root) {
        if (root == null)
            return 0;
        else {
            /* Calculates the each subtree's height */
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);

            /* Choose only higher one */
            if (leftHeight > rightHeight)
                return (leftHeight + 1);
            else return (rightHeight + 1);
        }
    }

    /* Print the nodes of tree at given level */
    static void printGivenLevel(Node root, int level) {

        if (root == null) {
            String str = " - ";
            SplayTree.WriteFile.fileWriter(str);
            return;
        }
        if (level == 1) {
            String str = root.data + " ";
            SplayTree.WriteFile.fileWriter(str);

        } else if (level > 1) {
            printGivenLevel(root.left, level - 1);
            printGivenLevel(root.right, level - 1);
        }

    }


    public static void remove(int ele) {
        if (!isNull()) {
            Splay(ele);
            if (root != null && root.data == ele) {
                if (root.left != null) {
                    Node temp = root.right;
                    root = root.left;
                    Splay(ele);
                    root.right = temp;
                } else
                    root = root.right;
            }
        }
        printLevelOrder();
        String str = "\n";
        SplayTree.WriteFile.fileWriter(str);
    }


    public static void find(int ele) {
        findd(ele);
        printLevelOrder();
        String str = "\n";
        SplayTree.WriteFile.fileWriter(str);
    }

    public static Node findd(int ele) {
        if (isNull())
            return root;
        Splay(ele);
        if (root.data == ele) {
            return root;
        }
        return root;
    }


    static class WriteFile {
        static FileWriter fileWriter;

        static {
            try {
                fileWriter = new FileWriter(outputfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        WriteFile() {
        }

        public static void fileWriter(String str) {
            try {
                fileWriter.append(str);
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}



