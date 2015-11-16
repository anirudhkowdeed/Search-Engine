/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package searchengine;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class Parsers{
   private String currentElement;
   private int Count = -1,C =1;
   private boolean page= false;
   PageInfo obj;


   // Constructor
   public Parsers() {
      try {
         SAXParserFactory factory = SAXParserFactory.newInstance();
         SAXParser saxParser = factory.newSAXParser();
         saxParser.parse(new File("I:/downloads/movies/telugu/Wiki Data/wiki.xml/wiki10mb.xml"), new MyHandler());
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   
   /*
    * Inner class for the Callback Handlers.
    */
   class MyHandler extends DefaultHandler {
        RegExp r;
        Tree m=new Tree();;
         String str;
      // Callback to handle element start tag
      @Override
      public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
         currentElement = qName;
         if (currentElement.equals("page")) {
            obj=new PageInfo();
            C++;
            page= true;
            }
        if(currentElement.equalsIgnoreCase("text"))
           {
                    obj.text=new StringBuffer();
           }


      }

      // Callback to handle element end tag
      @Override
      public void endElement(String uri, String localName, String qName)
            throws SAXException {
           if(currentElement.equalsIgnoreCase("text"))
           {
               try {
                   if(!obj.text.toString().contains("#REDIRECT"))
                   {  
                         Count++;
                       if((Count)%1000==1&&Count!=1)
                            m=new Tree();

                           
                     r = new RegExp(obj,Count,Parsers.this,m);
                  }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Parsers.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Parsers.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
          currentElement = "";


      }

      // Callback to handle the character text data inside an element
      @Override
      public void characters(char[] chars, int start, int length) throws SAXException {

          if (currentElement.equals("title")) {
            obj.title=new String(chars, start, length);
            String s=new String("");
            int i=0;
            while(i<obj.title.length()){
            if(Character.isUpperCase(obj.title.charAt(i)))
            {
                s=s+new String(" "+obj.title.charAt(i));
                i++;
                
            }
            else
            {
               s=s+new String(""+obj.title.charAt(i));
               i++;
            }
           }
           obj.title =new String(s);
         } else if (currentElement.equals("id")) {
            if(page)
            {
          obj.id=new String(chars, start, length);
            page=false;
            }
         }
         else if (currentElement.equals("text")) {
           obj.text.append(new String(chars, start, length));


         }
      }

      public void endDocument() throws SAXException{



           
   /*while (iterator.hasNext()) {
      Object key = iterator.next();
      System.out.println("" + key + "" + map.get(key));
        }*/
int h=Count/1000;
int g=h*1000;
if(h==0)
{
  BufferedWriter out = null;
                try {
                    out = new BufferedWriter(new FileWriter(new String("index.txt"), true));
                } catch (IOException ex) {
                    Logger.getLogger(Parsers.class.getName()).log(Level.SEVERE, null, ex);
                }
          Iterator iterator1 = m.s.keySet().iterator();
      while (iterator1.hasNext()) {
      Object key = iterator1.next();
                    try {
                        out.write(key.toString() + m.s.get(key).toString() + "\n");
                    } catch (IOException ex) {
                        Logger.getLogger(Parsers.class.getName()).log(Level.SEVERE, null, ex);
                    }

        }
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(Parsers.class.getName()).log(Level.SEVERE, null, ex);
                }





}

 else{


Iterator iterator2 = m.s.keySet().iterator();
 BufferedReader in = null;
            try {
                in = new BufferedReader(new FileReader(new String("index.txt" + g)));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Parsers.class.getName()).log(Level.SEVERE, null, ex);
            }

 BufferedWriter out = null;
            try {
                out = new BufferedWriter(new FileWriter(new String("index"), true));
            } catch (IOException ex) {
                Logger.getLogger(Parsers.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                str = in.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Parsers.class.getName()).log(Level.SEVERE, null, ex);
            }
Object key = iterator2.next();
 while((str)!=null&&(iterator2.hasNext()))
 {
   String w1= this.word(str);
   String patt1= this.pat(str);
   String w2=key.toString();
    String patt2=m.s.get(key).toString();
    if(w1.compareTo(w2)>0)
    {
                    try {
                        out.write(w2 + patt2 + "\n");
                    } catch (IOException ex) {
                        Logger.getLogger(Parsers.class.getName()).log(Level.SEVERE, null, ex);
                    }
        key = iterator2.next();
    }
   else if(w1.compareTo(w2)<0)
    {
                    try {
                        out.write(w1 + patt1 + "\n");
                    } catch (IOException ex) {
                        Logger.getLogger(Parsers.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        str = in.readLine();
                    } catch (IOException ex) {
                        Logger.getLogger(Parsers.class.getName()).log(Level.SEVERE, null, ex);
                    }
   }
   else
    {
                    try {
                        out.write(w1 + patt1 + patt2 + "\n");
                    } catch (IOException ex) {
                        Logger.getLogger(Parsers.class.getName()).log(Level.SEVERE, null, ex);
                    }
        key = iterator2.next();
                    try {
                        str = in.readLine();
                    } catch (IOException ex) {
                        Logger.getLogger(Parsers.class.getName()).log(Level.SEVERE, null, ex);
                    }
   }
  }
            try {
                while ((str = in.readLine()) != null) {
                    out.write(str + "\n");
                }
            } catch (IOException ex) {
                Logger.getLogger(Parsers.class.getName()).log(Level.SEVERE, null, ex);
            }
while(iterator2.hasNext())
{
    key = iterator2.next();
                try {
                    out.write(key.toString() + m.s.get(key).toString() + "\n");
                } catch (IOException ex) {
                    Logger.getLogger(Parsers.class.getName()).log(Level.SEVERE, null, ex);
                }
}
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(Parsers.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Parsers.class.getName()).log(Level.SEVERE, null, ex);
            }

File f1 = new File(new String("index"+g));
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
}
