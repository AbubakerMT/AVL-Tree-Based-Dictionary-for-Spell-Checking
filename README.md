# AVL Tree-Based Dictionary for Spell Checking (ICS202 Lab Project)  
A Java-implemented dictionary using an **AVL Tree** to efficiently store and manage words for spell-checking.  

### Key Features  
- **Core Operations:** Add, delete, search, and find similar words (differing by 1 character).  
- **Initialization Modes:** Create from a file, a single word, or an empty dictionary.  
- **Error Handling:** Custom exceptions (`WordAlreadyExistsException`, `WordNotFoundException`).  
- **File I/O:** Save/load dictionaries to/from `.txt` files.  
- **Efficiency:** Optimized operations with time complexities ranging from **O(log n)** to **O(n)**.  

---

## 🛠 Technologies  
- **Java**  
- **AVL Tree** (self-balancing BST)  
- **File Handling** (`Scanner`, `PrintWriter`)  

---

## 📂 Code Structure  
| File               | Description                                                                 |  
|--------------------|-----------------------------------------------------------------------------|  
| `Dictionary.java`  | Main class with methods for dictionary operations (add, delete, similarity checks). |  
| `BSTNode.java`     | Generic node class for the AVL Tree.                                        |  
| `mainProgram.java` | CLI for interactive testing of dictionary methods.                         |  
| `efficiency.txt`   | Documents time complexities of all operations.                             |  

### Key Methods in `Dictionary.java`  
- `isSimilar()`: Checks if two words differ by exactly 1 character (supports length differences).  
- `findSimilar()`: Recursively traverses the AVL Tree to collect similar words.  

---

## ⚡ Time Complexity  
| **Operation**       | **Complexity**       |  
|----------------------|-----------------------|  
| Add/Delete Word      | `O(log n)`           |  
| Search Word          | `O(log n)`           |  
| Find Similar Words   | `O(n * m)`           |  
| Save/Load Dictionary | `O(n)`               |  

---
## 🧪 Testing & Validation  
### Test Cases  
- **Invalid Input Handling:**  
  - Added words with special characters/numbers → rejected.  
  - Deleted non-existent words → `WordNotFoundException` thrown.  
- **Edge Cases:**  
  - Searched for words differing by 1 character (e.g., "puinter" vs. "printer").  
  - Saved dictionaries with illegal filenames → error prompts.  
- **Functionality Checks:**  
  - Verified AVL Tree balancing after multiple insertions/deletions.  
  - Confirmed file I/O consistency (loaded vs. saved dictionaries).  

### Screenshots  
Creating a dictionary with a file.<br>
<img src="https://media.discordapp.net/attachments/1339268473108299819/1339307004627255357/image.png?ex=67ae3e59&is=67acecd9&hm=772ae39852d3958a056b31c392aaca59c0571be277389062189bc457b67ca45f&=&format=webp&quality=lossless" />
<br>Adding a new word.<br>
<img src="https://media.discordapp.net/attachments/1339268473108299819/1339307144234795029/image.png?ex=67ae3e7a&is=67acecfa&hm=27d28153cfda07b2c0c4895181f8d92196b197c6a35173f57a47a4a0107d8e4f&=&format=webp&quality=lossless" />
<br>An attempt to delete a word that contains a special character.<br>
<img src="https://media.discordapp.net/attachments/1339268473108299819/1339307412548616202/image.png?ex=67ae3eba&is=67aced3a&hm=9a99e289455fa2a9291c8be7b7cf32213fcb1b51077511d602594c8240f927ef&=&format=webp&quality=lossless" />
<br>Find words similar to a one that exists in the dictionary.<br>
<img src="https://media.discordapp.net/attachments/1339268473108299819/1339307554463023134/image.png?ex=67ae3edc&is=67aced5c&hm=7862e193ad520bbcbc2a107a02fad1670b30d43171febe84d1a742311bc8201a&=&format=webp&quality=lossless" />
<br>Search for a word that doesn’t exist in the dictionary.<br>
<img src="https://cdn.discordapp.com/attachments/1339268473108299819/1339307710318903306/image.png?ex=67ae3f01&is=67aced81&hm=9e9dc3f120136fe81f8875baeeca39e1959a4ea93025096acc812a9403e5315e&" />
<br>Exiting and saving the dictionary that contains an invalid character.<br>
<img src="https://media.discordapp.net/attachments/1339268473108299819/1339307877160058951/image.png?ex=67ae3f29&is=67aceda9&hm=98aa372d81c12bcdf4b556e8c8d77d4732cf45415cca87350cc610345fe5731a&=&format=webp&quality=lossless" />
