package grammarModel.genericTools.impl;

import java.nio.file.Path;

import grammarModel.structure.ISyntacticGrove;
import grammars.seekWhence.treeGenUtils.exception.SwFileReaderException;
import grammars.seekWhence.treeGenUtils.exception.SwTreeGenException;

/**
 * IGenericFileReader allows the generation of a 'syntactic grove' out of a text file, provided it contains lists of paths 
 * of syntactic trees generated using the 'SeekWhence' context-free grammar.
 * @see ISyntacticGrove
 * @author Gael Tregouet
 *
 */
public interface IGenericFileReader {
	
	/**
	 * Writing rules to be respected in order to avoid throwing an exception : <br>
	 * 1-The text contains one or more descriptions of syntactic trees generated using the 'SeekWhence' context-free 
	 * grammar. <br>
	 * 2-Every tree description begins with a line containing only the character '/' <br>
	 * 3-Descriptions of syntactic trees take the form of the list of spanning paths a tree contains (i.e. paths 
	 * from the root element of the tree to any of its terminals). <br>
	 * 4-A path is a concatenation of symbols (i.e., strings) from the 'SeekWhence' grammar, separated by the 
	 * character '/'. New path, new line. No empty line. <br>
	 * 5-The list of paths must be generated using the following pattern : <br>
	 * a/b/c <br>
	 * a/b/d <br>
	 * a/e/f/g/h <br>
	 * a/e/f/g/i <br>
	 * a/e/f/j <br>
	 * where {a} is the start element, {b,e,f,g} are variable elements and {c,d,h,i,j} are terminals.
	 * @param textFile is a text file (UTF-8) that must respect the rules described above.
	 * @return a 'syntactic grove', i.e. a list of syntactic trees with some more functionalities. 
	 */
	ISyntacticGrove getSyntacticGrove(Path path) throws SwTreeGenException, SwFileReaderException;

}
