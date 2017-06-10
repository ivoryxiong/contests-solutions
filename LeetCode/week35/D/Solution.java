import java.util.LinkedList;

public class Solution {
    public boolean isValid(String code) {
        if (!code.startsWith("<")) {
            return false;
        }
        LinkedList<String> tags = new LinkedList<String>();
        String beginTag = this.parseTag(code, 1);
        if (beginTag != null && code.endsWith("</" + beginTag + ">")) {
            tags.add(beginTag);
        } else {
            return false;
        }
        boolean valid = true;
        int idx = 1 + beginTag.length();
        boolean comment = false;
        while(idx < code.length()) {
            if (comment) {
                if (code.startsWith("]]>", idx)) {
                    idx += 3;
                    comment = false;
                } else {
                    idx += 1;
                }
            } else {
                if (code.startsWith("<![CDATA[", idx)) {
                    comment = true;
                    idx += 9;
                } else if (code.startsWith("</", idx)) {
                    idx += 2;
                    String tag = this.parseTag(code, idx);
                    if (tag != null && tags.size() > 0) {
                        if (tag.compareTo(tags.getLast()) == 0) {
                            tags.removeLast();
                        } else {
                            valid = false;
                            break;
                        }
                    } else {
                        valid = false;
                        break;
                    }
                    idx = idx + tag.length();
                } else if(code.startsWith("<", idx)){
                    idx += 1;
                    String tag = this.parseTag(code, idx);
                    if (tag != null && tags.size() > 0) {
                        tags.add(tag);
                    } else {
                        valid = false;
                        break;
                    }
                    idx = idx + tag.length();
                } else {
                    idx += 1;
                }
            }
        }
        if (valid) {
            valid = tags.size() == 0;
        }
        return valid;
    }
    
    private String parseTag(String code, int idx) {
        int close = code.indexOf(">", idx);
        if (close >= idx + 1 && close < idx + 10) {
            String tag = code.substring(idx, close);
            for (int i = 0 ; i < tag.length(); i ++) {
                if (tag.charAt(i) > 'Z' || tag.charAt(i) <'A') {
                    return null;
                }
            }
            return tag;
        }
        return null;
    }
    
    public static void main(String [] args ) {
        Solution solution = new Solution();
        LinkedList<String> tests = new LinkedList<String>();
        tests.add("<A></A>>");
        tests.add("<A<></A<>");
        tests.add("<A>  </A>    <B></B>");
        tests.add("<DIV>  div tag is not closed  <DIV>");
        tests.add("<DIV>This is the first line <![CDATA[<div>]]></DIV>");
        tests.add("<DIV>>>  ![cdata[]] <![CDATA[<div>]>]]>]]>>]</DIV>");
        tests.add("<DIV> unmatched tags with invalid tag name  </1234567890> and <CDATA[[]]>  </DIV>");
        tests.add( "<DIV>  unmatched start tag <B>  and unmatched end tag </C>  </DIV>");

        for (String test : tests) {
            System.out.println(solution.isValid(test) + "\t\t" + test);
        }
    }
}
