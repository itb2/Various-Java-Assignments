/*
 * Written List & Tree Exercises
 */
//---------------------------------------------------------
// 1. Fruit Salad
//
public static int fruitCounter(Node list)
// precondition: list has no header node
// postcondition: returns # of occurrences of "bean" in list
{
   int total = 0;
   while (list != null)
   {
       if (isFruit(list.info))
           total++;
       list = list.next;
   }
   return total;
}


// b. recursive solution
//
public static int fruitCounter(Node list)
// precondition: list has no header node
// postcondition: returns # of occurrences of s in list
{
   if (list == null)
       return 0;
   if (isFruit(list.info))
       return 1 + fruitCounter(list.next);
   else 
       return fruitCounter(list.next);
}


//---------------------------------------------------------
// 2. Occurs Check 
// a. 
public static boolean hasDuplicates(Node list)
// precondition: list has no header node
// postcondition: returns whether the list has any duplicated elements
{
   if (list == null)
       return false;
   if (countOccurences(list.next, list.info) > 0)
       return true;
   else
     return hasDuplicates(list.next);
}

// b. Complexity
//    O(n^2)
//    T(n) = T(n-1) + O(n)
//    T(0) = O(1)

// c. New solution
//    Describe adding each item to a TreeSet, then removing them all.
//    New complexity is O(n lg n)



//---------------------------------------------------------
// 3. Polynomial. Polygon.
// a. MakePolyNomial 
TermNode makePolyNomHelper(int[] poly, int index)
// pre: polyV is of size > than index
// post: returns polynomial node list for all nonzero elements from
//       index up to length-1
{
   if (index >= poly.length())
       return null;

   if (poly[index] != 0)
       return new TermNode(poly[index], poly.length() - index - 1,
                           makePolyNomHelper(poly, index+1));
   else
       return makePolyNomHelper(poly, index+1);
}
public static TermNode makePolyNomial(int[] poly)
// precondition: vector has some number (>= 0) of coefficients
// postcondition: returns a list of TermNodes 
{
   return MakePolyNomHelper(poly, 0);
}

// iterative version
public static TermNode makePolyNomial(int[] poly)
{
   int power = poly.size()-1;
   TermNode head = new TermNode(3,3,null);
   TermNode tail = head;
   for(int k=0; k < poly.size(); k++){
       if (poly[k] != 0){
           tail.next = new TermNode(poly[k],power,0);
           tail = tail.next;
       }
       power--;
   }
   return head.next;
}


//------------------------------------------------------------
// 3b. addPolyNomial 
public static TermNode addPolyNomial(TermNode p1, TermNode p2)
// precondition: both p1 and p2 have no header nodes
// postcondition: returns NEW list of TermNodes form the sum
//                    of elements of p1 and p2
{
   if (p1 == null && p2 == null)
     return null;
   if (p1 == null)
       return new TermNode(p2.coeff, p2.power,
                         addPolyNomial(p1,p2.next));
   if (p2 == null)
       return new TermNode(p1.coeff, p1.power,
                           addPolyNomial(p1.next,p2));

   if (p1.power > p2.power)
       return new TermNode(p1.coeff, p1.power,
                           AddPolyNom(p1.next,p2));
   if (p1.power < p2.power)
       return new TermNode(p2.coeff, p2.power,
                           AddPolyNom(p1,p2.next));

   return new TermNode(p1.coeff + p2.coeff,
                       p1.power,
                       AddPolyNom(p1.next,p2.next));
}

// iterative version
public static TermNode addPolyNomial(TermNode a, TermNode b)
{
   TermNode dummy(0,0,null);
   TermNode tail = dummy;

   while (a != 0 || b != 0){

       if (a == 0 || a.power < b.power){
           tail.next = new TermNode(b.coeff,b.power,0);
           b = b.next;
       }
       else if (b == 0 || b.power < a.power){
           tail.next = new TermNode(a.coeff,a.power,0);
           a = a.next;
       }
       else {
           tail.next = new TermNode(a.coeff+b.coeff,a.power,0);
           a = a.next;
           b = b.next;
       }
       tail = tail.next;
   }
   return dummy.next;
}

// 3c multPolyNomial
TermNode distribute(TermNode p, TermNode single)
// pre: 
// post: returns single is multiplied by each element in p
{
    if (p == 0 || single==0)
        return 0;
    return new TermNode(p.coeffsingle.coeff, p.power+single.power,
                        distribute(p.next, single));
}
TermNode distributeAll(TermNode p1, TermNode p2)
// pre: p1, p2 no headers
// post: returns the result of multiplying the elements in p1 on elements
//       in p2  (applies distributative law)
{
    if (p1 == 0)
      return 0;
    return addPolyNom(distribute(p2,p1),
                      distributeAll(p1.next, p2));
    
}

TermNode insertInPowerOrder(TermNode sortedP, TermNode elem)
// pre: sortedP is sorted by power (decreasing order), elem is not null
//      and is properly intiialized
// post: list is returned with new copy of elem in sortedP
{
    if (sortedP == 0)
        return new TermNode(elem.coeff, elem.power, 0);
    if (elem.power >= sortedP.power)
        return new TermNode(elem.coeff, elem.power,
                            sortedP);
    sortedP.next = insertInPowerOrder(sortedP.next, elem);
    return sortedP;
}
      
