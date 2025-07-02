import java.util.*;

public class hashMap {
    public static void main (String[] args){
        System.out.println("Hello HashMap");
        HashMap<Integer,String> newHashMap = new HashMap<>();
        newHashMap.put(1,"Gaurav");
        newHashMap.put(2,"Zoro");
        newHashMap.put(3,"Luffy");
        newHashMap.put(4,"Eren");
        System.out.println(newHashMap);
        String str = newHashMap.get(4);
        System.out.println(str);
        newHashMap.put(4,"Mikasa");
        System.out.println(newHashMap);
        boolean bool1 = newHashMap.containsKey(7);
        System.out.println(bool1);
        boolean bool2 = newHashMap.containsKey(1);
        System.out.println(bool2);
        boolean bool3 = newHashMap.containsValue("Mikasa");
        System.out.println(bool3);
        // Set<Integer> keys = newHashMap.keySet();
        for(int i:newHashMap.keySet()){
            System.out.println(newHashMap.get(i));
        }
        
    
    }
}
