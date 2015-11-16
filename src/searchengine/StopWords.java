/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package searchengine;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
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
public class StopWords {
   Index in=null;
    public StopWords(StringBuffer str,PageInfo pi,int cnt,Parsers ps,Tree m) throws FileNotFoundException, IOException
    {
        HashSet stopw= new HashSet();
        String s;
     StringBuffer sres=new StringBuffer("");

stopw.add("a");
stopw.add("about");
stopw.add("above");
stopw.add("after");
stopw.add("again");
stopw.add("against");
stopw.add("all");
stopw.add("am");
stopw.add("an");
stopw.add("and");
stopw.add("any");
stopw.add("are");
stopw.add("aren't");
stopw.add("as");
stopw.add("at");
stopw.add("be");
stopw.add("because");
stopw.add("been");
stopw.add("before");
stopw.add("being");
stopw.add("below");
stopw.add("between");
stopw.add("both");
stopw.add("but");
stopw.add("by");
stopw.add("can't");
stopw.add("cannot");
stopw.add("could");
stopw.add("couldn't");
stopw.add("did");
stopw.add("didn't");
stopw.add("do");
stopw.add("does");
stopw.add("doesn't");
stopw.add("doing");
stopw.add("don't");
stopw.add("down");
stopw.add("during");
stopw.add("each");
stopw.add("few");
stopw.add("for");
stopw.add("from");
stopw.add("further");
stopw.add("had");
stopw.add("hadn't");
stopw.add("has");
stopw.add("hasn't");
stopw.add("have");
stopw.add("haven't");
stopw.add("having");
stopw.add("he");
stopw.add("he'd");
stopw.add("he'll");
stopw.add("he's");
stopw.add("her");
stopw.add("here");
stopw.add("here's");
stopw.add("hers");
stopw.add("herself");
stopw.add("him");
stopw.add("himself");
stopw.add("his");
stopw.add("how");
stopw.add("how's");
stopw.add("i");
stopw.add("i'd");
stopw.add("i'll");
stopw.add("i'm");
stopw.add("i've");
stopw.add("if");
stopw.add("in");
stopw.add("into");
stopw.add("is");
stopw.add("isn't");
stopw.add("it");
stopw.add("it's");
stopw.add("its");
stopw.add("itself");
stopw.add("let's");
stopw.add("me");
stopw.add("more");
stopw.add("most");
stopw.add("mustn't");
stopw.add("my");
stopw.add("myself");
stopw.add("no");
stopw.add("nor");
stopw.add("not");
stopw.add("of");
stopw.add("off");
stopw.add("on");
stopw.add("once");
stopw.add("only");
stopw.add("or");
stopw.add("other");
stopw.add("ought");
stopw.add("our");
stopw.add("ours ");
stopw.add("ourselves");
stopw.add("out");
stopw.add("over");
stopw.add("own");
stopw.add("same");
stopw.add("shan't");
stopw.add("she");
stopw.add("she'd");
stopw.add("she'll");
stopw.add("she's");
stopw.add("should");
stopw.add("shouldn't");
stopw.add("so");
stopw.add("some");
stopw.add("such");
stopw.add("than");
stopw.add("that");
stopw.add("that's");
stopw.add("the");
stopw.add("their");
stopw.add("theirs");
stopw.add("them");
stopw.add("themselves");
stopw.add("then");
stopw.add("there");
stopw.add("there's");
stopw.add("these");
stopw.add("they");
stopw.add("they'd");
stopw.add("they'll");
stopw.add("they're");
stopw.add("they've");
stopw.add("this");
stopw.add("those");
stopw.add("through");
stopw.add("to");
stopw.add("too");
stopw.add("under");
stopw.add("until");
stopw.add("up");
stopw.add("very");
stopw.add("www");
stopw.add("http");
stopw.add("https");
stopw.add("was");
stopw.add("wasn't");
stopw.add("we");
stopw.add("we'd");
stopw.add("we'll");
stopw.add("we're");
stopw.add("we've");
stopw.add("were");
stopw.add("weren't");
stopw.add("what");
stopw.add("what's");
stopw.add("when");
stopw.add("when's");
stopw.add("where");
stopw.add("where's");
stopw.add("which");
stopw.add("while");
stopw.add("who");
stopw.add("who's");
stopw.add("whom");
stopw.add("why");
stopw.add("why's");
stopw.add("with");
stopw.add("won't");
stopw.add("would");
stopw.add("wouldn't");
stopw.add("you");
stopw.add("you'd");
stopw.add("you'll");
stopw.add("you're");
stopw.add("you've");
stopw.add("your");
stopw.add("yours");
stopw.add("yourself");
stopw.add("yourselves");
 Porter p=new Porter();
       String [] st=str.toString().split("\\s");
       String bit=new String();
       int i=0;
       while(i<st.length){
       if(!stopw.contains(st[i])&&st[i].length()>1)
       {
       // System.out.println(st[i].toString());
         bit=p.stripAffixes(st[i].toString());
           sres.append(bit+" ");
           i++;
       }
         else
             i++;
      }
      //Stemmer stmm=new Stemmer(sres);
      // System.out.println(sres.toString());
       String [] stk=sres.toString().split("\\s");
       //if(sres.length()>1&&stk.length>1)
        in=new Index(sres,pi,cnt,m);
       


    }
}
