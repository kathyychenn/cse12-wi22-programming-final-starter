/**
 * Name: Kathy Chen
 * ID: A17030814
 * Email: ktchen@ucsd.edu
 * File description: This file contains all of the student-written tests. 
 * It contains test files for the CSE12NaryTree class to ensure accuracy 
 * (return correct values, correctly modify sets, exceptions, etc) 
 */
 
import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

/**
 * This class contains self-written J-unit tests for the CSE12NaryTree class.
 */
public class CSE12NaryTreeTester {
    private CSE12NaryTree<Integer> fiveNaryTree;
    private CSE12NaryTree<Integer> rootNaryTree;
    private CSE12NaryTree<Integer> emptyNaryTree;
	//private MyLinkedList<String> threeStringList;

	/**
	 * Standard Test Fixture. An empty list, a list with one entry (0) and
	 * a list with several entries (0,1,2)
	 */
	@Before
	public void setUp() {
		emptyNaryTree = new CSE12NaryTree<Integer>(5);
        emptyNaryTree.size = 0;

        rootNaryTree = new CSE12NaryTree<Integer>(5);
        rootNaryTree.root = this.rootNaryTree.new Node(9);
        rootNaryTree.size = 1;

        fiveNaryTree = new CSE12NaryTree<Integer>(5);
        fiveNaryTree.root = this.fiveNaryTree.new Node(9);
        fiveNaryTree.root.addChild(this.fiveNaryTree.new Node(3));
        fiveNaryTree.root.addChild(this.fiveNaryTree.new Node(7));
        fiveNaryTree.root.addChild(this.fiveNaryTree.new Node(1));
        fiveNaryTree.root.addChild(this.fiveNaryTree.new Node(2));
        fiveNaryTree.root.addChild(this.fiveNaryTree.new Node(8));
        fiveNaryTree.size = 6;
	}
    
    /**
     * Test add method on 5-ary tree with a root node and its 5 children nodes.
     */
    @Test
    public void testAdd(){
        fiveNaryTree.add(29);
        fiveNaryTree.add(30);

        //manually access data of new node added
        List<CSE12NaryTree<Integer>.Node> rootChildren = 
            fiveNaryTree.root.getChildren();
        CSE12NaryTree<Integer>.Node expected1 = 
            rootChildren.get(0).getChildren().get(0);
        CSE12NaryTree<Integer>.Node expected2 = 
            rootChildren.get(0).getChildren().get(1);

        assertEquals("29 is first child of first child of root", (Integer)29, 
                    expected1.getData());
        assertEquals("30 is second child of first child of root", (Integer)30, 
                    expected2.getData());
        assertEquals("size incremented", 8, fiveNaryTree.size);

    }

    /**
     * Test add method on empty 5-ary tree
     */
    @Test
    public void testAdd2(){
        emptyNaryTree.add(29);

        //manually access data of new node added
        CSE12NaryTree<Integer>.Node expected1 = emptyNaryTree.root;

        assertEquals("29 is the root", (Integer)29, expected1.getData());
        assertEquals("size incremented", 1, emptyNaryTree.size);
    }

    /**
     * Test add method when element is null
     */
    @Test
    public void testAdd3(){
        try {
            fiveNaryTree.add(null);
            fail();
        } catch (Exception e) {
            //exception caught successfully
        }
    }

    /**
     * Tests the contains method on a 5-ary tree when element is not present.
     */
    @Test
    public void testContains(){
        assertFalse("element not contained in NaryTree", 
                    fiveNaryTree.contains(12));
        assertFalse("element not contained in NaryTree", 
                    fiveNaryTree.contains(0));
    }

    /**
     * Tests the contains method on a 5-ary tree when element is present.
     */
    @Test
    public void testContains2(){
        assertTrue("element contained in NaryTree", fiveNaryTree.contains(9));
        assertTrue("element contained in NaryTree", fiveNaryTree.contains(2));
    }

    /**
     * Tests the contains method when element is null.
     */
    @Test
    public void testContains3(){
        try {
            fiveNaryTree.contains(null);
            fail();
        } catch (Exception e) {
            //exception caught successfully
        }
    }

    /**
     * Tests the contains method on an empty tree.
     */
    @Test
    public void testContains4(){
        assertFalse("element not contained in emptyNaryTree", 
                    emptyNaryTree.contains(12));
        assertFalse("element not contained in emptyNaryTree", 
                    emptyNaryTree.contains(0));
    }

    /**
     * Tests sortTree method on 5-ary tree with only the root node.
     */
    @Test
    public void testSortTree(){
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(9);

        assertEquals("sortTree returns list w root element", expected, 
                    rootNaryTree.sortTree());
    }

    /**
     * Tests sortTree method on 5-ary tree with root node and its 5 children
     */
    @Test
    public void testSortTree2(){
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(7);
        expected.add(8);
        expected.add(9);

        assertEquals("sortTree returns list elements sorted", expected, 
                    fiveNaryTree.sortTree());
    }

    /**
     * TODO: Add test case description
     */
    @Test
    public void testCustom(){
        
    }
}
