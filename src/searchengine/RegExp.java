/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package searchengine;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.StringBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author srinath
 */
public class RegExp {

    public RegExp(PageInfo pi,int cnt,Parsers ps,Tree t) throws FileNotFoundException, IOException
    {
        // Create a pattern to match cat   (<[\\w\\W]*?/[\\w\\W]*?>)|'''|''|<!--[\\w\\W]*?-->|\\.|\\[http[s]?:[\\w\\W]*?\\]|-|,|\\||\\'|/|\\*|[0-9]|==[\\w\\W]*?==|\\([\\w\\W]*?\\)|===[\\w\\W]*?===|=|\"|\\[\\[.*?:.*?\\]\\]|\\[\\[|\\]\\]|\\)|\\]|:|\\)|\\(|;|&nbsp
        Pattern p = Pattern.compile("(\\{\\{[\\w\\W]*?\\}\\})|(\\{[\\d\\D]*\\})|(<[\\w\\W]*?/[\\w\\W]*?>)|'''|''|<!--[\\w\\W]*?-->|\\.|\\[http[s]?:[\\w\\W]*?\\]|-|,|\\||\\'|/|\\*|[0-9]|==[\\w\\W]*?==|\\([\\w\\W]*?\\)|===[\\w\\W]*?===|=|\"|\\[\\[.*?:.*?\\]\\]|\\[\\[|\\]\\]|\\)|\\]|:|\\)|\\(|;|&nbsp|\\}|\\[|\\]|\\+|&");
       
       // Pattern p2 = Pattern.compile("(\\[\\[category[\\w\\W]*?\\]\\])");
        // Create a matcher with an input string
        pi.text=new StringBuffer(pi.text.toString().replaceAll("[^\\p{ASCII}]"," "));

        Matcher m = p.matcher(pi.text);

       // Matcher m2= p2.matcher(pi.text);
        StringBuffer sb = new StringBuffer();
        
        
        // Loop through and create a new String
        // with the replacements
       /* boolean result1 = m1.find();
        while(result1) {
            String groupStr=m1.group(0);
           System.out.print(groupStr);
            result1 = m1.find();
        }
        boolean result2 = m2.find();
        while(result2) {
            String str=m2.group(0);
            System.out.print(str);
            result2 = m2.find();
        }*/
        boolean result = m.find();
        while(result) {
            m.appendReplacement(sb," ");
            result = m.find();
        }
        // Add the last segment of input to
        // the new String
        m.appendTail(sb);
       //System.out.print(sb.toString());
     /*  int count=0,i=0,sindex=0,lindex=0;
       
       while(i<sb.length())
       {
         if(sb.charAt(i)=='{')
         {
             if(count==0)
                 sindex=i;
           count++;
         }
         if(sb.charAt(i)=='}')
         {
           count--;
         }
         if(sb.charAt(i)=='}'&&count==0)
         {
            lindex=i;
           st= new StringBuffer(sb.replace(sindex, lindex," "));
         }

        i++;
       }*/
         //System.out.println(sb.toString());
     sb = new StringBuffer( sb.toString().toLowerCase());
     StopWords s=new StopWords(sb,pi,cnt,ps,t);

    //System.out.println(sb.toString());
    }




}
