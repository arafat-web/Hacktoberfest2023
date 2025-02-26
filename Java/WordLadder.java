/*

    Write a Java function to determine the length of the shortest transformation sequence from startWord to endWord, such that only one letter can be changed at a time and each transformed word must exist in the word list.
    We will use a breadth-first search (BFS) approach to explore all possible transformations. We will start from the startWord and will change one character at a time to find words in the wordList. When we find the endWord, we will return the length of the transformation sequence. If no transformation sequence is found, the function will return 0.

*/

import java.util.*;

public class WordLadder {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int ladderLength = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                char[] wordChars = currentWord.toCharArray();
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) {
                            continue;
                        }
                        wordChars[j] = c;
                        String newWord = new String(wordChars);
                        if (newWord.equals(endWord)) {
                            return ladderLength + 1;
                        }
                        if (wordSet.contains(newWord)) {
                            queue.offer(newWord);
                            wordSet.remove(newWord);
                        }
                    }
                    wordChars[j] = originalChar;
                }
            }
            ladderLength++;
        }

        return 0;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(ladderLength(beginWord, endWord, wordList)); // Output: 5
    }
}
