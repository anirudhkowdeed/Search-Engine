/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package searchengine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author srinath
 */
public class temp {

    public static void main(String args[]) throws FileNotFoundException, IOException
    {
       String line;
       BufferedReader bufferedReader = new BufferedReader(new FileReader("stopwords.txt"));
            while ((line = bufferedReader.readLine()) != null)
  {
    System.out.println("stopw.add(\""+line+"\");");
  }

  // close the BufferedReader when we're done
  bufferedReader.close();

    }

}
