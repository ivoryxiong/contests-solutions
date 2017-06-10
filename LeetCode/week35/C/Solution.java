public class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> ret = new ArrayList<List<String>>();
        Map<String, List<String>> content2names = new HashMap<String, List<String>>();
        for (String str : paths) {
            String[] cs = str.split("[ )(]");
            String root = cs[0];
            for(int i = 1; i + 1 < cs.length; i += 3) {
                List<String> names = content2names.get(cs[i+1]);
                if (names == null) {
                    names = new LinkedList<String>();
                    content2names.put(cs[i+1], names);
                }
                names.add(root + "/" + cs[i]);
            }
        }
        for (List<String> names : content2names.values()) {
            if (names.size() > 1) {
                ret.add(names);
            }
        }
        return ret;
    }
}
