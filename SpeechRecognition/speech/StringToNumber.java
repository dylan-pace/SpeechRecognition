package speech;

/**
 * Class that converts the strings to numbers.
 * @author DylanPace
 *
 */
public class StringToNumber {

	/**
	 * English Word Representation to integer
	 * 
	 * @param word
	 * @return
	 */
	public int convert(String word) {
		int wordnum = 0;
		String[] arrinwords = word.split(" ");
		int arrinwordsLength = arrinwords.length;
		//If the value has no numbers and is empty.
		if ("zero".equals(word))
			return 0;
		//Checks to see what the strings contains.
		if (word.contains("thousand")) {
			int indexofthousand = word.indexOf("thousand");
			// System.out.println(indexofthousand)
			String beforethousand = word.substring(0, indexofthousand);
			// System.out.println(beforethousand)
			String[] arrbeforethousand = beforethousand.split(" ");
			int arrbeforethousandLength = arrbeforethousand.length;
			// System.out.println(arrbeforethousandLength)
			if (arrbeforethousandLength == 2) {
				wordnum = wordnum + 1000 * (wordToNumber(arrbeforethousand[0]) + wordToNumber(arrbeforethousand[1]));
				// System.out.println(wordnum)
			}
			if (arrbeforethousandLength == 1) {
				wordnum = wordnum + 1000 * (wordToNumber(arrbeforethousand[0]));
				// System.out.println(wordnum)
			}

		}
		//Contains the word 'hundred' the program will convert it to its number equivalent.
		if (word.contains("hundred")) {
			int indexofhundred = word.indexOf("hundred");
			// System.out.println(indexofhundred)
			String beforehundred = word.substring(0, indexofhundred);

			// System.out.println(beforehundred)
			String[] arrbeforehundred = beforehundred.split(" ");
			int arrbeforehundredLength = arrbeforehundred.length;
			wordnum = wordnum + 100 * (wordToNumber(arrbeforehundred[arrbeforehundredLength - 1]));
			String afterhundred = word.substring(indexofhundred + 8);
		
														
			// System.out.println(afterhundred)
			String[] arrafterhundred = afterhundred.split(" ");
			int arrafterhundredLength = arrafterhundred.length;
			if (arrafterhundredLength == 1) {
				wordnum = wordnum + (wordToNumber(arrafterhundred[0]));
			}
			if (arrafterhundredLength == 2) {
				wordnum = wordnum + (wordToNumber(arrafterhundred[1]) + wordToNumber(arrafterhundred[0]));
			}
			// System.out.println(wordnum)

		}
		//If the word contains 'thousand' and not 'hundred' the program will convert it to a number.
		if (!word.contains("thousand") && !word.contains("hundred")) {
			if (arrinwordsLength == 1) {
				wordnum = wordnum + (wordToNumber(arrinwords[0]));
			}
			if (arrinwordsLength == 2) {
				wordnum = wordnum + (wordToNumber(arrinwords[1]) + wordToNumber(arrinwords[0]));
			}
			// System.out.println(wordnum)
		}

		return wordnum;
	}

	/**
	 * Changes the words to numbers depending on their value.
	 * @param word
	 * @return
	 */
	private int wordToNumber(String word) {
		int num = 0;
		//Converts the string to numbers.
		switch (word) {
		case "one":
			num = 1;
			break;
		case "two":
			num = 2;
			break;
		case "three":
			num = 3;
			break;
		case "four":
			num = 4;
			break;
		case "five":
			num = 5;
			break;
		case "six":
			num = 6;
			break;
		case "seven":
			num = 7;
			break;
		case "eight":
			num = 8;
			break;
		case "nine":
			num = 9;
			break;
		case "ten":
			num = 10;
			break;
		case "eleven":
			num = 11;
			break;
		case "twelve":
			num = 12;
			break;
		case "thirteen":
			num = 13;
			break;
		case "fourteen":
			num = 14;
			break;
		case "fifteen":
			num = 15;
			break;
		case "sixteen":
			num = 16;
			break;
		case "seventeen":
			num = 17;
			break;
		case "eighteen":
			num = 18;
			break;
		case "nineteen":
			num = 19;
			break;
		case "twenty":
			num = 20;
			break;
		case "thirty":
			num = 30;
			break;
		case "forty":
			num = 40;
			break;
		case "fifty":
			num = 50;
			break;
		case "sixty":
			num = 60;
			break;
		case "seventy":
			num = 70;
			break;
		case "eighty":
			num = 80;
			break;
		case "ninety":
			num = 90;
			break;
		case "hundred":
			num = 100;
			break;
		case "thousand":
			num = 1000;
			break;
		/*
		 * default: num = "Invalid month"; break
		 */
		}
		return num;
	}
}