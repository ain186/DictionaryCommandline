package Directory;


import java.util.List;
import java.util.Scanner;

public class DictionaryCommandline {
  DictionaryManagement dictionaryManagement = new DictionaryManagement();
  private List<Word> listWord;

  public void showAllWord() {
    listWord = dictionaryManagement.getDictionary().getWordArray();
    System.out.println("No      | English           | Vietnamese            ");
    for (int i = 0; i < listWord.size(); i++) {
      System.out.format("%-10d%-20s%-20s%n", i + 1, listWord.get(i).word_target, listWord.get(i).word_explain);
    }
  }

  public void dictionaryAdvance() {
    Scanner sc = new Scanner(System.in);
    Word newWord;
//             ************All Word************
    dictionaryManagement.insertFromFile();
    this.showAllWord();
//             ************Search Word***********
    System.out.print("Nhap tu can tim kiem: ");
    String tu1 = sc.nextLine();

    dictionaryManagement.dictionaryLookup(tu1);
//             ************Delete Word***********
    System.out.print("Nhap tu can xoa: ");
    String tu2 = sc.nextLine();
    dictionaryManagement.removeWord(tu2);
    this.showAllWord();
//             ************Add Word************
    System.out.print("Nhap tu can them tieng anh: ");
    String viWord = sc.nextLine();
    System.out.print("Nhap tu can them tieng viet: ");
    String enWord = sc.nextLine();
    newWord = new Word(viWord, enWord);
    dictionaryManagement.addWord(newWord);
    this.showAllWord();
//             ************Edit Word***********
    System.out.print("Nhap tu can sua nghia: ");
    String word = sc.nextLine();
    System.out.print("Nhap nghia moi: ");
    String newMean = sc.nextLine();
    dictionaryManagement.editWord(word, newMean);
    this.showAllWord();
    //             ************Search Word***********


    System.out.print("Nhap van can tra cuu: ");
    String tu3 = sc.nextLine();
    System.out.print("Ket qua: \n");

    System.out.println("No      | English           | Vietnamese            ");
    for (int i = 0; i < dictionaryManagement.dictionarySearcher(tu3).size(); i++) {
      System.out.format("%-10d%-20s%-20s%n", i + 1, dictionaryManagement.dictionarySearcher(tu3).get(i).word_target, dictionaryManagement.dictionarySearcher(tu3).get(i).word_explain);
    }
    // *******************Export File*******************
    dictionaryManagement.dictionaryExportToFile();
  }

}
