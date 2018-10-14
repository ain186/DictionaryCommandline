package Directory;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DictionaryManagement {
  Dictionary dictionary = new Dictionary();


  public void insertFromCommandline() {
    int temp = 0;
    String word_target;
    String word_explain;
    Scanner sc = new Scanner(System.in);
    System.out.println("Moi ban nhap so luong tu: ");
    temp = sc.nextInt();
    sc.nextLine();
    for (int i = 0; i < temp; i++) {
      System.out.println("Moi ban nhap tu vung: ");
      word_target = sc.nextLine();
      System.out.println("Moi ban nhap giai nghia: ");
      word_explain = sc.nextLine();
      Word newWord = new Word(word_target, word_explain);
      dictionary.addWordElement(newWord);
    }
    sc.close();
  }

  public void insertFromFile() {
    File text = new File("E:\\Study\\Java\\Commandline\\src\\Directory\\data.txt");
//    File text1 = new File("")
    Scanner scanner;
    try {
      scanner = new Scanner(text);
      while (scanner.hasNextLine()) {
        String word_target;
        String word_explain;
        String line = scanner.nextLine();
        String[] parts = line.split("\t");
        word_target = parts[0];
        word_explain = parts[1];
        Word newWord = new Word(word_target, word_explain);
        dictionary.addWordElement(newWord);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public void dictionaryLookup(String tu) {
    List<Word> listWord = dictionary.getWordArray();
    for (int i = 0; i < listWord.size(); i++) {
      if (listWord.get(i).word_target.trim().equals(tu)) {
        System.out.format("Nghia cua tu can tim kiem la: %s%n", listWord.get(i).word_explain);
      }
    }
  }

  public void removeWord(String tu) {
    List<Word> listWord = dictionary.getWordArray();
    for (int i = 0; i < listWord.size(); i++) {
      if (listWord.get(i).word_target.trim().equals(tu)) {
        dictionary.removeWordElement(i);
      }
    }
    FileOutputStream fout = null;
    try {
      fout = new FileOutputStream("E:\\Study\\Java\\Commandline\\src\\Directory\\data.txt");
      for (int i = 0; i < listWord.size(); i++) {
        String s = listWord.get(i).word_target.trim() + "\t" + listWord.get(i).word_explain.trim() + "\n";
        byte b[] = s.getBytes();
        fout.write(b);
      }
      fout.close();
      System.out.println("success...");
    } catch (Exception e) {
      System.out.println(e);
    } finally {

    }
  }

  public void addWord(Word word) {
    List<Word> listWord = dictionary.getWordArray();
    dictionary.addWordElement(word);


    FileOutputStream fout = null;
    try {
      fout = new FileOutputStream("E:\\Study\\Java\\Commandline\\src\\Directory\\data.txt");
      for (int i = 0; i < listWord.size(); i++) {
        String s = listWord.get(i).word_target.trim() + "\t" + listWord.get(i).word_explain.trim() + "\n";
        byte b[] = s.getBytes();
        fout.write(b);
      }
      fout.close();
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      // close file output stream
    }
  }

  public void editWord(String work, String newMean) {
    List<Word> listWord = dictionary.getWordArray();
    for (int i = 0; i < listWord.size(); i++) {
      if (listWord.get(i).word_target.trim().equals(work)) {
        listWord.get(i).setWord_explain(newMean);
      }
    }
    FileOutputStream fout = null;
    try {
      fout = new FileOutputStream("E:\\Study\\Java\\Commandline\\src\\Directory\\data.txt");
      for (int i = 0; i < listWord.size(); i++) {
        String s = listWord.get(i).word_target.trim() + "\t" + listWord.get(i).word_explain.trim() + "\n";
        byte b[] = s.getBytes();
        fout.write(b);
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {

    }
  }


  public List<Word> dictionarySearcher(String search) {
    List<Word> listSearch = new ArrayList<>();
    int temp = dictionary.getWordArray().size();
    for(int i = 0; i < temp; i++) {
      Word word = dictionary.getWordElement(i);
      if(word.getWord_target().indexOf(search) == 0) {
        listSearch.add(word);
      }
    }
    return listSearch;
  }

  public void dictionaryExportToFile() {
    List<String> lines = new ArrayList<>();
    int soLuong = dictionary.getWordArray().size();
    for(int i = 0; i < soLuong; i++) {
      Word word = dictionary.getWordElement(i);
      String wordSet = String.format("%s\t%s", word.getWord_target(),word.getWord_explain());
      lines.add(wordSet);
    }

    Path file = Paths.get("export_dictionary.txt");
    try {
      Files.write(file, lines, Charset.forName("UTF-8"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public Dictionary getDictionary() {
    return dictionary;
  }

  public void setDictionary(Dictionary dictionary) {
    this.dictionary = dictionary;
  }
}
