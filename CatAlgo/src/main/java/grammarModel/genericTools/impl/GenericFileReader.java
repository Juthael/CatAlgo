package grammarModel.genericTools.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import grammarModel.exceptions.FileReaderException;
import grammarModel.genericTools.IGenericFileReader;
import grammarModel.structure.ISyntacticBranch;
import grammarModel.structure.ISyntacticGrove;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticGrove;

/**
 * GenericFileReader is an abstract implementation of IGenericFileReader. Its single abstract 'protected' method is 
 * the only one that depends on the context-free grammar at use. 
 * @author Gael Tregouet
 *
 */
public abstract class GenericFileReader implements IGenericFileReader {

	protected String ctxtName = "context";
	protected String[][][] treeDescriptions;
	protected ISyntacticStructure[][][] structures;
	
	public GenericFileReader() {
	}

	@Override
	public ISyntacticGrove getSyntacticGrove(Path path) throws FileReaderException {
		List<ISyntacticStructure> trees = new ArrayList<ISyntacticStructure>();
		treeDescriptions = setDescriptions(path);
		structures = setStructures(treeDescriptions);
		for (int treeIndex=0 ; treeIndex < treeDescriptions.length ; treeIndex++)
			trees.add(buildTree(treeIndex));
		return new SyntacticGrove(ctxtName, trees);
	}
	
	/**
	 * Called in the constructor to instantiate 'treeDescriptions', which contains the paths of all the trees in the grove.
	 * @param path Path pointing to a text file (UTF-8) containing a list of paths (one list per tree)
	 * @return String[][][] an array of paths formed as follows : [treeIndex][pathIndex][nodeIndex]
	 * @throws FileReaderException
	 * @see IGenericFileReader
	 */
	private String[][][] setDescriptions(Path path) throws FileReaderException {
		String[][][] treeDescripts = new String[getNumberOfTrees(path)][][];
		int treeDescriptsIndex = 0;
		BufferedReader reader;
		try {
			reader = Files.newBufferedReader(path);
		}
		catch (Exception e) {
			throw new FileReaderException("GenericFileReader.setDescriptions() : BufferedReader couldn't be instantiated."
					+ System.lineSeparator() + e.getMessage());
		}
		String line;
		String[][] treeDescript;
		List<String> currentTreePaths = new ArrayList<String>(); 
		do {
			try {
				line = reader.readLine();
			}
			catch (IOException e) {
				throw new FileReaderException("GenericFileReader.setDescriptions() : IOException thrown."
						+ System.lineSeparator() + e.getMessage());
			}
			if (line != null && !line.equals("/")) {
				currentTreePaths.add(line);
			}
			else {
				if (!currentTreePaths.isEmpty()) {
					treeDescript = new String[currentTreePaths.size()][];
					for (int i=0 ; i < currentTreePaths.size() ; i++) {
						treeDescript[i] = currentTreePaths.get(i).split("/");
					}
					treeDescripts[treeDescriptsIndex] = treeDescript;
					treeDescriptsIndex++;
					currentTreePaths = new ArrayList<String>();
				}
			}
		}
		while (line != null);
		return treeDescripts;
	}
	
	/**
	 * 
	 * @param path Path pointing to a text file (UTF-8) containing a list of paths (one list per tree)
	 * @return the number of trees, which is also the number of minimal objects in the context
	 * @throws FileReaderException
	 */
	private int getNumberOfTrees(Path path) throws FileReaderException {
		int numberOfTrees = 0;
		BufferedReader reader;
		try {
			reader = Files.newBufferedReader(path);
		}
		catch (Exception e) {
			throw new FileReaderException("GenericFileReader.getNumberOfTrees() : reader couldn't be instantiated."
					+ System.lineSeparator() + e.getMessage());
		}
		String line;
		do {
			try {
				line = reader.readLine();
			}
			catch (IOException e) {
				throw new FileReaderException("GenericFileReader.getNumberOfTrees() : IOException catched."
						+ System.lineSeparator() + e.getMessage());
			}
			if (line != null && line.equals("/")) {
				numberOfTrees++;
			}
		}
		while (line != null);
		return numberOfTrees;
	}	
	
	/**
	 * @param treeDescriptions String[][][] containing every path in every syntactic tree describing a minimal object in the context 
	 * @return ISyntacticStructure[][][], a syntactic structure array with the same dimensions as the treeDescriptions array
	 */
	private ISyntacticStructure[][][] setStructures(String[][][] treeDescriptions) {
		ISyntacticStructure[][][] structures = new ISyntacticStructure[treeDescriptions.length][][];
		for (int treeIndex=0 ; treeIndex < treeDescriptions.length ; treeIndex++) {
			structures[treeIndex] = new ISyntacticStructure[treeDescriptions[treeIndex].length][];
			for (int pathIndex=0 ; pathIndex < treeDescriptions[treeIndex].length ; pathIndex++) {
				structures[treeIndex][pathIndex] = new ISyntacticStructure[treeDescriptions[treeIndex][pathIndex].length];
			}
		}
		return structures;
	}	
	
