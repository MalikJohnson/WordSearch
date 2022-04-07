package wordSearch;
//Word search class stores the length and width of the wordsearch. Then stores the letters in a two dimensional array.

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class WordSearch {
	private int length; // Vertical length of wordsearch
	private int width; // Horizontal length of wordsearch
	private String wordSearchArray[][]; //Holds the array of strings the make up the wordsearch
	private String wordList[]; // The list of words to search for
	private int wordListLength; //The length of the list of words to find in the wordsearch
	private boolean searchUp; // boolean for up wordsearch search
	private boolean searchDown; //boolean for down wordsearch search
	private boolean searchLeft; //boolean for left wordsearch search
	private boolean searchRight; //boolean for right wordsearch search
	private boolean searchUpLeft; //boolean for up left wordsearch search
	private boolean searchUpRight; //boolean for up right wordsearch search
	private boolean searchDownLeft; //boolean for down left wordsearch search
	private boolean searchDownRight; //boolean for down right wordsearch search
	private Label gridArray [][]; //Array of labels to match array of strings for the wordsearch
	
	WordSearch(int columnCount, int rowCount, int wordListLength, String wordlist[], String wordArray[][])
	{
		this.setLength(columnCount);
		this.setWidth(rowCount);
		this.setWordListLength(wordListLength);
		wordList = new String[100];
		wordList = wordlist;
		wordSearchArray = new String[100][100];
		wordSearchArray = wordArray;
		gridArray = new Label[100][100];
		this.stringToLabel();
	}
	
	//Sets the vertical length of the wordsearch array
	public void setLength(int gridLength)
	{
		this.length = gridLength;
	}
	
	//Gets the vertical length of the wordsearch array
	public int getLength()
	{
		return this.length;
	}
	
	//Sets the horizontal width of the wordsearch array
	public void setWidth(int gridWidth)
	{
		this.width = gridWidth;
	}
	
	//Returns the horizontal width of the wordsearch array
	public int getWidth()
	{
		return this.width;
	}
	
	//Sets the length of the list of words to be searched
	public void setWordListLength(int wordsToSearch)
	{
		this.wordListLength = wordsToSearch;
	}
	
	//Gets the length of the list of words to be searched
	public int getWordListLength()
	{
		return this.wordListLength;
	}
	
	//Gets the list of words to be searched
	public String[] getWordList()
	{
		return wordList;
	}
	
	//Sets the list of words to be searched
	public void setWordList(String [] words)
	{
		this.wordList = words;
	}
	
	//Returns the two dimensional wordsearch array
	public String [][] getWordSearch()
	{
		return wordSearchArray;
				
	}
	
	//Sets the wordsearch array to a two dimensional array
	public void setWordSearch(String [][] gridArray)
	{
		this.wordSearchArray = gridArray;
	}
	
	//Prints the elements of the wordsearch array
	public void printWordSearch()
	{
		for(int a=0; a<length; a++)
		{
			for(int b=0; b<width; b++)
			{
				System.out.print(wordSearchArray[a][b]);
			}
			System.out.println();
		}
	}
	//Copies the string wordsearch array into the label array to be displayed by gui
	public void stringToLabel()
	{
		for(int a=0; a<length; a++)
		{
			for(int b=0; b<width; b++)
			{
				gridArray[a][b] = new Label();
				gridArray[a][b].setText(wordSearchArray[a][b]);
			}
		}
	}
	
	//Returns the two dimensional array of labels.
	public Label [][] getGridLabel()
	{
		return this.gridArray;
	}
	
	//Finds the words in the wordsearch array. First it finds the first letter of a word being searched. Then it checks all 8 possible directions. If a word was found in a direction. It then highlights the word from that direction.
	public void findWord(String word)
	{
		for(int a=0; a<length; a++)
		{
			for(int b=0; b<width; b++)
			{
				String firstLetter = word.substring(0, 1);
				if(wordSearchArray[a][b].equals(firstLetter))
				{
					if(horizontalCheckRight(wordSearchArray, a, b, "", word)==true)
					{
						highlightWordRight(a, b, word.length());
					}
					if(horizontalCheckLeft(wordSearchArray, a, b, "", word)==true)
					{
						highlightWordLeft(a, b, word.length());
					}
					if(verticalCheckUp(wordSearchArray, a, b, "", word)==true)
					{
						highlightWordUp(a, b, word.length());
					}
					if(verticalCheckDown(wordSearchArray, a, b, "", word)==true)
					{
						highlightWordDown(a, b, word.length());
					}
					
					//Diagnol checks
					if(diagnolCheckDownRight(wordSearchArray, a, b, "", word)==true)
					{
						highlightWordDiagnolDownRight(a, b, word.length());
					}
					if(diagnolCheckDownLeft(wordSearchArray, a, b, "", word)==true)
					{
						highlightWordDiagnolDownLeft(a, b, word.length());
					}
					if(diagnolCheckUpRight(wordSearchArray, a, b, "", word)==true)
					{
						highlightWordDiagnolUpRight(a, b, word.length());
					}
					if(diagnolCheckUpLeft(wordSearchArray, a, b, "", word)==true)
					{
						highlightWordDiagnolUpLeft(a, b, word.length());
					}	
				}
			}
		}
	}
	
	//Loops through all words in the wordlist array. Highlights each word in red.
	public void findAllWords()
	{
		for(int a=0; a<this.getWordListLength(); a++)
		{
			findWord(wordList[a]);
		}
	}
	
	
	//Horizontal check right
	public boolean horizontalCheckRight(String wordSearch[][], int row, int col, String wordBuilder, String targetWord)
    {
		
		while(col<this.width)
		{
			if(wordBuilder.equals(targetWord)){
				return true;}
			
			wordBuilder = wordBuilder + wordSearch[row][col];
			return horizontalCheckRight(wordSearch, row, col+1, wordBuilder, targetWord);
		}
		return false;
    }
	
	//Horizontal check left
	public boolean horizontalCheckLeft(String wordSearch[][], int row, int col, String wordBuilder, String targetWord)
    {
		
		while(col>0)
		{
			if(wordBuilder.equals(targetWord)){
				return true;}
			
			wordBuilder = wordBuilder + wordSearch[row][col];
			return horizontalCheckLeft(wordSearch, row, col-1, wordBuilder, targetWord);
		}
		return false;
    }
	
	//Vertical check down
	public boolean verticalCheckDown(String wordSearch[][], int row, int col, String wordBuilder, String targetWord)
    {
		
		while(row<this.length)
		{
			if(wordBuilder.equals(targetWord)){
				return true;}
			
			wordBuilder = wordBuilder + wordSearch[row][col];
			return verticalCheckDown(wordSearch, row+1, col, wordBuilder, targetWord);
		}
		return false;
    }
	//Vertical check up
	public boolean verticalCheckUp(String wordSearch[][], int row, int col, String wordBuilder, String targetWord)
    {
		
		while(row>0)
		{
			if(wordBuilder.equals(targetWord)){
				return true;}
			
			wordBuilder = wordBuilder + wordSearch[row][col];
			return verticalCheckUp(wordSearch, row-1, col, wordBuilder, targetWord);
		}
		return false;
    }
	
	//Diagonal check up right
	
	public boolean diagnolCheckUpRight(String wordSearch[][], int row, int col, String wordBuilder, String targetWord)
    {
		
		while(row>0&&col<this.width)
		{
			if(wordBuilder.equals(targetWord)){
				return true;}
			
			wordBuilder = wordBuilder + wordSearch[row][col];
			return diagnolCheckUpRight(wordSearch, row-1, col+1, wordBuilder, targetWord);
		}
		return false;
    }
    
	//Diagonal check up left
		public boolean diagnolCheckUpLeft(String wordSearch[][], int row, int col, String wordBuilder, String targetWord)
	    {
			
			while(row>0&&col>0)
			{
				if(wordBuilder.equals(targetWord)){
					return true;}
				
				wordBuilder = wordBuilder + wordSearch[row][col];
				return diagnolCheckUpLeft(wordSearch, row-1, col-1, wordBuilder, targetWord);
			}
			return false;
	    }
	
	
	//Diagonal check down right
		public boolean diagnolCheckDownRight(String wordSearch[][], int row, int col, String wordBuilder, String targetWord)
	    {
			
			while(row<this.length&&col<this.width)
			{
				if(wordBuilder.equals(targetWord)){
					return true;}
				
				wordBuilder = wordBuilder + wordSearch[row][col];
				return diagnolCheckDownRight(wordSearch, row+1, col+1, wordBuilder, targetWord);
			}
			return false;
	    }
		
	//Diagonal check down left
				public boolean diagnolCheckDownLeft(String wordSearch[][], int row, int col, String wordBuilder, String targetWord)
			    {
					
					while(row<this.length&&col>0)
					{
						if(wordBuilder.equals(targetWord)){
							return true;}
						
						wordBuilder = wordBuilder + wordSearch[row][col];
						return diagnolCheckDownLeft(wordSearch, row+1, col-1, wordBuilder, targetWord);
					}
					return false;
			    }
				
				
				//Highlights the word going up vertically
				public void highlightWordUp(int row, int column, int wordLength)
				{
					for(int a=0; a<wordLength; a++)
					{
						this.getGridLabel()[row-a][column].setTextFill(Color.web("#ff0000", 0.8));
					}
				}
				//Highlights the word going down vertically
				public void highlightWordDown(int row, int column, int wordLength)
				{
					for(int a=0; a<wordLength; a++)
					{
						this.getGridLabel()[row+a][column].setTextFill(Color.web("#ff0000", 0.8));
					}
				}
				//Highlights the word going left horizontally
				public void highlightWordLeft(int row, int column, int wordLength)
				{
					for(int a=0; a<wordLength; a++)
					{
						this.getGridLabel()[row][column-a].setTextFill(Color.web("#ff0000", 0.8));
					}
				}
				//Highlights the word going right horizontally
				public void highlightWordRight(int row, int column, int wordLength)
				{
					for(int a=0; a<wordLength; a++)
					{
						this.getGridLabel()[row][column+a].setTextFill(Color.web("#ff0000", 0.8));
					}
				}
				//Highlights the word going up right diagonally
				public void highlightWordDiagnolUpRight(int row, int column, int wordLength)
				{
					for(int a=0; a<wordLength; a++)
					{
						this.getGridLabel()[row-a][column+a].setTextFill(Color.web("#ff0000", 0.8));
					}
				}
				//Highlights the word going up left diagonally
				public void highlightWordDiagnolUpLeft(int row, int column, int wordLength)
				{
					for(int a=0; a<wordLength; a++)
					{
						this.getGridLabel()[row-a][column-a].setTextFill(Color.web("#ff0000", 0.8));
					}
				}
				//Highlights the word going down right diagonally
				public void highlightWordDiagnolDownRight(int row, int column, int wordLength)
				{
					for(int a=0; a<wordLength; a++)
					{
						this.getGridLabel()[row+a][column+a].setTextFill(Color.web("#ff0000", 0.8));
					}
				}
				//Highlights the word going down left diagonally
				public void highlightWordDiagnolDownLeft(int row, int column, int wordLength)
				{
					for(int a=0; a<wordLength; a++)
					{
						this.getGridLabel()[row+a][column-a].setTextFill(Color.web("#ff0000", 0.8));
					}
				}
	//Prints the length of the row - vertically. column - horizontally, length of the list of words to search for, and the words to be searched for
	public String toString()
	{
		String test="";
		
		for(int a=0; a<wordListLength; a++)
		{
			test = test + wordList[a]+" ";
		}
		return "rows " + length + " columns " + width + " wordCount " + wordListLength + " Words " + test;
	}
	
	
}
