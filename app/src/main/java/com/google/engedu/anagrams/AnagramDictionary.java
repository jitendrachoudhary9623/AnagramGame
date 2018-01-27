/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    public ArrayList<String> wordList=new ArrayList<String>();

    HashSet<String> wordSet=new HashSet<String>();
    HashMap<String,ArrayList<String>> lettersWord= new HashMap<>();
    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();

            wordList.add(word);
            wordSet.add(word);

          String sorted=SortLetters(word);
          if(lettersWord.containsKey(sorted))
          {
              lettersWord.get(sorted).add(word);
          }
          else
          {
              ArrayList<String> AnagramList=new ArrayList<>();
              AnagramList.add(word);
              lettersWord.put(sorted,AnagramList);
          }
        }


    }


    //Sorts the word in Alphbetical Order
    public String SortLetters(String word)
    {
        char wordArray[] = word.toCharArray();
        Arrays.sort(wordArray);
        return new String(wordArray);
    }

    //base=stop
    public boolean isGoodWord(String word, String base) {

       for(String i :wordList)
       {
           if(word.equals(i)&&!word.contains(base))
               return true;
       }
       return false;
    }

    public List<String> getAnagrams(String targetWord) {

        ArrayList<String> result = new ArrayList<String>();
        //targetWord=SortLetters(targetWord);

     /*   if(wordList.contains(targetWord))
        {
            result.add(targetWord);
        }
*/
     targetWord=SortLetters(targetWord);
      for(int i=0;i<wordList.size();i++)
      {
          String check=wordList.get(i);
          String work=SortLetters(check);
          if(work.equals(targetWord))
          {
              result.add(check);

          }
      }
        return result;
    }

    public boolean ContainsWord(String word)
    {
        for(int i=0;i<wordList.size();i++)
        {
            String check=wordList.get(i);
            String work=SortLetters(check);
            if(work.equals(word))
            {
               return true;

            }
        }

            return false;
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> resultant;
        ArrayList<String> result = new ArrayList<String>();
        for(char alphabet = 'a'; alphabet <= 'z';alphabet++) {
            StringBuilder newWord = new StringBuilder();
            newWord.append(word).append(alphabet);
            String extendedKey = SortLetters(newWord.toString());
            if(lettersWord.containsKey(extendedKey) ){
                resultant = new ArrayList<>();
                resultant = (ArrayList) lettersWord.get(extendedKey);
                for(int i = 0;i< resultant.size();i++)
                    result.add(String.valueOf(resultant.get(i)));
            }

        }

        return result;
    }

    //Done With Two
    public List<String> getAnagramsWithTwoMoreLetters(String word)
    {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> resultant;
        for(char Firstalphabet = 'a'; Firstalphabet <= 'z';Firstalphabet++) {
            StringBuilder newWord = new StringBuilder();
            newWord.append(word).append(Firstalphabet);

            for(char SecondAlphabet = 'a'; SecondAlphabet <= 'z';SecondAlphabet++) {
                StringBuilder addLetter=new StringBuilder();
                addLetter.append(newWord).append(SecondAlphabet);
                String extendedKey = SortLetters(addLetter.toString());
                if(lettersWord.containsKey(extendedKey) ){
                    resultant = new ArrayList<>();
                    resultant = (ArrayList) lettersWord.get(extendedKey);
                    for(int i = 0;i< resultant.size();i++)
                        result.add(String.valueOf(resultant.get(i)));


                }
            }

        }

        return result;
    }



    public String pickGoodStarterWord() {
        int x = MIN_NUM_ANAGRAMS+new Random().nextInt(wordList.size());
        String Word=wordList.get(x);
        if(Word.length()<=MAX_WORD_LENGTH)
        return wordList.get(x);
       else {
           return pickGoodStarterWord();
      }

      //TODO(1) For Testing Comment out above code and uncomment return Tax
           // return "tax";
    }
}