void sortByPower(TermNode p, TermNode sortedp)
// pre: sortedp is sorted in decreasing order by power
// post: sortedp has the elements of p inserted in it in order
{
    if (p == 0)
      return sortedp;
    
    sortedp = insertInPowerOrder(sortedp, p);
    sortByPower(p.next, sortedp);
    return sortedp;
}
TermNode combineDups(TermNode p)
// pre: TermNodes are sorted by power
// post: All consecutive nodes with the same power with the same
//       power have their coeffs combined and nodes with 0 coeff
//       are removed (linked around)
{
    if (p == 0)
        return 0;
    p.next = CombineDups(p.next);
    if (p.next != 0 && p.power == p.next.power)
    {
        p.coeff += p.next.coeff;
        p.next = p.next.next;
    }
    if (p.coeff == 0)
        // Get rid of zero coefficients
        return p.next;
    else
        return p;
}

TermNode simplify(TermNode p)
{
    TermNode sortedp = 0;       
    if (p == 0)
        return 0;
    sortedP = sortByPower(p, sortedp);
    return combineDups(sortedp);
}
TermNode multPolyNomial(TermNode p2)
// precondition: both p1 and p2 have no header nodes
// postcondition: returns NEW list of TermNodes form the product
//                    of elements of p1 and p2
{
    return simplify(distributeAll(this,p2));
}

//---------------------------------------------------------
// 4. Circular
pubic static boolean isCircular(Node list)
{  
    Node L1 = null;
    Node L2 = null;
    if (list == null)
        return false;
    // Have 2 list counters. One will go at double the speed of the othe
    // (consider two hands going around a clock)
    for (L1 = list, L2 = list.next; L2 != null && L2.next != null; 
         L1 = L1.next, L2 = L2.next.next)
        // is L2 pointing to the same thing as L1 or one before it
        // in case the number of nodes in the cycle is odd
        if (L1 == L2 || L1 == L2->next)
            return true;
    return false;
}

//---------------------------------------------------------
// 5. TreeToList Revisited

// a. 
/*
Let T(n) be the time for treeToList to execute when tree
has n-nodes.

Two recursive calls, each T(n/2) in average case.
The while loop iterates over the list returned for half the
tree which has n/2 nodes in it. This loop is thus O(n). Other
code is all O(1)/constant-time

T(n) = 2T(n/2)+O(n) solution is: O(n log n)
T(1) = O(1)
*/

// b.

/*
 Two recursive calls again, only other code is constant
time so

T(n) = 2T(n/2) + O(1) which is O(n)
T(0) = O(1)
*/

// 6. Find Kth

/*
  findKthInOrder is

T(n) = T(n/2) + O(n) which has solution O(n)

There is only one recursive call, either to left subtree or
right depending on value returned by count. Count is O(n)
*/

// b.

/*
In worst case all nodes are in left-subtree and we want the
last node

T(n) = T(n-1) + O(n) which is O(n^2)

*/

// 7. Isomorphic Trees
// a. 
public static boolean isIsomorphic(TreeNode s, TreeNode t) { 
        if (s == null) return t == null;
        if (t == null) return false;

        return isIsomorphic(s.left,t.left) && isIsomorphic(s.right,t.right);
}

/*
Let T(n) be time for isIsomorphic to execute when total number of nodes
in both s and t together is n. Then:

T(n) = 2T(n/2) + O(1) which is in O(n)
T(0) = O(1)
*/

public static boolean isQuasiIsomorphic(TreeNode s, TreeNode t) { 
    if (s == null) return t == null;
    if (t == null) return false;
    
    return
      (isQuasiIsomorphic(s.left,t.left) && isQuasiIsomorphic(s.right,t.right))
        ||
      (isQuasiIsomorphic(s.left,t.right) && isQuasiIsomorphic(s.right,t.left));
}

/*
  this is

T(n) = 4T(n/2) + O(1)
T(1) = O(1) = a

T(n) = 4T(n/2) + a
      since T(n/2) = 4T(n/2/2) + a
     = 4[4T(n/4) + a] + a
     = 16T(N/4) + 5a
      since T(n/4) = 4T(n/4/2) + a     
     = 16[4T(n/8) + a] + 5a = 64T(n/8) + 21

     4 = 2^2
     = 2^(2*3)T(n/2^3) + 2^(2*2)a + 2^(2*1)a + 2^(2*0)a
       see a pattern:
     = 2^(2k)T(n/2^k) + 2^(2(k-1))a + 2^(2(k-2))a + ... + 2^(2*0)a

in the base case T(1) n/2^k = 1 -> n = 2^k
so k = lg n

T(n) = 2^(2lgn)T(1) + 2^(2(lg n -1))a + 2^(2(lg n-2))a + ... + 2^(2*0)a

     in O(n^2)
*/
