import java.util.*;

public class MajorityElement {
    public static int majorityElement(final List<Integer> A) {
        Map<Integer, Integer> elementCount = new HashMap<>();
        for (Integer elem : A){
            if(elementCount.containsKey(elem)){
                elementCount.put(elem, elementCount.get(elem)+1);
            }else{
                elementCount.put(elem, 1);
            }
        }

        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(elementCount.entrySet());
        entries.sort(Map.Entry.comparingByValue());
        return entries.get(entries.size()-1).getKey();
    }

    public static void main(String[] args) {
        System.out.println(majorityElement(Arrays.asList(1,1,2)));
    }
}
