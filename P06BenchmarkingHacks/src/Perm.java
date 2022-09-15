import java.util.*;

public class Perm {
  private static HashMap<String, ArrayList<String>> memo = new HashMap<>();
  
  public static void main(String[] args) {
    String ab = "ABCD";
    System.out.println(permutations(ab));
    System.out.println(permutations(ab).size());
  }
  
  public static ArrayList<String> permutations(String str) {
    if (memo.containsKey(str)) {
      System.out.println("hit: " + str);
      return memo.get(str);
    }
    
    ArrayList<String> perms = new ArrayList<>();
    if (str.length() == 1) {
      perms.add(str);
      return perms;
    }
    
    for (int i = 0; i < str.length(); i++) {
      String head = str.substring(i, i + 1);
      ArrayList<String> smallerPerms = permutations(str.replace(head, ""));
      for (String s : smallerPerms) {
        perms.add(head + s);
      }
    }
    memo.put(str, perms);
    return perms;
  }
}

/*
 * str = ABC
 * from 0 -> 2
 * i = 0
 * head = A
 * smallerPerms = permutations("BC");
 *  from 0 -> 1;
 *  i = 0
 *  head = B
 *  smallerPerms = perm("C");
 *  smallerPerms = [C];
 *  perms.add(BC);
 *  i = 1;
 *  head = C
 *  smallerPerms = perms("B");
 *  perms.add("CB");
 * smallerPerms = [BC, CB];
 * perms.add(A B C, A C B);
 * i = 1;
 * head = B
 * 
 */

// A B C D
// A B D C
// A C B D
// A C D B
// A D C B
// A D B C
// B A C D...