/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package searchengine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;
import java.io.*;
import java.util.HashMap;
/**
 *
 * @author srinath
 */
public class Index {
    HashMap text = new HashMap();
   HashMap title = new HashMap();
   HashMap category = new HashMap();
   HashMap links = new HashMap();
     String str;
 public Index(StringBuffer s,PageInfo pi,int cnt,Tree m) throws FileNotFoundException, IOException
    {
     Porter p= new Porter();

    String [] st=s.toString().split("\\s");
     String [] st1=pi.title.split("\\s");
   //System.out.print(  m.s.firstKey().toString());
     int i=0,j=0;
      while(j<st1.length)
      {
          if(st1[j].length()>0)
          st1[j]=(p.stripAffixes(st1[j]));
          j++;
      }
     j=0;
     while(i<st.length)
     {
         if(text.containsKey(st[i]))
         {
             Integer count=Integer.parseInt(text.get(st[i]).toString());
             count++;
             text.put(st[i], count);

         }
         else
         {
             if(st[i].length()>0)
             text.put(st[i],1);
         }
         i++;
     }
     while(j<st1.length)
     {
         st1[j]= st1[j].replaceAll("[0-9]*","");
         if(!title.containsKey(st1[j])&&st1[j].length()>1)
         {

             title.put(st1[j],1);
             text.put(st1[j],0);
         }
          j++;
     }
     Iterator iterator = text.keySet().iterator();
     int t = 0;
     while (iterator.hasNext())
     {
      Object key = iterator.next();
      if(m.s.containsKey(key))
      {
        String value=m.s.get(key).toString();
        if(title.containsKey(key))
            t=1;
        value=value+"|"+pi.id+":"+text.get(key)+":"+t;
        m.s.put(key,value);
            t=0;
      }
      else
      {
          if(title.containsKey(key))
            t=1;
          m.s.put(key,"|"+pi.id+":"+text.get(key)+":"+new String(""+t));
            t=0;
      }
    }
    
      if(cnt==1000)
      {

           BufferedWriter out = new BufferedWriter(new FileWriter(new
String("index"+cnt),true));
          Iterator iterator1 = m.s.keySet().iterator();
      while (iterator1.hasNext()) {
      Object key = iterator1.next();
      out.write(key.toString()+m.s.get(key).toString()+"\n");

        }

          out.close();

       }
     if(cnt%1000==0&&cnt!=1000&&cnt!=0)
     {
         Iterator iterator2 = m.s.keySet().iterator();
   /*while (iterator.hasNext()) {
      Object key = iterator.next();
      System.out.println("" + key + "" + map.get(key));
        }*/
int g=cnt-1000;
 BufferedReader in = new BufferedReader(new FileReader(new String
("index"+g)));

 BufferedWriter out = new BufferedWriter(new FileWriter(new String
("index"+cnt),true));
str=in.readLine();
Object key = iterator2.next();
 while((str)!=null&&(iterator2.hasNext()))
 {
   String w1= this.word(str);
   String patt1= this.pat(str);
   String w2=key.toString();
    String patt2=m.s.get(key).toString();
    if(w1.compareTo(w2)>0)
    {
        out.write(w2+patt2+"\n");
        key = iterator2.next();
    }
   else if(w1.compareTo(w2)<0)
    {
       out.write(w1+patt1+"\n");
       str=in.readLine();
   }
   else
    {
        out.write(w1+patt1+patt2+"\n");
        key = iterator2.next();
         str=in.readLine();
   }
  }
while((str=in.readLine())!=null)
{
    out.write(str+"\n");
}
while(iterator.hasNext())
{
    key = iterator2.next();
    out.write(key.toString()+m.s.get(key).toString()+"\n");
}

out.close();
in.close();
int v=cnt-1000;
File f1 = new File(new String("index"+v));
f1.delete();
}

     
 }
public String word(String str)
    {
     int i=0,j=0;
     StringBuffer ch= new StringBuffer("");
     while(i<str.length()&&str.charAt(i)!='|')
     {
         ch.append(str.charAt(i));
         i++;
     }
         return ch.toString();
    }
 public String pat(String str)
    {

     int i=0,j=0,k;
      StringBuffer ch= new StringBuffer("");
     while(i<str.length()&&str.charAt(i)!='|')
     {
         i++;
     }
     for(k=i;k<str.length();k++)
     {
         ch.append(str.charAt(k));
     }

     return ch.toString();


  }




}


