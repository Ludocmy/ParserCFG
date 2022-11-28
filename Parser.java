import computation.contextfreegrammar.*;
import computation.parser.*;
import computation.parsetree.*;
import computation.derivation.*;
import computation.contextfreegrammar.Rule;

import java.util.ArrayList;

public class Parser implements IParser {

  public boolean isInLanguage(ContextFreeGrammar cfg, Word w){
    Derivation valid_d = generateDerivations(cfg, w.length(), w);
    return valid_d.getLatestWord().toString().equals(w.toString());
  }

  public Derivation generateDerivations(ContextFreeGrammar cfg, int length, Word input_w){
    ArrayList<Word> words = new ArrayList<>();
    ArrayList<Word> new_words = new ArrayList<>();
    int steps = 0;
    int index = 0;
    Word new_word;
    Derivation d = new Derivation(new Word(cfg.getStartVariable()));
    words.add(d.getLatestWord());
//    if (length == 0){
//      length = 1;
//    }
    while (steps < 2*length - 1) {
      for (Word word : words) {
        for (Symbol s : word){
          if (word.get(index).isTerminal()){
            index += 1;
          }
          else{
            Symbol var = word.get(index);
            for (Rule r : cfg.getRules()) {
              if (r.getVariable() == var) {
                new_word = word.replace(index, r.getExpansion());
                new_words.add(new_word);
                d.addStep(new_word, r, index);
              }
            }
          }
        }
        index = 0;
      }
      words.clear();
      words.addAll(new_words);
      new_words.clear();
      steps += 1;
    }
    for (Word w : words) {
      if (new_words.contains(w)) {
        // skip word
      } else {
        int count = 0;
        for (Symbol s : w) {
          if (s.isTerminal()) {
            count += 1;
            if (count == w.length()) {
              new_words.add(w);
            }
          }
        }
      }
    }

    Derivation new_d = findValidDerivation(cfg, input_w, d);

    Derivation valid_d = new Derivation(new Word(cfg.getStartVariable()));
    for (Step s : new_d) {
      if (s.getRule() == null) {
        // skip
      }
      else {
        valid_d.addStep(s.getWord(), s.getRule(), s.getIndex());
      }
    }
    return valid_d;
  }

  public Derivation findValidDerivation(ContextFreeGrammar cfg, Word input_w, Derivation d){
    Derivation new_d = new Derivation(new Word(cfg.getStartVariable()));

    for (Step s : d){
      if (s.getWord().equals(input_w)){
        new_d.addStep(s.getWord(), s.getRule(), s.getIndex());
        if (s.getRule().getExpansion().length() == 2 && s.getRule().getExpansion().toString().equals(input_w.subword(s.getIndex(), s.getIndex() + 2).toString())){
          input_w = input_w.replace(s.getIndex(), new Word(s.getRule().getVariable()));
          if (!input_w.get(s.getIndex()+1).toString().equals("")) {
            input_w = input_w.replace(s.getIndex() + 1, new Word(""));
          }
        }
        else{
          input_w = input_w.replace(s.getIndex(), new Word(s.getRule().getVariable()));
        }
        if (input_w.get(0).equals(cfg.getStartVariable())){
          break;
        }
      }
    }
    return new_d;
  }

  public ParseTreeNode generateParseTree(ContextFreeGrammar cfg, Word input_w) {
    Derivation valid_d = generateDerivations(cfg, input_w.length(), input_w);
    if (!isInLanguage(cfg, input_w)){
      System.out.println("Word not in language, parse tree :");
      return null;
    }
    ArrayList<ParseTreeNode> tree = new ArrayList<>();
    ArrayList<ParseTreeNode> new_tree = new ArrayList<>();
    for (Symbol symb : input_w){
      tree.add(new ParseTreeNode(symb));
    }

    for (Step s : valid_d){
      new_tree.addAll(tree);
      if (s.getRule() == null){
        break;
      }
      if (s.getRule().getExpansion().length() == 2) {
        ArrayList<ParseTreeNode> child_tree = new ArrayList<>();
        int index = 0;
        while (index < 2) {
          for (ParseTreeNode node : tree) {
            if (s.getRule().getExpansion().get(index).equals(node.getSymbol())) {
              if (!child_tree.contains(node)) {
                child_tree.add(node);
              }
            }
          }
          index += 1;
        }
        new_tree.add(s.getIndex(), new ParseTreeNode(s.getRule().getVariable(), child_tree.get(0), child_tree.get(1)));
        new_tree.remove(s.getIndex() + 1);
        new_tree.remove(s.getIndex() + 1);
        child_tree.clear();
      }
      else if (s.getRule().getExpansion().isTerminal()){
        for (ParseTreeNode node : tree){
          if (node.getSymbol().toString().equals(s.getRule().getExpansion().toString())){
            new_tree.add(s.getIndex(), new ParseTreeNode(s.getRule().getVariable(), node));
            new_tree.remove(s.getIndex() + 1);
          }
        }
      }
      tree.clear();
      tree.addAll(new_tree);
      new_tree.clear();
    }
    // Prints the valid Derivation
//    for ( Step s : valid_d) {
//      System.out.println(s);
//    }
    return tree.get(0);
  }
}
