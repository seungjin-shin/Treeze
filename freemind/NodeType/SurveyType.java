package freemind.NodeType;

public class SurveyType extends NodeType{

	
	public SurveyType() {
	}
	
	@Override
	public void act() {
		// TODO Auto-generated method stub
	}

	@Override
	public NodeType getNodeType() {
		return this;
	}

	@Override
	public void forSurveyAct() {
		System.out.println("Survey func");
	}	

}
