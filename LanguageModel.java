import java.util.HashMap;
import java.util.Random;

public class LanguageModel {

    // The map of this model.
    // Maps windows to lists of charachter data objects.
    HashMap<String, List> CharDataMap;
    
    // The window length used in this model.
    int windowLength;
    
    // The random number generator used by this model. 
	private Random randomGenerator;

    /** Constructs a language model with the given window length and a given
     *  seed value. Generating texts from this model multiple times with the 
     *  same seed value will produce the same random texts. Good for debugging. */
    public LanguageModel(int windowLength, int seed) {
        this.windowLength = windowLength;
        randomGenerator = new Random(seed);
        CharDataMap = new HashMap<String, List>();
    }

    /** Constructs a language model with the given window length.
     * Generating texts from this model multiple times will produce
     * different random texts. Good for production. */
    public LanguageModel(int windowLength) {
        this.windowLength = windowLength;
        randomGenerator = new Random();
        CharDataMap = new HashMap<String, List>();
    }

    /** Builds a language model from the text in the given file (the corpus). */
	public void train(String fileName) {
		
	}

    // Computes and sets the probabilities (p and cp fields) of all the
	// characters in the given list. */
	public void calculateProbabilities(List probs) {				
		int chrTotal = 0;
		Node current = probs.getFirstNode(); // I tried to write probs.first since the since all the fields are package private. however it didnt work. so 
		                                     // I created  Get in the List class. Hope its ok. 
		while (current != null) {
			chrTotal += current.cp.count;
			current = current.next;
                   }
			current=probs.getFirstNode();
			double cumuProab=0.0;
			while (current !=null){
				current.cp.p=(double)current.cp.count/chrTotal;
				
				cumuProab+=current.cp.p;
				current.cp.cp=cumuProab;
				current=current.next;
			}
	}

    // Returns a random character from the given probabilities list.
	public char getRandomChar(List probs) {
		double random=randomGenerator.nextDouble();
		char res=0;
		Node current=probs.getFirstNode();
		while(current !=null){
			if (random<current.cp.cp){
				res=current.cp.chr;
				break;
			}
			current=current.next;
		}
		return res;
	}

    /**
	 * Generates a random text, based on the probabilities that were learned during training. 
	 * @param initialText - text to start with. If initialText's last substring of size numberOfLetters
	 * doesn't appear as a key in Map, we generate no text and return only the initial text. 
	 * @param numberOfLetters - the size of text to generate
	 * @return the generated text
	 */
	public String generate(String initialText, int textLength) {
		String str=" ";
		return str ;
	}

    /** Returns a string representing the map of this language model. */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (String key : CharDataMap.keySet()) {
			List keyProbs = CharDataMap.get(key);
			str.append(key + " : " + keyProbs + "\n");
		}
		return str.toString();
	}

    public static void main(String[] args) {
		// Your code goes here
    }
}
