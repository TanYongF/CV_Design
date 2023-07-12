package fun.tans.tools;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private final Map<Character, TrieNode> children;
    private boolean isEndOfWord;

    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }

    public void insert(String word) {
        TrieNode node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.children.containsKey(ch)) {
                node.children.put(ch, new TrieNode());
            }
            node = node.children.get(ch);
        }
        node.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }


    /**
     * 判断是否包含特定单词
     *
     * @param str
     * @return
     */
    public String searchWord(String str) {
        TrieNode node = this;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!node.children.containsKey(ch)) {
                return null;
            }
            node = node.children.get(ch);
            if (node.isEndOfWord) return str.substring(0, i + 1);
        }
        return null;
    }

    private TrieNode searchPrefix(String prefix) {
        TrieNode node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (!node.children.containsKey(ch)) {
                return null;
            }
            node = node.children.get(ch);
        }
        return node;
    }
}