//Java Solution

class TrieNode {
    HashMap<Character, TrieNode> children;
    String word = null;
      
    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
    }
  }
  
  class Solution {
      public List<String> findWords(char[][] board, String[] words) {
          TrieNode root = new TrieNode();
          for (String word : words) {
              TrieNode node = root;
              
              for (Character letter : word.toCharArray()) {
                  if (!node.children.containsKey(letter)) {
                      TrieNode newNode = new TrieNode();
                      node.children.put(letter, newNode);
                      node = newNode;
                  } else {
                      node = node.children.get(letter);
                  }
              }
              node.word = word;
          }
          
          List<String> result = new ArrayList<>();
          
          for (int i = 0; i < board.length; i++) {
              for (int j = 0; j < board[0].length; j++) {
                  dfs(i, j, root, board, result);
              }
          }
          
          return result;
      }
      
      public void dfs(int i, int j, TrieNode root, char[][] board, List result) {
          if (root.word != null) {
              result.add(root.word);
              root.word = null;
          }
          
          if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || !root.children.containsKey(board[i][j])) {
              return;
          }
          
          char temp = board[i][j];
          board[i][j] = '#';
          
          dfs(i + 1, j, root.children.get(temp), board, result);
          dfs(i - 1, j, root.children.get(temp), board, result);
          dfs(i, j + 1, root.children.get(temp), board, result);
          dfs(i, j - 1, root.children.get(temp), board, result);
          
          board[i][j] = temp;
      }
  }