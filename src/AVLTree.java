public class AVLTree<T extends Comparable<? super T>> extends BST<T> implements Comparable<AVLTree> {
	
   protected int height;
	
	public AVLTree() {
		super();
		height = -1;
	}
	
	public AVLTree(BSTNode<T> root) {
		super(root);
		height = -1;
	}
	
	public int getHeight() {
		return getHeight(root);
	}
	
	private int getHeight(BSTNode<T> node) {
      if(node == null)
         return -1;
      else
         return 1 + Math.max(getHeight(node.left), getHeight(node.right));
   }
	
   private AVLTree<T> getLeftAVL() {
      AVLTree<T> leftsubtree = new AVLTree<T>(root.left);
      return leftsubtree;
   }

   private AVLTree<T> getRightAVL() {
      AVLTree<T> rightsubtree = new AVLTree<T>(root.right);
      return rightsubtree;
    }
    
	protected int getBalanceFactor() {
      if(isEmpty())
         return 0;
      else
         return getRightAVL().getHeight() - getLeftAVL().getHeight();
    }
    
    public void insertAVL(T el)  {
      super.insert(el);
      this.balance();
    }
    
    public void deleteAVL(T el) {
      //Q1
        this.deleteByCopying(el); // delete the desired element.
        this.balance();// rebalance the tree.
    }
    
    protected void balance()
    {
      if(!isEmpty())
      {
         getLeftAVL().balance();
    	   getRightAVL().balance();

         adjustHeight();
        
         int balanceFactor = getBalanceFactor();
        
         if(balanceFactor == -2) {
            if(getLeftAVL().getBalanceFactor() < 0)
			      rotateRight();
            else
               rotateLeftRight();
         }
		
         else if(balanceFactor == 2) {
            if(getRightAVL().getBalanceFactor() > 0)
               rotateLeft();
            else
               rotateRightLeft();
         }
      }
   }
    
   protected void adjustHeight()
   {
      if(isEmpty())
         height = -1;
      else
         height = 1 + Math.max(getLeftAVL().getHeight(), getRightAVL().getHeight());   
   }

    protected void rotateRight() {
        BSTNode<T> tempNode = this.root.right; // Store a reference to the right subtree in a temporary node
        this.root.right = this.root.left; // Update the right subtree of the root
        this.root.left = this.root.right.left; // Update the left subtree of the root
        this.root.right.left = this.root.right.right;
        this.root.right.right = tempNode; // Update the left subtree's right child

        // Swap the elements between the root and its right child
        T tmpObj =(T) this.root.el;
        this.root.el = this.root.right.el;
        this.root.right.el = tmpObj;

        this.getRightAVL().adjustHeight(); // Adjust the height of the current tree
        this.adjustHeight();

    }
  
    
   protected void rotateLeft() {
      BSTNode<T> tempNode = root.left;
      root.left = root.right;
      root.right = root.left.right;
      root.left.right = root.left.left;
      root.left.left = tempNode;
            
      T val = (T) root.el;
      root.el = root.left.el;
      root.left.el = val;
            
      getLeftAVL().adjustHeight();
      adjustHeight();
	}
	
	protected void rotateLeftRight() {
      // Q1

      getLeftAVL().rotateLeft(); // Perform a left rotation on the left subtree (single left rotation)
      getLeftAVL().adjustHeight(); // Adjust the height of the left subtree  
      this.rotateRight(); // Perform a right rotation on the current tree (single right rotation)
      this.adjustHeight(); // Adjust the height of the current tree
  }
  

   protected void rotateRightLeft()
   {
      getRightAVL().rotateRight();
      getRightAVL().adjustHeight();
      this.rotateLeft();
      this.adjustHeight();
   }

    @Override
    public int compareTo(AVLTree o) {
        return 0;
    }
}