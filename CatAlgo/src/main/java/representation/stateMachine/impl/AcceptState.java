package representation.stateMachine.impl;

import java.util.Iterator;

import representation.stateMachine.IAcceptState;
import representation.stateMachine.IEvaluationLog;

public class AcceptState extends State implements IAcceptState {

	public AcceptState() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IEvaluationLog notifyAcceptanceInLog(Iterator<String> symbolIterator) {
		// TODO Auto-generated method stub
		return null;
	}

}
