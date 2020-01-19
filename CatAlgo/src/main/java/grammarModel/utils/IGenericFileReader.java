package grammarModel.utils;

import java.nio.file.Path;

import grammarModel.exceptions.FileReaderException;
import grammarModel.structure.ISyntaxGrove;

/**
 * IGenericFileReader allows the generation of a {@link ISyntaxGrove} out of a text file, provided it contains lists of 
 * paths of syntax trees generated using a context-free grammar.
 * @see ISyntaxGrove
 * @author Gael Tregouet
 *
 */
public interface IGenericFileReader {
	
	/**
	 * Generates a 'syntax grove' ({@link ISyntaxGrove}) out of a {@link Path} parameter pointing to a text file.
	 * Writing rules to be respected in order to avoid throwing an exception : <br>
	 * 1-The text contains one or more descriptions of syntax trees generated using a context-free 
	 * grammar. <br>
	 * 2-Every tree description begins with a line containing only the character '/' <br>
	 * 3-Descriptions of syntax trees take the form of the list of spanning paths a tree contains (i.e. paths 
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
	 * @param path points to a text file (UTF-8) that must respect the rules described above.
	 * @return a 'syntax grove', i.e. a list of syntax trees with some more functionalities. 
	 */
	ISyntaxGrove getSyntacticGrove(Path path) throws FileReaderException;

}
