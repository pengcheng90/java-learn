package datastructure.bean;

public class BinaryTree {
    private HeroNode root;

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        HeroNode root = new BinaryTree.HeroNode(1, "a");

        HeroNode right = new BinaryTree.HeroNode(3, "c");
        HeroNode node4 = new BinaryTree.HeroNode(4, "c");
        HeroNode node5 = new BinaryTree.HeroNode(5, "c");
        right.setLeft(node4);
        right.setRight(node5);

        root.setLeft(new HeroNode(2, "b"));
        root.setRight(right);
        tree.setRoot(root);

        //前序遍历
        tree.preOrder();
        //中序遍历
        tree.infixOrder();
        //后序遍历
        tree.postOrder();
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void preOrder() {
        System.out.println("前序遍历");
        if (this.root == null) {
            System.out.println("roo is null");
            return;
        }
        this.root.preOrder();
    }

    public void infixOrder() {
        System.out.println("中序遍历");
        if (this.root == null) {
            System.out.println("roo is null");
            return;
        }
        this.root.infixOrder();
    }

    public void postOrder() {
        System.out.println("后序遍历");
        if (this.root == null) {
            System.out.println("roo is null");
            return;
        }
        this.root.postOrder();
    }


    static class HeroNode {
        private int no;
        private String name;
        private HeroNode left;
        private HeroNode right;

        public HeroNode(String name) {
            this.name = name;
        }

        public HeroNode(int no, String name) {
            this.no = no;
            this.name = name;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public HeroNode getLeft() {
            return left;
        }

        public void setLeft(HeroNode left) {
            this.left = left;
        }

        public HeroNode getRight() {
            return right;
        }

        public void setRight(HeroNode right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }

        //前序遍历： 根>左>右顺序， 依次递归左子树，右子树
        public void preOrder() {
            System.out.println(this);  //先输出父节点
            if (this.left != null) { //递归左子节点
                this.left.preOrder();
            }
            if (this.right != null) {
                this.right.preOrder();
            }
        }

        //中遍历： 左>根>右顺序， 依次递归左子树，右子树
        public void infixOrder() {
            if (this.left != null) { //递归左子节点
                this.left.infixOrder();
            }
            //左子树递归完后，打印根
            System.out.println(this);
            if (this.right != null) {
                this.right.infixOrder();
            }
        }

        //后遍历： 左>右>根顺序， 依次递归左子树，右子树
        public void postOrder() {
            if (this.left != null) { //递归左子节点
                this.left.postOrder();
            }
            if (this.right != null) {
                this.right.postOrder();
            }
            //右子树递归完后，打印根
            System.out.println(this);
        }
    }


}