	/**
	 * Builds a syntactic tree incrementally, starting with its leaves, ending with its root. 
	 * Each element attainable at a path index 'x' (x>0) is a component of another element at the path index 'x-1'.
	 * The only element with a path index x=0 is the root of the syntactic tree.     
	 * @param treeIndex index of the tree in the treeDescriptions array
	 * @return a syntactic tree, i.e. a syntactic branch whose name is the start element of the context-free grammar at use
	 * @throws FileReaderException
	 */
	private ISyntacticBranch buildTree(int treeIndex) throws FileReaderException {
		int pathMaxLength = setPathMaxLength(treeIndex);
		for (int nodeIndex=pathMaxLength-1 ; nodeIndex >= 0 ; nodeIndex--) {
			int pathIndex = 0;
			while (pathIndex < treeDescriptions[treeIndex].length) {
				if (nodeIndex < treeDescriptions[treeIndex][pathIndex].length) {
					pathIndex = buildSyntacticStructure(treeIndex, pathIndex, nodeIndex);
				}
				else pathIndex++;
			}
		}
		return (ISyntacticBranch) structures[treeIndex][0][0];
	}
	
	/**
	 * @param treeIndex index of the tree in the treeDescriptions array
	 * @return maximal length of a path in the given tree 
	 */
	private int setPathMaxLength(int treeIndex) {
		int pathMaxLength = 0;
		for (String[] path : treeDescriptions[treeIndex]) {
			if (pathMaxLength < path.length)
				pathMaxLength = path.length;
		}
		return pathMaxLength;
	}
	
	/**
	 * The parameters give the coordinates (in the treeDescriptions array) of the 'generating node', i.e. the
	 * node that will determine the type of the generated syntactic structure.
	 * This structure's components will first be retrieved in the 'structures' homomorphic array, and the structure
	 * itself will be instantiated and stored in the 'structures' array at the very coordinates given as 
	 * parameters (using the castComponentsAndInstantiateStructure() private method).   
	 * @param treeIndex index of the tree containing the generating node 
	 * @param pathIndex index of the first path containing the generating node
	 * @param nodeIndex index of the generating node 
	 * @return the index of the first next path in the given tree that does not go through the generating node
	 * @throws FileReaderException
	 */
	private int buildSyntacticStructure(int treeIndex, int pathIndex, int nodeIndex) throws FileReaderException {
		int currentPathIndex = pathIndex;
		String nodeName = treeDescriptions[treeIndex][pathIndex][nodeIndex];
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		if (nodeIndex+1 < treeDescriptions[treeIndex][pathIndex].length) {
			while(currentPathIndex < treeDescriptions[treeIndex].length
					&& nodeIndex+1 < treeDescriptions[treeIndex][currentPathIndex].length
					&& treeDescriptions[treeIndex][currentPathIndex][nodeIndex].equals(nodeName)) {
				if (structures[treeIndex][currentPathIndex][nodeIndex+1] != null) {
					components.add(structures[treeIndex][currentPathIndex][nodeIndex+1]);
				}
				currentPathIndex++;
			}
		}
		else {
			currentPathIndex++;
		}
		castComponentsAndInstantiateStructure(nodeName, components, treeIndex, pathIndex, nodeIndex);
		return currentPathIndex;
	}	
	
	/**
	 * In order to instantiate a syntactic structure of the type specified by the 'nodeName' parameter, this method 
	 * first casts the syntactic structures in the 'components' parameter to the type required by the context-free 
	 * grammar at use. It then instantiates the new structure and stores it in the 'structures' array, at the 
	 * coordinates specified in parameters. 
	 * @param nodeName name of the 'generating node'
	 * @param components list of components of the syntactic structure to be instantiated
	 * @param treeIndex first coordinate to be used for the storage of the generated structure in the 3-dimensional 'structures' array 
	 * @param pathIndex second coordinate to be used for the storage of the generated structure in the 3-dimensional 'structures' array
	 * @param nodeIndex third coordinate to be used for the storage of the generated structure in the 3-dimensional 'structures' array
	 * @throws FileReaderException
	 */
	protected abstract void castComponentsAndInstantiateStructure(String nodeName, List<ISyntacticStructure> components, 
			int treeIndex, int pathIndex, int nodeIndex) throws FileReaderException;
	
	/**
	 * @param structure structure to be stored
	 * @param treeIndex first 'structures' array coordinate
	 * @param pathIndex second 'structures' array coordinate
	 * @param nodeIndex third 'structures' array coordinate
	 * @throws FileReaderException 
	 */
	protected void putStructureIntoArray(ISyntacticStructure structure, int treeIndex, int pathIndex, int nodeIndex) 
			throws FileReaderException {
		try {
			structures[treeIndex][pathIndex][nodeIndex] = structure;
		}
		catch (Exception e) {
			throw new FileReaderException("GenericFileReader.castComponentsAndInstantiateStructure() : could not "
					+ "put the new structure into the 'structures' array. Node : " + structure.getName() + 
					", treeIndex : " + Integer.toString(treeIndex) + ", pathIndex : " + Integer.toString(pathIndex) 
					+ ", nodeIndex : " + Integer.toString(nodeIndex) + System.lineSeparator() + "Message : "
					+ System.lineSeparator() + e.getMessage());
		}
	}

}