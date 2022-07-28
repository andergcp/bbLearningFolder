
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for(String word: myWords){
            ret = ret + word + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if(this.length() != other.length()){
            return false;
        }
        for(int i=0; i < myWords.length; i++){
            if(! myWords[i].equals(other.wordAt(i))){
                return false;
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word) { 
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        String[] newWords = new String[myWords.length];
        for(int i=1; i < myWords.length; i++){
            newWords[i-1] = myWords[i];
        }
        newWords[myWords.length - 1] = word;
        out = new WordGram(newWords, 0, newWords.length);
        return out;
    }

    public int hashCode(){
        String text = this.toString();
        return text.hashCode();
    }
}